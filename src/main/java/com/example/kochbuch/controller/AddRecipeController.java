package com.example.kochbuch.controller;



import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import com.example.kochbuch.model.RezeptModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.converter.NumberStringConverter;
import java.io.File;

public class AddRecipeController {

    public TextField name;
    public TextField beschreibung;
    public TextField dauer;
    public TextField portion;
    public TextField schwierigkeitsgrad;
    public TextField anweisungen;
    public TextField zutaten;
    public TextField bild;
    private RezeptModel model;

    @FXML
    private void initialize() {
        model = new RezeptModel();
        bindModel();
    }

    private void bindModel() {
        name.textProperty().bindBidirectional(model.nameProperty());
        beschreibung.textProperty().bindBidirectional(model.beschreibungProperty());
        dauer.textProperty().bindBidirectional(model.dauerProperty(), new NumberStringConverter());
        portion.textProperty().bindBidirectional(model.portionProperty(), new NumberStringConverter());
        schwierigkeitsgrad.textProperty().bindBidirectional(model.schwierigkeitsgradProperty());
        anweisungen.textProperty().bindBidirectional(model.anweisungenProperty());
        zutaten.textProperty().bindBidirectional(model.zutatenProperty());
        bild.textProperty().bindBidirectional(model.bildProperty());
    }

    public void onBackToRecipesDetailBtnClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    public void onAddBtnClick() {
        // Speichern der Werte aus den Textfeldern in einer Datenbank oder einem Repository
        // Hier ein Beispiel, wie Sie auf die Daten zugreifen und speichern können
        String recipeName = name.getText();
        String recipeDescription = beschreibung.getText();
        int recipeDuration = Integer.parseInt(dauer.getText());
        int recipePortion = Integer.parseInt(portion.getText());
        String recipeDifficulty = schwierigkeitsgrad.getText();
        String recipeInstructions = anweisungen.getText();
        String recipeIngredients = zutaten.getText();
        String recipeImage = bild.getText();

        // Speichern der Daten in einer Datenbank oder einem Repository
        // Beispiel: repository.saveRecipe(recipeName, recipeDescription, recipeDuration, recipePortion, recipeDifficulty, recipeInstructions, recipeIngredients, recipeImage);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rezept wurde gespeichert.", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void onResetBtnClick() {
        name.clear();
        beschreibung.clear();
        dauer.clear();
        portion.clear();
        schwierigkeitsgrad.clear();
        anweisungen.clear();
        zutaten.clear();
        bild.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Daten wurden zurückgesetzt.", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    public void onAddPictureBtnClick(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Bild auswählen");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Bilddateien", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Alle Dateien", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            bild.setText(selectedFile.getAbsolutePath());
        }
}
