package com.example.kochbuch.databasehandler;

public class DataTransmitter {
    private static DataTransmitter instance = new DataTransmitter();

    private int recipeId;

    private DataTransmitter() {}

    public static DataTransmitter getInstance() {
        return instance;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}

