package com.example.kochbuch.databasehandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.kochbuch.model.RezeptModel;

public class DatabaseHandler {

    private static final String URL = "jdbc:mysql://lx8.hoststar.hosting/ch355797_kochbuch";
    private static final String USER = "ch355797_ale";
    private static final String PASSWORD = "Admin_ale1";

    private Connection databaseLink;

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

    public List<RezeptModel> getRezepteFromDatabase() {
        List<RezeptModel> rezepte = new ArrayList<>();

        try {
            // Verbindung zur Datenbank herstellen
            Connection connection = getConnection();

            // SQL-Abfrage vorbereiten
            String query = "SELECT * FROM Rezepte";
            PreparedStatement statement = connection.prepareStatement(query);

            // Abfrage ausführen
            ResultSet resultSet = statement.executeQuery();

            // Ergebnis verarbeiten
            while (resultSet.next()) {
                RezeptModel rezept = new RezeptModel();
                rezept.setRezeptID(resultSet.getInt("rezeptID"));
                rezept.setName(resultSet.getString("name"));
                rezept.setBeschreibung(resultSet.getString("beschreibung"));
                rezept.setDauer(resultSet.getInt("dauer"));
                rezept.setPortion(resultSet.getInt("portion"));
                rezept.setSchwierigkeitsgrad(resultSet.getString("schwierigkeitsgrad"));
                rezept.setAnweisungen(resultSet.getString("anweisungen"));
                rezept.setZutaten(resultSet.getString("zutaten"));
                rezept.setBild(resultSet.getString("bild"));

                rezepte.add(rezept);
            }

            // Ressourcen freigeben
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezepte;
    }

}
