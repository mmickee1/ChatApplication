package com.company;

public class ChatConsole implements Observer {
    public ChatConsole() {
    }

    @Override
    public void update(ChatMessage n) {
        System.out.println(n.toString());
        //Prints out to system.out the chat messages in the conversation
    }
}