package com.workmotion.app.schedule;

import com.workmotion.app.project.model.TaskDTO;
import com.workmotion.app.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/*")
public class ScheduleAPI {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TaskService taskService;

    @GetMapping("schedules/{schedule_id}")
    public ResponseEntity<List<ScheduleDTO>> getAllSchedule(ScheduleDTO scheduleDTO, @PathVariable Long schedule_id) throws Exception {
        scheduleDTO.setId(schedule_id);
        List<ScheduleDTO> schedules = scheduleService.getSchedule(scheduleDTO);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("projects/{project_id}/schedules")
    public ResponseEntity<List<ScheduleDTO>> getProjectSchedule(@PathVariable Long project_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setProject_id(project_id);
        List<ScheduleDTO> schedules = scheduleService.getSchedule(scheduleDTO);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("tasks/{task_id}/schedules")
    public ResponseEntity<List<ScheduleDTO>> getTaskSchedule(@PathVariable Long task_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setTask_id(task_id);
        List<ScheduleDTO> schedules = scheduleService.getSchedule(scheduleDTO);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("members/{member_id}/schedules")
    public ResponseEntity<List<ScheduleDTO>> getMemberSchedule(@PathVariable Long member_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setMember_id(member_id);
        List<ScheduleDTO> schedules = scheduleService.getSchedule(scheduleDTO);

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PostMapping("tasks/{task_id}/schedules")
    public ResponseEntity<?> createTaskSchedule(@PathVariable Long task_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setTask_id(task_id);
        int result = scheduleService.createSchedule(scheduleDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("members/{member_id}/schedules")
    public ResponseEntity<?> createMemberSchedule(@PathVariable Long member_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setMember_id(member_id);
        int result = scheduleService.createSchedule(scheduleDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("schedules/{schedule_id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long schedule_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setId(schedule_id);
        List<ScheduleDTO> schedule = scheduleService.getSchedule(scheduleDTO);
        scheduleDTO = schedule.get(0);
        int result = scheduleService.deleteSchedule(scheduleDTO);
        if (scheduleDTO.getTask_id() != null) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(scheduleDTO.getTask_id());
            taskDTO.setHas_schedule(0);
            taskService.updateTask(taskDTO);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("schedules/{schedule_id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long schedule_id, ScheduleDTO scheduleDTO) throws Exception {
        scheduleDTO.setId(schedule_id);
        int result = scheduleService.updateSchedule(scheduleDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
