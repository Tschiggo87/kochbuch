package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataTransmitter;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.model.RezeptModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

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

    private DatabaseHandler databaseHelper;

    public void initialize() {
        // Instanziierung des DatabaseHandler
        databaseHelper = new DatabaseHandler();
        List<RezeptModel> rezepte = databaseHelper.getRezepteFromDatabase();
        populateLabels(rezepte);

    }
    private void applyCoverScaling(ImageView imageView, double parentWidth, double parentHeight) {
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(parentWidth);
        imageView.setFitHeight(parentHeight);
        imageView.setSmooth(true);
        imageView.setClip(new Rectangle(parentWidth, parentHeight));
    }

    // Methode zum Befüllen der Labels mit den Rezeptinformationen
    public void populateLabels(List<RezeptModel> rezepte) {
        String imageDirectory = "src/main/resources/images/RezeptBilder/";
        Label[] recipeNames = {recipeName1, recipeName2, recipeName3, recipeName4, recipeName5, recipeName6};
        ImageView[] recipeImages = {recipeImage1, recipeImage2, recipeImage3, recipeImage4, recipeImage5, recipeImage6};
        Label[] recipeDescriptions = {recipeDescription1, recipeDescription2, recipeDescription3, recipeDescription4, recipeDescription5, recipeDescription6};
        Label[] recipeDifficulties = {recipeDifficulty1, recipeDifficulty2, recipeDifficulty3, recipeDifficulty4, recipeDifficulty5, recipeDifficulty6};
        for (int i = 0; i < NUM_RECIPES; i++) {
            recipeImages[i].setImage(new Image("file:" + imageDirectory + rezepte.get(i).getBild()));
            recipeNames[i].setText(rezepte.get(i).getName());
            recipeDescriptions[i].setText(rezepte.get(i).getBeschreibung());
            recipeDifficulties[i].setText(rezepte.get(i).getSchwierigkeitsgrad());
        }
    }

    @FXML
    public void onDetailBtnClick1() {
        // Rezept-ID setzen und zur Detailansicht wechseln
        DataTransmitter.getInstance().setRecipeId(1);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick2() {
        // Rezept-ID setzen und zur Detailansicht wechseln
        DataTransmitter.getInstance().setRecipeId(2);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick3() {
        // Rezept-ID setzen und zur Detailansicht wechseln
        DataTransmitter.getInstance().setRecipeId(3);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick4() {
        // Rezept-ID setzen und zur Detailansicht wechseln
        DataTransmitter.getInstance().setRecipeId(4);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick5() {
        // Rezept-ID setzen und zur Detailansicht wechseln
        DataTransmitter.getInstance().setRecipeId(5);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick6() {
        // Rezept-ID setzen und zur Detailansicht wechseln
        DataTransmitter.getInstance().setRecipeId(6);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onRecipesBackBtnClick() {
        // Zurück zur Willkommensansicht wechseln
        Main.switchToView(StaticViews.WelcomeView);
    }
}
