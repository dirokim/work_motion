package com.workmotion.app.schedule;

import com.workmotion.app.member.MemberDTO;
import com.workmotion.app.project.model.TaskDTO;

public class ScheduleDTO {
    private Long id;
    private String title;
    private String start;
    private String end;
    private Long task_id;
    private Long member_id;

    private Long project_id;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    private TaskDTO task;
    private MemberDTO member;

    public ScheduleDTO() {

    }

    public ScheduleDTO(Long id, String title, String start, String end, Long task_id, Long member_id, Long project_id, Integer status) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.task_id = task_id;
        this.member_id = member_id;
        this.project_id = project_id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", task_id=" + task_id +
                ", member_id=" + member_id +
                ", project_id=" + project_id +
                ", task=" + task +
                ", member=" + member +
                '}';
    }
}
