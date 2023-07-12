package com.example.kochbuch.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private ImageView welcomeImageView;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File welcomeFile = new File("src/main/resources/images/backgroundWelcome1-split (7).jpg");
        Image welocomeImage = new Image(welcomeFile.toURI().toString());
        welcomeImageView.setImage( welocomeImage);
    }
}

