package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataBaseRecipesHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipesDetailController {

    @FXML
    private Label recipeName;
    @FXML
    private Label recipeDescription;
    @FXML
    private Label recipeInstruction;
    @FXML
    private Label recipeTime;
    @FXML
    private Label recipeDifficulty;
    @FXML
    private Label recipePortion;
    @FXML
    private Label recipeIngredients;
    @FXML
    private ImageView recipeImage;

    private final DataBaseRecipesHandler databaseHandler;

    /*Button Interaktion*/

    public void onEditBtnClick() {
        Main.switchToView(StaticViews.RecipeEditView);
    }

    public RecipesDetailController() {
        databaseHandler = new DataBaseRecipesHandler();
    }

    public void initialize() {
        try {
            Connection connection = databaseHandler.connect();
            String[] recipeInfo = getRecipeInfoFromDatabase(connection);
            showRecipeInfo(recipeInfo);
        } catch (SQLException e) {
            e.printStackTrace();
            // Fehlerbehandlung
        }
    }

    private String[] getRecipeInfoFromDatabase(Connection connection) throws SQLException {
        String[] recipeInfo = new String[7];
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT name, beschreibung, dauer, portion, schwierigkeitsgrad, anweisungen, zutaten FROM Rezepte WHERE RezeptId = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1); //
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                recipeInfo[0] = resultSet.getString("name");
                recipeInfo[1] = resultSet.getString("beschreibung");
                recipeInfo[2] = resultSet.getString("dauer");
                recipeInfo[3] = resultSet.getString("portion");
                recipeInfo[4] = resultSet.getString("schwierigkeitsgrad");
                recipeInfo[5] = resultSet.getString("anweisungen");
                recipeInfo[6] = resultSet.getString("zutaten");
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }

        return recipeInfo;
    }

    private void showRecipeInfo(String[] recipeInfo) {
        recipeName.setText(recipeInfo[0]);
        recipeDescription.setText(recipeInfo[1]);
        recipeTime.setText(recipeInfo[2]);
        recipePortion.setText(recipeInfo[3]);
        recipeDifficulty.setText(recipeInfo[5]);
        recipeInstruction.setText(recipeInfo[4]);
        recipeIngredients.setText(recipeInfo[6]);

    }
}
