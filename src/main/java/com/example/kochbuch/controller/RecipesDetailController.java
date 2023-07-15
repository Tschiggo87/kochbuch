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

/**
 * Der RecipesDetailController ist für die Anzeige der Detailansicht eines Rezepts zuständig.
 */
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

    /**
     * Konstruktor für den RecipesDetailController.
     * Initialisiert die Instanzvariablen.
     */
    public RecipesDetailController() {
        databaseHandler = new DatabaseHandler();
        recipeModel = new RezeptModel();
    }

    /**
     * Initialisiert die Detailansicht eines Rezepts.
     */
    public void initialize() {
        try {
            // Holt die Rezept-ID vom DataTransmitter
            recipeId = DataTransmitter.getInstance().getRecipeId();

            // Erstellt eine Verbindung zur Datenbank
            Connection connection = databaseHandler.getConnection();

            // Lädt die Rezeptinformationen aus der Datenbank
            loadRecipeInfoFromDatabase(connection);

            // Zeigt die Rezeptinformationen an
            showRecipeInfo();

            // Aktualisiert die Sichtbarkeit der Buttons
            updateButtonVisibility();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }

    /**
     * Aktualisiert die Sichtbarkeit des Edit-Buttons, des Favorite-Buttons und des UserFavoritesIcons basierend auf dem angemeldeten Benutzer.
     * Wenn der Benutzer ein Administrator ist, wird der Edit-Button angezeigt und der Favorite-Button sowie das UserFavoritesIcon werden ausgeblendet.
     * Wenn der Benutzer angemeldet ist, aber kein Administrator ist, wird der Edit-Button ausgeblendet und der Favorite-Button sowie das UserFavoritesIcon werden angezeigt.
     * Wenn kein Benutzer angemeldet ist, werden sowohl der Edit-Button als auch der Favorite-Button zusammen mit dem UserFavoritesIcon ausgeblendet.
     */
    public void updateButtonVisibility() {
        String loggedInUser = MainController.getLoggedInUser();
        boolean isAdmin = loggedInUser != null && loggedInUser.equals("admin");

        editBtn.setVisible(isAdmin);
        favoriteBtn.setVisible(!isAdmin);
        userFavoritesIcon.setVisible(!isAdmin);
    }


    /**
     * Lädt die Rezeptinformationen aus der Datenbank basierend auf der Rezept-ID.
     *
     * @param connection Die Datenbankverbindung.
     * @throws SQLException Wenn ein Datenbankzugriffsfehler auftritt.
     */
    private void loadRecipeInfoFromDatabase(Connection connection) throws SQLException {
        // SQL-Statement, um die Rezeptinformationen aus der Datenbank zu laden
        String query = "SELECT name, beschreibung, dauer, portion, anweisungen, schwierigkeitsgrad, zutaten, bild FROM Rezepte WHERE RezeptId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Setzt die Rezept-ID als Parameter in das SQL-Statement
            statement.setInt(1, recipeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Setzt die Rezeptinformationen im RezeptModel
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
     * Zeigt die Rezeptinformationen auf den entsprechenden UI-Elementen an.
     */
    private void showRecipeInfo() {
        // Pfad zum Ordner mit den Rezeptbildern
        String imageDirectory = "src/main/resources/images/RezeptBilder/";

        // Zeigt die Rezeptinformationen auf den entsprechenden UI-Elementen an
        recipeName.setText(recipeModel.getName());
        recipeDescription.setText(recipeModel.getBeschreibung());
        recipeTime.setText(recipeModel.getDauer());
        recipePortion.setText(recipeModel.getPortion());
        recipeInstruction.setText(recipeModel.getAnweisungen());
        recipeDifficulty.setText(recipeModel.getSchwierigkeitsgrad());
        recipeIngredients.setText(recipeModel.getZutaten());

        // Lädt das Rezeptbild aus dem Ordner mit den Rezeptbildern
        recipeImage.setImage(new Image("file:" + imageDirectory + recipeModel.getBild()));
    }

    @FXML
    public void onEditBtnClick() {
        // Wechselt zur Rezeptbearbeiten-Ansicht
        Main.switchToView(StaticViews.RecipeEditView);
    }

    @FXML
    public void onBackToRecipesBtnClick() {
        // Wechselt zur Rezepte-Ansicht
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
