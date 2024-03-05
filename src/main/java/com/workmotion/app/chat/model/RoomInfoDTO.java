package com.workmotion.app.chat.model;

import java.util.Date;

public class RoomInfoDTO {
    private Long member_id;
    private String room_name;
    private Date join_dt;
    private Date recently_dt;
    private Long new_message_count;
    private String name;

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getNew_message_count() {
        return new_message_count;
    }

    public void setNew_message_count(Long new_message_count) {
        this.new_message_count = new_message_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Date getJoin_dt() {
        return join_dt;
    }

    public void setJoin_dt(Date join_dt) {
        this.join_dt = join_dt;
    }

    public Date getRecently_dt() {
        return recently_dt;
    }

    public void setRecently_dt(Date recently_dt) {
        this.recently_dt = recently_dt;
    }
}
