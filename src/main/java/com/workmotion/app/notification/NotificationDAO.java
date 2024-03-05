package com.workmotion.app.notification;

import com.workmotion.app.member.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NotificationDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.notification.NotificationDAO.";

    public List<NotificationDTO> getNotification(MemberDTO memberDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getNotification", memberDTO);
    }

    public int addNotification(NotificationDTO notificationDTO) throws Exception {
        return sqlSession.insert(NAMESPACE + "addNotification", notificationDTO);
    }

    public int updateNotification(Map<String, Object> map) throws Exception {
        return sqlSession.update(NAMESPACE + "updateNotification", map);
    }
}
