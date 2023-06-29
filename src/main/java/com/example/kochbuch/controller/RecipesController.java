package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
public class RecipesController {

    public void onRecipesDetailButtonClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }


}