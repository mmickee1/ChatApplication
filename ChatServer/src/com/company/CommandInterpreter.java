package com.company;

import javax.rmi.CORBA.StubDelegate;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class CommandInterpreter implements Runnable, Observer {
    private InputStream inputStream;
    private PrintStream outputStream;

    public CommandInterpreter(InputStream inputStream, PrintStream outputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        outputStream.println("Hello!");
    }

    public void run() {

        String userName = null;
        Scanner in = new Scanner(inputStream);

        try {
            do {
                outputStream.println("Create user with :user 'username' ");

                String input = in.nextLine();
                String[] arr = input.split("[ ]", 2);
                String command = arr[0];
                String argument = arr.length > 1 ? arr[1] : null;

                if (isItAUserName(input) && !UserNameList.getInstance().checkIfUserExists(argument)) {
                    UserNameList.getInstance().addUser(argument);
                    outputStream.println("Username is set to " + argument);
                    ChatHistory.getInstance().observers.add(this);
                    userName = argument;
                } else {
                    outputStream.println("Type username again (example: :user John)");
                }
            } while (userName == null);
        } catch (Exception exception) {
            exception.printStackTrace(outputStream);
        }

        boolean done = false;
        try {
            do {
                String input = in.nextLine();
                String[] arr = input.split("[ ]", 2);
                String command = arr[0];
                String argument = arr.length > 1 ? arr[1] : null;

                if (isItQuit(command)) {
                    UserNameList.getInstance().removeUser(argument);
                    ChatHistory.getInstance().deregister(this);
                    outputStream.println("Quitting");
                    done = true;
                } else if (isItUsers(command)) {
                    outputStream.println("All users:");
                    outputStream.println(UserNameList.getInstance().toString());
                } else if (isItHistory(command)) {
                    outputStream.println(ChatHistory.getInstance().toString());
                } else {
                    //SimpleDateFormat timeStamp = new SimpleDateFormat("h:mm a"); //this or long timeStamp !
                    LocalDateTime currentTime = LocalDateTime.now();
                    ChatMessage message = new ChatMessage(input, userName, currentTime.toString());
                    ChatHistory.getInstance().insert(message);
                }
            } while (!done);
        } catch (Exception exception) {
            exception.printStackTrace(outputStream);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.outputStream.close();

    }

    public static boolean isItAUserName(String input) {
        if (input.startsWith(":user ") && input.length() > 6) {
            return true;
        }
        return false;
    }

    public static boolean isItHistory(String input) {
        if (input.equals(":history")) {
            return true;
        }
        return false;
    }

    public static boolean isItUsers(String input) {
        if (input.equals(":users")) {
            return true;
        }
        return false;
    }

    public static boolean isItQuit(String input) {
        if (input.equals(":quit")) {
            return true;
        }
        return false;
    }

    @Override
    public void update(ChatMessage message) {
        outputStream.println(message.toString());
    }
}