package com.example.kochbuch.helper;

import java.util.Observable;

public class UserSession extends Observable {
    private static UserSession instance = null;
    private String loggedInUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
        setChanged();
        notifyObservers(loggedInUser);
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}
