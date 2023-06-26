package com.example.kochbuch.controller;

import com.example.kochbuch.databasehandler.DatabaseHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView shieldImageView;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label confirmPasswordLabel;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("images/registerLogo.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }

    public void registerButtonOnAction(ActionEvent event){
        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            // confirmPasswordLabel.setText("Passwörter stimmen überein!");
            registrationMessageLabel.setText("Anmeldung Erfolgreich!");
        } else {
            confirmPasswordLabel.setText("Passwörter stimmen nicht überein!");
        }


    }
    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser() {

        DatabaseHandler handler = new DatabaseHandler();
        Connection connection = handler.getConnection();

        String firstName = firstnameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userName = usernameTextField.getText();
        String password = setPasswordField.getText();

        String insertFields = "INSERT INTO Login (firstname, lastname, username, password) VALUES ('";
        String insertValues = firstName + "','" + lastName + "','" + userName + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("Anmeldung Erfolgreich!");


        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    //SELECT lastname, firstname, username, password FROM users;
}
