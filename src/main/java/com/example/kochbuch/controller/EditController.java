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
import javafx.util.converter.NumberStringConverter;

public class EditController {

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
    private void initialize(){

        model = new RezeptModel();
        onShowValues();

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
        name.clear();
        beschreibung.clear();
        dauer.clear();
        portion.clear();
        schwierigkeitsgrad.clear();
        anweisungen.clear();
        zutaten.clear();
        bild.clear();
    }



    @FXML
    public void onSaveBtnClick() {
        // Speichern der Werte aus dem Textfield.
        model.setName(name.getText());
        model.setBeschreibung(beschreibung.getText());
        model.setDauer(Integer.parseInt(dauer.getText()));
        model.setPortion(Integer.parseInt(portion.getText()));
        model.setSchwierigkeitsgrad(schwierigkeitsgrad.getText());
        model.setAnweisungen(anweisungen.getText());
        model.setZutaten(zutaten.getText());
        model.setBild(bild.getText());

        // Hier den Code zum Speichern der Daten oder zum Aktualisieren der Anzeige einfügen

        // Optional: Zeige eine Bestätigungsmeldung an
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Daten wurden gespeichert.", ButtonType.OK);
        alert.showAndWait();
    }


}
