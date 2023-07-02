package com.example.kochbuch.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RezeptModel {

    private IntegerProperty rezeptID;
    private StringProperty name;
    private StringProperty beschreibung;
    private IntegerProperty dauer;
    private IntegerProperty portion;
    private StringProperty schwierigkeitsgrad;
    private StringProperty anweisungen;
    private StringProperty zutaten;
    private StringProperty bild;

    public RezeptModel(){
        rezeptID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        beschreibung = new SimpleStringProperty();
        dauer = new SimpleIntegerProperty();
        portion = new SimpleIntegerProperty();
        schwierigkeitsgrad = new SimpleStringProperty();
        anweisungen = new SimpleStringProperty();
        zutaten = new SimpleStringProperty();
        bild = new SimpleStringProperty();
    }

    // Getter and Setter methods
    // RezeptID
    public int getRezeptID() {
        return rezeptID.get();
    }

    public IntegerProperty rezeptIDProperty() {
        return rezeptID;
    }

    public void setRezeptID(int rezeptID) {
        this.rezeptID.set(rezeptID);
    }
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

    public int getPortion() {
        return portion.get();
    }

    public IntegerProperty portionProperty() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion.set(portion);
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

    public String getZutaten() {
        return zutaten.get();
    }

    public StringProperty zutatenProperty() {
        return zutaten;
    }

    public void setZutaten(String zutaten) {
        this.zutaten.set(zutaten);
    }

    public String getBild() {
        return bild.get();
    }

    public StringProperty bildProperty() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild.set(bild);
    }

    @Override
    public String toString() {
        return "RezeptModel{" +
                "  rezeptID=" + rezeptID.get() +
                ", name=" + name.get() +
                ", beschreibung=" + beschreibung.get() +
                ", dauer=" + dauer.get() +
                ", portion=" + portion.get() +
                ", schwierigkeitsgrad=" + schwierigkeitsgrad.get() +
                ", anweisungen=" + anweisungen.get() +
                ", zutaten=" + zutaten.get() +
                ", bild=" + bild.get() + '}';
    }


}
