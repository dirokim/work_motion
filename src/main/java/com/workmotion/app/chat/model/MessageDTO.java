package com.workmotion.app.chat.model;

import com.workmotion.app.member.MemberDTO;

import java.util.Date;

public class MessageDTO {
    private Long id;
    private Long sender_id;
    private String message;
    private Date time;
    private String room_name;
    private String type;
    private MemberDTO sender;
    private RoomInfoDTO roomInfo;

    public RoomInfoDTO getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(RoomInfoDTO roomInfo) {
        this.roomInfo = roomInfo;
    }

    public MemberDTO getSender() {
        return sender;
    }

    public void setSender(MemberDTO sender) {
        this.sender = sender;
    }

    public MessageDTO() {
    }

    public MessageDTO(Long sender_id, String message, Date time, String room_name) {
        this.sender_id = sender_id;
        this.message = message;
        this.time = time;
        this.room_name = room_name;
    }

    // getters and setters


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
