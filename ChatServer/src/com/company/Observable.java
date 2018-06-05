package com.company;

public interface Observable {
    void register(Observer observer);

    void deregister(Observer observer);

    void notifyObservers(ChatMessage chatMessage);
}