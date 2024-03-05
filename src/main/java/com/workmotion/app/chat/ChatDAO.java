package com.workmotion.app.chat;

import com.workmotion.app.chat.model.MessageDTO;
import com.workmotion.app.chat.model.RoomDTO;
import com.workmotion.app.chat.model.RoomInfoDTO;
import com.workmotion.app.member.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ChatDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.chat.ChatDAO.";

    public int createRoom(RoomInfoDTO room) throws Exception {
        return sqlSession.insert(NAMESPACE + "createRoom", room);
    }

    public RoomDTO getRoom(RoomInfoDTO room) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getRoom", room);
    }

    public int addMember(Map<String, Object> map) throws Exception {
        return sqlSession.insert(NAMESPACE + "addMember", map);
    }

    public int sendMessage(MessageDTO message) throws Exception {
        return sqlSession.insert(NAMESPACE + "sendMessage", message);
    }

    public int updateRoomInfo(RoomInfoDTO roomInfoDTO) {
        return sqlSession.update(NAMESPACE + "updateRoomInfo", roomInfoDTO);
    }

    public List<MessageDTO> getMessage(Map<String, Object> map) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getMessage", map);
    }

    public RoomInfoDTO getRoomInfo(RoomInfoDTO roomInfoDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getRoomInfo", roomInfoDTO);
    }

    public List<MessageDTO> getUserRoom(MemberDTO memberDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getUserRoom", memberDTO);
    }

    public List<MemberDTO> getRoomUsers(RoomInfoDTO roomInfoDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getRoomUsers", roomInfoDTO);
    }

    public void exitRoom(RoomInfoDTO roomInfoDTO) {
        sqlSession.delete(NAMESPACE + "exitRoom", roomInfoDTO);
    }
}
