package com.workmotion.app.project.api;

import com.workmotion.app.project.model.ProjectDTO;
import com.workmotion.app.project.model.TaskDTO;
import com.workmotion.app.project.service.TaskService;
import com.workmotion.app.schedule.ScheduleDTO;
import com.workmotion.app.schedule.ScheduleService;
import com.workmotion.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/projects/*")
public class TaskAPI {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("{project_id}/tasks")
    public ResponseEntity<?> createTask(@PathVariable Long project_id, TaskDTO taskDTO) throws Exception {
        taskDTO.setProject_id(project_id);
        int result = taskService.createTask(taskDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{project_id}/tasks")
    public ResponseEntity<?> getTaskList(@PathVariable Long project_id, Pager pager) throws Exception {
        List<TaskDTO> taskDTOList = taskService.getTaskList(new ProjectDTO(project_id), pager);

        return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
    }

    @GetMapping("{project_id}/tasks/{task_id}")
    public ResponseEntity<?> getTaskDetail(@PathVariable Long project_id, @PathVariable Long task_id, TaskDTO taskDTO) throws Exception {
        taskDTO.setId(task_id);
        taskDTO.setProject_id(project_id);
        taskDTO = taskService.getTaskDetail(taskDTO);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PutMapping("{project_id}/tasks/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable Long project_id, @PathVariable Long task_id, @RequestBody TaskDTO taskDTO) throws Exception {
        taskDTO.setId(task_id);
        taskDTO.setProject_id(project_id);
        int result = taskService.updateTask(taskDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{project_id}/tasks/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long project_id, @PathVariable Long task_id, TaskDTO taskDTO) throws Exception {
        taskDTO.setId(task_id);
        taskDTO.setProject_id(project_id);
        int result = taskService.deleteTask(taskDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{project_id}/tasks/{task_id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable Long project_id, @PathVariable Long task_id, @RequestBody TaskDTO taskDTO) throws Exception {
        taskDTO.setId(task_id);
        taskDTO.setProject_id(project_id);
        int result = taskService.changeStatus(taskDTO);
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setTask_id(task_id);
        scheduleDTO.setStatus(taskDTO.getStatus());
        scheduleService.updateSchedule(scheduleDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
