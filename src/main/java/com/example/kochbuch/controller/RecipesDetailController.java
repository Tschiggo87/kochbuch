package com.example.kochbuch.controller;

import com.example.kochbuch.Main;
import com.example.kochbuch.StaticViews;
import com.example.kochbuch.helper.DataTransmitter;
import com.example.kochbuch.model.RezeptModel;
import com.example.kochbuch.databasehandler.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RecipesDetailController {

    @FXML
    private Label recipeName;

    @FXML
    private Label recipeDescription;

    @FXML
    private Label recipeInstruction;

    @FXML
    private Label recipeTime;

    @FXML
    private Label recipeDifficulty;

    @FXML
    private Label recipePortion;

    @FXML
    private Label recipeIngredients;

    @FXML
    private ImageView recipeImage;

    @FXML
    private Button editBtn;

    @FXML
    private Button favoriteBtn;

    @FXML
    private ImageView userFavoritesIcon;

    private final DatabaseHandler databaseHandler;
    private final RezeptModel recipeModel;
    private int recipeId;

    public RecipesDetailController() {
        // Initialisiert die Instanzvariablen
        databaseHandler = new DatabaseHandler();
        recipeModel = new RezeptModel();
    }

    public void initialize() {
        try {
            // Holt die Rezept-ID vom DataTransmitter
            recipeId = DataTransmitter.getInstance().getRecipeId();

            // Erstellt eine Verbindung zur Datenbank
            Connection connection = databaseHandler.getConnection();

            // Laden der Rezept details aus der Datenbank
            loadRecipeInfoFromDatabase(connection);

            // Zeigt die Rezept details an
            showRecipeInfo();

            // Zuständig für die Sichtbarkeit der Buttons
            updateButtonVisibility();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }

    /**
     * Update the visibility of the edit button, favorite button, and userFavoritesIcon based on the logged-in user.
     * If the user is an admin, show the edit button and hide the favorite button and userFavoritesIcon.
     * If the user is logged in but not an admin, hide the edit button and show the favorite button and userFavoritesIcon.
     * If no user is logged in, hide both the edit button and the favorite button along with userFavoritesIcon.
     */
    public void updateButtonVisibility() {
        String loggedInUser = MainController.getLoggedInUser();

        editBtn.setVisible(loggedInUser != null && loggedInUser.equals("admin"));
        favoriteBtn.setVisible(loggedInUser != null && !loggedInUser.equals("admin"));
        userFavoritesIcon.setVisible(loggedInUser != null && !loggedInUser.equals("admin"));
    }


    /**
     * Load recipe information from the database based on the recipe ID.
     * @param connection The database connection
     * @throws SQLException if a database access error occurs
     */
    private void loadRecipeInfoFromDatabase(Connection connection) throws SQLException {
        // SQL statement um die Rezept details aus der Datenbank zu laden
        String query = "SELECT name, beschreibung, dauer, portion, anweisungen, schwierigkeitsgrad, zutaten, bild FROM Rezepte WHERE RezeptId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Setzt die Rezept-ID als Parameter in das SQL statement
            statement.setInt(1, recipeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Setzt die Rezept details in das RezeptModel
                    recipeModel.setName(resultSet.getString("name"));
                    recipeModel.setBeschreibung(resultSet.getString("beschreibung"));
                    recipeModel.setDauer(resultSet.getString("dauer"));
                    recipeModel.setPortion(resultSet.getString("portion"));
                    recipeModel.setAnweisungen(resultSet.getString("anweisungen"));
                    recipeModel.setSchwierigkeitsgrad(resultSet.getString("schwierigkeitsgrad"));
                    recipeModel.setZutaten(resultSet.getString("zutaten"));
                    recipeModel.setBild(resultSet.getString("bild"));
                }
            }
        }
    }

    /**
     * Show recipe information on the corresponding UI elements.
     */
    private void showRecipeInfo() {
        // Pfad zum Ordner mit den Rezept Bildern
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        // Zeigt die Rezept details auf den entsprechenden UI Elementen an
        recipeName.setText(recipeModel.getName());
        recipeDescription.setText(recipeModel.getBeschreibung());
        recipeTime.setText(recipeModel.getDauer());
        recipePortion.setText(recipeModel.getPortion());
        recipeInstruction.setText(recipeModel.getAnweisungen());
        recipeDifficulty.setText(recipeModel.getSchwierigkeitsgrad());
        recipeIngredients.setText(recipeModel.getZutaten());

        // Lädt das Rezept Bild aus dem Ordner mit den Rezept Bildern
        recipeImage.setImage(new Image("file:" + imageDirectory + recipeModel.getBild()));
    }

    @FXML
    public void onEditBtnClick() {
        // Wechselt zur Rezept bearbeiten Ansicht
        Main.switchToView(StaticViews.RecipeEditView);
    }

    @FXML
    public void onBackToRecipesBtnClick() {
        // Wechselt zur Rezepte Ansicht
        Main.switchToView(StaticViews.RecipesView);
    }

    @FXML
    public void addToFavoritesBtn() {
        // Zeigt eine Meldung an, dass das Rezept zu den Favoriten hinzugefügt wurde
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add to Favorites");
        alert.setHeaderText(null);
        alert.setContentText("The recipe has been added to favorites!");
        alert.showAndWait();
    }
}
