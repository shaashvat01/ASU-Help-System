package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*******
 * <p> HomePage Class </p>
 *
 * <p> Description: This class represents the home page of the application.
 * It includes a top bar with a "Home" label and a logout button, and a back button to return to the previous screen. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

public class InstructorPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar with "Home" label on the left and "Logout" button on the right
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // "Home" text on the left
        Label homeText = WindowUtil.createStyledLabel("Home", 24);
        topBar.getChildren().add(homeText);
        HBox.setMargin(homeText, new Insets(0, 0, 0, 10));  // Extra padding for spacing

        // Add spacer to push the "Logout" button to the right
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);  // Make the spacer grow to push the Logout button to the right
        topBar.getChildren().add(spacer);

        // "Logout" button on the right
        Button logoutButton = WindowUtil.createStyledButton("Logout");
        logoutButton.setFont(WindowUtil.createStyledLabel("Logout", 18).getFont());  // Consistent font
        topBar.getChildren().add(logoutButton);

        // Align the top bar
        topBar.setAlignment(Pos.CENTER);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Create the main layout with the top bar and back button
        BorderPane root = new BorderPane();
        root.setTop(topBar);

        // Create a BorderPane to position the back button at the top left
        BorderPane backButtonPane = new BorderPane();
        backButtonPane.setTop(backButton);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        root.setLeft(backButtonPane);

        // Create and style the "Articles" button
        Button article = new Button("Articles");
        article.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        article.setFont(Font.font("Arial", 18));
        article.setPrefWidth(250);
        article.setOnAction(e -> showArticleScreen(primaryStage));

        // Add the article button to the center of the layout
        root.setCenter(article);  // Set the button in the center of the layout

        // Set the scene with the required window size
        Scene scene = new Scene(root, 900, 700);  // Set to 600x600
        primaryStage.setTitle("Home Page");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set action for the logout button to show the SignIn screen
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));
    }

    private void showArticleScreen(Stage primaryStage) {
       ArticlesPage articles = new ArticlesPage();
        try{
            articles.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    // Method to show the SignIn screen when Logout is clicked
    private void showSignInScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try {
            signIn.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to handle back button logic
    private void showPreviousScreen(Stage primaryStage) {
        SignInAs signInAs = new SignInAs();
        try{
            signInAs.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // main
    public static void main(String[] args) {
        launch(args);
    }
}
