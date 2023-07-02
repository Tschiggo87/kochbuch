package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.model.RezeptModel;
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
    private final RezeptModel recipeModel;

    public RecipesDetailController() {
        databaseHandler = new DataBaseRecipesHandler();
        recipeModel = new RezeptModel();
    }

    public void initialize() {
        try {
            Connection connection = databaseHandler.connect();
            loadRecipeInfoFromDatabase(connection);
            showRecipeInfo();
        } catch (SQLException e) {
            e.printStackTrace();
            // Fehlerbehandlung
        }
    }

    private void loadRecipeInfoFromDatabase(Connection connection) throws SQLException {
        String query = "SELECT name, beschreibung, dauer, portion, anweisungen, schwierigkeitsgrad, zutaten, bild FROM Rezepte WHERE RezeptId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, 5); // Setze die gewünschte RezeptId

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            recipeModel.setName(resultSet.getString("name"));
            recipeModel.setBeschreibung(resultSet.getString("beschreibung"));
            recipeModel.setDauer(resultSet.getInt("dauer"));
            recipeModel.setPortion(resultSet.getInt("portion"));
            recipeModel.setAnweisungen(resultSet.getString("anweisungen"));
            recipeModel.setSchwierigkeitsgrad(resultSet.getString("schwierigkeitsgrad"));
            recipeModel.setZutaten(resultSet.getString("zutaten"));
            //recipeModel.setBild(resultSet.getString("bild"));
        }

        resultSet.close();
        statement.close();
    }

    private void showRecipeInfo() {
        recipeName.setText(recipeModel.getName());
        recipeDescription.setText(recipeModel.getBeschreibung());
        recipeTime.setText(Integer.toString(recipeModel.getDauer()));
        recipePortion.setText(Integer.toString(recipeModel.getPortion()));
        recipeInstruction.setText(recipeModel.getAnweisungen());
        recipeDifficulty.setText(recipeModel.getSchwierigkeitsgrad());
        recipeIngredients.setText(recipeModel.getZutaten());
    }


    /* Weitere Methoden für die Interaktion mit der Benutzeroberfläche */


    public void onEditBtnClick() {
        Main.switchToView(StaticViews.RecipeEditView);
    }

    public void onBackToRecipesBtnClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

}
