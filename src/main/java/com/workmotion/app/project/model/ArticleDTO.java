package com.workmotion.app.project.model;

import com.workmotion.app.member.MemberDTO;

import java.util.Date;

public class ArticleDTO {
    private Long id;
    private Long project_id;
    private String title;
    private String content;
    private Date create_dt;
    private Long writer_id;

    private Date update_dt;

    public ArticleDTO() {

    }

    public ArticleDTO(Long id) {
        this.id = id;
    }

    public Date getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(Date update_dt) {
        this.update_dt = update_dt;
    }

    private ProjectDTO project;
    private MemberDTO writer;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(Date create_dt) {
        this.create_dt = create_dt;
    }

    public Long getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(Long writer_id) {
        this.writer_id = writer_id;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public MemberDTO getWriter() {
        return writer;
    }

    public void setWriter(MemberDTO writer) {
        this.writer = writer;
    }
}
