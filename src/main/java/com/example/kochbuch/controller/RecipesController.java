package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.helper.DataTransmitter;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.model.RezeptModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

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
    @FXML
    private Button detailButton1, detailButton2, detailButton3, detailButton4, detailButton5, detailButton6;

    public void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<RezeptModel> rezepte = databaseHandler.getRezepteFromDatabase();
        populateLabels(rezepte);

        Button[] detailButtons = {detailButton1, detailButton2, detailButton3, detailButton4, detailButton5, detailButton6};
        for (int i = 0; i < NUM_RECIPES; i++) {
            detailButtons[i].setUserData(i + 1);
        }
    }

    public void populateLabels(List<RezeptModel> rezepte) {
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        Label[] recipeNames = {recipeName1, recipeName2, recipeName3, recipeName4, recipeName5, recipeName6};
        ImageView[] recipeImages = {recipeImage1, recipeImage2, recipeImage3, recipeImage4, recipeImage5, recipeImage6};
        Label[] recipeDescriptions = {recipeDescription1, recipeDescription2, recipeDescription3, recipeDescription4, recipeDescription5, recipeDescription6};
        Label[] recipeDifficulties = {recipeDifficulty1, recipeDifficulty2, recipeDifficulty3, recipeDifficulty4, recipeDifficulty5, recipeDifficulty6};

        for (int i = 0; i < NUM_RECIPES; i++) {
            RezeptModel rezept = rezepte.get(i);
            recipeImages[i].setImage(new Image("file:" + imageDirectory + rezept.getBild()));
            recipeNames[i].setText(rezept.getName());
            recipeDescriptions[i].setText(rezept.getBeschreibung());
            recipeDifficulties[i].setText(rezept.getSchwierigkeitsgrad());
        }
    }

    @FXML
    public void openRecipeDetailView(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        int recipeId = (int) sourceButton.getUserData();
        DataTransmitter.getInstance().setRecipeId(recipeId);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onRecipesBackBtnClick() {
        Main.switchToView(StaticViews.WelcomeView);
    }
}
