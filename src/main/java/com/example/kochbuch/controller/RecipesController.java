package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.helper.DataTransmitter;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.model.RezeptModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.List;
/**
 * @class RecipesController ist für die Anzeige der Rezepte auf der Rezept Overview zuständig.
 * @author Enrico Kälin
 * */
public class RecipesController {
    private static final int NUM_RECIPES = 6;

    @FXML
    private Label recipeName1, recipeName2, recipeName3, recipeName4, recipeName5, recipeName6;
    @FXML
    private ImageView recipeImage1, recipeImage2, recipeImage3, recipeImage4, recipeImage5, recipeImage6;
    @FXML
    private Label recipeDescription1, recipeDescription2, recipeDescription3, recipeDescription4, recipeDescription5, recipeDescription6;
    @FXML
    private Label recipeDifficulty1, recipeDifficulty2, recipeDifficulty3, recipeDifficulty4, recipeDifficulty5, recipeDifficulty6;

    private DatabaseHandler databaseHandler;

    /**
     * Initialisierung der Rezept Overview
     * */
    public void initialize() {
        // Initialisierung des DatabaseHandler
        databaseHandler = new DatabaseHandler();
        List<RezeptModel> rezepte = databaseHandler.getRezepteFromDatabase();
        populateLabels(rezepte);
    }


    /**
     * @param rezepte Liste der Rezepte
     * */
    public void populateLabels(List<RezeptModel> rezepte) {
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        // Arrays für die Labels und ImageView-Elemente erstellen
        Label[] recipeNames = {recipeName1, recipeName2, recipeName3, recipeName4, recipeName5, recipeName6};
        ImageView[] recipeImages = {recipeImage1, recipeImage2, recipeImage3, recipeImage4, recipeImage5, recipeImage6};
        Label[] recipeDescriptions = {recipeDescription1, recipeDescription2, recipeDescription3, recipeDescription4, recipeDescription5, recipeDescription6};
        Label[] recipeDifficulties = {recipeDifficulty1, recipeDifficulty2, recipeDifficulty3, recipeDifficulty4, recipeDifficulty5, recipeDifficulty6};

        for (int i = 0; i < NUM_RECIPES; i++) {
            // Rezeptinformationen aus der Liste abrufen
            RezeptModel rezept = rezepte.get(i);

            // Bild des Rezepts setzen
            recipeImages[i].setImage(new Image("file:" + imageDirectory + rezept.getBild()));

            // Name, Beschreibung und Schwierigkeitsgrad des Rezepts setzen
            recipeNames[i].setText(rezept.getName());
            recipeDescriptions[i].setText(rezept.getBeschreibung());
            recipeDifficulties[i].setText(rezept.getSchwierigkeitsgrad());
        }
    }

    // Detailansichts-Buttons
    @FXML
    public void onDetailBtnClick1() {
        openRecipeDetailView(1);
    }

    @FXML
    public void onDetailBtnClick2() {
        openRecipeDetailView(2);
    }

    @FXML
    public void onDetailBtnClick3() {
        openRecipeDetailView(3);
    }

    @FXML
    public void onDetailBtnClick4() {
        openRecipeDetailView(4);
    }

    @FXML
    public void onDetailBtnClick5() {
        openRecipeDetailView(5);
    }

    @FXML
    public void onDetailBtnClick6() {
        openRecipeDetailView(6);
    }

    // Methode zum Öffnen der Detailansicht eines Rezepts
    private void openRecipeDetailView(int recipeId) {
        // Rezept-ID setzen und Detailansicht öffnen
        DataTransmitter.getInstance().setRecipeId(recipeId);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    // Zurück-Button
    @FXML
    public void onRecipesBackBtnClick() {
        Main.switchToView(StaticViews.WelcomeView);
    }

    public void addToFavoritesBtn() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rezept wurde zu den Favoriten hinzugefügt!", ButtonType.OK);
        alert.setHeaderText(null); // Keinen Header-Text anzeigen
        alert.setTitle("Favoriten hinzugefügt");
        alert.showAndWait();
    }
}
