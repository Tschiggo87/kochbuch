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



/**
 * Die Klasse RegisterController ist zuständig für die Registrierung neuer Benutzer.
 * Sie prüft, ob die eingegebenen Anmeldedaten korrekt sind und führt entsprechende Aktionen aus.
 * Dieser Controller steuert auch die Darstellung der Registrierungsseite, inklusive der Hintergrundbilder und Icons.
 */
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

/** Hierm wird initialisiert, dass das Logo angezeigt wird und die Textfelder mit dem Model verbunden werden. */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("src/main/resources/images/LoginResources/registerLogo.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView = new ImageView();
        shieldImageView.setImage(shieldImage);
        registerModel = new RegisterModel();
        bindPropertiesToModel();
    }

    /** Hier wird die Registrierung abgebrochen und das Login-Fenster wieder angezeigt. */
    private void bindPropertiesToModel() {
        firstnameTextField.textProperty().bindBidirectional(registerModel.firstnameTextFieldProperty());
        lastNameTextField.textProperty().bindBidirectional(registerModel.lastNameTextFieldProperty());
        usernameTextField.textProperty().bindBidirectional(registerModel.usernameTextFieldProperty());
        setPasswordField.textProperty().bindBidirectional(registerModel.setPasswordFieldProperty());
        confirmPasswordField.textProperty().bindBidirectional(registerModel.confirmPasswordFieldProperty());
    }


    /** Hier wird die Registrierung abgebrochen und das Login-Fenster wieder angezeigt. */
    private void onShowValues() {
        System.out.println(registerModel.toString());
    }


    /** Hier wird die Registrierung abgebrochen und das Login-Fenster wieder angezeigt. */
    public void registerButtonOnAction(ActionEvent event) {
        String firstName = registerModel.getFirstnameTextField();
        String lastName = registerModel.getLastNameTextField();
        String userName = registerModel.getUsernameTextField();
        String password = registerModel.getSetPasswordField();

        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                userName == null || userName.isEmpty() ||
                password == null || password.isEmpty()) {
            registrationMessageLabel.setText("Bitte füllen Sie alle Felder aus.");
            registrationMessageLabel.setStyle("-fx-text-fill: red;");
        } else if (checkUsernameExists()) {
            registrationMessageLabel.setText("Der Benutzername ist bereits vorhanden.");
            registrationMessageLabel.setStyle("-fx-text-fill: red;");

        } else if (password == null || !password.equals(confirmPasswordField.getText())) {
            registrationMessageLabel.setText("Die Passwörter stimmen nicht überein.");
            registrationMessageLabel.setStyle("-fx-text-fill: red;");
        } else {
            registerUser();
            registrationMessageLabel.setText("Anmeldung erfolgreich!");
            registrationMessageLabel.setStyle("-fx-text-fill: green;");

            //Es wird eine Pause von 2,5 Sekunden eingefügt, bevor der LoginView aufgerufen wird, damit der Nutzer die Meldung "Anmeldung erfolgreich" lesen kann.
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(e -> Main.switchToView(StaticViews.LoginView));
            pause.play();
        }
    }



    /** Hier wird das Passwort gehasht. */
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


    /** Hier wird die View geschlossen und zur Welcomeview gewechselt. */
    public void closeButtonOnAction() {
        Main.switchToView(StaticViews.WelcomeView);
    }

    /** Der User wird registriert. */
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


    /** Hier wird das Passwort gehasht. */
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
