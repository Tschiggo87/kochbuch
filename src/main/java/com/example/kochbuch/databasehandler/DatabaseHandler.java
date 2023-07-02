package com.example.kochbuch.databasehandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {


    public Connection databaseLink;

    public Connection getConnection(){
         String URL = "jdbc:mysql://lx8.hoststar.hosting/ch355797_kochbuch";
         String USER = "ch355797_ale";
         String PASSWORD = "Admin_ale1";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

}

