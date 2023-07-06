package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.model.RegisterModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.util.Duration;
import javafx.animation.PauseTransition;




public class RegisterController implements Initializable {


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
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    private RegisterModel registerModel;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/main/resources/images/LoginResources/registerLogo.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView = new ImageView();
        shieldImageView.setImage(shieldImage);
        registerModel = new RegisterModel();
        bindPropertiesToModel();
    }


    private void bindPropertiesToModel() {
        firstnameTextField.textProperty().bindBidirectional(registerModel.firstnameTextFieldProperty());
        lastNameTextField.textProperty().bindBidirectional(registerModel.lastNameTextFieldProperty());
        usernameTextField.textProperty().bindBidirectional(registerModel.usernameTextFieldProperty());
        setPasswordField.textProperty().bindBidirectional(registerModel.setPasswordFieldProperty());
        confirmPasswordField.textProperty().bindBidirectional(registerModel.confirmPasswordFieldProperty());
    }
    private void onShowValues() {
        System.out.println(registerModel.toString());
    }


    //Button aktion zum Absenden der anmeldedaten
    public void registerButtonOnAction(ActionEvent event) {
        String firstName = registerModel.getFirstnameTextField();
        String lastName = registerModel.getLastNameTextField();
        String userName = registerModel.getUsernameTextField();
        String password = registerModel.getSetPasswordField();

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            registrationMessageLabel.setText("Bitte füllen Sie alle Felder aus.");
        } else if (checkUsernameExists()) {
            registrationMessageLabel.setText("Der Benutzername ist bereits vorhanden.");
        } else if (!setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registrationMessageLabel.setText("Die Passwörter stimmen nicht überein.");
        } else {
            registerUser();
            registrationMessageLabel.setText("Anmeldung erfolgreich!");

            //Es wird eine Pause von 2,5 Sekunden eingefügt, bevor der LoginView aufgerufen wird, damit der Nutzer die Meldung "Anmeldung erfolgreich" lesen kann.
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(e -> Main.switchToView(StaticViews.LoginView));
            pause.play();
        }
    }


    //Überprüfung, ob der Benutzername bereits existiert
    private boolean checkUsernameExists() {
        String username = registerModel.getUsernameTextField();
        boolean usernameExists = false;
        DatabaseHandler dbhandler = new DatabaseHandler();
        Connection connection = null;
        String checkUsernameQuery = "SELECT COUNT(1) FROM Login WHERE username = ?";

        try {
            connection = dbhandler.getConnection();
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


    //Button aktion zum Schließen des Fensters, beziehungsweise wird hier die WelcomeView aufgerufen
    public void closeButtonOnAction() {
        Main.switchToView(StaticViews.WelcomeView);
    }


    public void registerUser() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connection = handler.getConnection();

        String firstName = registerModel.getFirstnameTextField();
        String lastName = registerModel.getLastNameTextField();
        String userName = registerModel.getUsernameTextField();
        String password = registerModel.getSetPasswordField();
        String profileImage = registerModel.getProfileImagePath();
        String hashedPassword = hashPassword(password);

        String insertQuery = "INSERT INTO Login (firstname, lastname, username, password, profile_image_path) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, hashedPassword);
            preparedStatement.setString(5, profileImage);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        onShowValues();
    }


    //das Passwort wird für das Speichern in der Datenbank gehasht(SHA-256)
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

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
