package com.example.kochbuch.model;

import  javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RegisterModel {
    private StringProperty firstnameTextField;
    private StringProperty lastNameTextField;
    private StringProperty usernameTextField;
    private StringProperty setPasswordField;
    private StringProperty confirmPasswordField;
    private StringProperty profileImage;
    private StringProperty profileImagePath;


    // Konstruktor für die Klasse RegisterModel
    public RegisterModel(){
        this.firstnameTextField = new SimpleStringProperty();
        this.lastNameTextField = new SimpleStringProperty();
        this.usernameTextField = new SimpleStringProperty();
        this.setPasswordField = new SimpleStringProperty();
        this.confirmPasswordField = new SimpleStringProperty();
        this.profileImage = new SimpleStringProperty();
        this.profileImagePath = new SimpleStringProperty();
        this.profileImagePath.set(getRandomProfileImage());
    }


    // Model für die vergabe der UserIcons. Wird zufällig generiert
    private static final List<String> PROFILE_IMAGES = new ArrayList<>();

    static {
        PROFILE_IMAGES.add("images/profile_images/profile1.png");
        PROFILE_IMAGES.add("images/profile_images/profile2.png");
        PROFILE_IMAGES.add("images/profile_images/profile3.png");
        PROFILE_IMAGES.add("images/profile_images/profile4.png");
        PROFILE_IMAGES.add("images/profile_images/profile5.png");
        PROFILE_IMAGES.add("images/profile_images/profile6.png");
        PROFILE_IMAGES.add("images/profile_images/profile7.png");
        PROFILE_IMAGES.add("images/profile_images/profile8.png");
        PROFILE_IMAGES.add("images/profile_images/profile9.png");
        PROFILE_IMAGES.add("images/profile_images/profile10.png");
    }

    // Methode für die zufällige Auswahl eines UserIcons
    private String getRandomProfileImage() {
        Random random = new Random();
        int index = random.nextInt(PROFILE_IMAGES.size());
        return PROFILE_IMAGES.get(index);
    }


    // Getter und Setter für die Klasse RegisterModel
    public String getFirstnameTextField() {
        return firstnameTextField.get();
    }

    public StringProperty firstnameTextFieldProperty() {
        return firstnameTextField;
    }

    public void setFirstnameTextField(String firstnameTextField) {
        this.firstnameTextField.set(firstnameTextField);
    }

    public String getLastNameTextField() {
        return lastNameTextField.get();
    }

    public StringProperty lastNameTextFieldProperty() {
        return lastNameTextField;
    }

    public void setLastNameTextField(String lastNameTextField) {
        this.lastNameTextField.set(lastNameTextField);
    }

    public String getUsernameTextField() {
        return usernameTextField.get();
    }

    public StringProperty usernameTextFieldProperty() {
        return usernameTextField;
    }

    public void setUsernameTextField(String usernameTextField) {
        this.usernameTextField.set(usernameTextField);
    }

    public String getSetPasswordField() {
        return setPasswordField.get();
    }

    public StringProperty setPasswordFieldProperty() {
        return setPasswordField;
    }

    public void setSetPasswordField(String setPasswordField) {
        this.setPasswordField.set(setPasswordField);
    }

    public String getConfirmPasswordField() {
        return confirmPasswordField.get();
    }

    public StringProperty confirmPasswordFieldProperty() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField(String confirmPasswordField) {
        this.confirmPasswordField.set(confirmPasswordField);
    }

    public String getProfileImage() {  // Getter hinzugefügt
        return profileImage.get();
    }

    public StringProperty profileImageProperty() {  // Property-Methode hinzugefügt
        return profileImage;
    }

    public void setProfileImage(String profileImage) {  // Setter hinzugefügt
        this.profileImage.set(profileImage);
    }

    public String getProfileImagePath() {
        return profileImagePath.get();
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath.set(profileImagePath);
    }


    // Methode für die Ausgabe der Daten zur Kontrolle
    @Override
    public String toString(){
        return "LoginModel{" +
                "Vorname=" + firstnameTextField.get() +
                ", Nachname=" + lastNameTextField.get() +
                ", Benutzername=" + usernameTextField.get() +
                ", Passwort=" + setPasswordField.get() + "}";
    }
}
