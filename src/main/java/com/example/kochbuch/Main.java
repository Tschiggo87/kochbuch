package com.example.kochbuch;


import com.example.kochbuch.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class Main extends Application {

    public static boolean isAdmin;
    private static MainController mainController;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(StaticViews.StartView));
        fxmlLoader.load();
        mainController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.getRoot(), 1300, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Cookz - Kochbuch");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToView(String viewName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
        try {

            mainController.switchContent(fxmlLoader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static MainController getMainController() {
        return mainController;
    }


}


 /*   public static void saveImage(String imageName, String imagePath) {
        String sql = "INSERT INTO imagetable (imagename, image) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
