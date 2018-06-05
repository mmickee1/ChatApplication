package com.example.android.chatapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadFactory;

/**
 * Created by mikael
 */

public class Receiver implements Runnable {

    //private String message;
    private Scanner scanner;
    private BlockingQueue<String> outGoingMessageQueue;
    private InputStream inputStream;
    private Socket socket;
    private String localhost;
    private String homeIP = "192.168.8.114";
    private int port = 12345;
    TextView textView;
    private String message;
    private boolean connectionOk = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Receiver(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        outGoingMessageQueue = new LinkedTransferQueue<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void run() {
        textView = (TextView) textView.findViewById(R.id.messageHistory);
        while (!Thread.interrupted()) {
            if (scanner.hasNext()) {
                try {
                    socket = new Socket(homeIP, port);
                    scanner = new Scanner(socket.getInputStream());
                    connectionOk = true;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String message = scanner.nextLine();
            while (true) {
                UpdateTextView(message);
            }
        }
    }


    private void UpdateTextView(String message) {
        textView.append(message);
    }
}
//socket , inputstream , do{scanner.readline()} */