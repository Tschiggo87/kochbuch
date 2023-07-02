package com.example.kochbuch.model;

public class Rezept {
    private int rezeptId;
    private String name;
    private String beschreibung;
    private String kochzeit;
    private String vorbereitungszeit;
    private String gesamtzeit;
    private int portion;
    private String schwierigkeitsgrad;
    private String anweisungen;

    // Getter and Setter methods
    // RezeptID
    public int getRezeptId() {
        return rezeptId;
    }

    public void setRezeptId(int rezeptId) {
        this.rezeptId = rezeptId;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Beschreibung
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    // Kochzeit
    public String getKochzeit() {
        return kochzeit;
    }

    public void setKochzeit(String kochzeit) {
        this.kochzeit = kochzeit;
    }

    // Vorbereitungszeit
    public String getVorbereitungszeit() {
        return vorbereitungszeit;
    }

    public void setVorbereitungszeit(String vorbereitungszeit) {
        this.vorbereitungszeit = vorbereitungszeit;
    }

    // Gesamtzeit
    public String getGesamtzeit() {
        return gesamtzeit;
    }

    public void setGesamtzeit(String gesamtzeit) {
        this.gesamtzeit = gesamtzeit;
    }

    // Anzahl der Portionen
    public int getportion() {
        return portion;
    }

    public void setportion(int portion) {
        this.portion = portion;
    }

    // Schwierigkeitsgrad
    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    // Anweisungen
    public String getAnweisungen() {
        return anweisungen;
    }

    public void setAnweisungen(String anweisungen) {
        this.anweisungen = anweisungen;
    }
}
