package com.workmotion.app.project.model;

import com.workmotion.app.member.MemberDTO;

import java.util.Date;
import java.util.List;

public class TaskDTO {
    private Long id;
    private Long project_id;
    private String name;
    private Date create_dt;
    private String content;
    private Long writer_id;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private List<MemberDTO> task_member;

    public List<MemberDTO> getTask_member() {
        return task_member;
    }

    public void setTask_member(List<MemberDTO> task_member) {
        this.task_member = task_member;
    }

    private MemberDTO writer;

    private ProjectDTO project;

    public TaskDTO() {
    }

    public TaskDTO(Long projectId) {
        this.project_id = projectId;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    private String start;

    private String end;

    private Integer has_limit;
    private Integer has_schedule;

    public Integer getHas_schedule() {
        return has_schedule;
    }

    public void setHas_schedule(Integer has_schedule) {
        this.has_schedule = has_schedule;
    }

    private String charge;

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
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

    public Integer getHas_limit() {
        return has_limit;
    }

    public void setHas_limit(Integer has_limit) {
        this.has_limit = has_limit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(Date create_dt) {
        this.create_dt = create_dt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(Long writer_id) {
        this.writer_id = writer_id;
    }

    public MemberDTO getWriter() {
        return writer;
    }

    public void setWriter(MemberDTO writer) {
        this.writer = writer;
    }
}
