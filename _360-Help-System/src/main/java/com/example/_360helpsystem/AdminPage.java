package com.example._360helpsystem;
import Backend.*;
import Backend.OTP_Generator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.example._360helpsystem.CreateAdminAccount.OTP_LIST;
import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;

/*******
 * <p> AdminPage Class </p>
 *
 * <p> Description: This class is the main dashboard for admin users.
 * It includes various options such as inviting users, modifying user accounts,
 * resetting accounts, and logging out. The layout is built using JavaFX elements. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */


public class AdminPage extends Application {

    private VBox mainContentArea; // VBox to hold main content dynamically
    OTP_Generator otpGenerator = new OTP_Generator();

    @Override
    public void start(Stage primaryStage) {
        Admin admin = new Admin();

        // Sidebar buttons for "User Accounts", "Articles", and "Messages" with icons
        Button userModificationsButton = createSidebarButtonWithIcon("User Accounts", "👤");
        userModificationsButton.setOnAction(e -> showUserSettingsScreen(primaryStage));

        Button articleButton = createSidebarButtonWithIcon("Articles", "📘");
        articleButton.setOnAction(e -> showArticleScreen(primaryStage));

        Button messagesButton = createSidebarButtonWithIcon("Messages", "✉️");
        messagesButton.setOnAction(e -> showMessagesScreen(primaryStage)); // Link to Messages screen

        // Sidebar VBox with buttons
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);
        sidebar.getChildren().addAll(userModificationsButton, articleButton, messagesButton); // Add Messages button

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(Font.font("Arial", 18));
        logoutButton.setOnAction(e -> showSignInPage(primaryStage));

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

        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(adminScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    // Function to update the content when "User Account" is clicked
    private void showUserSettingsScreen(Stage primaryStage) {
        mainContentArea.getChildren().clear(); // Clear current content

        // Invite User Button
        Button inviteUserButton = new Button("Invite User");
        inviteUserButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        inviteUserButton.setFont(Font.font("Arial", 18));
        inviteUserButton.setPrefWidth(250);
        inviteUserButton.setOnAction(e -> showInviteUserScreen(primaryStage));

        // List Accounts Button
        Button listAccountsButton = new Button("List Accounts");
        listAccountsButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        listAccountsButton.setFont(Font.font("Arial", 18));
        listAccountsButton.setPrefWidth(250);
        listAccountsButton.setOnAction(e -> showUserModificationsScreen(primaryStage));

        // Reset Account Button
        Button resetAccountButton = new Button("Reset Account");
        resetAccountButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        resetAccountButton.setFont(Font.font("Arial", 18));
        resetAccountButton.setPrefWidth(250);
        resetAccountButton.setOnAction(e -> showResetAccountScreen(primaryStage));

        // Delete Account Button
        Button deleteAccountButton = new Button("Delete Account");
        deleteAccountButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteAccountButton.setFont(Font.font("Arial", 18));
        deleteAccountButton.setPrefWidth(250);
        deleteAccountButton.setOnAction(e -> showDeleteUserScreen(primaryStage));

        // VBox to hold buttons with spacing, and center everything
        VBox buttonContainer = new VBox(20);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(inviteUserButton, listAccountsButton, resetAccountButton, deleteAccountButton);

        // Set the mainContentArea to contain the centered button container
        mainContentArea.getChildren().add(buttonContainer);
    }

    // Helper method to create a sidebar button with an icon
    private Button createSidebarButtonWithIcon(String text, String icon) {
        Button button = new Button(icon + " " + text);
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: CENTER_LEFT;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPadding(new Insets(10, 10, 10, 10));
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
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

    // Implement logic for back button on Admin Dashboard
    private void showPreviousScreen(Stage primaryStage) {
        SignInAs signInAs = new SignInAs();
        try {
            signInAs.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();  // Print the stack trace to debug any potential issues
        }
    }


    private void showUserModificationsScreen(Stage primaryStage) {
        GridPane userModificationsLayout = new GridPane();
        userModificationsLayout.setPadding(new Insets(20));
        userModificationsLayout.setVgap(20);
        userModificationsLayout.setHgap(20);
        userModificationsLayout.setAlignment(Pos.CENTER);

        Text userModificationsTitle = new Text("Accounts");
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

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(userModificationsLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);

        int count = 0;

        for (User user : USER_LIST.getUserList()) {

            // Skip the admin account, assuming the admin role is represented by user.isAdmin()
            if (user.isAdmin()) {
                continue; // Skip this iteration and don't display the admin account
            }
            if(!user.isAccountSetupDone())
            {
                continue;
            }

            Label usernameLabel = new Label(user.getUserName());
            String name = user.getPreferredName().isEmpty() ? user.getFirstName() : user.getPreferredName();
            Label nameLabel = new Label(name);

            // Checkboxes for roles (non-editable)
            CheckBox studentCheckbox = new CheckBox("S");
            CheckBox instructorCheckbox = new CheckBox("I");

            studentCheckbox.setSelected(user.isStudent());
            studentCheckbox.setDisable(true);  // Disable editing
            instructorCheckbox.setSelected(user.isInstructor());
            instructorCheckbox.setDisable(true);  // Disable editing

            HBox rolesBox = new HBox(10);
            rolesBox.getChildren().addAll(studentCheckbox, instructorCheckbox);

            // Action buttons depending on the role
            Button studentActionButton = new Button(user.isStudent() ? "Remove Student" : "Make Student");
            Button instructorActionButton = new Button(user.isInstructor() ? "Remove Instructor" : "Make Instructor");

            // Style the buttons
            studentActionButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
            instructorActionButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");

            // Add action to studentActionButton
            studentActionButton.setOnAction(e -> {
                if (user.isStudent()) {
                    user.setStudent(false);
                    studentActionButton.setText("Make Student");
                    studentCheckbox.setSelected(false);
                } else {
                    user.setStudent(true);
                    studentActionButton.setText("Remove Student");
                    studentCheckbox.setSelected(true);
                }
            });

            // Add action to instructorActionButton
            instructorActionButton.setOnAction(e -> {
                if (user.isInstructor()) {
                    user.setInstructor(false);
                    instructorActionButton.setText("Make Instructor");
                    instructorCheckbox.setSelected(false);
                } else {
                    user.setInstructor(true);
                    instructorActionButton.setText("Remove Instructor");
                    instructorCheckbox.setSelected(true);
                }
            });

            HBox actionBox = new HBox(10);
            actionBox.getChildren().addAll(studentActionButton, instructorActionButton);

            // Add to grid layout
            userModificationsLayout.add(usernameLabel, 0, count + 2);
            userModificationsLayout.add(nameLabel, 1, count + 2);
            userModificationsLayout.add(rolesBox, 2, count + 2);
            userModificationsLayout.add(actionBox, 3, count + 2);

            count++; // Increment the row count for each displayed user
        }

        // Back button
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));

        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(scrollPane);

        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));

        Scene userModificationsScene = new Scene(root, 800, 600);
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

        CheckBox S_check = new CheckBox("Student");
        CheckBox I_check = new CheckBox("Instructor");
        inviteLayout.add(S_check, 0, 2);
        inviteLayout.add(I_check, 1, 2);

        Button inviteButton = new Button("Invite");
        inviteButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        inviteButton.setFont(Font.font("Arial", 18));
        inviteLayout.add(inviteButton, 0, 3, 2, 1);

        inviteButton.setOnAction(e -> {
            String email = emailField.getText();
            boolean isStudent = S_check.isSelected();
            boolean isInstructor = I_check.isSelected();

            // Create a new User based on the selection
            User newUser = null;

            if (isInstructor && isStudent) {
                newUser = new Student("", "", email, "", "", "", ""); // Create a Student
                ((Student) newUser).setInstructor(true); // Set as Instructor as well
                System.out.println("Created Role Student = " + newUser.isStudent());
                System.out.println("Created Role Instructor = " + newUser.isInstructor());
            } else if (isInstructor) {
                newUser = new Instructor("", "", email, "", "", "", ""); // Use the Instructor constructor
                System.out.println("Created Only Role Instructor = " + newUser.isInstructor());
            } else if (isStudent) {
                newUser = new Student("", "", email, "", "", "", ""); // Use the Student constructor
                System.out.println("Created Only Role Student = " + newUser.isStudent());
            }

            // Add the new user to the USER_LIST
            if (newUser != null) {
                USER_LIST.addUser(newUser); // Ensure USER_LIST is initialized

                // Generate OTP and add to OTP_LIST
                OTP_Generator otpGenerator = new OTP_Generator(); // Initialize properly
                int generatedOtp = otpGenerator.generateOTP();
                OTP_LIST.addOTP(generatedOtp); // Ensure OTP_LIST is initialized
                System.out.println(OTP_LIST.toString());
                newUser.setAccOTP(generatedOtp);

                // Optionally, show some confirmation or proceed to the next screen
                System.out.println("New user added: " + newUser.getEmail() + " | OTP: " + generatedOtp);
            }

            showInvitationSentScreen(primaryStage, emailField.getText());
        });

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(inviteLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene resetScene = new Scene(root, 800, 600);
        primaryStage.setScene(resetScene);
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


        Scene confirmationScene = new Scene(root, 800, 600);
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
        deleteLayout.add(deleteUserButton, 0, 3, 2, 1);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility
        deleteLayout.add(errorLabel, 1, 2);  // Add it to row 5, below the Confirm Password field


        deleteUserButton.setOnAction(e -> {
            String username = usernameField.getText();


            if (USER_LIST.findUser(username) != null) {
                if(!USER_LIST.findUser(username).isAdmin())
                {
                    showConfirmationDialog(primaryStage, username );
                }
                else{
                    errorLabel.setText("Permission Denied");
                }

            } else {
                errorLabel.setText("User not found");
            }
        });


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(deleteLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene deleteScene = new Scene(root, 800, 600);
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
            User toDelete = USER_LIST.findUser(username);
            boolean userRemoved = USER_LIST.removeUser(toDelete); // Call the remove method
            if (userRemoved) {
                // Call method to show final delete screen
                confirmationStage.close();
                showUserDeletedScreen(primaryStage, username);
            }

        });

        noButton.setOnAction(e -> confirmationStage.close());

        HBox buttonLayout = new HBox(10, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        confirmationLayout.getChildren().addAll(confirmationMessage, buttonLayout);

        Scene confirmationScene = new Scene(confirmationLayout, 800, 600);
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
        Scene userDeletedScene = new Scene(userDeletedLayout, 800, 600);
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


        resetUserAccountButton.setOnAction(e -> {
            String username = usernameField.getText();  // Get the username from the text field
            int newotp = new OTP_Generator().generateOTP();
            USER_LIST.findUser(username).setAccOTP(newotp);
            System.out.println("Reset "+username+"'s password : OTP Generated = "+newotp);

            // Now you can pass the username to the next method or handle it as needed
            showAccountResetConfirmation(primaryStage);
        });


        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));


        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(resetLayout);


        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));


        Scene resetScene = new Scene(root, 800, 600);
        primaryStage.setScene(resetScene);
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


        Scene confirmationScene = new Scene(root, 800, 600);
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
