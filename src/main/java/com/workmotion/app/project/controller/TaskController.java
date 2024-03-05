package com.workmotion.app.project.controller;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.CustomResponse;
import com.workmotion.app.project.model.ProjectDTO;
import com.workmotion.app.project.model.TaskDTO;
import com.workmotion.app.project.service.TaskService;
import com.workmotion.app.schedule.ScheduleDTO;
import com.workmotion.app.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tasks/*")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ScheduleService scheduleService;
    private final CustomResponse customResponse = new CustomResponse();

    @GetMapping("create")
    public String create(Model model, ProjectDTO projectDTO) throws Exception {
        model.addAttribute("page", "project/createTask");
        model.addAttribute("project", projectDTO);
        return "index";
    }

    @PostMapping("create")
    public String create(Model model, TaskDTO taskDTO, Integer addScheduleInput, HttpSession session) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        taskDTO.setWriter_id(memberDTO.getId());
        int result = taskService.createTask(taskDTO);
        result = taskService.addCharge(taskDTO, taskDTO.getCharge());
        if (taskDTO.getHas_schedule() == 1) {
            result = scheduleService.createSchedule(new ScheduleDTO(null, taskDTO.getName(), taskDTO.getStart(), taskDTO.getEnd(), taskDTO.getId(), taskDTO.getWriter_id(), taskDTO.getProject_id(), 0));
        }
        customResponse.setResult(result);
        customResponse.setMessage("업무 생성");
        customResponse.setRedirectUrl("/projects/detail?id=" + taskDTO.getProject_id() + "#taskTab");
        model.addAttribute("response", customResponse);
        model.addAttribute("page", "project/result");
        return "index";
    }

    @GetMapping("setting")
    public String setting(Model model, TaskDTO taskDTO) throws Exception {
        taskDTO = taskService.getTaskDetail(taskDTO);
        model.addAttribute("task", taskDTO);
        model.addAttribute("page", "project/editTask");
        return "index";
    }

    @PostMapping("update")
    public String update(Model model, TaskDTO taskDTO) throws Exception {
        int result = taskService.updateTask(taskDTO);
        result = taskService.removeCharge(taskDTO);
        result = taskService.addCharge(taskDTO, taskDTO.getCharge());
        if (taskDTO.getHas_schedule() == 0) {
            result = scheduleService.deleteSchedule(new ScheduleDTO(null, null, null, null, taskDTO.getId(), null, null, null));
        } else if (taskDTO.getHas_schedule() == 1) {
            result = scheduleService.updateSchedule(new ScheduleDTO(null, taskDTO.getName(), taskDTO.getStart(), taskDTO.getEnd(), taskDTO.getId(), taskDTO.getWriter_id(), taskDTO.getProject_id(), taskDTO.getStatus()));
        }

        customResponse.setResult(result);
        customResponse.setMessage("업무 수정");
        customResponse.setRedirectUrl("/projects/detail?id=" + taskDTO.getProject_id() + "#taskTab");
        model.addAttribute("response", customResponse);
        model.addAttribute("page", "project/result");
        return "index";
    }

    @GetMapping("delete")
    public String delete(Model model, TaskDTO taskDTO) throws Exception {
        int result = taskService.deleteTask(taskDTO);
        customResponse.setResult(result);
        customResponse.setMessage("업무 삭제");
        customResponse.setRedirectUrl("/projects/detail?id=" + taskDTO.getProject_id() + "#taskTab");
        model.addAttribute("page", "project/result");
        model.addAttribute("response", customResponse);
        return "index";
    }
}
