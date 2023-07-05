package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataTransmitter;
import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private final DatabaseHandler databaseHandler;
    private final RezeptModel recipeModel;
    private int recipeId;

    public RecipesDetailController() {
        databaseHandler = new DatabaseHandler();
        recipeModel = new RezeptModel();
    }

    public void initialize() {
        try {
            // RezeptId aus dem DataTransmitter erhalten
            recipeId = DataTransmitter.getInstance().getRecipeId();

            // Verbindung zur Datenbank herstellen
            Connection connection = databaseHandler.getConnection();

            // Rezeptinformationen aus der Datenbank laden
            loadRecipeInfoFromDatabase(connection);

            // Rezeptinformationen anzeigen
            showRecipeInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            // Fehlerbehandlung
        }
    }

    private void loadRecipeInfoFromDatabase(Connection connection) throws SQLException {
        // SQL-Abfrage für das Laden der Rezeptinformationen
        String query = "SELECT name, beschreibung, dauer, portion, anweisungen, schwierigkeitsgrad, zutaten, bild FROM Rezepte WHERE RezeptId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Parameter für die RezeptId setzen
            statement.setInt(1, recipeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Rezeptinformationen aus dem ResultSet in das Rezeptmodell laden
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
        // Verzeichnis, in dem die Rezeptbilder gespeichert sind
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        // Rezeptinformationen auf den entsprechenden UI-Elementen anzeigen
        recipeName.setText(recipeModel.getName());
        recipeDescription.setText(recipeModel.getBeschreibung());
        recipeTime.setText(Integer.toString(recipeModel.getDauer()));
        recipePortion.setText(Integer.toString(recipeModel.getPortion()));
        recipeInstruction.setText(recipeModel.getAnweisungen());
        recipeDifficulty.setText(recipeModel.getSchwierigkeitsgrad());
        recipeIngredients.setText(recipeModel.getZutaten());

        // Das Rezeptbild laden und anzeigen
        recipeImage.setImage(new Image("file:" + imageDirectory + recipeModel.getBild()));
    }

    /* Weitere Methoden für die Interaktion mit der Benutzeroberfläche */

    public void setRecipeId(int id) {
        this.recipeId = id;
    }

    public void onEditBtnClick() {
        // Zur Bearbeitungsansicht wechseln
        Main.switchToView(StaticViews.RecipeEditView);
    }

    public void onBackToRecipesBtnClick() {
        // Zur Rezepteansicht zurückkehren
        Main.switchToView(StaticViews.RecipesView);
    }
}
