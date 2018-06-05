package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class ChatHistory implements Observable {
    private static ChatHistory ourInstance = new ChatHistory();
    ArrayList<ChatMessage> wholeChat;
    HashSet<Observer> observers;

    public static ChatHistory getInstance() {
        return ourInstance;
    }

    private ChatHistory() {
        this.wholeChat = new ArrayList<ChatMessage>();
        this.observers = new HashSet<>();
    }

    public void insert(ChatMessage message) {
        wholeChat.add(message);
        notifyObservers(message);
        //commandinterpreter uses this when it has received a message.
    }

    @Override
    public String toString() {
        StringBuilder allChat = new StringBuilder("Whole Chat History:");
        for (ChatMessage w : wholeChat) {
            allChat.append("\r\n");
            allChat.append(w.toString());
        }
        return allChat.toString();
        //return the whole chat history as a nicely formatted string
    }

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(ChatMessage chatMessage) {
        for (Observer c : observers) {
            c.update(chatMessage);
        }
    }
}