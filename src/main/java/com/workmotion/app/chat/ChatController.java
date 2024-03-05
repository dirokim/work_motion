package com.workmotion.app.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.app.chat.model.MessageDTO;
import com.workmotion.app.chat.model.RoomDTO;
import com.workmotion.app.chat.model.RoomInfoDTO;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.notification.NotificationDTO;
import com.workmotion.app.notification.NotificationMessage;
import com.workmotion.app.notification.NotificationService;
import com.workmotion.app.project.service.CrewService;
import com.workmotion.app.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private CrewService crewService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private NotificationService notificationService;

    private static List<String> connectionMembers = new ArrayList<>();
    private static List<String> connectionChats = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);


    // 새로운 사용자가 웹 소켓을 연결할 때 실행됨
    // @EventListener은 한개의 매개변수만 가질 수 있다.
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        MemberDTO memberDTO = (MemberDTO) headerAccesor.getSessionAttributes().get("member");
//        String sessionId = headerAccesor.getSessionId();
        String sessionId = memberDTO.getId().toString();

        if (connectionMembers.contains(sessionId)) {
            connectionChats.add(sessionId);
            logger.info("체팅 세션 연결됨 : " + sessionId);
        } else {
            connectionMembers.add(sessionId);
            logger.info("멤버 세션 연결됨 : " + sessionId);
        }
        ;

    }

    // 사용자가 웹 소켓 연결을 끊으면 실행됨
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        MemberDTO memberDTO = (MemberDTO) headerAccesor.getSessionAttributes().get("member");
//        String sessionId = headerAccesor.getSessionId();
        String sessionId = memberDTO.getId().toString();
        if (connectionChats.contains(sessionId)) {
            connectionChats.remove(sessionId);
            logger.info("체팅 세션 연결 종료 : " + sessionId);
        } else {
            connectionMembers.remove(sessionId);
            logger.info("멤버 세션 연결 종료 : " + sessionId);
            logger.info("멤버 세션 연결 종료 : " + sessionId);
        }

    }

    @GetMapping("/chat")
    public String getChat(Model model, RoomDTO roomDTO, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setMember_id(memberDTO.getId());
        roomInfoDTO.setRoom_name(roomDTO.getName());
        roomInfoDTO = chatService.getRoomInfo(roomInfoDTO);
        model.addAttribute("member", memberDTO);
        model.addAttribute("room", roomInfoDTO);

        return "chat";
    }

    @PostMapping("/chat/exitRoom")
    @ResponseBody
    public ResponseEntity<?> exitRoom(Model model, RoomInfoDTO roomInfoDTO, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        roomInfoDTO.setMember_id(memberDTO.getId());
        chatService.exitRoom(roomInfoDTO);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/chat/getMessage")
    @ResponseBody
    public ResponseEntity<?> getMessage(Model model, RoomInfoDTO roomInfoDTO, Pager pager, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        roomInfoDTO.setMember_id(memberDTO.getId());
        List<MessageDTO> messageList = chatService.getMessage(roomInfoDTO, pager);

        RoomInfoDTO room = chatService.getRoomInfo(roomInfoDTO);
        chatService.updateRoomInfo(roomInfoDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("messages", messageList);
        response.put("room", room);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/chat/getRoom")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getRoom(RoomInfoDTO room, HttpSession session, String memberName, String memberId) throws Exception {
        Map<String, Object> response = new HashMap<>();

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if (memberName == null) {
            room = chatService.getRoom(room, memberDTO);
            System.out.println("Hello");
        } else {
            room = chatService.getRoom(room, memberName, memberId, memberDTO);

        }

        response.put("room", room);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/chat/getRooms")
    @ResponseBody
    public ResponseEntity<List<MessageDTO>> getUserRoom(RoomDTO room, HttpSession session) throws Exception {

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        List<MessageDTO> messageDTOS = chatService.getUserRoom(memberDTO);

        return ResponseEntity.ok().body(messageDTOS);
    }


    @MessageMapping("/sendMessage")
    public void sendMessage(MessageDTO message, SimpMessageHeaderAccessor accessor) throws Exception {
        MemberDTO sender = (MemberDTO) accessor.getSessionAttributes().get("member");
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setMember_id(sender.getId());
        roomInfoDTO.setRoom_name(message.getRoom_name());
        chatService.getRoom(roomInfoDTO, sender);
        
        List<MemberDTO> memberList = chatService.getRoomUsers(roomInfoDTO);
        NotificationMessage notificationMessage = new NotificationMessage();
        NotificationDTO notificationDTO = new NotificationDTO();

        notificationMessage.setSender(sender.getName());
        if (message.getType().equals("image")) notificationMessage.setMessage("이미지");
        else notificationMessage.setMessage(message.getMessage());
        notificationMessage.setTargetRoom(message.getRoom_name());
        notificationDTO.setType_name("MESSAGE");
        int result = chatService.sendMessage(message);
        message.setSender(sender);
        String destination = "/chat/messages/" + message.getRoom_name();
        simpMessagingTemplate.convertAndSend(destination, message);
        for (MemberDTO memberDTO : memberList) {
            notificationDTO.setMember_id(memberDTO.getId());
            destination = "/notification/messages/" + memberDTO.getId();
            ObjectMapper mapper = new ObjectMapper();
            notificationDTO.setContent(mapper.writeValueAsString(notificationMessage));

            if (!connectionChats.contains((memberDTO.getId().toString()))) {
                notificationService.addNotification(notificationDTO);
                simpMessagingTemplate.convertAndSend(destination, notificationDTO);
                destination = "/notification/update/" + sender.getId();
                simpMessagingTemplate.convertAndSend(destination, notificationDTO);
            }
        }
    }

    @MessageMapping("/readMessage")
    public void readMessage(MessageDTO message, SimpMessageHeaderAccessor accessor) throws Exception {
        MemberDTO sender = (MemberDTO) accessor.getSessionAttributes().get("member");
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setRoom_name(message.getRoom_name());
        roomInfoDTO.setMember_id(message.getSender_id());
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMember_id(roomInfoDTO.getMember_id());
        notificationDTO.setType_name("MESSAGE");
        notificationDTO.setContent(roomInfoDTO.getRoom_name());
        if (connectionChats.contains(sender.getId().toString())) {
            notificationService.updateNotification(notificationDTO, "1");
            chatService.updateRoomInfo(roomInfoDTO);
            String destination = "/notification/update/" + sender.getId();
            simpMessagingTemplate.convertAndSend(destination, message);
        }


    }

    @MessageMapping("/exitRoom")
    public void exitRoom(RoomInfoDTO roomInfoDTO, SimpMessageHeaderAccessor accessor) throws Exception {
        MemberDTO memberDTO = (MemberDTO) accessor.getSessionAttributes().get("member");
        roomInfoDTO.setMember_id(memberDTO.getId());
        chatService.exitRoom(roomInfoDTO);
        String destination = "/notification/update/" + memberDTO.getId();
        simpMessagingTemplate.convertAndSend(destination, roomInfoDTO);
    }

    @GetMapping("/chat/members/{member_id}")
    @ResponseBody
    public void getProfile() throws Exception {

    }
}
