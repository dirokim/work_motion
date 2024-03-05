package com.workmotion.app.project.service;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.dao.ProjectDAO;
import com.workmotion.app.project.model.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectDAO projectDAO;

    public int createProject(ProjectDTO projectDTO) throws Exception {
        return projectDAO.createProject(projectDTO);
    }

    public List<ProjectDTO> getProjectList(MemberDTO memberDTO) throws Exception {
        return projectDAO.getProjectList(memberDTO);
    }

    public List<ProjectDTO> getMyProjectList(MemberDTO memberDTO) throws Exception {
        return projectDAO.getMyProjectList(memberDTO);
    }

    public ProjectDTO getProjectDetail(ProjectDTO projectDTO) throws Exception {
        return projectDAO.getProjectDetail(projectDTO);
    }

    public int deleteProject(ProjectDTO projectDTO) throws Exception {
        return projectDAO.deleteProject(projectDTO);
    }

    public int updateProject(ProjectDTO projectDTO) throws Exception {
        return projectDAO.updateProject(projectDTO);
    }


    public int changeOwner(ProjectDTO projectDTO) {
        return projectDAO.changeOwner(projectDTO);
    }
}
