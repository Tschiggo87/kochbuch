package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.helper.DataTransmitter;
import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    @FXML
    private Button editBtn;
    @FXML
    private Button favoriteBtn;
    @FXML
    private ImageView userFavoritesIcon;

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

            // Update the visibility of the edit button
            updateButtonVisibility();

        } catch (SQLException e) {
            e.printStackTrace();
            // SQLException behandeln
        }
    }

    public void updateButtonVisibility() {
        String loggedInUser = MainController.getLoggedInUser();

        if (loggedInUser != null && loggedInUser.equals("admin")) {
            editBtn.setVisible(true);
            favoriteBtn.setVisible(false);
            userFavoritesIcon.setVisible(false);
        } else if (loggedInUser != null) { // Benutzer eingeloggt, aber nicht admin
            editBtn.setVisible(false);
            favoriteBtn.setVisible(true);
            userFavoritesIcon.setVisible(true);
        } else { // Benutzer nicht eingeloggt
            editBtn.setVisible(false);
            favoriteBtn.setVisible(false);
            userFavoritesIcon.setVisible(false);
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
                    recipeModel.setDauer(resultSet.getString("dauer"));
                    recipeModel.setPortion(resultSet.getString("portion"));
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
        recipeTime.setText(recipeModel.getDauer());
        recipePortion.setText(recipeModel.getPortion());
        recipeInstruction.setText(recipeModel.getAnweisungen());
        recipeDifficulty.setText(recipeModel.getSchwierigkeitsgrad());
        recipeIngredients.setText(recipeModel.getZutaten());

        // Rezeptbild laden und anzeigen
        recipeImage.setImage(new Image("file:" + imageDirectory + recipeModel.getBild()));
    }

    @FXML
    public void onEditBtnClick() {
        // Wechseln Sie zur Bearbeitungsansicht
        Main.switchToView(StaticViews.RecipeEditView);
    }

    @FXML
    public void onBackToRecipesBtnClick() {
        // Zurück zur Rezeptansicht wechseln
        Main.switchToView(StaticViews.RecipesView);
    }

    @FXML
    public void addToFavoritesBtn() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Favoriten hinzufügen");
        alert.setHeaderText(null);
        alert.setContentText("Das Rezept wurde zu den Favoriten hinzugefügt!");

        alert.showAndWait();
    }

}
