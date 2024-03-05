package com.workmotion.app.project.service;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.dao.TaskDAO;
import com.workmotion.app.project.model.ProjectDTO;
import com.workmotion.app.project.model.TaskDTO;
import com.workmotion.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;

    public List<TaskDTO> getTaskList(ProjectDTO projectDTO, Pager pager) throws Exception {
        Map<String, Object> map = new HashMap<>();
        pager.setLastNum(pager.getPage() * 10);
        pager.setStartNum(pager.getLastNum() - 9);
        map.put("project", projectDTO);
        map.put("pager", pager);
        return taskDAO.getTaskList(map);
    }

    public int createTask(TaskDTO taskDTO) throws Exception {
        return taskDAO.createTask(taskDTO);
    }

    public int updateTask(TaskDTO taskDTO) throws Exception {
        return taskDAO.updateTask(taskDTO);
    }

    public int deleteTask(TaskDTO taskDTO) throws Exception {
        return taskDAO.deleteTask(taskDTO);
    }

    public TaskDTO getTaskDetail(TaskDTO taskDTO) throws Exception {
        return taskDAO.getTaskDetail(taskDTO);
    }

    public int addCharge(TaskDTO taskDTO, String charge) throws Exception {
        if (charge.isEmpty()) return 1;
        Map<String, Object> map = new HashMap<>();
        map.put("charge", charge);
        map.put("task_id", taskDTO.getId());
        return taskDAO.addCharge(map);
    }

    public int removeCharge(TaskDTO taskDTO) throws Exception {
        return taskDAO.removeCharge(taskDTO);
    }

    public List<MemberDTO> getCharge(TaskDTO taskDTO) throws Exception {
        return taskDAO.getCharge(taskDTO);
    }

    public int changeStatus(TaskDTO taskDTO) throws Exception {
        return taskDAO.changeStatus(taskDTO);
    }
}
