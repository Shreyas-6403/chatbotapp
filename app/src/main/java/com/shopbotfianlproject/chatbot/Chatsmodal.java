package com.shopbotfianlproject.chatbot;

import android.widget.ImageView;

public class Chatsmodal {
    private ImageView link;
    private String message;
    private String sender;

    public Chatsmodal(String message, String sender,ImageView link) {
        this.link=link;
        this.message = message;
        this.sender = sender;
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

    public ImageView getlink() {
        return link;
    }

    public void setlink(ImageView link) {
        this.link = link;
    }
}
