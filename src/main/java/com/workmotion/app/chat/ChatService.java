package com.workmotion.app.chat;

import com.workmotion.app.chat.model.MessageDTO;
import com.workmotion.app.chat.model.RoomInfoDTO;
import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    @Autowired
    private ChatDAO chatDAO;

    public int createRoom(RoomInfoDTO room, String memberId) throws Exception {
        int result = chatDAO.createRoom(room);


        return result;
    }

    public int addMember(RoomInfoDTO room, String memberId, String avatar) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("members", memberId);
        map.put("room_name", room.getRoom_name());
        map.put("name", room.getName());
        map.put("avatar", avatar);
        System.out.println(map);
        int result = chatDAO.addMember(map);
        return result;
    }

    public RoomInfoDTO getRoom(RoomInfoDTO room, String memberName, String memberId, MemberDTO memberDTO) throws Exception {
        RoomInfoDTO resultRoom = new RoomInfoDTO();
        if (chatDAO.getRoom(room) == null) {
            room.setName(memberName);
            createRoom(room, memberId);
            addMember(room, Long.toString(memberDTO.getId()), memberId);
            room.setName(memberDTO.getName());
            addMember(room, memberId, Long.toString(memberDTO.getId()));

            return room;
        } else {
            room.setName(memberDTO.getName());
            addMember(room, memberId, Long.toString(memberDTO.getId()));
            resultRoom.setRoom_name(room.getRoom_name());
        }


        return resultRoom;
    }

    public RoomInfoDTO getRoom(RoomInfoDTO room, MemberDTO memberDTO) throws Exception {
        RoomInfoDTO resultRoom = new RoomInfoDTO();
        room.setName(memberDTO.getName());
        String[] ids = room.getRoom_name().split("-");
        for (String id : ids) {
            if (!id.equals(memberDTO.getId().toString())) {
                addMember(room, id, Long.toString(memberDTO.getId()));
            }
        }
        resultRoom.setRoom_name(chatDAO.getRoom(room).getName());


        return resultRoom;
    }

    public int addMember(Map<String, Object> map) throws Exception {
        return chatDAO.addMember(map);
    }

    public int sendMessage(MessageDTO message) throws Exception {
        return chatDAO.sendMessage(message);
    }

    public int updateRoomInfo(RoomInfoDTO roomInfoDTO) throws Exception {
        return chatDAO.updateRoomInfo(roomInfoDTO);
    }

    public List<MessageDTO> getMessage(RoomInfoDTO roomInfoDTO, Pager pager) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        pager.setLastNum(pager.getPage() * 20);
        pager.setStartNum(pager.getLastNum() - 19);
        System.out.println(pager.getPage() + ":" + pager.getStartNum() + ":" + pager.getLastNum());
        map.put("roomInfo", roomInfoDTO);
        map.put("pager", pager);
        return chatDAO.getMessage(map);
    }

    public RoomInfoDTO getRoomInfo(RoomInfoDTO roomInfoDTO) throws Exception {
        return chatDAO.getRoomInfo(roomInfoDTO);
    }

    public List<MessageDTO> getUserRoom(MemberDTO memberDTO) throws Exception {
        return chatDAO.getUserRoom(memberDTO);
    }

    public List<MemberDTO> getRoomUsers(RoomInfoDTO roomInfoDTO) throws Exception {
        return chatDAO.getRoomUsers(roomInfoDTO);
    }

    public void exitRoom(RoomInfoDTO roomInfoDTO) throws Exception {
//        String dateString = "9999-12-31";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(dateString);
//
//        roomInfoDTO.setJoin_dt(date);
        chatDAO.exitRoom(roomInfoDTO);
    }
}
