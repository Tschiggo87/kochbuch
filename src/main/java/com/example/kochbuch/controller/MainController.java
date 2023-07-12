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

    private static String loggedInUser;

    @FXML
    private Button adminEdit;
    @FXML
    private Button adminAdd;

    @FXML
    private ImageView loginImage;


    public static MainController getControllerInstance() {
        return controllerInstance;
    }

    @FXML
    protected void onRecipesButtonClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

    @FXML
    protected void onEditClick() {
        Main.switchToView(StaticViews.RecipesView);
    }

    @FXML
    protected void onAddClick() {
        Main.switchToView(StaticViews.AddRecipeView);
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

    public void switchContent(Pane root) {
        content.setCenter(root);
    }


    public static void setLoggedInUser(String username) {
        loggedInUser = username;
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public void initialize() {
        controllerInstance = this;
        updateLoggedInUserLabel();
        updateAccountButtonText();
    }

    private static final double ORIGINAL_WIDTH = 30.0; // Ersetzen Sie durch die ursprüngliche Breite
    private static final double ORIGINAL_HEIGHT = 30.0; // Ersetzen Sie durch die ursprüngliche Höhe
    private static final double ENLARGED_WIDTH = 30.0; // Ersetzen Sie durch die gewünschte vergrößerte Breite
    private static final double ENLARGED_HEIGHT = 30.0; // Ersetzen Sie durch die gewünschte vergrößerte Höhe

    public void setProfileImage(Image profileImage) {
        loginImage.setImage(profileImage); // Das übergebene Bild dem ImageView zuweisen
        loginImage.setFitWidth(ENLARGED_WIDTH);
        loginImage.setFitHeight(ENLARGED_HEIGHT);
    }

    public void resetProfileImage() {
        String resourcePath = "/images/icons/user-solid.png";
        InputStream imageStream = getClass().getResourceAsStream(resourcePath);
        if (imageStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        Image defaultImage = new Image(imageStream); // Das Standardbild als Image-Objekt erstellen
        setProfileImage(defaultImage);
        loginImage.setFitWidth(ORIGINAL_WIDTH);
        loginImage.setFitHeight(ORIGINAL_HEIGHT);
    }

    private static void updateLoggedInUserLabel() {
        if (controllerInstance != null && controllerInstance.loggedInUserLabel != null) {
            String loggedInUser = getLoggedInUser();

            if (loggedInUser != null) {
                controllerInstance.loggedInUserLabel.setText(loggedInUser);
            } else {
                controllerInstance.loggedInUserLabel.setText("");
            }
        }
    }

    private static void updateAccountButtonText() {
        if (controllerInstance != null && controllerInstance.accountBtn != null) {
            String loggedInUser = getLoggedInUser();
            if (loggedInUser != null) {
                controllerInstance.accountBtn.setText("Logout");
                controllerInstance.adminEdit.setVisible(loggedInUser.equals("admin")); // Setzen Sie die Sichtbarkeit des adminEdit-Buttons basierend auf dem Benutzernamen
                controllerInstance.adminAdd.setVisible(loggedInUser.equals("admin")); // Setzen Sie die Sichtbarkeit des adminAdd-Buttons basierend auf dem Benutzernamen
            } else {
                controllerInstance.accountBtn.setText("Login");
                MainController.getControllerInstance().resetProfileImage();
                if (Main.getMainController() != null) {
                    Main.switchToView(StaticViews.LoginView);
                } else {
                    System.out.println("Main.mainController is null");
                }
                // Setzen Sie die Sichtbarkeit des adminEdit-Buttons auf false, wenn der Benutzer nicht eingeloggt ist
                controllerInstance.adminEdit.setVisible(false);
                controllerInstance.adminAdd.setVisible(false);
            }
        }
    }

}



  /*  public static void saveImage(String imageName, String imagePath) {
        String sql = "INSERT INTO imagetable (imagename, image) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            File imageFile = new File(imagePath);
            try (FileInputStream fis = new FileInputStream(imageFile)) {
                preparedStatement.setString(1, imageName);
                preparedStatement.setBinaryStream(2, fis, (int) imageFile.length());
                preparedStatement.executeUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hier wird die Methode zum Abrufen der Bilder erstellt
    public static byte[] retrieveImageData(String imageName) {
        String sql = "SELECT image FROM imagetable WHERE imagename = ?";
        byte[] imageData = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, imageName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob imageBlob = resultSet.getBlob("image");
                imageData = imageBlob.getBytes(1, (int) imageBlob.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageData;
    }

   */