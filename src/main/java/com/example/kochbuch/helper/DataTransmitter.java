package com.example.kochbuch.helper;

public class DataTransmitter {


    private static final DataTransmitter instance = new DataTransmitter();

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
