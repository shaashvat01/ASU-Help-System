package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Title Text
        Text title = new Text("Admin Dashboard");
        title.setFont(Font.font("Arial", 36));

        // Invite User Button
        Button inviteUserButton = new Button("Invite User");
        inviteUserButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        inviteUserButton.setFont(Font.font("Arial", 18));
        inviteUserButton.setPrefWidth(250);

        // Action for Invite User Button
        inviteUserButton.setOnAction(e -> showInviteUserScreen(primaryStage));

        // Other buttons
        Button userModificationsButton = new Button("User Modifications");
        userModificationsButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        userModificationsButton.setFont(Font.font("Arial", 18));
        userModificationsButton.setPrefWidth(250);
        userModificationsButton.setOnAction(e -> showUserModificationsScreen(primaryStage));

        Button resetAccountButton = new Button("Reset Account");
        resetAccountButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        resetAccountButton.setFont(Font.font("Arial", 18));
        resetAccountButton.setPrefWidth(250);
        resetAccountButton.setOnAction(e -> showResetAccountScreen(primaryStage));

        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteAccountButton.setFont(Font.font("Arial", 18));
        deleteAccountButton.setPrefWidth(250);
        deleteAccountButton.setOnAction(e -> showDeleteUserScreen(primaryStage));

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(Font.font("Arial", 18));
        logoutButton.setOnAction(e -> showSignInPage(primaryStage));

        // Logout button in top-right corner
        HBox logoutBox = new HBox(logoutButton);
        logoutBox.setAlignment(Pos.TOP_RIGHT);
        logoutBox.setPadding(new Insets(10, 10, 0, 0)); // Padding for logout button

        // VBox layout for buttons
        VBox layout = new VBox(20);  // 20 is the spacing between elements
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(title, inviteUserButton, userModificationsButton, resetAccountButton, deleteAccountButton);

        // BorderPane layout to place Logout button in top-right and buttons in center
        BorderPane root = new BorderPane();
        root.setTop(logoutBox);  // Logout button at the top-right corner
        root.setCenter(layout);  // Center the VBox with buttons

        // Scene setup
        Scene adminScene = new Scene(root, 500, 500);

        // Set up the stage
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(adminScene);
        primaryStage.show();
    }

    // Method to show the "User Modifications" screen
    // Method to show the "User Modifications" screen
    private void showUserModificationsScreen(Stage primaryStage) {
        // Create a GridPane layout for displaying user details and role modifications
        GridPane userModificationsLayout = new GridPane();
        userModificationsLayout.setPadding(new Insets(20));
        userModificationsLayout.setVgap(20);  // Increase the vertical spacing for bigger layout
        userModificationsLayout.setHgap(20);  // Increase the horizontal spacing for bigger layout
        userModificationsLayout.setAlignment(Pos.CENTER);

        // Title Text
        Text userModificationsTitle = new Text("User Modifications");
        userModificationsTitle.setFont(Font.font("Arial", 36));  // Make the title text bigger
        userModificationsLayout.add(userModificationsTitle, 0, 0, 4, 1);  // Span 4 columns

        // Table headers
        Label usernameHeader = new Label("Username");
        Label nameHeader = new Label("Name");
        Label roleHeader = new Label("Roles");
        Label actionsHeader = new Label("Actions");

        usernameHeader.setFont(Font.font("Arial", 18));
        nameHeader.setFont(Font.font("Arial", 18));
        roleHeader.setFont(Font.font("Arial", 18));
        actionsHeader.setFont(Font.font("Arial", 18));

        userModificationsLayout.add(usernameHeader, 0, 1);
        userModificationsLayout.add(nameHeader, 1, 1);
        userModificationsLayout.add(roleHeader, 2, 1);
        userModificationsLayout.add(actionsHeader, 3, 1);

        // Hardcoded User 1
        Label usernameLabel1 = new Label("");
        Label nameLabel1 = new Label("");
        Label roleLabel1 = new Label("");

// Add Role and Delete Role buttons
        Button addRoleButton1 = new Button("Add Role");
        Button deleteRoleButton1 = new Button("Delete Role");

// Setting the same style for both buttons
        addRoleButton1.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteRoleButton1.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        HBox buttonBox1 = new HBox(10);  // 10px spacing between buttons
        buttonBox1.getChildren().addAll(addRoleButton1, deleteRoleButton1);

        userModificationsLayout.add(usernameLabel1, 0, 2);
        userModificationsLayout.add(nameLabel1, 1, 2);
        userModificationsLayout.add(roleLabel1, 2, 2);
        userModificationsLayout.add(buttonBox1, 3, 2);

        // Create the new scene for user modifications
        Scene userModificationsScene = new Scene(userModificationsLayout, 700, 500);  // Set larger dimensions
        primaryStage.setScene(userModificationsScene);
    }


    // Method to show the "Invite User" screen
    // Method to show the "Invite User" screen
    private void showInviteUserScreen(Stage primaryStage) {
        // Create a GridPane layout for better control
        GridPane inviteLayout = new GridPane();
        inviteLayout.setPadding(new Insets(20));
        inviteLayout.setVgap(10);
        inviteLayout.setHgap(10);
        inviteLayout.setAlignment(Pos.CENTER);

        // Title Text
        Text inviteTitle = new Text("Invite User");
        inviteTitle.setFont(Font.font("Arial", 24));
        inviteLayout.add(inviteTitle, 0, 0, 2, 1);  // Span 2 columns

        // Email Label and TextField
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);
        emailField.setMaxWidth(250);
        inviteLayout.add(emailLabel, 0, 1);
        inviteLayout.add(emailField, 1, 1);

        // Checkboxes for selecting roles
        inviteLayout.add(new CheckBox("Student"), 0, 2);
        inviteLayout.add(new CheckBox("Instructor"), 1, 2);

        // Invite Button
        Button inviteButton = new Button("Invite");
        inviteButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        inviteButton.setFont(Font.font("Arial", 18));
        inviteLayout.add(inviteButton, 0, 3, 2, 1);  // Span 2 columns

        // Action for Invite Button
        inviteButton.setOnAction(e -> {
            String email = emailField.getText();
            showInvitationSentScreen(primaryStage, email);
        });

        // Create the new scene
        Scene inviteScene = new Scene(inviteLayout, 400, 400);

        // Set the new scene on the stage
        primaryStage.setScene(inviteScene);
    }

    // Method to show the confirmation that the invitation code was sent
    private void showInvitationSentScreen(Stage primaryStage, String email) {
        // Create a VBox layout for the confirmation message
        VBox confirmationLayout = new VBox(20);
        confirmationLayout.setPadding(new Insets(20));
        confirmationLayout.setAlignment(Pos.CENTER);

        // Confirmation message
        Text confirmationMessage = new Text("Invitation code sent to " + email);
        confirmationMessage.setFont(Font.font("Arial", 20));

        // Add the confirmation message to the layout
        confirmationLayout.getChildren().add(confirmationMessage);

        // Create the new scene for the confirmation page
        Scene confirmationScene = new Scene(confirmationLayout, 400, 200);

        // Set the new scene on the stage
        primaryStage.setScene(confirmationScene);
    }


    // Method to show the "Delete User" screen
    private void showDeleteUserScreen(Stage primaryStage) {
        // Create a GridPane layout for the delete screen
        GridPane deleteLayout = new GridPane();
        deleteLayout.setPadding(new Insets(20));
        deleteLayout.setVgap(10);
        deleteLayout.setHgap(10);
        deleteLayout.setAlignment(Pos.CENTER);

        // Title Text
        Text deleteTitle = new Text("Delete User");
        deleteTitle.setFont(Font.font("Arial", 24));
        deleteLayout.add(deleteTitle, 0, 0, 2, 1);  // Span 2 columns

        // Username Label and TextField
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        usernameField.setMaxWidth(250);
        deleteLayout.add(usernameLabel, 0, 1);
        deleteLayout.add(usernameField, 1, 1);

        // Delete User Button
        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteUserButton.setFont(Font.font("Arial", 18));
        deleteLayout.add(deleteUserButton, 0, 2, 2, 1);  // Span 2 columns

        // Set the action for the Delete User button
        deleteUserButton.setOnAction(e -> showConfirmationDialog(primaryStage, usernameField.getText()));

        // Create the new scene
        Scene deleteScene = new Scene(deleteLayout, 400, 300);

        // Set the new scene on the stage
        primaryStage.setScene(deleteScene);
    }

    // Method to show the confirmation dialog when "Delete User" is clicked
    private void showConfirmationDialog(Stage primaryStage, String username) {
        // Create a new stage for the confirmation dialog
        Stage confirmationStage = new Stage();
        confirmationStage.initModality(Modality.APPLICATION_MODAL);
        confirmationStage.setTitle("Confirmation");

        // Create layout for the confirmation message
        VBox confirmationLayout = new VBox(10);
        confirmationLayout.setPadding(new Insets(20));
        confirmationLayout.setAlignment(Pos.CENTER);

        // Confirmation message
        Text confirmationMessage = new Text("Are you sure you want to delete user: " + username + "?");

        // Yes and No buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        noButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        // Button actions
        yesButton.setOnAction(e -> {
            confirmationStage.close();
            // Implement the deletion logic here
            System.out.println("User " + username + " deleted.");
            primaryStage.setScene(new Scene(new VBox(new Text("User " + username + " deleted.")), 400, 300));
        });

        noButton.setOnAction(e -> confirmationStage.close());

        // Add elements to the confirmation layout
        HBox buttonLayout = new HBox(10, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        confirmationLayout.getChildren().addAll(confirmationMessage, buttonLayout);

        // Set the scene for the confirmation dialog
        Scene confirmationScene = new Scene(confirmationLayout, 300, 150);
        confirmationStage.setScene(confirmationScene);

        // Show the confirmation dialog
        confirmationStage.showAndWait();
    }

    // Method to show the Reset Account screen
    private void showResetAccountScreen(Stage primaryStage) {
        // Create a GridPane layout for the reset account screen
        GridPane resetLayout = new GridPane();
        resetLayout.setPadding(new Insets(20));
        resetLayout.setVgap(10);
        resetLayout.setHgap(10);
        resetLayout.setAlignment(Pos.CENTER);

        // Title Text for Reset Account Screen
        Text resetTitle = new Text("Reset Account");
        resetTitle.setFont(Font.font("Arial", 24));
        resetLayout.add(resetTitle, 0, 0, 2, 1);  // Span 2 columns

        // Username Label and TextField
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        resetLayout.add(usernameLabel, 0, 1);
        resetLayout.add(usernameField, 1, 1);

        // Reset User Account Button
        Button resetUserAccountButton = new Button("Reset User Account");
        resetUserAccountButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        resetUserAccountButton.setFont(Font.font("Arial", 16));
        resetLayout.add(resetUserAccountButton, 2, 1); // Placing it next to the username field

        // Set action for the Reset User Account Button
        resetUserAccountButton.setOnAction(e -> {
            // Show confirmation page when button is clicked
            showAccountResetConfirmation(primaryStage);
        });

        // Create the new scene for resetting the account
        Scene resetScene = new Scene(resetLayout, 400, 200);
        primaryStage.setScene(resetScene);
    }

    // Method to show the confirmation page after resetting the account
    private void showAccountResetConfirmation(Stage primaryStage) {
        // Create a VBox layout for the confirmation screen
        VBox confirmationLayout = new VBox(20);
        confirmationLayout.setPadding(new Insets(20));
        confirmationLayout.setAlignment(Pos.CENTER);

        // Confirmation message
        Text confirmationMessage = new Text("Account reset requested has been generated.");
        confirmationMessage.setFont(Font.font("Arial", 20));

        // Add the confirmation message to the layout
        confirmationLayout.getChildren().add(confirmationMessage);

        // Create the new scene for the confirmation page
        Scene confirmationScene = new Scene(confirmationLayout, 400, 200);
        primaryStage.setScene(confirmationScene);
    }


    // Method to show the SignIn page when Logout is clicked
    private void showSignInPage(Stage primaryStage) {
        SignIn signInPage = new SignIn();
        signInPage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
