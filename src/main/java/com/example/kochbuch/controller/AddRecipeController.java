
package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.model.RezeptModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class AddRecipeController {
    public TextField recipeName;
    public TextField recipeDescription;
    public TextField recipeTime;
    public TextField recipePortion;
    public TextField recipeDifficulty;
    public TextArea recipeInstruction;
    public TextArea recipeIngredients;
    public TextField recipeImage;
    private RezeptModel model;

    @FXML
    private void initialize() {
        model = new RezeptModel();
        bindModel();
    }

    private void bindModel() {
        recipeName.textProperty().bindBidirectional(model.nameProperty());
        recipeDescription.textProperty().bindBidirectional(model.beschreibungProperty());
        recipeTime.textProperty().bindBidirectional(model.dauerProperty(), new NumberStringConverter());
        recipePortion.textProperty().bindBidirectional(model.portionProperty(), new NumberStringConverter());
        recipeDifficulty.textProperty().bindBidirectional(model.schwierigkeitsgradProperty());
        recipeInstruction.textProperty().bindBidirectional(model.anweisungenProperty());
        recipeIngredients.textProperty().bindBidirectional(model.zutatenProperty());
        recipeImage.textProperty().bindBidirectional(model.bildProperty());
    }

    public void onShowValues() {
        System.out.println(model.toString());
    }

    public void onReset() {
        model.setName(null);
        model.setBeschreibung(null);
        model.setDauer(0);
        model.setPortion(0);
        model.setSchwierigkeitsgrad(null);
        model.setAnweisungen(null);
        model.setZutaten(null);
        model.setBild(null);
    }

    public void onBackToRecipesDetailBtnClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onSaveNewRecipeBtnClick() {
        // Speichern des Rezepts in der Datenbank
        DatabaseHandler databaseHandler = new DatabaseHandler();
        boolean success = databaseHandler.addRezeptToDatabase(model);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rezept wurde erfolgreich hinzugefügt.", ButtonType.OK);
            alert.showAndWait();
            // Zurück zur Rezeptübersicht wechseln
            Main.switchToView(StaticViews.RecipesDetailView);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Hinzufügen des Rezepts.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}