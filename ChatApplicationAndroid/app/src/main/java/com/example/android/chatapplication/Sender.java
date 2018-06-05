package com.example.android.chatapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.widget.Button;

import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

import static android.R.id.message;

/**
 * Created by mikael
 */

public class Sender implements Runnable {

    private String message;
    private BlockingQueue<String> outGoingMessageQueue;
    private PrintStream outputStream;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Sender(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.outGoingMessageQueue = new LinkedTransferQueue<>();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                //Take messages from the queue  (blockqueue)
                // message to the output stream
                message = this.outGoingMessageQueue.take();
                sendMessage(message);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        outputStream.println(message);
    }
}
//outputstream, printstream, run (){add.blockingqueue, take(); , ps.printline(-1),
//check that blockqueue isnt empty