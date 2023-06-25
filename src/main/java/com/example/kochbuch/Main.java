package com.example.kochbuch;

import com.example.kochbuch.controller.MainController;
import com.example.kochbuch.databasehandler.DataBaseRecipesHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {

    private static MainController mainController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(StaticViews.StartView));
        fxmlLoader.load();
        mainController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.getRoot(), 1200, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm()); // CSS zur Scene hinzufügen
        stage.setTitle("Cookz - Kochbuch");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToView(String viewName){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
        try {
            mainController.switchContent(fxmlLoader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        DataBaseRecipesHandler dbHandler = new DataBaseRecipesHandler();
        try {
            dbHandler.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        launch();
    }
}
