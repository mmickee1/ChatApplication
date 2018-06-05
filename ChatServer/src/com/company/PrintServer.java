package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;

public class PrintServer {

    private static PrintServer ourInstance = new PrintServer();
    //private static int MAXSIZE = 20;
    private int queueSize;

    PrintServer() {
        queueSize = 10;
        System.out.println("Server created. Queue size " + queueSize);
    }

    public static PrintServer getInstance() {
        return ourInstance;
    }

    public void serve() {
        System.out.println("Server started");
        try {
            //ServerSocket ss = new ServerSocket(0, 2);
            ServerSocket ss = new ServerSocket(12345, 2);
            System.out.println("I have socket " + ss.getLocalPort());
            ChatHistory.getInstance().register(new ChatConsole());

            while (true) {
                Socket s = ss.accept();
                System.out.println("Accepted new connection");
                CommandInterpreter commandInterpreter = new CommandInterpreter(s.getInputStream(), new PrintStream(s.getOutputStream(), true));
                Thread t = new Thread(commandInterpreter);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
