package com.example._360helpsystem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserDetails extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Background setup
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(500, 500, Color.web("#f8f5f3"));
        backgroundPane.getChildren().add(background);

        // GridPane for form layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Heading
        Text headingText = new Text("Finish Setting Up Your Account");
        headingText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headingText.setFill(Color.web("#8b0000"));
        grid.add(headingText, 0, 0, 2, 1);

        // First Name
        Label firstNameLabel = new Label("First Name");
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(200);
        firstNameField.setMaxWidth(250);
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);

        // Middle Name
        Label middleNameLabel = new Label("Middle Name");
        TextField middleNameField = new TextField();
        middleNameField.setPrefWidth(200);
        middleNameField.setMaxWidth(250);
        grid.add(middleNameLabel, 0, 2);
        grid.add(middleNameField, 1, 2);

        // Last Name
        Label lastNameLabel = new Label("Last Name");
        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(200);
        lastNameField.setMaxWidth(250);
        grid.add(lastNameLabel, 0, 3);
        grid.add(lastNameField, 1, 3);

        // Preferred Name
        Label preferredNameLabel = new Label("Preferred Name");
        TextField preferredNameField = new TextField();
        preferredNameField.setPrefWidth(200);
        preferredNameField.setMaxWidth(250);
        grid.add(preferredNameLabel, 0, 4);
        grid.add(preferredNameField, 1, 4);

        // Email
        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);
        emailField.setMaxWidth(250);
        grid.add(emailLabel, 0, 5);
        grid.add(emailField, 1, 5);

        // Save button
        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(100);
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        grid.add(saveButton, 1, 6);


        // Add grid to background pane
        backgroundPane.getChildren().add(grid);

        // Set the scene and show the stage
        Scene scene = new Scene(backgroundPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Details");
        primaryStage.show();

        saveButton.setOnAction(e -> showSignInScreen(primaryStage));
    }

    private void showSignInScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try{
            signIn.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
