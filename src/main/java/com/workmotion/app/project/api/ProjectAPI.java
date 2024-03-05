package com.workmotion.app.project.api;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.ProjectDTO;
import com.workmotion.app.project.service.CrewService;
import com.workmotion.app.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/v1/*")
public class ProjectAPI {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CrewService crewService;


    @PostMapping("/projects")
    public ResponseEntity<?> createProject(ProjectDTO projectDTO) throws Exception {
        int result = projectService.createProject(projectDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getProjectList(HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        return new ResponseEntity<>(projectService.getProjectList(memberDTO), HttpStatus.OK);
    }

    @GetMapping("/myprojects")
    public ResponseEntity<List<ProjectDTO>> getMyProjectList(HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        return new ResponseEntity<>(projectService.getMyProjectList(memberDTO), HttpStatus.OK);
    }

    @GetMapping("/projects/{project_id}")
    public ResponseEntity<ProjectDTO> getProjectDetail(@PathVariable Long project_id, ProjectDTO projectDTO) throws Exception {
        projectDTO.setId(project_id);
        return new ResponseEntity<>(projectService.getProjectDetail(projectDTO), HttpStatus.OK);
    }

    @PutMapping("/projects/{project_id}")
    public ResponseEntity<?> updateProject(@PathVariable Long project_id, @RequestBody ProjectDTO projectDTO) throws Exception {
        projectDTO.setId(project_id);
        int result = projectService.updateProject(projectDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{project_id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long project_id, @RequestBody ProjectDTO projectDTO) throws Exception {
        projectDTO.setId(project_id);
        int result = projectService.deleteProject(projectDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/projects/{project_id}/changeOwner/{owner_id}")
    public ResponseEntity<?> changeOwner(@PathVariable Long project_id, @PathVariable String owner_id, ProjectDTO projectDTO) throws Exception {
        projectDTO.setId(project_id);
        int result = crewService.addCrew(project_id, projectService.getProjectDetail(projectDTO).getOwner_id().toString());
        result = projectService.changeOwner(projectDTO);
        result = crewService.removeCrew(project_id, owner_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
