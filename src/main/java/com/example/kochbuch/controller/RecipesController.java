package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DatabaseHelper;
import com.example.kochbuch.model.RezeptModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class RecipesController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private HBox recipe1;

    @FXML
    private HBox recipe2;

    @FXML
    private HBox recipe3;

    @FXML
    private HBox recipe4;

    @FXML
    private HBox recipe5;

    @FXML
    private HBox recipe6;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private ImageView imageView5;

    @FXML
    private ImageView imageView6;

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
    private Label recipeDescription1;

    @FXML
    private Label recipeDescription2;

    @FXML
    private Label recipeDescription3;

    @FXML
    private Label recipeDescription4;

    @FXML
    private Label recipeDescription5;

    @FXML
    private Label recipeDescription6;

    @FXML
    private Label recipeDifficulty1;

    @FXML
    private Label recipeDifficulty2;

    @FXML
    private Label recipeDifficulty3;

    @FXML
    private Label recipeDifficulty4;

    @FXML
    private Label recipeDifficulty5;

    @FXML
    private Label recipeDifficulty6;

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
    @FXML
    private Button detailButton1;

    @FXML
    private Button detailButton2;

    @FXML
    private Button detailButton3;

    @FXML
    private Button detailButton4;

    @FXML
    private Button detailButton5;

    @FXML
    private Button detailButton6;

    @FXML
    private Label loggedInUserLabel;

    @FXML
    private Button backButton;

    private DatabaseHelper databaseHelper;

    public void initialize() {
        // Erstelle eine Instanz des DatabaseHelper
        databaseHelper = new DatabaseHelper();

        // Lade die Rezepte aus der Datenbank
        List<RezeptModel> rezepte = databaseHelper.getRezepteFromDatabase();

        // Befülle die Labels mit den Rezeptdaten
        populateLabels(rezepte);
    }



    public void populateLabels(List<RezeptModel> rezepte) {

        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        recipeImage1.setImage(new Image("file:" + imageDirectory + rezepte.get(0).getBild()));
        recipeName1.setText(rezepte.get(0).getName());
        recipeDescription1.setText(rezepte.get(0).getBeschreibung());
        recipeDifficulty1.setText(rezepte.get(0).getSchwierigkeitsgrad());


        recipeImage2.setImage(new Image("file:" + imageDirectory + rezepte.get(1).getBild()));
        recipeName2.setText(rezepte.get(1).getName());
        recipeDescription2.setText(rezepte.get(1).getBeschreibung());
        recipeDifficulty2.setText(rezepte.get(1).getSchwierigkeitsgrad());

        recipeImage3.setImage(new Image("file:" + imageDirectory + rezepte.get(2).getBild()));
        recipeName3.setText(rezepte.get(2).getName());
        recipeDescription3.setText(rezepte.get(2).getBeschreibung());
        recipeDifficulty3.setText(rezepte.get(2).getSchwierigkeitsgrad());

        recipeImage4.setImage(new Image("file:" + imageDirectory + rezepte.get(3).getBild()));
        recipeName4.setText(rezepte.get(3).getName());
        recipeDescription4.setText(rezepte.get(3).getBeschreibung());
        recipeDifficulty4.setText(rezepte.get(3).getSchwierigkeitsgrad());

        recipeImage5.setImage(new Image("file:" + imageDirectory + rezepte.get(4).getBild()));
        recipeName5.setText(rezepte.get(4).getName());
        recipeDescription5.setText(rezepte.get(4).getBeschreibung());
        recipeDifficulty5.setText(rezepte.get(4).getSchwierigkeitsgrad());

        recipeImage6.setImage(new Image("file:" + imageDirectory + rezepte.get(5).getBild()));
        recipeName6.setText(rezepte.get(5).getName());
        recipeDescription6.setText(rezepte.get(5).getBeschreibung());
        recipeDifficulty6.setText(rezepte.get(5).getSchwierigkeitsgrad());
    }

    @FXML
    public void onRecipesDetailButtonClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onRecipesBackBtnClick() {
        Main.switchToView(StaticViews.WelcomeView);
    }
}
