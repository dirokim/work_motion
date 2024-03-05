package com.workmotion.app.project.controller;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.CustomResponse;
import com.workmotion.app.project.model.ProjectDTO;
import com.workmotion.app.project.service.CrewService;
import com.workmotion.app.project.service.ProjectService;
import com.workmotion.app.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/projects/*")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CrewService crewService;
    private final CustomResponse customResponse = new CustomResponse();


    @GetMapping("create")
    public String createProject(Model model, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        List<MemberDTO> memberDTOList = crewService.getAllMemberList(memberDTO);
        model.addAttribute("members", memberDTOList);
        model.addAttribute("member", memberDTO);
        model.addAttribute("page", "project/createProject");
        return "index";
    }

    @PostMapping("create")
    public String createProject(ProjectDTO projectDTO, Model model, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        projectDTO.setOwner_id(memberDTO.getId());

        int result = projectService.createProject(projectDTO);
        if (!projectDTO.getCrew().isEmpty()) {
            result = crewService.addCrew(projectDTO.getId(), projectDTO.getCrew());
        }

        customResponse.setResult(result);
        customResponse.setMessage("프로젝트 생성");
        customResponse.setRedirectUrl("/projects/detail?id=" + projectDTO.getId());
        model.addAttribute("response", customResponse);
        model.addAttribute("page", "project/result");
        return "index";
    }

    @GetMapping("list")
    public String getProjectList(Model model, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        model.addAttribute("myProjects", projectService.getMyProjectList(memberDTO));
        model.addAttribute("includeProjects", projectService.getProjectList(memberDTO));
        model.addAttribute("page", "project/index");
        return "index";
    }

    @GetMapping("detail")
    public String getProjectDetail(Model model, ProjectDTO projectDTO, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        model.addAttribute("member", memberDTO);
        model.addAttribute("project", projectService.getProjectDetail(projectDTO));
        //model.addAttribute("tasks", taskService.getTaskList(projectDTO));
        model.addAttribute("crews", crewService.getCrewList(projectDTO));
        model.addAttribute("owner", crewService.getOwner(projectDTO));
        model.addAttribute("page", "project/task");
        return "index";
    }

    @GetMapping("setting")
    public String settingProject(Model model, ProjectDTO projectDTO, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        projectDTO = projectService.getProjectDetail(projectDTO);
        model.addAttribute("project", projectDTO);
        System.out.println(projectDTO.getOwner_id() + ":" + memberDTO.getId());
        if (!Objects.equals(projectDTO.getOwner_id(), memberDTO.getId())) {
            customResponse.setRedirectUrl("/projects/detail?id=" + projectDTO.getId());
            customResponse.setMessage("권한 없음");
            model.addAttribute("response", customResponse);
            model.addAttribute("page", "project/result");
            return "index";
        }
        model.addAttribute("crews", crewService.getCrewList(projectDTO));
        model.addAttribute("page", "project/editProject");
        return "index";
    }

    @PostMapping("update")
    public String updateProject(Model model, ProjectDTO projectDTO, HttpSession session) throws Exception {
        ProjectDTO projectDetail = projectService.getProjectDetail(projectDTO);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if (!Objects.equals(projectDetail.getOwner_id(), memberDTO.getId())) {
            customResponse.setRedirectUrl("/projects/detail?id=" + projectDTO.getId());
            customResponse.setMessage("권한 없음");
            model.addAttribute("response", customResponse);
            model.addAttribute("page", "project/result");
            return "index";
        }
        int result = projectService.updateProject(projectDTO);
        customResponse.setResult(result);
        customResponse.setRedirectUrl("/projects/detail?id=" + projectDTO.getId());
        customResponse.setMessage("프로젝트 수정");
        model.addAttribute("response", customResponse);
        model.addAttribute("page", "project/result");
        return "index";
    }

    @PostMapping("delete")
    public String deleteProject(Model model, ProjectDTO projectDTO, HttpSession session) throws Exception {
        projectDTO = projectService.getProjectDetail(projectDTO);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if (!Objects.equals(projectDTO.getOwner_id(), memberDTO.getId())) {
            customResponse.setRedirectUrl("/projects/detail?id=" + projectDTO.getId());
            customResponse.setMessage("권한 없음");
            model.addAttribute("response", customResponse);
            model.addAttribute("page", "project/result");
            return "index";
        }
        int result = projectService.deleteProject(projectDTO);
        customResponse.setResult(result);
        customResponse.setRedirectUrl("/projects/list");
        customResponse.setMessage("프로젝트 삭제");
        model.addAttribute("response", customResponse);
        model.addAttribute("page", "project/result");
        return "index";
    }
}
