package com.example.android.chatapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mikael
 */

public class UtilServer implements Runnable{

    private String address;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private int port;
    private MainActivity mainActivity;

    public UtilServer(String address, int port){
        this.address=address;
        this.port=port;
    }

    public UtilServer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void run() {
        try {
            socket = new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            receiver = new Receiver(socket.getInputStream());
            sender = new Sender(new PrintStream(socket.getOutputStream()));

        }catch (IOException io){
            io.printStackTrace();
        }
        Thread androidSender = new Thread(sender);
        Thread androidReceiver = new Thread(receiver);
        androidSender.start();
        androidReceiver.start();
}
}
