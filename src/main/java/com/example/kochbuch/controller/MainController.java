package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private BorderPane content;

    @FXML
    protected void onRecipesButtonClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

    protected void onRecipesDetailButtonClick() {
        Main.switchToView(StaticViews.RecipeDetailView);
    }

    /**
     *
     * @param root The Root Component of the view
     */
    public void switchContent(Pane root) {
        content.setCenter(root);
    }
}