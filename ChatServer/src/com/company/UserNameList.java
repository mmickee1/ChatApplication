package com.company;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;

public class UserNameList {

    private HashSet<String> userNameList;
    private static UserNameList singletonInstance = new UserNameList();

    private UserNameList() {
        this.userNameList = new HashSet<String>();
    }

    public static UserNameList getInstance() {
        return singletonInstance;
    }

    public void addUser(String user) {
        getInstance().userNameList.add(user);
    }

    public void removeUser(String user) {
        getInstance().userNameList.remove(user);
    }

    public boolean checkIfUserExists(String input) {
        return getInstance().userNameList.contains(input);
    }

    @Override
    public String toString() {
        StringBuilder allUserNames = new StringBuilder("");
        for (String name : getInstance().userNameList) {
            allUserNames.append(name.toString());
            allUserNames.append(" ");
        }
        return allUserNames.toString();
    }
}