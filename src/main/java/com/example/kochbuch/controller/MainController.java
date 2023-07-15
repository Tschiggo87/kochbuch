package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.helper.UserSession;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.InputStream;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Der Controller für die Hauptansicht der Anwendung.
 */
public class MainController implements Observer {

    @FXML
    private AnchorPane content;

    @FXML
    private Button accountBtn;

    @FXML
    private Label loggedInUserLabel;

    private static MainController controllerInstance;

    @FXML
    private Button adminEdit;

    @FXML
    private Button adminAdd;

    @FXML
    private Button userFavorites;

    @FXML
    private ImageView userFavoritesIcon;

    @FXML
    private ImageView loginImage;

    @FXML
    private ImageView editIcon;

    @FXML
    private ImageView addIcon;

    /**
     * Gibt eine Instanz des MainControllers zurück.
     *
     * @return Die Instanz des MainControllers.
     */
    public static MainController getControllerInstance() {
        return controllerInstance;
    }

    /**
     * Wird aufgerufen, wenn der "Rezepte" Button geklickt wird.
     * Wechselt zur Ansicht "RecipesView".
     */
    @FXML
    protected void onRecipesButtonClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

    /**
     * Wird aufgerufen, wenn der "Bearbeiten" Button geklickt wird.
     * Wechselt zur Ansicht "RecipesView".
     */
    @FXML
    protected void onEditClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

    /**
     * Wird aufgerufen, wenn der "Hinzufügen" Button geklickt wird.
     * Wechselt zur Ansicht "AddRecipeView".
     */
    @FXML
    protected void onAddClick() {
        Main.switchToView(StaticViews.AddRecipeView);
    }

    /**
     * Wird aufgerufen, wenn der "Favoriten" Button geklickt wird.
     * Wechselt zur Ansicht "FavoritesView".
     */
    @FXML
    protected void onFavoritesButtonClick() {
        Main.switchToView(StaticViews.FavoritesView);
    }

    /**
     * Wird aufgerufen, wenn der "Account" Button geklickt wird.
     * Überprüft den angemeldeten Benutzer und wechselt zur "LoginView" oder meldet den Benutzer ab.
     */
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

    /**
     * Wechselt den angezeigten Inhalt auf die übergebene Pane.
     *
     * @param root Die Pane, die angezeigt werden soll.
     */
    public void switchContent(Pane root) {
        StackPane stackPane = new StackPane(root);
        StackPane.setAlignment(root, Pos.CENTER);
        content.getChildren().clear();
        content.getChildren().add(stackPane);
    }

    /**
     * Setzt den angemeldeten Benutzer.
     *
     * @param username Der Benutzername des angemeldeten Benutzers.
     */
    public static void setLoggedInUser(String username) {
        UserSession.getInstance().setLoggedInUser(username);
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }

    /**
     * Gibt den angemeldeten Benutzer zurück.
     *
     * @return Der Benutzername des angemeldeten Benutzers.
     */
    public static String getLoggedInUser() {
        return UserSession.getInstance().getLoggedInUser();
    }

    /**
     * Wird beim Initialisieren des Controllers aufgerufen.
     * Fügt den MainController als Observer zur UserSession hinzu und aktualisiert die Anzeige.
     */
    public void initialize() {
        controllerInstance = this;
        UserSession.getInstance().addObserver(this);
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }

    private static final double ORIGINAL_WIDTH = 30.0;
    private static final double ORIGINAL_HEIGHT = 30.0;
    private static final double ENLARGED_WIDTH = 30.0;
    private static final double ENLARGED_HEIGHT = 30.0;

    /**
     * Setzt das Profilbild.
     *
     * @param profileImage Das Profilbild als Image-Objekt.
     */
    public void setProfileImage(Image profileImage) {
        loginImage.setImage(profileImage);
        loginImage.setFitWidth(ENLARGED_WIDTH);
        loginImage.setFitHeight(ENLARGED_HEIGHT);
    }

    /**
     * Setzt das Profilbild zurück auf das Standardbild.
     */
    public void resetProfileImage() {
        String resourcePath = "/images/icons/user-solid.png";
        InputStream imageStream = getClass().getResourceAsStream(resourcePath);
        if (imageStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        Image defaultImage = new Image(imageStream);
        setProfileImage(defaultImage);
        loginImage.setFitWidth(ORIGINAL_WIDTH);
        loginImage.setFitHeight(ORIGINAL_HEIGHT);
    }

    /**
     * Wird aufgerufen, wenn sich die UserSession ändert.
     * Aktualisiert die Anzeige.
     */

    private static void updateLoggedInUserLabel() {
        if (controllerInstance != null && controllerInstance.loggedInUserLabel != null) {
            String loggedInUser = getLoggedInUser();
            controllerInstance.loggedInUserLabel.setText(Objects.requireNonNullElse(loggedInUser, ""));
        }
    }

    /**
     * Aktualisiert den Text des "Account" Buttons.
     */

    private static void updateAccountButtonText() {
        if (controllerInstance != null && controllerInstance.accountBtn != null) {
            String loggedInUser = getLoggedInUser();
            if (loggedInUser != null) {
                controllerInstance.accountBtn.setText("Logout");
                setUserBtnsVisible(loggedInUser.equals("admin"), !loggedInUser.equals("admin"));
            } else {
                controllerInstance.accountBtn.setText("Login");
                MainController.getControllerInstance().resetProfileImage();
                if (Main.getMainController() != null) {
                    Main.switchToView(StaticViews.LoginView);
                } else {
                    System.out.println("Main.mainController is null");
                }
                setUserBtnsVisible(false, false);
            }
        }
    }

    private static void setUserBtnsVisible(boolean isAdmin, boolean isUser) {
        controllerInstance.adminEdit.setVisible(isAdmin);
        controllerInstance.adminAdd.setVisible(isAdmin);
        controllerInstance.userFavorites.setVisible(isUser);
        controllerInstance.userFavoritesIcon.setVisible(isUser);
        controllerInstance.editIcon.setVisible(isAdmin);
        controllerInstance.addIcon.setVisible(isAdmin);
    }

    /**
     * Aktualisiert die Ansicht basierend auf Änderungen in der UserSession.
     *
     * @param o   Das Observable-Objekt.
     * @param arg Das Argument, das an den Observer übergeben wird.
     */

    @Override
    public void update(Observable o, Object arg) {
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }
}
