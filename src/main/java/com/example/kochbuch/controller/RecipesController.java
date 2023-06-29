package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RecipesController {

    public void onRecipesDetailButtonClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }




}