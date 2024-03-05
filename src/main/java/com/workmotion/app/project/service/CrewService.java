package com.workmotion.app.project.service;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.dao.CrewDAO;
import com.workmotion.app.project.model.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrewService {
    @Autowired
    private CrewDAO crewDAO;

    public List<MemberDTO> getCrewList(ProjectDTO projectDTO) throws Exception {
        return crewDAO.getCrewList(projectDTO);
    }

    public int addCrew(Long projectId, String memberIds) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_id", projectId);
        map.put("member_ids", memberIds);
        return crewDAO.addCrew(map);
    }

    public MemberDTO getCrewDetail(Long projectId, Long memberId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_id", projectId);
        map.put("member_id", memberId);
        return crewDAO.getCrewDetail(map);
    }

    public MemberDTO getOwner(ProjectDTO projectDTO) throws Exception {
        return crewDAO.getOwner(projectDTO);
    }

    public int removeCrew(Long projectId, String memberIds) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_id", projectId);
        map.put("member_ids", memberIds);
        return crewDAO.removeCrew(map);
    }

    public List<MemberDTO> getMemberList(ProjectDTO projectDTO) throws Exception {
        return crewDAO.getMemberList(projectDTO);
    }

    public List<MemberDTO> getAllMemberList(MemberDTO memberDTO) throws Exception {
        return crewDAO.getAllMemberList(memberDTO);
    }
}
