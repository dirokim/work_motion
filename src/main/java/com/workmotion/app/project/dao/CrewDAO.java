package com.workmotion.app.project.dao;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.ProjectDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CrewDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.project.dao.CrewDAO.";

    public List<MemberDTO> getCrewList(ProjectDTO projectDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getCrewList", projectDTO);
    }

    public MemberDTO getOwner(ProjectDTO projectDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getOwner", projectDTO);
    }

    public int addCrew(Map<String, Object> map) throws Exception {
        return sqlSession.insert(NAMESPACE + "addCrew", map);
    }

    public MemberDTO getCrewDetail(Map<String, Object> map) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getCrewDetail", map);
    }

    public int removeCrew(Map<String, Object> map) throws Exception {
        return sqlSession.delete(NAMESPACE + "removeCrew", map);
    }

    public List<MemberDTO> getMemberList(ProjectDTO projectDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getMemberList", projectDTO);
    }

    public List<MemberDTO> getAllMemberList(MemberDTO memberDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getAllMemberList", memberDTO);
    }
}
