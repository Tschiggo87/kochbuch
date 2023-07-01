package com.example.kochbuch.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {
    private StringProperty usernameTextField;
    private StringProperty enterPasswordField;

    public LoginModel() {
        this.usernameTextField = new SimpleStringProperty();
        this.enterPasswordField = new SimpleStringProperty();
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

    public String getEnterPasswordField() {
        return enterPasswordField.get();
    }

    public StringProperty enterPasswordFieldProperty() {
        return enterPasswordField;
    }

    public void setEnterPasswordField(String enterPasswordField) {
        this.enterPasswordField.set(enterPasswordField);
    }

    @Override
    public String toString(){
        return "LoginModel{" +
                "Benutzername=" + usernameTextField.get() +
                ", Passwort=" + enterPasswordField.get() + "}";
    }
}
