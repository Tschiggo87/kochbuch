package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataTransmitter;
import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.controller.EditController;


public class RecipesDetailController {

    @FXML
    private Label recipeName;

    @FXML
    private Label recipeDescription;

    @FXML
    private Label recipeInstruction;

    @FXML
    private Label recipeTime;

    @FXML
    private Label recipeDifficulty;

    @FXML
    private Label recipePortion;

    @FXML
    private Label recipeIngredients;

    @FXML
    private ImageView recipeImage;
    @FXML
    private Button editBtn;
    private final DatabaseHandler databaseHandler;
    private final RezeptModel recipeModel;
    private int recipeId;

    public RecipesDetailController() {
        // Instanziierung des DatabaseHandler und RezeptModel
        databaseHandler = new DatabaseHandler();
        recipeModel = new RezeptModel();
    }

    public void initialize() {
        try {
            // Rezept-ID von DataTransmitter abrufen
            recipeId = DataTransmitter.getInstance().getRecipeId();

            // Verbindung zur Datenbank herstellen
            Connection connection = databaseHandler.getConnection();

            // Rezeptinformationen aus der Datenbank laden
            loadRecipeInfoFromDatabase(connection);

            // Rezeptinformationen anzeigen
            showRecipeInfo();

        } catch (SQLException e) {
            e.printStackTrace();
            // SQLException behandeln
        }
    }

    private void loadRecipeInfoFromDatabase(Connection connection) throws SQLException {
        // SQL-Abfrage zum Abrufen der Rezeptinformationen basierend auf der Rezept-ID
        String query = "SELECT name, beschreibung, dauer, portion, anweisungen, schwierigkeitsgrad, zutaten, bild FROM Rezepte WHERE RezeptId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Die Rezept-ID als Parameter in der SQL-Abfrage setzen
            statement.setInt(1, recipeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Rezeptinformationen aus dem ResultSet abrufen und im recipeModel speichern
                    recipeModel.setName(resultSet.getString("name"));
                    recipeModel.setBeschreibung(resultSet.getString("beschreibung"));
                    recipeModel.setDauer(resultSet.getInt("dauer"));
                    recipeModel.setPortion(resultSet.getInt("portion"));
                    recipeModel.setAnweisungen(resultSet.getString("anweisungen"));
                    recipeModel.setSchwierigkeitsgrad(resultSet.getString("schwierigkeitsgrad"));
                    recipeModel.setZutaten(resultSet.getString("zutaten"));
                    recipeModel.setBild(resultSet.getString("bild"));
                }
            }
        }
    }

    private void showRecipeInfo() {
        // Verzeichnis, in dem Rezeptbilder gespeichert sind
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        // Rezeptinformationen auf den entsprechenden UI-Elementen anzeigen
        recipeName.setText(recipeModel.getName());
        recipeDescription.setText(recipeModel.getBeschreibung());
        recipeTime.setText(Integer.toString(recipeModel.getDauer()));
        recipePortion.setText(Integer.toString(recipeModel.getPortion()));
        recipeInstruction.setText(recipeModel.getAnweisungen());
        recipeDifficulty.setText(recipeModel.getSchwierigkeitsgrad());
        recipeIngredients.setText(recipeModel.getZutaten());

        // Rezeptbild laden und anzeigen
        recipeImage.setImage(new Image("file:" + imageDirectory + recipeModel.getBild()));
    }

    public void onEditBtnClick() {

        // Wechseln Sie zur Bearbeitungsansicht
        Main.switchToView(StaticViews.RecipeEditView);


    }

    public void onBackToRecipesBtnClick() {
        // Zurück zur Rezeptansicht wechseln
        Main.switchToView(StaticViews.RecipesView);
    }

}
