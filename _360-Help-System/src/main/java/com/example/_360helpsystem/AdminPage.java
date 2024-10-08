package com.example._360helpsystem;
import Backend.Admin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AdminPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        Admin admin = new Admin();
        Text title = new Text("Admin Dashboard");
        title.setFont(Font.font("Arial", 36));

        // Invite User Button
        Button inviteUserButton = new Button("Invite User");
        inviteUserButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        inviteUserButton.setFont(Font.font("Arial", 18));
        inviteUserButton.setPrefWidth(250);
        inviteUserButton.setOnAction(e -> showInviteUserScreen(primaryStage));

        // Other buttons
        Button userModificationsButton = new Button("User Accounts & Modifications");
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

        // Back Button
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back logic here

        // Top bar layout: Back button on the left and Logout button on the right
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        // Align buttons in their respective positions
        HBox leftBox = new HBox(backButton); // Left side with back button
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(logoutButton); // Right side with logout button
        rightBox.setAlignment(Pos.TOP_RIGHT);

        // Add both to topBar with spacing in between
        topBar.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(rightBox, Priority.ALWAYS); // Make the rightBox grow to push the logout button to the right

        // Main layout with admin dashboard buttons
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(title, inviteUserButton, userModificationsButton, resetAccountButton, deleteAccountButton);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(layout);

        Scene adminScene = new Scene(root, 600, 600);
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(adminScene);
        primaryStage.show();
    }

    // Implement logic for back button on Admin Dashboard
    private void showPreviousScreen(Stage primaryStage) {
       SignInAs signInAs = new SignInAs();
       try{
           signInAs.start(primaryStage);
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
    }

    private void showUserModificationsScreen(Stage primaryStage) {
        GridPane userModificationsLayout = new GridPane();
        userModificationsLayout.setPadding(new Insets(20));
        userModificationsLayout.setVgap(20);
        userModificationsLayout.setHgap(20);
        userModificationsLayout.setAlignment(Pos.CENTER);


        Text userModificationsTitle = new Text("User Accounts & Modifications");
        userModificationsTitle.setFont(Font.font("Arial", 36));
        userModificationsLayout.add(userModificationsTitle, 0, 0, 4, 1);


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


        Label usernameLabel1 = new Label("");
        Label nameLabel1 = new Label("");
        Label roleLabel1 = new Label("");


        Button addRoleButton1 = new Button("Add Role");
        Button deleteRoleButton1 = new Button("Delete Role");


        addRoleButton1.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteRoleButton1.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");


        HBox buttonBox1 = new HBox(10);
        buttonBox1.getChildren().addAll(addRoleButton1, deleteRoleButton1);


        userModificationsLayout.add(usernameLabel1, 0, 2);
        userModificationsLayout.add(nameLabel1, 1, 2);
        userModificationsLayout.add(roleLabel1, 2, 2);
        userModificationsLayout.add(buttonBox1, 3, 2);


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(userModificationsLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene userModificationsScene = new Scene(root, 600, 600);
        primaryStage.setScene(userModificationsScene);
    }


    private void showInviteUserScreen(Stage primaryStage) {
        GridPane inviteLayout = new GridPane();
        inviteLayout.setPadding(new Insets(20));
        inviteLayout.setVgap(10);
        inviteLayout.setHgap(10);
        inviteLayout.setAlignment(Pos.CENTER);


        Text inviteTitle = new Text("Invite User");
        inviteTitle.setFont(Font.font("Arial", 24));
        inviteLayout.add(inviteTitle, 0, 0, 2, 1);


        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);
        inviteLayout.add(emailLabel, 0, 1);
        inviteLayout.add(emailField, 1, 1);


        inviteLayout.add(new CheckBox("Student"), 0, 2);
        inviteLayout.add(new CheckBox("Instructor"), 1, 2);


        Button inviteButton = new Button("Invite");
        inviteButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        inviteButton.setFont(Font.font("Arial", 18));
        inviteLayout.add(inviteButton, 0, 3, 2, 1);


        inviteButton.setOnAction(e -> showInvitationSentScreen(primaryStage, emailField.getText()));


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(inviteLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene inviteScene = new Scene(root, 600, 600);
        primaryStage.setScene(inviteScene);
    }


    private void showInvitationSentScreen(Stage primaryStage, String email) {
        VBox confirmationLayout = new VBox(20);
        confirmationLayout.setPadding(new Insets(20));
        confirmationLayout.setAlignment(Pos.CENTER);


        Text confirmationMessage = new Text("Invitation code sent to " + email);
        confirmationMessage.setFont(Font.font("Arial", 20));
        confirmationLayout.getChildren().add(confirmationMessage);


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showInviteUserScreen(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(confirmationLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene confirmationScene = new Scene(root, 600, 600);
        primaryStage.setScene(confirmationScene);
    }


    private void showDeleteUserScreen(Stage primaryStage) {
        GridPane deleteLayout = new GridPane();
        deleteLayout.setPadding(new Insets(20));
        deleteLayout.setVgap(10);
        deleteLayout.setHgap(10);
        deleteLayout.setAlignment(Pos.CENTER);


        Text deleteTitle = new Text("Delete User");
        deleteTitle.setFont(Font.font("Arial", 24));
        deleteLayout.add(deleteTitle, 0, 0, 2, 1);


        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        deleteLayout.add(usernameLabel, 0, 1);
        deleteLayout.add(usernameField, 1, 1);


        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteUserButton.setFont(Font.font("Arial", 18));
        deleteLayout.add(deleteUserButton, 0, 2, 2, 1);


        deleteUserButton.setOnAction(e -> showConfirmationDialog(primaryStage, usernameField.getText()));


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(deleteLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene deleteScene = new Scene(root, 600, 600);
        primaryStage.setScene(deleteScene);
    }


    private void showConfirmationDialog(Stage primaryStage, String username) {
        Stage confirmationStage = new Stage();
        confirmationStage.initModality(Modality.APPLICATION_MODAL);
        confirmationStage.setTitle("Confirmation");

        VBox confirmationLayout = new VBox(10);
        confirmationLayout.setPadding(new Insets(20));
        confirmationLayout.setAlignment(Pos.CENTER);

        Text confirmationMessage = new Text("Are you sure you want to delete user: " + username + "?");
        confirmationMessage.setFont(Font.font("Arial", 18));  // Make the font size larger for better readability

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        noButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

        yesButton.setOnAction(e -> {
            confirmationStage.close();
            showUserDeletedScreen(primaryStage, username);  // Call method to show final delete screen
        });

        noButton.setOnAction(e -> confirmationStage.close());

        HBox buttonLayout = new HBox(10, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        confirmationLayout.getChildren().addAll(confirmationMessage, buttonLayout);

        Scene confirmationScene = new Scene(confirmationLayout, 400, 250);
        confirmationStage.setScene(confirmationScene);
        confirmationStage.showAndWait();
    }

    // Method to show the final "User Deleted" screen
    private void showUserDeletedScreen(Stage primaryStage, String username) {
        // Create layout for the final confirmation
        BorderPane userDeletedLayout = new BorderPane();

        // Message in the center
        Text deletedMessage = new Text("User " + username + " deleted.");
        deletedMessage.setFont(Font.font("Arial", 24));  // Set larger font size
        BorderPane.setAlignment(deletedMessage, Pos.CENTER);

        // Back button on the left
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));  // Navigate back to the admin dashboard or previous screen
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));

        // Add message and back button to the layout
        userDeletedLayout.setCenter(deletedMessage);
        userDeletedLayout.setTop(backButton);

        // Create scene and set on the primary stage
        Scene userDeletedScene = new Scene(userDeletedLayout, 600, 600);
        primaryStage.setScene(userDeletedScene);
    }


    private void showResetAccountScreen(Stage primaryStage) {
        GridPane resetLayout = new GridPane();
        resetLayout.setPadding(new Insets(20));
        resetLayout.setVgap(10);
        resetLayout.setHgap(10);
        resetLayout.setAlignment(Pos.CENTER);


        Text resetTitle = new Text("Reset Account");
        resetTitle.setFont(Font.font("Arial", 24));
        resetLayout.add(resetTitle, 0, 0, 2, 1);


        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);
        resetLayout.add(usernameLabel, 0, 1);
        resetLayout.add(usernameField, 1, 1);


        Button resetUserAccountButton = new Button("Reset User Account");
        resetUserAccountButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        resetUserAccountButton.setFont(Font.font("Arial", 16));
        resetLayout.add(resetUserAccountButton, 2, 1);


        resetUserAccountButton.setOnAction(e -> showAccountResetConfirmation(primaryStage));


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(resetLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene resetScene = new Scene(root, 600, 600);
        primaryStage.setScene(resetScene);
    }


    private void showAccountResetConfirmation(Stage primaryStage) {
        VBox confirmationLayout = new VBox(20);
        confirmationLayout.setPadding(new Insets(20));
        confirmationLayout.setAlignment(Pos.CENTER);


        Text confirmationMessage = new Text("Account reset request has been generated.");
        confirmationMessage.setFont(Font.font("Arial", 20));
        confirmationLayout.getChildren().add(confirmationMessage);


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showResetAccountScreen(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(confirmationLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene confirmationScene = new Scene(root, 600, 600);
        primaryStage.setScene(confirmationScene);
    }


    private void showSignInPage(Stage primaryStage) {
        SignIn signInPage = new SignIn();
        signInPage.start(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
