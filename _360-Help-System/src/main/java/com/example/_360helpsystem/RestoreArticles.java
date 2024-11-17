package com.example._360helpsystem;

import Backend.Article;
import Backend.ArticleList;
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

import java.util.ArrayList;
import java.util.List;

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;
import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

/*******
 * <p> RestoreArticles Class </p>
 *
 * <p> Description: This class manages the restoration functionality for articles within the help system.
 * It provides methods to retrieve and reinstate article data from backups, ensuring data consistency
 * and availability when needed. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

public class RestoreArticles extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating a label for instruction
        Text instructionText = new Text("Choose the backup file to be restored");
        instructionText.setFont(Font.font("Arial", 20));

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        Label createdLabel = new Label();
        createdLabel.setTextFill(Color.GREEN);
        createdLabel.setText("Backup Successful");
        createdLabel.setVisible(false);


        ComboBox<String> backupListBox = new ComboBox<>();
        backupListBox.getItems().addAll(new Update_DB().getBackupList());
        backupListBox.setValue("");

        boolean backupExist;

        if(backupListBox.getItems().isEmpty()) {
            backupListBox.setVisible(false);
            errorLabel.setText("No backups found!");
            errorLabel.setVisible(true);
            backupExist = false;
        } else {
            backupExist = true;
        }


        // Remove all the existing help articles from the selected groups Button
        Button removeAllButton = new Button("Overwrite existing articles");
        removeAllButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        removeAllButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        removeAllButton.setMinWidth(Control.USE_COMPUTED_SIZE);
        removeAllButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        removeAllButton.setOnAction(e -> { if(backupExist)
        {
            overwriteBackup(backupListBox,errorLabel,createdLabel);
        }});

        // Merge with existing articles Button
        Button mergeToExistingButton = new Button("Merge with existing articles");
        mergeToExistingButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        mergeToExistingButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        mergeToExistingButton.setMinWidth(Control.USE_COMPUTED_SIZE);
        mergeToExistingButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        mergeToExistingButton.setOnAction(e -> {if(backupExist)
        {
            mergeBackup(backupListBox,errorLabel,createdLabel);
        }});

        // Center layout for checkboxes and backup button
        VBox contentLayout = new VBox(20); // 20 is the spacing between elements
        contentLayout.setPadding(new Insets(20));
        contentLayout.setAlignment(Pos.CENTER);
        contentLayout.getChildren().addAll(instructionText,backupListBox,errorLabel, removeAllButton, mergeToExistingButton,createdLabel);

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        logoutButton.setFont(javafx.scene.text.Font.font("Arial", 18));
        logoutButton.setPrefWidth(100);  // Set preferred width to control the button size
        logoutButton.setVisible(false);

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

        Scene backupScene = new Scene(root, 900, 700);  // Set the window size to 900x700

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

    public void mergeBackup(ComboBox<String> backupListBox,Label errorLabel,Label createdLabel)
    {
        errorLabel.setVisible(false);
        createdLabel.setVisible(false);
            String fileToRestore = backupListBox.getValue();
            if(fileToRestore.isEmpty())
            {
                errorLabel.setVisible(true);
                errorLabel.setText("Choose a backup file!");
            }
            else{
                ArticleList articles = new Update_DB().readBackup(fileToRestore);
                if(articles != null)
                {
                    for(Article article : articles)
                    {
                        if(!ARTICLE_LIST.contains(article))
                        {
                            ARTICLE_LIST.addArticle(article);
                            createdLabel.setVisible(true);
                            createdLabel.setText("Restore Successful");
                            errorLabel.setVisible(false);
                            backupListBox.setValue("");
                        }
                    }
                }
                else{
                    errorLabel.setVisible(true);
                    errorLabel.setText("Access Denied!");
                }

            }


    }

    public void overwriteBackup(ComboBox<String> backupListBox,Label errorLabel,Label createdLabel)
    {
        errorLabel.setVisible(false);
        createdLabel.setVisible(false);
            String fileToRestore = backupListBox.getValue();
            if(fileToRestore.isEmpty())
            {
                errorLabel.setVisible(true);
                errorLabel.setText("Choose a backup file!");
            }
            else{

                ArticleList articles = new Update_DB().readBackup(fileToRestore);
                if(articles != null)
                {
                    ARTICLE_LIST = articles;
                    createdLabel.setVisible(true);
                    createdLabel.setText("Restore Successful");
                    errorLabel.setVisible(false);
                    backupListBox.setValue("");
                }
                else{
                    errorLabel.setVisible(true);
                    errorLabel.setText("Access Denied!");
                }

            }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
