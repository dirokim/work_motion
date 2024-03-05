package com.workmotion.app.project.dao;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.ProjectDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.project.dao.ProjectDAO.";

    public int createProject(ProjectDTO projectDTO) throws Exception {
        return sqlSession.insert(NAMESPACE + "createProject", projectDTO);
    }

    public List<ProjectDTO> getProjectList(MemberDTO memberDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getProjectList", memberDTO);
    }

    public List<ProjectDTO> getMyProjectList(MemberDTO memberDTO) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getMyProjectList", memberDTO);
    }

    public ProjectDTO getProjectDetail(ProjectDTO projectDTO) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getProjectDetail", projectDTO);
    }

    public int deleteProject(ProjectDTO projectDTO) throws Exception {
        return sqlSession.delete(NAMESPACE + "deleteProject", projectDTO);
    }

    public int updateProject(ProjectDTO projectDTO) throws Exception {
        return sqlSession.update(NAMESPACE + "updateProject", projectDTO);
    }

    public int changeOwner(ProjectDTO projectDTO) {
        return sqlSession.update(NAMESPACE + "changeOwner", projectDTO);
    }
}
