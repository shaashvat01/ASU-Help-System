package com.example._360helpsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/*******
 * <p> HelloApplication Class </p>
 *
 * <p> Description: This class is the main entry point for launching the JavaFX application.
 * It loads the FXML file and sets up the initial scene for the application. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class HelloApplication extends Application {
    // This method loads the FXML file and sets the scene for the main application window.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    // main
    public static void main(String[] args) {
        launch();
    }
}