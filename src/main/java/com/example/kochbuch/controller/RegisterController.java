package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DataBaseRecipesHandler;
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
import java.sql.*;
import java.util.ResourceBundle;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    //Bild wird der Scene eingebunden
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/main/resources/images/LoginResources/registerLogo.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }

    //Aufnahme der Passwörter und Überprüfung, ob diese übereinstimmen. Username wird überprüft ob dieser bereits vorhanden ist.
    public void registerButtonOnAction(ActionEvent event) {
        String firstName = firstnameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userName = usernameTextField.getText();
        String password = setPasswordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            registrationMessageLabel.setText("Bitte füllen Sie alle Felder aus.");
        } else if (checkUsernameExists(userName)) {
            registrationMessageLabel.setText("Der Benutzername ist bereits vorhanden.");
        } else if (!setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registrationMessageLabel.setText("Die Passwörter stimmen nicht überein.");
        } else {
            registerUser();
            registrationMessageLabel.setText("Anmeldung erfolgreich!");
            Main.switchToView(StaticViews.LoginView);
        }
    }

    //Methode zum Usernamen überprüfen
    private boolean checkUsernameExists(String username) {
            boolean usernameExists = false;
            DataBaseRecipesHandler dbhandler = new DataBaseRecipesHandler();
            Connection connection = null;
            String checkUsernameQuery = "SELECT COUNT(1) FROM Login WHERE username = ?";

            try {
                connection = dbhandler.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(checkUsernameQuery);
                preparedStatement.setString(1, username);
                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next()) {
                    int count = queryResult.getInt(1);
                    if (count == 1) {
                        usernameExists = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            return usernameExists;
        }



    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    //Methode zum registrieren des Users
    public void registerUser() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connection = handler.getConnection();

        String firstName = firstnameTextField.getText();
        String lastName = lastNameTextField.getText();
        String userName = usernameTextField.getText();
        String password = setPasswordField.getText();

        // Hashen Sie das Passwort
        String hashedPassword = hashPassword(password);

        String insertQuery = "INSERT INTO Login (firstname, lastname, username, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, hashedPassword);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                registrationMessageLabel.setText("Anmeldung Erfolgreich!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //Methode zum Hashen des Passworts
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Konvertieren Sie das Byte-Array in eine Hexadezimal-Zeichenkette
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
    }


