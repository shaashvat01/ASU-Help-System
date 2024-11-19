package com.example._360helpsystem;

/*******
 * <p> StudentPage Class </p>
 *
 * <p> Description: This class serves as the primary dashboard for students,
 * providing options to navigate to Articles and Help sections. It also includes
 * Logout functionality and a back button to return to the previous screen. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class StudentPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Top bar with back button on the left and "Logout" button on the right
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Create circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));  // Implement your back button logic here

        // Spacer to push the "Logout" button to the right
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);  // Make the spacer grow to push the Logout button to the right

        // "Logout" button on the right
        Button logoutButton = WindowUtil.createStyledButton("Logout");
        logoutButton.setFont(WindowUtil.createStyledLabel("Logout", 18).getFont());

        // Add the back button, spacer, and logout button to the top bar
        topBar.getChildren().addAll(backButton, spacer, logoutButton);
        topBar.setAlignment(Pos.CENTER);

        // Sidebar buttons for "Articles" and "Help"
        Button articlesButton = createSidebarButtonWithIcon("Articles", "📄");
        articlesButton.setOnAction(e -> showArticlesScreen(primaryStage));

        Button helpButton = createSidebarButtonWithIcon("Help", "❓");
        helpButton.setOnAction(e -> showHelpScreen(primaryStage));

        // Sidebar VBox with buttons
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);
        sidebar.getChildren().addAll(articlesButton, helpButton);

        // Main content area
        VBox mainContentArea = new VBox(20);
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
        primaryStage.setTitle("Student Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set action for the logout button to show the SignIn screen
        logoutButton.setOnAction(e -> showSignInScreen(primaryStage));
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

    // Method to show the Articles screen
    private void showArticlesScreen(Stage primaryStage) {
        StudentArticle studentArticle = new StudentArticle();
        try{
            studentArticle.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to show the Help screen
    private void showHelpScreen(Stage primaryStage) {
        StudentHelp studentHelp = new StudentHelp();
        try{
            studentHelp.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();;
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
        try {
            signInAs.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // main
    public static void main(String[] args) {
        launch(args);
    }
}
