package com.example._360helpsystem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Main class to handle initial Login/Signup buttons and navigation
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // StackPane to hold the background
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(500, 500, Color.web("#f8f5f3"));  // Very light grey
        backgroundPane.getChildren().add(background);

        // VBox layout to hold the "Sign In" and "Sign Up" buttons
        VBox layout = new VBox(20);  // 20 is the spacing between elements
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));

        // Create "Sign In" Button
        Button signInOption = new Button("Sign In");
        signInOption.setPrefWidth(200);
        signInOption.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        signInOption.setFont(Font.font("Arial", 18));

        // Create "Sign Up" Button (For future implementation)
        Button signUpOption = new Button("Sign Up");
        signUpOption.setPrefWidth(200);
        signUpOption.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        signUpOption.setFont(Font.font("Arial", 18));

        // Add buttons to the layout
        layout.getChildren().addAll(signInOption, signUpOption);
        backgroundPane.getChildren().add(layout);

        // Create scene for the initial screen
        Scene scene = new Scene(backgroundPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Action for "Sign In" button to open the username/password login window
        signInOption.setOnAction(e -> showSignInScreen(primaryStage));

        // "Sign Up" functionality is currently a placeholder
       signUpOption.setOnAction(e -> showNewUserOtpScreen(primaryStage));
        
    }

    // Method to show the Sign In screen (Main.java login form)
    private void showSignInScreen(Stage primaryStage) {
        SignIn mainLogin = new SignIn();
        try {
            mainLogin.start(primaryStage);  // Open the Main.java login screen
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showNewUserOtpScreen(Stage primaryStage){
        NewUserOtp mainOtp = new NewUserOtp();
            try{
            mainOtp.start(primaryStage);
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);  // Ensure you're running LoginSignup as the entry point
    }
}
