package com.example.kochbuch.controller;

import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.databasehandler.DataTransmitter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.util.List;

public class EditController {

    public TextField recipeName;
    public TextField recipeDescription;
    public TextField recipeTime;
    public TextField recipePortion;
    public TextField recipeDifficulty;
    public TextArea recipeInstruction;
    public TextArea recipeIngredients;
    public TextField recipeImage;
    private RezeptModel model;
    private List<RezeptModel> recipeList;
    private int currentIndex;

    @FXML
    private void initialize(){

        model = new RezeptModel();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        recipeList = databaseHandler.getRezepteFromDatabase();
        currentIndex = 5;

        if (!recipeList.isEmpty()) {
            loadRecipeDetails(recipeList.get(currentIndex));
        }

        bindModel();
    }

    public void loadRecipeDetails(RezeptModel recipeModel) {
        model = recipeModel;
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
        model.setDauer(0); // Setzen Sie den Standardwert für die Dauer auf 0
        model.setPortion(0); // Setzen Sie den Standardwert für die portion auf 0
        model.setSchwierigkeitsgrad(null);
        model.setAnweisungen(null);
        model.setZutaten(null);
        model.setBild(null);
        //Durch das Setzen auf 0 geben Sie an, dass die Dauer und die Anzahl der Portionen auf ihren Standardwert zurückgesetzt werden sollen (bei Integer).

    }

    public void onBackToRecipesDetailBtnClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }


    @FXML
    public void onResetBtnClick() {
        recipeName.clear();
        recipeDescription.clear();
        recipeTime.clear();
        recipePortion.clear();
        recipeDifficulty.clear();
        recipeInstruction.clear();
        recipeIngredients.clear();
        recipeImage.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Daten wurden zurückgesetzt.", ButtonType.OK);
        alert.showAndWait();
    }


    @FXML
    public void onSaveBtnClick() {
        // Speichern der Werte aus dem Textfield.
        model.setName(recipeName.getText());
        model.setBeschreibung(recipeDescription.getText());
        model.setDauer(Integer.parseInt(recipeTime.getText()));
        model.setPortion(Integer.parseInt(recipePortion.getText()));
        model.setSchwierigkeitsgrad(recipeDifficulty.getText());
        model.setAnweisungen(recipeInstruction.getText());
        model.setZutaten(recipeIngredients.getText());
        model.setBild(recipeImage.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Daten wurden gespeichert.", ButtonType.OK);
        alert.showAndWait();
    }


}
