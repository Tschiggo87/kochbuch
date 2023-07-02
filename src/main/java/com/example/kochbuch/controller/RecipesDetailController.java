package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataBaseRecipesHandler;
import com.example.kochbuch.model.Rezept;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    private static MainController controllerInstance;
    private final DataBaseRecipesHandler databaseHandler;

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
        String[] recipeInfo = new String[6];
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT Name, Beschreibung, Anweisungen, Gesamtzeit, Portionen, Schwierigkeit FROM Rezepte WHERE RezeptId = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1); //
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                recipeInfo[0] = resultSet.getString("Name");
                recipeInfo[1] = resultSet.getString("Beschreibung");
                recipeInfo[2] = resultSet.getString("Anweisungen");
                recipeInfo[3] = resultSet.getString("Gesamtzeit");
                recipeInfo[4] = resultSet.getString("Portionen");
                recipeInfo[5] = resultSet.getString("Schwierigkeit");
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
        recipeInstruction.setText(recipeInfo[2]);
        recipeTime.setText(recipeInfo[3]);
        recipePortion.setText(recipeInfo[4]);
        recipeDifficulty.setText(recipeInfo[5]);

    }
}
