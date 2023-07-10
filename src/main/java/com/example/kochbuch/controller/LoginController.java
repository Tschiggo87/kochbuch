package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import com.example.kochbuch.model.LoginModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;


//LoginController ist für die Anmeldung zuständig
public class LoginController implements Initializable {

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


    private LoginModel loginModel = new LoginModel ();
    private LoginModel editBtn = new LoginModel ();

    // Methode zum Öffnen des CreateAccount Fensters und bindet die Bilder ein.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("src/main/resources/images/LoginResources/background1.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("src/main/resources/images/LoginResources/LockLogo1.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        usernameTextField.textProperty().bindBidirectional(loginModel.usernameTextFieldProperty());
        enterPasswordField.textProperty().bindBidirectional(loginModel.enterPasswordFieldProperty());
    }

    // Login Felder werden überprüft, ob sie leer sind. Wenn nicht, wird die Methode validateLogin() aufgerufen.
    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();
        if (username != null && !username.isBlank() && password != null && !password.isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Benutzername und Passwort eingeben!");
        }
    }


    // Der Cancel Button schließt das Login Fenster.
    public void cancelButtonOnAction() {
        Main.switchToView(StaticViews.WelcomeView);
    }

    @FXML
    protected void onActionSingUpButton()  {
        Main.switchToView(StaticViews.RegisterView);
    }

    @FXML
    private void validateLogin() {
        DatabaseHandler dbhandler = new DatabaseHandler();
        Connection connection = null;
        String hashedPassword = getHashedPassword(loginModel.getEnterPasswordField());
        String verifyLogin = "SELECT count(1), role, profile_image_path FROM Login WHERE username = ? AND password = ?";

        try {
            connection = dbhandler.getConnection(); // Verbindung zur Datenbank herstellen

            PreparedStatement preparedStatement = connection.prepareStatement(verifyLogin);
            preparedStatement.setString(1, loginModel.getUsernameTextField());
            preparedStatement.setString(2, hashedPassword);

            ResultSet queryResult = preparedStatement.executeQuery();

            while (queryResult.next()) {
                int count = queryResult.getInt(1);
                String role = queryResult.getString("role");
                String profileImagePath = queryResult.getString("profile_image_path"); // Bildpfad aus der Datenbank abrufen

                if (count == 1) {
                    MainController.setLoggedInUser(loginModel.getUsernameTextField());
                    loginMessageLabel.setText("Willkommen " + role + "!");
                    Main.switchToView(StaticViews.WelcomeView);
                    System.out.println("willkommen " + role + "!");

                    String resourcePath = "/" + profileImagePath;
                    InputStream imageStream = getClass().getResourceAsStream(resourcePath);
                    if(imageStream == null) {
                        throw new IllegalArgumentException("Resource not found: " + resourcePath);
                    }
                    Image profileImage = new Image(imageStream); // Das geladene Bild als Image-Objekt erstellen
                    MainController.getControllerInstance().setProfileImage(profileImage);

                } else {
                    loginMessageLabel.setText("Falsches Passwort, bitte wiederholen!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Verschlüsseln des Passworts.
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

}
