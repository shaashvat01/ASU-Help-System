package com.example._360helpsystem;

import Backend.Article;
import Backend.Group;
import Backend.Instructor;
import Backend.Update_DB;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;
import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

/*******
 * <p> BackupArticles Class </p>
 *
 * <p> Description: This class manages the backup functionality for articles within the help system.
 * It provides methods to save and restore article data, ensuring data preservation and
 * easy recovery of important information. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

public class BackupArticles extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        Label createdLabel = new Label();
        createdLabel.setTextFill(Color.GREEN);
        createdLabel.setText("Backup Successful");
        createdLabel.setVisible(false);

        // Creating a label for instruction
        Text instructionText = new Text("Please select the groups to Backup:");
        instructionText.setFont(Font.font("Arial", 20));

        // Backup Button
        Button backupButton = new Button("Backup Articles");
        backupButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        backupButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        backupButton.setPrefWidth(150);


        // VBox for checkboxes and instruction text centered and just above the Backup button
        VBox checkboxesLayout = new VBox(15); // 15 is the spacing between elements
        checkboxesLayout.setAlignment(Pos.CENTER);  // Align everything to the center

        List<CheckBox> groupCheckBoxes = new ArrayList<>();

        int iterations = 0;
        HBox currentHBox = new HBox(15);  // Create the first HBox with 15px spacing
        currentHBox.setAlignment(Pos.CENTER);  // Center-align the elements

        for (Group grp : GROUP_LIST) {
            if(grp.getAdmins().contains(CURRENT_USER.getUserName()) || CURRENT_USER.isAdmin())
            {
                CheckBox checkBox = new CheckBox(grp.getName());
                checkBox.setFont(Font.font("Arial", 14));
                groupCheckBoxes.add(checkBox);
                currentHBox.getChildren().add(checkBox);  // Add checkboxes to the current HBox
                iterations++;

                // If 6 checkboxes have been added, create a new HBox
                if (iterations % 6 == 0) {
                    checkboxesLayout.getChildren().add(currentHBox);  // Add the current HBox to the VBox
                    currentHBox = new HBox(15);  // Create a new HBox for the next set of 6 checkboxes
                    currentHBox.setAlignment(Pos.CENTER);  // Center-align the new HBox
                }
            }
        }

        // If there are remaining checkboxes (less than 6 in the last row), add the last HBox
        if (!currentHBox.getChildren().isEmpty()) {
            checkboxesLayout.getChildren().add(currentHBox);  // Add the last HBox to the VBox
        }

        // Label and TextField for entering the backup file name, placed side by side in an HBox
        Label fileNameLabel = new Label("Enter backup file name:");
        fileNameLabel.setFont(Font.font("Arial", 14));
        TextField fileNameField = new TextField();
        fileNameField.setPrefWidth(200);

        // HBox to align the label and text field horizontally
        HBox fileNameLayout = new HBox(10); // 10px spacing between label and text field
        fileNameLayout.setAlignment(Pos.CENTER);
        fileNameLayout.getChildren().addAll(fileNameLabel, fileNameField);

        // Center layout for checkboxes and backup button
        VBox contentLayout = new VBox(20); // 20 is the spacing between elements
        contentLayout.setPadding(new Insets(20));
        contentLayout.setAlignment(Pos.CENTER);
        contentLayout.getChildren().addAll(instructionText,checkboxesLayout, fileNameLayout,errorLabel,backupButton,createdLabel);

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        logoutButton.setPrefWidth(100);  // Set preferred width to control the button size
        logoutButton.setVisible(false);

        backupButton.setOnAction(event -> {
            try {
                createBackup(fileNameField,groupCheckBoxes,errorLabel,createdLabel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Top bar layout with Back and Logout button
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);  // Align back button to the left

        HBox rightBox = new HBox(logoutButton);
        rightBox.setAlignment(Pos.TOP_RIGHT);  // Align logout button to the right

        topBar.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(rightBox, Priority.ALWAYS); // Make the rightBox grow to push the logout button to the right

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(contentLayout); // Add checkboxes and backup button in the center
        root.setStyle("-fx-background-color: #f8f5f3;");  // Set the background color similar to AdminPage

        Scene backupScene = new Scene(root, 900, 700);  // Set the window size to 800x600

        primaryStage.setTitle("Backup Page");
        primaryStage.setScene(backupScene);
        primaryStage.setResizable(false);  // Disable resizing for fixed size
        primaryStage.show();
    }

    private void showPreviousScreen(Stage primaryStage) {
        ArticlesPage articles = new ArticlesPage();
        InstructorsArticlePage instructorsArticlePage = new InstructorsArticlePage();
        try {
            if(CURRENT_USER.isAdmin())
            {
                articles.start(primaryStage);
            }
            else{
                instructorsArticlePage.start(primaryStage);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createBackup(TextField textField, List<CheckBox> CheckBoxList,Label error,Label message) throws IOException {
        message.setVisible(false);
        error.setVisible(false);
        String fileName = textField.getText();
        boolean isGroupSelected = false;
        for(CheckBox checkBox : CheckBoxList)
        {
            if(checkBox.isSelected())
            {
                isGroupSelected = true;
            }
        }

        if(!isGroupSelected || fileName.isEmpty())
        {
            error.setText("Please select atleast one group and enter a file name.");
            error.setVisible(true);
        }

        if(!fileName.isEmpty() && isGroupSelected)
        {
            Update_DB UDB = new Update_DB();

            if(!UDB.isFileUnique(fileName))
            {
                error.setText("File name in use. Enter another name");
                error.setVisible(true);
                return;
            }
            List<String> selectedGroups = new ArrayList<>();

            for(CheckBox checkBox : CheckBoxList)
            {
                if(checkBox.isSelected())
                {
                    selectedGroups.add(checkBox.getText());
                }
            }



            if(!UDB.checkDupBackup(fileName))
            {
                UDB.writeBackup(fileName,selectedGroups);
                message.setVisible(true);
                textField.setText("");
                for(CheckBox checkBox : CheckBoxList)
                {
                    checkBox.setSelected(false);
                }
                error.setVisible(false);
            }
            else
            {
                error.setText("File name in use. Enter another name");
                error.setVisible(true);
            }

        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
