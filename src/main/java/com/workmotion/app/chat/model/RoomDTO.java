package com.workmotion.app.chat.model;

public class RoomDTO {
    private String name;

    private MessageDTO message;

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
