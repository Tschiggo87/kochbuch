package com.example.kochbuch.controller;

import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.helper.DataTransmitter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.control.TextInputControl;

import java.util.Arrays;
import java.util.List;

public class EditController {

    // UI-Elemente, die in der FXML-Datei definiert sind
    @FXML
    public TextField recipeName;
    public TextField recipeDescription;
    public TextField recipeTime;
    public TextField recipePortion;
    public TextField recipeDifficulty;
    public TextArea recipeInstruction;
    public TextArea recipeIngredients;
    public TextField recipeImage;

    // Instanzvariablen
    private RezeptModel model;

    @FXML
    private void initialize() {
        model = new RezeptModel(); // Erstellen einer neuen Instanz von RezeptModel

        // Datenbankzugriff zum Laden der Rezepte
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<RezeptModel> recipeList = databaseHandler.getRezepteFromDatabase();

        // Laden der aktuellen Rezept-IDs vom DataTransmitter
        int recipeId = DataTransmitter.getInstance().getRecipeId();

        // Laden der Rezept-details in das Modell
        loadRecipeDetails(recipeList.get(recipeId - 1));

        // Binden der Modellwerte an die UI-Elemente
        bindModel();
        textInputs = List.of(
                recipeName,
                recipeDescription,
                recipeTime,
                recipePortion,
                recipeDifficulty,
                recipeInstruction,
                recipeIngredients,
                recipeImage
        );
    }

    // Methode zum Laden der Rezept-details in das Modell
    public void loadRecipeDetails(RezeptModel recipeModel) {
        model = recipeModel;
    }

    // Methode zum Binden der Modellwerte an die UI-Elemente
    private void bindModel() {
        recipeName.textProperty().bindBidirectional(model.nameProperty());
        recipeDescription.textProperty().bindBidirectional(model.beschreibungProperty());
        recipeTime.textProperty().bindBidirectional(model.dauerProperty());
        recipePortion.textProperty().bindBidirectional(model.portionProperty());
        recipeDifficulty.textProperty().bindBidirectional(model.schwierigkeitsgradProperty());
        recipeInstruction.textProperty().bindBidirectional(model.anweisungenProperty());
        recipeIngredients.textProperty().bindBidirectional(model.zutatenProperty());
        recipeImage.textProperty().bindBidirectional(model.bildProperty());
    }

    // Methode zum Wechseln zur Rezept-detailansicht
    public void onBackToRecipesDetailBtnClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    // Methode zum Zurücksetzen der UI-Elemente auf die Standardwerte
    @FXML
    public void onResetBtnClick() {
        initialize();
    }

    // Methode zum Speichern der Modellwerte in die Datenbank
    @FXML
    public void onSaveBtnClick() {

        if (isAnyFieldEmpty()) {
            // Erzeugen eines Warnungsdialogs
            Alert alert = new Alert(Alert.AlertType.WARNING, "Alle Felder müssen ausgefüllt werden!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Speichern des Modells in der Datenbank
        DatabaseHandler databaseHandler = new DatabaseHandler();
        boolean success = databaseHandler.updateRezeptInDatabase(model);

        // Überprüfen des Speichererfolgs
        Alert alert;
        if (success) {
            // Erzeugen eines Informationsdialogs
            alert = new Alert(Alert.AlertType.INFORMATION, "Daten wurden gespeichert.", ButtonType.OK);
        } else {
            // Erzeugen eines Fehlerdialogs
            alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Speichern der Daten.", ButtonType.OK);
        }
        alert.showAndWait();
    }
   public List<TextInputControl> textInputs;
    private boolean isAnyFieldEmpty() {

        return textInputs.stream().anyMatch(input -> input.getText().isEmpty());
    }

}
