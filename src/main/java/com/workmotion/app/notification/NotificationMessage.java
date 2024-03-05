package com.workmotion.app.notification;

public class NotificationMessage {
    private String message;
    private String sender;
    private String type;
    private String targetRoom;
    private Long targetId;

    private Long subId;

    public String getTargetRoom() {
        return targetRoom;
    }

    public void setTargetRoom(String targetRoom) {
        this.targetRoom = targetRoom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
