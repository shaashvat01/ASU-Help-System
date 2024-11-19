
package com.example._360helpsystem;

import Backend.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import java.util.ArrayList;

/*******
 * <p> CreateAdminAccount Class </p>
 *
 * <p> Description: This class handles the first-time setup for creating an admin account.
 * If no users exist in the system, it presents a form to create the admin account. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */
public class CreateAdminAccount extends Application {

    public static UserList USER_LIST = new UserList();
    public static OTPList OTP_LIST = new OTPList();
    public static ArticleList ARTICLE_LIST = new ArticleList();
    public static GroupList GROUP_LIST = new GroupList();
    public static AccessList ACCESS_LIST = new AccessList();
    public static ArrayList<String> SEARCH_HISTORY = new ArrayList<>();
    Update_DB UDB = new Update_DB();
    //DEBUG

    public static void main(String[] args) {
        launch(args);
    }

    // This method checks if users exist and either shows the admin creation screen or opens the main screen based on that condition.
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Check if users exist (if none exist, show the first-time setup screen)
        initializeDB();
        boolean usersExist = checkIfUsersExist();

        if (!usersExist) {
            showFirstTimeSetupScreen(primaryStage);  // Show the Admin creation screen
        } else {
            openMainScreen(primaryStage);
        }
    }

    // If users already exist, this method opens the main screen.
    private void openMainScreen(Stage primaryStage) {
        Main main = new Main();
        main.start(primaryStage);
    }

    // Method to show the first-time setup screen
    private void showFirstTimeSetupScreen(Stage primaryStage) {
        VBox layout = WindowUtil.createStandardLayout();  // Use standardized VBox layout

        Label passwordFeedbackLabel = new Label();  // This will display real-time feedback
        passwordFeedbackLabel.setTextFill(Color.RED);  // Initial color is red for unmet conditions

        Label prompt = WindowUtil.createStyledLabel("Create Admin Account", 24);  // Standardized title label

        // Username and Password fields
        Label usernameLabel = WindowUtil.createStyledLabel("Username", 16);  // Label with standard style
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(250);
        usernameField.setMaxWidth(250);

        Label passwordLabel = WindowUtil.createStyledLabel("Password", 16);  // Label with standard style
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);
        passwordField.setMaxWidth(250);

        PasswordEvaluator PE = new PasswordEvaluator(passwordField, passwordFeedbackLabel);

        // Create the standardized button
        Button createAdminButton = WindowUtil.createStyledButton("Create Admin Account");

        // Action to create the Admin account
        createAdminButton.setOnAction(e -> {
            // Store the entered username and password
            String username = usernameField.getText();
            String password = passwordField.getText();

            if(PE.checkPassword(password)) {
                {
                    showUserDetailsScreen(primaryStage,username,password);
                }
            }

        });
        // Add all elements to the layout
        layout.getChildren().addAll(prompt, usernameLabel, usernameField, passwordLabel, passwordField,passwordFeedbackLabel, createAdminButton);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setVisible(false);

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Add the layout to the background pane
        StackPane backgroundPane = new StackPane();
        backgroundPane.getChildren().add(layout);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Set standardized window size
        Scene scene = new Scene(root);
        WindowUtil.setWindowSize(primaryStage, scene, 900, 700);  // Standard size applied

        primaryStage.show();
    }
    // This method passes the username and password to the UserDetails screen.
    private void showUserDetailsScreen(Stage primaryStage,String username,String password) {
        UserDetails userDetails = new UserDetails(username, password,"A",0); // Pass username and password
        try {
            userDetails.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Returns true if there are users in the system, otherwise false.
    private boolean checkIfUsersExist() {
        if(USER_LIST.getUserList().isEmpty()) {
            return false;
        }
        return true;  // For testing purposes, this is set to false
    }

    // Method to handle back button logic
    private void showPreviousScreen(Stage primaryStage) {
        Main main = new Main();
        try{
            main.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    // Loads the user and OTP databases from files.
    private void initializeDB() throws Exception {
        UDB.loadUserDB(USER_LIST);
        UDB.loadOTPDB(OTP_LIST);
        UDB.loadArticleDB(ARTICLE_LIST);
        UDB.loadGrpDB(GROUP_LIST);
        UDB.loadRequestsDB();
        SEARCH_HISTORY.clear();
    }
    // This method saves the databases when the application is closing.
    public void stop() {
        // Save user and OTP databases when the application is closing
        UDB.saveUserDB(USER_LIST);
        UDB.saveOTPDB(OTP_LIST);
        UDB.saveArticleDB(ARTICLE_LIST);
        UDB.saveGrpDB(GROUP_LIST);
        UDB.saveRequestsDB();
        SEARCH_HISTORY.clear();
        System.out.println("Databases saved successfully.");
    }
}
