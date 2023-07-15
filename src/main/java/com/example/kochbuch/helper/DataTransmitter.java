package com.example.kochbuch.helper;

public class DataTransmitter {


    private static DataTransmitter instance;

    private int recipeId;

    private DataTransmitter() {}

    public static DataTransmitter getInstance() {
        if (instance == null) {
            instance = new DataTransmitter();
        }
        return instance;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
