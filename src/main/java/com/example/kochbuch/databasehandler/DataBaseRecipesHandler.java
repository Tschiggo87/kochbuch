package com.example.kochbuch.databasehandler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseRecipesHandler {
    private static final String URL = "jdbc:mysql://lx8.hoststar.hosting/ch355797_kochbuch";
    private static final String USER = "ch355797_ale";
    private static final String PASSWORD = "Admin_ale1";

    private Connection connection;
    public Connection connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Verbindung erfolgreich!");
            return connection; // Verbindung zurückgeben
        } catch (SQLException e) {
            System.out.println("Verbindung fehlgeschlagen: " + e.getMessage());
            throw e;
        }
    }



}

