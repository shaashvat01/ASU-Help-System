package com.example._360helpsystem;

import Backend.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*******
 * <p> UserDetails Class </p>
 *
 * <p> Description: This class handles the final setup of a user's account by collecting
 * their personal details (name, email, etc.) and creating the user in the system. </p>
 *
 * @version 1.00, 2024-10-09
 * @author Team - Th15
 *
 */

import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.CreateAdminAccount.USER_LIST;

/*******
 * <p> UserDetails Class </p>
 *
 * <p> Description: This class stores and manages details for users within the help system.
 * It provides fields and methods to handle user information such as username, role, and
 * other relevant attributes, supporting user authentication and authorization processes. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

public class UserDetails extends Application {

    private String username;
    private String password;
    private String Role;
    private int otp;

    public UserDetails(String username, String password,String Role, int otp) {
        this.username = username;
        this.password = password;
        this.Role = Role;
        this.otp = otp;
    }

    public UserDetails() {

    }

    @Override
    public void start(Stage primaryStage) {
        // Background setup
        StackPane backgroundPane = new StackPane();
        Rectangle background = new Rectangle(900, 700, Color.web("#f8f5f3"));  // Set background size to 600x600
        backgroundPane.getChildren().add(background);

        // GridPane for form layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the grid on the screen
        grid.setHgap(10);  // Horizontal gap between fields
        grid.setVgap(15);  // Vertical gap between fields
        grid.setPadding(new Insets(30, 30, 30, 30));  // Padding to avoid edge overlap

        // Heading using WindowUtil for consistency
        Label headingText = WindowUtil.createStyledLabel("Finish Setting Up Your Account", 24);
        headingText.setTextFill(Color.web("#8b0000"));  // Custom color for heading
        grid.add(headingText, 0, 0, 2, 1);  // Span the heading across 2 columns

        // First Name field using WindowUtil
        Label firstNameLabel = WindowUtil.createStyledLabel("First Name", 16);
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);

        // Middle Name field using WindowUtil
        Label middleNameLabel = WindowUtil.createStyledLabel("Middle Name", 16);
        TextField middleNameField = new TextField();
        middleNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(middleNameLabel, 0, 2);
        grid.add(middleNameField, 1, 2);

        // Last Name field using WindowUtil
        Label lastNameLabel = WindowUtil.createStyledLabel("Last Name", 16);
        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(lastNameLabel, 0, 3);
        grid.add(lastNameField, 1, 3);

        // Preferred Name field using WindowUtil
        Label preferredNameLabel = WindowUtil.createStyledLabel("Preferred Name", 16);
        TextField preferredNameField = new TextField();
        preferredNameField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(preferredNameLabel, 0, 4);
        grid.add(preferredNameField, 1, 4);

        // Email field using WindowUtil
        Label emailLabel = WindowUtil.createStyledLabel("Email", 16);
        TextField emailField = new TextField();
        emailField.setPrefWidth(250);  // Adjusted width for larger screen
        grid.add(emailLabel, 0, 5);
        grid.add(emailField, 1, 5);

        // Save button using WindowUtil for consistency
        Button saveButton = WindowUtil.createStyledButton("Save");
        saveButton.setPrefWidth(150);  // Increased button width to match larger screen
        grid.add(saveButton, 1, 6);

        // Create the circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();

        // Handle back button action
        backButton.setOnAction(e -> showPreviousScreen(primaryStage,0));  // Implement the previous screen action

        // Add grid to background pane
        backgroundPane.getChildren().add(grid);

        // Create a BorderPane to position the back button at the top left
        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(backgroundPane);

        // Align the back button to the top-left and set padding (gap of 5)
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));  // Gap of 5 from top and left

        // Set the scene size to 600x600 and make all elements visible
        Scene scene = new Scene(root, 900, 700);  // Adjusted scene size to 600x600
        primaryStage.setTitle("User Details");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);  // Disable resizing to keep the layout consistent
        primaryStage.show();

        saveButton.setOnAction(e -> {
            // Collect the user details
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String preferredName = preferredNameField.getText();
            String email = emailField.getText();

            // Add the Admin object to the UserList
            if(this.Role.equals("A"))
            {
                Admin newAdmin = new Admin(username, password, email, firstName, middleName, lastName, preferredName);
                newAdmin.finishAccountSetup();
                USER_LIST.getUserList().add(newAdmin);  // getUserList() returns the LinkedList of User objects
            }
            if(this.Role.equals("SI") || this.Role.equals("IS"))
            {
                Student newStudent = new Student(username, password, email, firstName, middleName, lastName, preferredName);
                newStudent.finishAccountSetup();
                newStudent.setInstructor(true);
                for(Group g : GROUP_LIST)
                {
                    if(!g.isSpecial())
                    {
                        g.addUser(newStudent);
                        g.addAdmin(newStudent.getUserName());
                    }
                }
                USER_LIST.getUserList().add(newStudent);  // getUserList() returns the LinkedList of User objects
                USER_LIST.removeUser(USER_LIST.findUserByOTP(otp));
            }
            if(this.Role.equals("S"))
            {
                Student newStudent = new Student(username, password, email, firstName, middleName, lastName, preferredName);
                newStudent.finishAccountSetup();
                for(Group g : GROUP_LIST)
                {
                    if(!g.isSpecial())
                    {
                        g.addUser(newStudent);
                    }
                }
                USER_LIST.getUserList().add(newStudent);
                USER_LIST.removeUser(USER_LIST.findUserByOTP(otp));
            }
            if(this.Role.equals("I"))
            {
                Instructor newInstructor = new Instructor(username, password, email, firstName, middleName, lastName, preferredName);
                newInstructor.finishAccountSetup();
                for(Group g : GROUP_LIST)
                {
                    if(!g.isSpecial())
                    {
                        g.addUser(newInstructor);
                        g.addAdmin(newInstructor.getUserName());
                    }
                }
                USER_LIST.getUserList().add(newInstructor);
                USER_LIST.removeUser(USER_LIST.findUserByOTP(otp));
            }

            // Optionally, show a confirmation or navigate to another screen
            showSignInScreen(primaryStage);  // Navigate to the Sign In screen
        });
    }

    // Method to go back to the previous screen
    private void showPreviousScreen(Stage primaryStage,int otp) {
        SignUp signUp = new SignUp(this.Role,0);
        try{
            signUp.start(primaryStage);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to show the Sign In screen
    private void showSignInScreen(Stage primaryStage) {
        SignIn signIn = new SignIn();
        try {
            signIn.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}