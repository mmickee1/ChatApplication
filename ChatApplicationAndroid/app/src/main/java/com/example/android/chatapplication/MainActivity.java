package com.example.android.chatapplication;

import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import static android.R.id.empty;
import static android.R.id.message;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
    //private String message;
    //private EditText typedMessage;
    private ArrayList<Message> allMessages = new ArrayList<>();
    private UtilServer utilServer;
    //TextView textView = (TextView) findViewById(R.id.messageHistory);
    //EditText typedMessage = (EditText) findViewById(R.id.messageField);
    private TextView textView;
    private EditText typedMessage;
    private Socket socket;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        //added next 2 this morning + changed the private fields
        textView = (TextView) findViewById(R.id.messageHistory);
        typedMessage = (EditText) findViewById(R.id.messageField);

        //utilServer = new UtilServer(this);
        //Thread socketServer = new Thread(utilServer);
        //socketServer.start();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //testi tästä nää kaks linee
                EditText editText = (EditText) findViewById(R.id.messageField);
                final String mussage = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PrintStream printStream = new PrintStream(socket.getOutputStream(), true);
                            if (!mussage.isEmpty()) {
                                printStream.println(mussage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
                editText.getText().clear();
            }
        });
    }
}