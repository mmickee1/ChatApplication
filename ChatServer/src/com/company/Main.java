package com.company;

/**
 * @author Mikael Meinander
 * My chat server application
 */

public class Main {

    public static void main(String[] args) {
        PrintServer ps = PrintServer.getInstance();
        ps.serve();
    }
}