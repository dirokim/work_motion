package com.workmotion.app.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.app.member.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    @Autowired
    private NotificationDAO notificationDAO;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public List<NotificationDTO> getNotification(MemberDTO memberDTO) throws Exception {
        return notificationDAO.getNotification(memberDTO);
    }

    public int addNotification(NotificationDTO notificationDTO) throws Exception {
        sendNotification(notificationDTO);
        return notificationDAO.addNotification(notificationDTO);
    }

    public int addNotification(NotificationDTO notificationDTO, NotificationMessage notificationMessage) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        notificationDTO.setContent(mapper.writeValueAsString(notificationMessage));
        return notificationDAO.addNotification(notificationDTO);
    }

    public int updateNotification(NotificationDTO notificationDTO, String id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("notification", notificationDTO);
        return notificationDAO.updateNotification(map);
    }

    public void sendNotification(NotificationDTO notificationDTO) throws Exception {
        System.out.println(notificationDTO.getMember_id());
        String destination = "/notification/messages" + notificationDTO.getMember_id();
        simpMessagingTemplate.convertAndSend(destination, notificationDTO);
    }

}
