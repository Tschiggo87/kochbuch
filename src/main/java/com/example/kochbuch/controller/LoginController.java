package com.example.kochbuch.controller;

import com.example.kochbuch.databasehandler.DataBaseRecipesHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import  javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.security.MessageDigest;


//LoginController ist für die Anmeldung zuständig
public class LoginController implements Initializable
{
    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField enterPasswordField;


    //Methode zum Öffnen des CreateAccount Fensters und bindet die Bilder ein.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/resources/images/LoginResources/background1.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("src/main/resources/images/LoginResources/LockLogo1.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    //Login Felder werden überprüft, ob sie leer sind. Wenn nicht, wird die Methode validateLogin() aufgerufen.
    @FXML
    private void loginButtonOnAction(ActionEvent event)
    {
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        } else {
            loginMessageLabel.setText("Bitte Passwort eingeben!");
        }
    }

    //der Cancel Button schließt das Login Fenster.
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    //Methode zum Überprüfen des Logins. Passwort wird gehasht und mit dem gehashten Passwort in der Datenbank verglichen.
    private void validateLogin() {
        DataBaseRecipesHandler dbhandler = new DataBaseRecipesHandler();
        Connection connection = null;
        String hashedPassword = getHashedPassword(enterPasswordField.getText());
        String verifyLogin = "SELECT count(1) FROM Login WHERE username = ? AND password = ?";

        try {
            connection = dbhandler.connect(); // Verbindung zur Datenbank herstellen

            PreparedStatement preparedStatement = connection.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, hashedPassword);

            ResultSet queryResult = preparedStatement.executeQuery();

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Congrats!");
                    createAccountForm();
                } else {
                    loginMessageLabel.setText("Falsches Passwort, bitte wiederholen!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Methode zum Hashen des Passworts.
    private String getHashedPassword(String password) {
        String hashedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : hashBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            hashedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

//Methode zum Erstellen des Registrierungsformulars.
    public void createAccountForm() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/kochbuch/register-view.fxml"));
            Stage registerStage = new Stage();
            //registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 540));
            registerStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}