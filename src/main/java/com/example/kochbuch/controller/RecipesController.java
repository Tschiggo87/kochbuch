package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataTransmitter;
import com.example.kochbuch.databasehandler.DatabaseHelper;
import com.example.kochbuch.model.RezeptModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class RecipesController {

    @FXML
    private Label recipeName1;
    @FXML
    private Label recipeName2;
    @FXML
    private Label recipeName3;
    @FXML
    private Label recipeName4;
    @FXML
    private Label recipeName5;
    @FXML
    private Label recipeName6;

    @FXML
    private ImageView recipeImage1;
    @FXML
    private ImageView recipeImage2;
    @FXML
    private ImageView recipeImage3;
    @FXML
    private ImageView recipeImage4;
    @FXML
    private ImageView recipeImage5;
    @FXML
    private ImageView recipeImage6;

    private DatabaseHelper databaseHelper;

    public void initialize() {
        databaseHelper = new DatabaseHelper();
        List<RezeptModel> rezepte = databaseHelper.getRezepteFromDatabase();
        populateLabels(rezepte);
    }

    public void populateLabels(List<RezeptModel> rezepte) {
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        // Rezept 1
        recipeImage1.setImage(new Image("file:" + imageDirectory + rezepte.get(0).getBild()));
        recipeName1.setText(rezepte.get(0).getName());

        // Rezept 2
        recipeImage2.setImage(new Image("file:" + imageDirectory + rezepte.get(1).getBild()));
        recipeName2.setText(rezepte.get(1).getName());

        // Rezept 3
        recipeImage3.setImage(new Image("file:" + imageDirectory + rezepte.get(2).getBild()));
        recipeName3.setText(rezepte.get(2).getName());

        // Rezept 4
        recipeImage4.setImage(new Image("file:" + imageDirectory + rezepte.get(3).getBild()));
        recipeName4.setText(rezepte.get(3).getName());

        // Rezept 5
        recipeImage5.setImage(new Image("file:" + imageDirectory + rezepte.get(4).getBild()));
        recipeName5.setText(rezepte.get(4).getName());

        // Rezept 6
        recipeImage6.setImage(new Image("file:" + imageDirectory + rezepte.get(5).getBild()));
        recipeName6.setText(rezepte.get(5).getName());
    }

    @FXML
    public void onDetailBtnClick1() {
        DataTransmitter.getInstance().setRecipeId(1);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick2() {
        DataTransmitter.getInstance().setRecipeId(2);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick3() {
        DataTransmitter.getInstance().setRecipeId(3);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick4() {
        DataTransmitter.getInstance().setRecipeId(4);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onDetailBtnClick5() {
        DataTransmitter.getInstance().setRecipeId(5);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    public void onDetailBtnClick6(){
        DataTransmitter.getInstance().setRecipeId(6);
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onRecipesBackBtnClick() {
        Main.switchToView(StaticViews.WelcomeView);
    }
}
