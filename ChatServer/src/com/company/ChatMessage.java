package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatMessage {
    private String chatMessage;
    private String sender;
    //SimpleDateFormat timeStamp = new SimpleDateFormat("h:mm a"); //this or long timeStamp !
    private String timeStamp;

    public ChatMessage(String chatMessage, String sender, String timeStamp) {
        this.chatMessage = chatMessage;
        this.sender = sender;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return timeStamp + " " + this.sender + ": " + this.chatMessage;
    }
}