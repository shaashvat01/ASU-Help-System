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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

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

    private VBox mainContentArea; // Declare mainContentArea as a class-level variable

    @Override
    public void start(Stage primaryStage) {

        // Main content area with black border
        mainContentArea = new VBox(); // Initialize mainContentArea
        mainContentArea.setAlignment(Pos.CENTER);
        mainContentArea.setPadding(new Insets(20));
        mainContentArea.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");

        // Top bar with back button on the left and "Logout" button on the right
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Create circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage)); // Back button logic

        // Spacer to push the "Logout" button to the right
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // "Logout" button on the right
        Button logoutButton = WindowUtil.createStyledButton("Logout");
        logoutButton.setFont(WindowUtil.createStyledLabel("Logout", 18).getFont());

        // Add the back button, spacer, and logout button to the top bar
        topBar.getChildren().addAll(backButton, spacer, logoutButton);
        topBar.setAlignment(Pos.CENTER);

        // Sidebar buttons for "Articles" and "Messages"
        Button articlesButton = createSidebarButtonWithIcon("Articles", "📄");
        articlesButton.setOnAction(e -> showArticleScreen(primaryStage));

        Button messagesButton = createSidebarButtonWithIcon("Messages", "✉️");
        messagesButton.setOnAction(e -> showMessagesScreen(primaryStage));

        // Sidebar VBox with buttons
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);
        sidebar.getChildren().addAll(articlesButton, messagesButton);

        // Main content area
        mainContentArea = new VBox(); // Initialize mainContentArea
        mainContentArea.setAlignment(Pos.CENTER);
        mainContentArea.setPadding(new Insets(20));

        // Main layout with sidebar and main content area
        HBox mainLayout = new HBox(sidebar, mainContentArea);

        // BorderPane root to include top bar and layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Scene settings
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Instructor Page");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set action for the logout button to show the SignIn screen
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));
    }

    private Button createSidebarButtonWithIcon(String text, String icon) {
        Button button = new Button(icon + " " + text);
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: CENTER_LEFT;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPadding(new Insets(10, 10, 10, 10));
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }

    private void showMessageDetailsScreen(Stage primaryStage, String messageType) {
        // Predefined student name and message
        String studentName = "Alice Johnson";
        String message = (messageType.equalsIgnoreCase("Generic"))
                ? "Please check your latest assignment!"
                : "Your project submission has been reviewed.";

        Text studentNameText = new Text("Student: " + studentName);
        studentNameText.setFont(Font.font("Arial", 20));

        Text messageText = new Text(messageType + " Message: " + message);
        messageText.setFont(Font.font("Arial", 16));

        VBox messageDetails = new VBox(10, studentNameText, messageText);
        messageDetails.setAlignment(Pos.CENTER);

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));

        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(messageDetails);

        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
    }

    private void showMessagesScreen(Stage primaryStage) {
        // Create buttons for Generic Messages and Specific Messages
        Button genericMessagesButton = new Button("Generic Messages");
        genericMessagesButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; -fx-font-size: 16px;");
        genericMessagesButton.setPadding(new Insets(10, 20, 10, 20));

        Button specificMessagesButton = new Button("Specific Messages");
        specificMessagesButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white; -fx-font-size: 16px;");
        specificMessagesButton.setPadding(new Insets(10, 20, 10, 20));

        // Set actions for buttons
        genericMessagesButton.setOnAction(e -> showMessageDetailsScreen(primaryStage, "Generic"));
        specificMessagesButton.setOnAction(e -> showMessageDetailsScreen(primaryStage, "Specific"));

        // VBox to hold the buttons
        VBox messagesBox = new VBox(20, genericMessagesButton, specificMessagesButton); // Spacing between buttons
        messagesBox.setAlignment(Pos.CENTER); // Center align buttons
        messagesBox.setPadding(new Insets(20));

        // Wrap the messagesBox in a VBox with a black border
        VBox borderedBox = new VBox(messagesBox);
        borderedBox.setStyle(" -fx-border-width: 2; -fx-padding: 20; -fx-background-color: white;");
        borderedBox.setAlignment(Pos.CENTER); // Ensure the bordered box is centered
        borderedBox.setPrefWidth(800);
        borderedBox.setPrefHeight(600);


        // Clear existing content in the main content area and add the bordered box
        mainContentArea.getChildren().clear();
        mainContentArea.getChildren().add(borderedBox);
    }



    private void showArticleScreen(Stage primaryStage) {
        InstructorsArticlePage articlePage = new InstructorsArticlePage();
        try{
            articlePage.start(primaryStage);
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
