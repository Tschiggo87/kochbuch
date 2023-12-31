package com.example.kochbuch.databasehandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.kochbuch.model.RezeptModel;

/**
 * @author Enrico
 * @version 1.0.0
 *
 *         Diese Klasse stellt die Verbindung zur Datenbank her und liest die
 *         Rezepte aus der Datenbank aus.
 */
public class DatabaseHandler {

    private static final String URL = "jdbc:mysql://lx8.hoststar.hosting/ch355797_kochbuch";
    private static final String USER = "ch355797_ale";
    private static final String PASSWORD = "Admin_ale1";

    private Connection databaseLink;

    /**
     * Stellt eine Verbindung zur Datenbank her.
     * @return Verbindung zur Datenbank
     */
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

    /**
     * Liest alle Rezepte aus der Datenbank aus und gibt sie als Liste zurück.
     * @return Liste aller Rezepte
     */
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
                rezept.setDauer(resultSet.getString("dauer"));
                rezept.setPortion(resultSet.getString("portion"));
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

    /**
     * Updated ein Rezept in der Datenbank.
     * @param recipeModel Rezept, das aktualisiert werden soll
     * @return true, wenn das Rezept erfolgreich aktualisiert wurde
     */
    public boolean updateRezeptInDatabase(RezeptModel recipeModel) {
        try {
            // Verbindung zur Datenbank herstellen
            Connection connection = getConnection();

            // SQL-Abfrage vorbereiten
            String query = "UPDATE Rezepte SET name = ?, beschreibung = ?, dauer = ?, portion = ?, schwierigkeitsgrad = ?, anweisungen = ?, zutaten = ?, bild = ? WHERE rezeptID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, recipeModel.getName());
            statement.setString(2, recipeModel.getBeschreibung());
            statement.setString(3, recipeModel.getDauer());
            statement.setString(4, recipeModel.getPortion());
            statement.setString(5, recipeModel.getSchwierigkeitsgrad());
            statement.setString(6, recipeModel.getAnweisungen());
            statement.setString(7, recipeModel.getZutaten());
            statement.setString(8, recipeModel.getBild());
            statement.setInt(9, recipeModel.getRezeptID());

            // Abfrage ausführen
            int rowsUpdated = statement.executeUpdate();

            // Ressourcen freigeben
            statement.close();
            connection.close();

            // Überprüfen, ob die Aktualisierung erfolgreich war
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Fügt ein Rezept in die Datenbank ein.
     * @param rezept Rezept, das in die Datenbank eingefügt werden soll
     * @return true, wenn das Rezept erfolgreich hinzugefügt wurde
     */
    public boolean addRezeptToDatabase(RezeptModel rezept) {
        try {
            // Verbindung zur Datenbank herstellen
            Connection connection = getConnection();

            // SQL-Abfrage vorbereiten
            String query = "INSERT INTO Rezepte (name, beschreibung, dauer, portion, schwierigkeitsgrad, anweisungen, zutaten, bild) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Werte setzen
            statement.setString(1, rezept.getName());
            statement.setString(2, rezept.getBeschreibung());
            statement.setString(3, rezept.getDauer());
            statement.setString(4, rezept.getPortion());
            statement.setString(5, rezept.getSchwierigkeitsgrad());
            statement.setString(6, rezept.getAnweisungen());
            statement.setString(7, rezept.getZutaten());
            statement.setString(8, rezept.getBild());

            // Abfrage ausführen
            int rowsAffected = statement.executeUpdate();

            // Ressourcen freigeben
            statement.close();
            connection.close();

            // Überprüfen, ob das Rezept erfolgreich hinzugefügt wurde
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



}
