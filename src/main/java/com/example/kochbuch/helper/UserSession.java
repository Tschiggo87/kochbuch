package com.example.kochbuch.helper;

import java.util.Observable;

/**
 * Die UserSession Klasse ist für die Verwaltung der aktuell angemeldeten Benutzer zuständig.
 */

public class UserSession extends Observable {
    private static UserSession instance = null;
    private String loggedInUser;


    private UserSession() {}

    /**
     * Gibt eine Instanz der UserSession zurück.
     *
     * @return Die Instanz der UserSession.
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Setzt den aktuell angemeldeten Benutzer.
     *
     * @param loggedInUser Der aktuell angemeldete Benutzer.
     */
    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
        setChanged();
        notifyObservers(loggedInUser);
    }

    /**
     * Gibt den aktuell angemeldeten Benutzer zurück.
     *
     * @return Der aktuell angemeldete Benutzer.
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Überprüft, ob ein Admin angemeldet ist.
     *
     * @return true, wenn ein Benutzer angemeldet ist, sonst false.
     */
    public boolean isAdmin() {
        return loggedInUser != null && loggedInUser.equals("admin");
    }

}
