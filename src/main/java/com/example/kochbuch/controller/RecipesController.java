package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import com.example.kochbuch.databasehandler.DataBaseRecipesHandler;

public class RecipesController {

    /* FXML Elements */
    @FXML
    private Label recipesPaneTitle1;
    @FXML
    private Label recipesPaneTitle2;
    @FXML
    private Label recipesPaneTitle3;
    @FXML
    private Label recipesPaneTitle4;
    @FXML
    private Label recipesPaneTitle5;
    @FXML
    private Label recipesPaneTitle6;
    @FXML
    private ImageView recipePaneImage1;
    @FXML
    private ImageView recipePaneImage2;
    @FXML
    private ImageView recipePaneImage3;
    @FXML
    private ImageView recipePaneImage4;
    @FXML
    private ImageView recipePaneImage5;
    @FXML
    private ImageView recipePaneImage6;
    @FXML
    private Label recipePaneDescription1;
    @FXML
    private Label recipePaneDescription2;
    @FXML
    private Label recipePaneDescription3;
    @FXML
    private Label recipePaneDescription4;
    @FXML
    private Label recipePaneDescription5;
    @FXML
    private Label recipePaneDescription6;

    private final DataBaseRecipesHandler databaseHandler;

    public RecipesController() {
        databaseHandler = new DataBaseRecipesHandler();
    }



    /* Button Interactions */
    public void onRecipesDetailButtonClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }





}