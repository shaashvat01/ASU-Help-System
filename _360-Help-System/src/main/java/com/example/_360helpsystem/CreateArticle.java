package com.example._360helpsystem;

import Backend.Article;
import Backend.UID_Generator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import static com.example._360helpsystem.ArticlesPage.selectedArticle;
import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;
import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

/*******
 * <p> CreateArticle Class </p>
 *
 * <p> Description: This class manages the creation of new articles within the help system.
 * It provides an interface for users to input and save article information, enabling
 * organized addition of content to the system. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

public class CreateArticle extends Application {

    private String previousScreen; // To store the reference to the previous screen

    // Constructor to accept the previous screen
    public CreateArticle(String previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void start(Stage primaryStage) {

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);


        // Create an HBox to center the errorLabel
        HBox errorLabelContainer = new HBox(errorLabel);
        errorLabelContainer.setAlignment(Pos.CENTER);  // Center the error label horizontally

        Label message = new Label();
        message.setTextFill(Color.GREEN);
        message.setText("Article Created");
        message.setVisible(false);

        HBox messageContainer = new HBox(message);
        messageContainer.setAlignment(Pos.CENTER);

        // Title Label and TextField
        Label titleLabel = new Label("Title:");
        titleLabel.setFont(Font.font("Arial", 14));
        titleLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField titleField = new TextField();
        titleField.setFont(Font.font("Arial", 14));  // Reduced font size
        titleField.setPrefWidth(400);  // Increased width

        // Level Label and ComboBox (with "Choose a level" option)
        Label levelLabel = new Label("Level:");
        levelLabel.setFont(Font.font("Arial", 14));
        levelLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        ComboBox<String> levelComboBox = new ComboBox<>();
        levelComboBox.getItems().addAll("Beginner","Intermediate", "Advanced", "Expert");
        levelComboBox.setValue("");  // Set default option
        levelComboBox.setStyle("-fx-font-size: 14px;");  // Smaller font for consistency

        // Create a layout to place Title and Level vertically
        VBox titleAndLevelLayout = new VBox(10);  // Vertical box layout with spacing of 10px
        titleAndLevelLayout.setAlignment(Pos.CENTER_LEFT);  // Align to the left
        titleAndLevelLayout.getChildren().addAll(titleLabel, titleField, levelLabel, levelComboBox);

        // Description Label and TextField
        Label descriptionLabel = new Label("Short Description:");
        descriptionLabel.setFont(Font.font("Arial", 14));
        descriptionLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField descriptionField = new TextField();
        descriptionField.setFont(Font.font("Arial", 14));  // Reduced font size
        descriptionField.setPrefWidth(400);

        // Keywords Label and TextField
        Label keywordsLabel = new Label("Keywords: ( separate the words with a comma )");
        keywordsLabel.setFont(Font.font("Arial", 14));
        keywordsLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField keywordsField = new TextField();
        keywordsField.setFont(Font.font("Arial", 14));  // Reduced font size
        keywordsField.setPrefWidth(400);

        // Reference Links Label and TextField
        Label referenceLinksLabel = new Label("Reference Links:");
        referenceLinksLabel.setFont(Font.font("Arial", 14));
        referenceLinksLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextField referenceLinksField = new TextField();
        referenceLinksField.setFont(Font.font("Arial", 14));  // Reduced font size
        referenceLinksField.setPrefWidth(400);

        // Grouping Identifiers Label and CheckBoxes
        Label groupLabel = new Label("Grouping Identifiers:");
        groupLabel.setFont(Font.font("Arial", 14));
        groupLabel.setStyle("-fx-font-weight: bold;");

        // HBox to hold the CheckBoxes in a row
        HBox groupCheckBoxLayout = new HBox(10);  // Horizontal layout with 10px spacing
        groupCheckBoxLayout.setAlignment(Pos.CENTER_LEFT);

        List<CheckBox> groupCheckBoxes = new ArrayList<>();

        for(String grpName : GROUP_LIST)
        {
            if(!grpName.equals("General"))
            {
                CheckBox checkBox = new CheckBox(grpName);
                checkBox.setFont(Font.font("Arial", 14));
                groupCheckBoxes.add(checkBox);
                groupCheckBoxLayout.getChildren().addAll(checkBox);
            }
        }

        // Body Label and adjustable TextArea (positioned at the bottom)
        Label bodyLabel = new Label("Body:");
        bodyLabel.setFont(Font.font("Arial", 14));
        bodyLabel.setStyle("-fx-font-weight: bold;");  // Make the label bold
        TextArea bodyField = new TextArea();
        bodyField.setPromptText("Body of the help article");
        bodyField.setFont(Font.font("Arial", 14));  // Reduced font size
        bodyField.setPrefWidth(400);
        bodyField.setPrefHeight(200);  // Increased height for the TextArea
        bodyField.setWrapText(true);  // Allow text wrapping in the TextArea

        // Buttons for actions (centered)
        Button saveButton = new Button("Save Article");
        saveButton.setFont(Font.font("Arial", 16));
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        saveButton.setPrefWidth(150);  // Button width
        saveButton.setOnAction(e -> createArticle(titleField, descriptionField, keywordsField, bodyField, referenceLinksField, groupCheckBoxes,levelComboBox,errorLabel,message));


        Button clearButton = new Button("Clear Fields");
        clearButton.setFont(Font.font("Arial", 16));
        clearButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        clearButton.setPrefWidth(150);  // Button width
        clearButton.setOnAction(e -> clearFields(titleField, descriptionField, keywordsField, bodyField, referenceLinksField, groupCheckBoxes,levelComboBox,errorLabel,message));

        // Layout for buttons (centered horizontally)
        HBox buttonLayout = new HBox(20);  // Horizontal box layout with spacing between buttons
        buttonLayout.setAlignment(Pos.CENTER);  // Center the buttons
        buttonLayout.getChildren().addAll(saveButton, clearButton);

        // Layout for form fields (stacked vertically)
        VBox formLayout = new VBox(15);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER_LEFT);  // Align fields to the left
        formLayout.getChildren().addAll(titleAndLevelLayout, descriptionLabel, descriptionField, keywordsLabel, keywordsField, referenceLinksLabel, referenceLinksField, groupLabel, groupCheckBoxLayout, bodyLabel, bodyField,errorLabelContainer, buttonLayout,messageContainer);

        // Create Back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Logout button
        Button logoutButton = new Button("");
        logoutButton.setVisible(false);


        // Layout for the top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(5, 10, 0, 10));  // Reduced padding to move the bar up
        topBar.setAlignment(Pos.CENTER);

        HBox leftBox = new HBox(backButton);  // Back button aligned to the left
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(logoutButton);  // Logout button aligned to the right
        rightBox.setAlignment(Pos.TOP_RIGHT);

        topBar.getChildren().addAll(leftBox, rightBox);
        HBox.setHgrow(rightBox, Priority.ALWAYS);  // Make the right box grow to push the Logout button to the right


        if(selectedArticle != null)
        {
            titleField.setText(selectedArticle.getTitle());
            levelComboBox.setValue(selectedArticle.getLevel());
            descriptionField.setText(selectedArticle.getBody());
            keywordsField.setText(selectedArticle.getKeywords());
            bodyField.setText(selectedArticle.getBody());
            referenceLinksField.setText(selectedArticle.getLinks());
            for(CheckBox checkBox : groupCheckBoxes)
            {
                if(selectedArticle.hasGroup(checkBox.getText()))
                {
                    checkBox.setSelected(true);
                }
            }
        }

        // Main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(formLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Increased screen size
        Scene scene = new Scene(root, 900, 700);  // Increased window size

        primaryStage.setTitle("Create Article");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true); // Allow window resizing
        primaryStage.show();
    }

    // Method to clear all fields
    private void clearFields(TextField titleField, TextField descriptionField, TextField keywordsField, TextArea bodyField, TextField referenceLinksField, List<CheckBox> groupCheckBoxes,ComboBox<String> levelBox,Label errorLabel,Label message) {
        errorLabel.setVisible(false);
        message.setVisible(false);
        titleField.clear();
        descriptionField.clear();
        keywordsField.clear();
        bodyField.clear();
        referenceLinksField.clear();
        levelBox.setValue("");
        for (CheckBox checkBox : groupCheckBoxes) {
            checkBox.setSelected(false);  // Deselect all checkboxes
        }
    }

    public void createArticle(TextField titleField, TextField descriptionField, TextField keywordsField, TextArea bodyField, TextField referenceLinksField, List<CheckBox> groupCheckBoxes, ComboBox<String> levelBox, Label errorLabel,Label message) {
        // Print diagnostic info
        System.out.println("Creating article with fields:");
        System.out.println("Title: " + titleField.getText());
        System.out.println("Description: " + descriptionField.getText());
        System.out.println("Keywords: " + keywordsField.getText());
        System.out.println("Body: " + bodyField.getText());
        System.out.println("Reference Links: " + referenceLinksField.getText());
        System.out.println("Level: " + levelBox.getValue());

        message.setVisible(false);
        errorLabel.setVisible(false);

        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();
        String keywords = keywordsField.getText().trim();
        String body = bodyField.getText().trim();
        String referenceLinks = referenceLinksField.getText().trim();
        String level = (levelBox.getValue() != null) ? levelBox.getValue().trim() : "";

        if (title.isEmpty() || description.isEmpty() || keywords.isEmpty() || body.isEmpty() || referenceLinks.isEmpty() || level.isEmpty()) {
            errorLabel.setText("All fields must be filled.");
            errorLabel.setVisible(true);
            return;
        }

        // Collect selected group identifiers
        StringBuilder selectedGrpBuilder = new StringBuilder("General");
        for (CheckBox checkBox : groupCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedGrpBuilder.append(",").append(checkBox.getText());
            }
        }

        String selectedGrp = selectedGrpBuilder.toString();

        System.out.println("Selected group: " + selectedGrp);

        String security = "Public";

        if(selectedArticle == null)
        {
            long articleUID = new UID_Generator().getUID();
            Article newArticle = new Article(articleUID, title, CURRENT_USER.getUserName(), level, security, description, keywords, body, referenceLinks, selectedGrp);

            ARTICLE_LIST.addArticle(newArticle);
            System.out.println("Article created: " + newArticle); // Placeholder for actual save operation
            clearFields(titleField, descriptionField, keywordsField, bodyField, referenceLinksField, groupCheckBoxes, levelBox,errorLabel,message);
            message.setText("Article Created!");
            message.setVisible(true);
        }
        else {
            Article newArticle = new Article(selectedArticle.getUID(), title, selectedArticle.getAuthor(), level, security, description, keywords, body, referenceLinks, selectedGrp);
            ARTICLE_LIST.getArticleByUID(selectedArticle.getUID()).replaceArticle(newArticle);
            clearFields(titleField, descriptionField, keywordsField, bodyField, referenceLinksField, groupCheckBoxes, levelBox,errorLabel,message);
            selectedArticle = null;
            message.setText("Article Updated!");
            message.setVisible(true);
        }


    }

    private void showPreviousScreen(Stage primaryStage) {
        try {
            switch (previousScreen) {
                case "Admin":
                    new ArticlesPage().start(primaryStage);
                    break;
                case "Instructor":
                    new InstructorsArticlePage().start(primaryStage);
                    break;
                case "ManageGeneralGroup":
                    new ManageGeneralGroup("AdminOrInstructor").start(primaryStage); // Pass "AdminOrInstructor" or similar identifier to distinguish between Admin or Instructor
                    break;
                default:
                    System.out.println("Unknown previous screen: " + previousScreen);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}