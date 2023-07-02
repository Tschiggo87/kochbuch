package com.example.kochbuch.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import com.example.kochbuch.model.RezeptModel;

public class EditController {

    public TextField name;
    public TextField beschreibung;
    public TextField dauer;
    public TextField portion;
    public TextField schwierigkeitsgrad;
    public TextField anweisungen;
    public TextField bild;
    private RezeptModel model;

    @FXML
    private void initialize(){

        model = new RezeptModel();
        onShowValues();

        bindModel();
    }

    private void bindModel(){
        name.textProperty().bindBidirectional(model.nameProperty());
        beschreibung.textProperty().bindBidirectional(model.beschreibungProperty());
        dauer.textProperty().bindBidirectional(dauer.textProperty());
        portion.textProperty().bindBidirectional(portion.textProperty());
        schwierigkeitsgrad.textProperty().bindBidirectional(schwierigkeitsgrad.textProperty());
        anweisungen.textProperty().bindBidirectional(anweisungen.textProperty());
        bild.textProperty().bindBidirectional(bild.textProperty());
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
        model.setBild(null);
        //Durch das Setzen auf 0 geben Sie an, dass die Dauer und die Anzahl der Portionen auf ihren Standardwert zurückgesetzt werden sollen (bei Integer).

    }

}