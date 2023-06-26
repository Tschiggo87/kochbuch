package com.example.kochbuch.controller;


import com.example.kochbuch.databasehandler.DatabaseHandler;
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/LoginResources/background1.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/LoginResources/LockLogo1.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }


    @FXML
    private void loginButtonOnAction(ActionEvent event)
    {
        if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        } else {
            loginMessageLabel.setText("Bitte Passwort eingeben!");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void validateLogin(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection = databaseHandler.getConnection();

        String verifyLogin = "SELECT count(1) FROM Login WHERE username = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    // loginMessageLabel.setText("Congrats!");
                    createAccountForm();
                } else {
                    loginMessageLabel.setText("Falsches Passwort, bitte wiederholen!");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("register-view.fxml"));
            Stage registerStage = new Stage();
            //registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 540));
            registerStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}