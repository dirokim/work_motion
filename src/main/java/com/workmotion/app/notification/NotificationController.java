package com.workmotion.app.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.app.member.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/v1/*")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/notifications")
    public ResponseEntity<?> addNotification(NotificationDTO notificationDTO, NotificationMessage notificationMessage, HttpSession session) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        notificationDTO.setContent(mapper.writeValueAsString(notificationMessage));
        return ResponseEntity.ok().body(notificationService.addNotification(notificationDTO));
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getNotifications(HttpSession session) throws Exception {
        return ResponseEntity.ok().body(notificationService.getNotification((MemberDTO) session.getAttribute("member")));
    }

    @PutMapping("/notifications/{notification_id}")
    public ResponseEntity<?> updateNotification(NotificationDTO notificationDTO, HttpSession session, @PathVariable String notification_id) throws Exception {
        return ResponseEntity.ok().body(notificationService.updateNotification(notificationDTO, notification_id));
    }

    @MessageMapping("/readNotification")
    public void readNotification(NotificationDTO notificationDTO) throws Exception {
        notificationService.updateNotification(notificationDTO, notificationDTO.getId().toString());
    }

}
