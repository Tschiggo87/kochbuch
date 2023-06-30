package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.InputStream;

public class MainController {
    @FXML
    private BorderPane content;

    @FXML
    private Button accountBtn;

    @FXML
    private Label loggedInUserLabel;


    private static MainController controllerInstance;

    public static MainController getControllerInstance() {
        return controllerInstance;
    }

    @FXML
    protected void onRecipesButtonClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

    protected void onRecipesDetailButtonClick() {
        Main.switchToView(StaticViews.RecipesDetailView);
    }

    @FXML
    protected void onAccountButtonClick() {
        String loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            // Benutzer ausloggen
            setLoggedInUser(null);
        } else {
            Main.switchToView(StaticViews.LoginView);
        }
    }


    //
    public void switchContent(Pane root) {
                content.setCenter(root);
    }



    private static String loggedInUser;
    @FXML
    private ImageView loginImage;

    public static void setLoggedInUser(String username) {
        loggedInUser = username;
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }

    // Methode zum aktualisieren des Labels, welches den eingeloggten Benutzer anzeigt
    public static String getLoggedInUser() {
        return loggedInUser;
    }


    public void initialize() {
        controllerInstance = this;
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }


    //Variablen zum veränder der grösse des Profilbildes, da ich ein grösseres Bild für die Anzeige des Profilbildes möchte
    private static final double ORIGINAL_WIDTH = 30.0;
    private static final double ORIGINAL_HEIGHT = 30.0;
    private static final double ENLARGED_WIDTH = 60.0;
    private static final double ENLARGED_HEIGHT = 60.0;



    public void setProfileImage(Image profileImage) {
        loginImage.setImage(profileImage); // Das übergebene Bild dem ImageView zuweisen
        loginImage.setFitWidth(ENLARGED_WIDTH);
        loginImage.setFitHeight(ENLARGED_HEIGHT);
    }


    //Methode zum zurücksetzen des Profilbildes
    public void resetProfileImage() {
        String resourcePath = "/images/icons/user-solid.png";
        InputStream imageStream = getClass().getResourceAsStream(resourcePath);
        if(imageStream == null) {
            throw new IllegalArgumentException("Resource nicht gefunden " + resourcePath);
        }
        Image defaultImage = new Image(imageStream);
        setProfileImage(defaultImage);
        loginImage.setFitWidth(ORIGINAL_WIDTH);
        loginImage.setFitHeight(ORIGINAL_HEIGHT);
    }


    //Methode zum aktualisieren des Profilbildes, beziehungsweise wird das profilicon durch das Profilbild ersetzt
    private static void updateLoggedInUserLabel() {
        if (controllerInstance != null && controllerInstance.loggedInUserLabel != null) {
            String loggedInUser = getLoggedInUser();
            if (loggedInUser != null) {
                controllerInstance.loggedInUserLabel.setText( "                       " + loggedInUser);
            } else {
                controllerInstance.loggedInUserLabel.setText("");
            }
        }
    }


    //Methode zum aktualisieren des Textes des Accountbuttons, beziehungsweise wird der Text des Accountbuttons durch "Logout" ersetzt
    private static void updateAccountButtonText() {
        if (controllerInstance != null && controllerInstance.accountBtn != null) {
            String loggedInUser = getLoggedInUser();
            if (loggedInUser != null) {
                controllerInstance.accountBtn.setText("Logout");
            } else {
                controllerInstance.accountBtn.setText("Login");
                MainController.getControllerInstance().resetProfileImage();
                if (Main.getMainController() != null) {
                    Main.switchToView(StaticViews.LoginView);
                } else {
                    System.out.println("Main.mainController is null");
                }
            }
        }
    }

}