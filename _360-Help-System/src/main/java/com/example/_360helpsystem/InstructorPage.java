package com.example._360helpsystem;

import Backend.Update_DB;
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

import java.util.ArrayList;
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


        // Sidebar buttons for "Articles" and "Messages" with icons
        Button articleButton = createSidebarButtonWithIcon("Articles", "📘");
        articleButton.setOnAction(e -> showArticleScreen(primaryStage));

        Button messagesButton = createSidebarButtonWithIcon("Messages", "✉️");
        messagesButton.setOnAction(e -> showMessagesScreen(primaryStage)); // Link to Messages screen


        // Sidebar VBox with buttons
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);
        sidebar.getChildren().addAll(articleButton, messagesButton); // Only "Articles" and "Messages" buttons

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(Font.font("Arial", 18));
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Top bar layout with Back and Logout button
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(logoutButton);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        topBar.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        // Main content area to show buttons dynamically
        mainContentArea = new VBox();
        mainContentArea.setAlignment(Pos.CENTER); // Center main content area in the middle of the screen

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(mainContentArea); // Place mainContentArea in the center of the BorderPane
        root.setStyle("-fx-background-color: #f8f5f3;");

        Scene adminScene = new Scene(root, 900, 700);

        primaryStage.setTitle("Instructor Dashboard");
        primaryStage.setScene(adminScene);
        primaryStage.setResizable(false);
        primaryStage.show();
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

        ArrayList<String> messages = new ArrayList<>();
        Update_DB UDB = new Update_DB();

        ArrayList<String> search = new ArrayList<>();

        VBox msgList = new VBox();

        if(messageType.equalsIgnoreCase("Generic"))
        {
            UDB.readGenericMsg(messages);

            for(String line : messages)
            {
                String[] parts = line.split("-");
                VBox msg = new VBox(5);
                msg.setPadding(new Insets(10));
                msg.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                msg.setAlignment(Pos.TOP_LEFT);

                HBox header = new HBox(10);


                if (parts.length >= 2) {
                    // Extract username and message
                    String username = parts[0].trim();
                    String message = parts[1].trim();

                    Label messageLabel = new Label(username+" - "+message);

                    header.getChildren().addAll(messageLabel);
                    header.setAlignment(Pos.TOP_LEFT);

                    msg.getChildren().addAll(header);
                    msgList.getChildren().add(msg);
                }
            }
        }
        else{
            UDB.readSpecificMsg(messages);

            for(String line : messages)
            {
                String[] parts = line.split("-");
                VBox msg = new VBox(5);
                msg.setPadding(new Insets(10));
                msg.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                msg.setAlignment(Pos.TOP_LEFT);

                HBox header = new HBox(10);


                if (parts.length >= 2) {
                    // Extract username and message
                    String username = parts[0].trim();
                    String message = parts[1].trim();

                    Label messageLabel = new Label(username+" - "+message);

                    header.getChildren().addAll(messageLabel);
                    header.setAlignment(Pos.TOP_LEFT);

                    VBox searchBox = new VBox();
                    // Add the remaining parts to the searchList
                    for (int i = 2; i < parts.length; i++) {
                        searchBox.getChildren().add(new Label(parts[i].trim()));
                    }

                    msg.getChildren().addAll(header, searchBox);
                    msgList.getChildren().add(msg);
                }
            }
        }


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));

        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(msgList);

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
