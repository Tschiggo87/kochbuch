package com.example.kochbuch;

import com.example.kochbuch.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Die Hauptklasse für die Kochbuch-Anwendung.
 */
public class Main extends Application {

    private static MainController mainController;

    /**
     * Startet die Anwendung und richtet die Hauptbühne ein.
     *
     * @param stage Die primäre Bühne für die Anwendung.
     * @throws IOException, falls ein Fehler beim Laden der FXML-Dateien auftritt.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(StaticViews.StartView));
        fxmlLoader.load();
        mainController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.getRoot(), 1290, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.setTitle("Cookz - Kochbuch");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Wechselt zur angegebenen Ansicht in der Anwendung.
     *
     * @param viewName Der Name der Ansicht, zu der gewechselt werden soll.
     */
    public static void switchToView(String viewName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
        try {
            mainController.switchContent(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ruft den Hauptcontroller der Anwendung ab.
     *
     * @return Der Hauptcontroller.
     */
    public static MainController getMainController() {
        return mainController;
    }
}
