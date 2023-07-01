package com.example.kochbuch.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RezeptModel {
    private StringProperty name;
    private StringProperty beschreibung;
    private IntegerProperty dauer;
    private IntegerProperty anzahlDerPortionen;
    private StringProperty schwierigkeitsgrad;
    private StringProperty anweisungen;

    public RezeptModel(){
        name = new SimpleStringProperty();
        beschreibung = new SimpleStringProperty();
        dauer = new SimpleIntegerProperty();
        anzahlDerPortionen = new SimpleIntegerProperty();
        schwierigkeitsgrad = new SimpleStringProperty();
        anweisungen = new SimpleStringProperty();
    }

    // Getter and Setter methods
    // RezeptID

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBeschreibung() {
        return beschreibung.get();
    }

    public StringProperty beschreibungProperty() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung.set(beschreibung);
    }

    public int getDauer() {
        return dauer.get();
    }

    public IntegerProperty dauerProperty() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer.set(dauer);
    }

    public int getAnzahlDerPortionen() {
        return anzahlDerPortionen.get();
    }

    public IntegerProperty anzahlDerPortionenProperty() {
        return anzahlDerPortionen;
    }

    public void setAnzahlDerPortionen(int anzahlDerPortionen) {
        this.anzahlDerPortionen.set(anzahlDerPortionen);
    }

    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad.get();
    }

    public StringProperty schwierigkeitsgradProperty() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad.set(schwierigkeitsgrad);
    }

    public String getAnweisungen() {
        return anweisungen.get();
    }

    public StringProperty anweisungenProperty() {
        return anweisungen;
    }

    public void setAnweisungen(String anweisungen) {
        this.anweisungen.set(anweisungen);
    }

    @Override
    public String toString() {
        return "RezeptModel{" + "name=" + name.get() + ", beschreibung=" + beschreibung.get() + ", dauer=" + dauer.get() + ", anzahlDerPortionen=" + anzahlDerPortionen.get() + ", schwierigkeitsgrad=" + schwierigkeitsgrad.get() + ", anweisungen=" + anweisungen.get() + '}';
    }
}
