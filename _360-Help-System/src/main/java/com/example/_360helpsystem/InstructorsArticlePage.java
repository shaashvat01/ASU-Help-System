package com.example._360helpsystem;

import Backend.Article;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.util.Duration;


import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;
import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

public class InstructorsArticlePage extends Application {

    private VBox articleContainerVBox; // The container to display the articles
    private Button activeButton = null;
    public static Article selectedArticle = null;

    @Override

    public void start(Stage primaryStage) {

        // ScrollPane for articles
        ScrollPane scrollPane_Article = new ScrollPane();
        scrollPane_Article.setFitToWidth(true);
        scrollPane_Article.setFitToHeight(true);
        scrollPane_Article.setStyle("-fx-background-color: transparent;");

        // ScrollPane for groups
        ScrollPane scrollPane_Groups = new ScrollPane();
        scrollPane_Groups.setFitToWidth(true);
        scrollPane_Groups.setFitToHeight(true);
        scrollPane_Groups.setStyle("-fx-background-color: transparent;");

        // Sidebar with groups
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);

        Label grpTitle = new Label("Groups");
        grpTitle.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px;");
        grpTitle.setMaxWidth(Double.MAX_VALUE);
        grpTitle.setPadding(new Insets(10, 0, 10, 0));
        grpTitle.setAlignment(Pos.CENTER);
        sidebar.getChildren().add(grpTitle);

        for (String grpName : GROUP_LIST) {
            Button groupButton = createGroupButton(grpName, primaryStage);
            sidebar.getChildren().add(groupButton);
        }

        scrollPane_Groups.setContent(sidebar);
        scrollPane_Groups.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane_Groups.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Article-related buttons
        Button createArticleBtn = new Button("Create Article");
        createArticleBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createArticleBtn.setPrefWidth(150);
        createArticleBtn.setPrefHeight(35);
        createArticleBtn.setFont(Font.font("Arial", 15));
        createArticleBtn.setOnAction(e -> showCreateArticleScreen(primaryStage));

        Button backupBtn = createStyledButton("Backup", e -> showBackupScreen(primaryStage));
        Button restoreBtn = createStyledButton("Restore", e -> showRestoreArticles(primaryStage));

        HBox articleButtons = new HBox(20, createArticleBtn, backupBtn, restoreBtn);
        articleButtons.setAlignment(Pos.CENTER);
        articleButtons.setPadding(new Insets(0, 0, 0, 0));

        articleContainerVBox = new VBox(10);
        articleContainerVBox.setPrefSize(700, 565);
        articleContainerVBox.setStyle("-fx-background-color: white;");
        scrollPane_Article.setContent(articleContainerVBox);
        scrollPane_Article.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane_Article.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox articleSection = new VBox(20, articleButtons, scrollPane_Article);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        TextField searchField = new TextField();
        searchField.setPromptText("Search articles...");
        searchField.setPrefWidth(250);

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        searchButton.setFont(Font.font("Arial", 15));
        searchButton.setPrefHeight(29);
        searchButton.setOnAction(e -> displayArticlesForSearch(searchField.getText(), primaryStage));

        Button filterButton = new Button("Filter");
        filterButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        filterButton.setFont(Font.font("Arial", 15));
        filterButton.setPrefHeight(29);

        HBox searchBar = new HBox(5, searchField, searchButton, filterButton);
        searchBar.setAlignment(Pos.CENTER);
        searchBar.setPadding(new Insets(10, 0, 0, 240));

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);
        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);
        HBox rightBox = new HBox(createArticleBtn);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        topBar.getChildren().addAll(leftBox, searchBar, rightBox);
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        HBox mainLayout = new HBox(scrollPane_Groups, articleSection);

        BorderPane mainContent = new BorderPane();
        mainContent.setTop(topBar);
        mainContent.setCenter(mainLayout);
        mainContent.setStyle("-fx-background-color: #f8f5f3;");

        VBox filterPanel = createFilterPanel();

        StackPane root = new StackPane(mainContent, filterPanel);
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Instructor Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        TranslateTransition slideIn = new TranslateTransition(Duration.millis(300), filterPanel);
        slideIn.setFromX(900);
        slideIn.setToX(0);

        TranslateTransition slideOut = new TranslateTransition(Duration.millis(300), filterPanel);
        slideOut.setFromX(0);
        slideOut.setToX(900);

        filterButton.setOnAction(e -> {
            filterPanel.setVisible(true);
            slideIn.play();
        });

        Button saveButton = (Button) filterPanel.lookup("#saveButton");
        saveButton.setOnAction(e -> slideOut.play());
        slideOut.setOnFinished(e -> filterPanel.setVisible(false));

        displayArticlesForGroup("General", primaryStage);
    }

    private VBox createFilterPanel() {
        // Filter options panel with Content Level and Group checkboxes
        VBox filterPanel = new VBox(20);
        filterPanel.setPadding(new Insets(20));
        filterPanel.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc;");
        filterPanel.setPrefWidth(300);
        filterPanel.setTranslateX(900); // Initially hidden off-screen
        filterPanel.setVisible(false); // Hidden initially

        // Content Level heading and checkboxes
        Label contentLevelLabel = new Label("Content Level:");
        contentLevelLabel.setFont(Font.font("Arial", 14));
        contentLevelLabel.setStyle("-fx-font-weight: bold;");

        CheckBox allContentCheckBox = new CheckBox("All");
        CheckBox beginnerCheckBox = new CheckBox("Beginner");
        CheckBox intermediateCheckBox = new CheckBox("Intermediate");
        CheckBox advancedCheckBox = new CheckBox("Advanced");
        CheckBox expertCheckBox = new CheckBox("Expert");

        VBox contentLevelOptions = new VBox(10, allContentCheckBox, beginnerCheckBox, intermediateCheckBox, advancedCheckBox, expertCheckBox);

        // Groups heading and checkboxes
        Label groupLabel = new Label("Groups:");
        groupLabel.setFont(Font.font("Arial", 14));
        groupLabel.setStyle("-fx-font-weight: bold;");

        CheckBox allGroupCheckBox = new CheckBox("All");
        CheckBox javafxCheckBox = new CheckBox("JavaFX");
        CheckBox eclipseCheckBox = new CheckBox("Eclipse");
        CheckBox githubCheckBox = new CheckBox("GitHub");

        VBox groupOptions = new VBox(10, allGroupCheckBox, javafxCheckBox, eclipseCheckBox, githubCheckBox);

        // Search By heading and checkboxes
        Label searchByLabel = new Label("Search By:");
        searchByLabel.setFont(Font.font("Arial", 14));
        searchByLabel.setStyle("-fx-font-weight: bold;");

        CheckBox titleCheckBox = new CheckBox("Title");
        CheckBox authorCheckBox = new CheckBox("Author");
        CheckBox uidCheckBox = new CheckBox("UID");

        VBox searchByOptions = new VBox(10, titleCheckBox, authorCheckBox, uidCheckBox);

        // Buttons for clearing and saving filter selections
        Button clearButton = new Button("Clear");
        clearButton.setFont(Font.font("Arial", 16));
        clearButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        clearButton.setPrefWidth(100);
        clearButton.setOnAction(e -> {
            // Clear all checkboxes
            allContentCheckBox.setSelected(false);
            beginnerCheckBox.setSelected(false);
            intermediateCheckBox.setSelected(false);
            advancedCheckBox.setSelected(false);
            expertCheckBox.setSelected(false);
            allGroupCheckBox.setSelected(false);
            javafxCheckBox.setSelected(false);
            eclipseCheckBox.setSelected(false);
            githubCheckBox.setSelected(false);
            titleCheckBox.setSelected(false);
            authorCheckBox.setSelected(false);
            uidCheckBox.setSelected(false);
        });

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Arial", 16));
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        saveButton.setPrefWidth(100);
        saveButton.setId("saveButton");

        HBox buttonLayout = new HBox(20, clearButton, saveButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Add all elements to the filter panel
        filterPanel.getChildren().addAll(contentLevelLabel, contentLevelOptions, groupLabel, groupOptions, searchByLabel, searchByOptions, buttonLayout);
        return filterPanel;
    }



    private Button createGroupButton(String text, Stage primaryStage) {
        Button button = new Button(text);

        // Default, hover, and active styles
        String defaultStyle = "-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-border-radius: 15; -fx-background-radius: 15; "
                + "-fx-border-color: white; -fx-border-width: 2px; "
                + "-fx-padding: 10 0 10 0;";  // Padding for uniformity

        String hoverStyle = "-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-border-radius: 15; -fx-background-radius: 15; "
                + "-fx-border-color: white; -fx-border-width: 2px; "
                + "-fx-padding: 10 0 10 0;";

        String activeStyle = "-fx-background-color: #222; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-background-radius: 15;";

        button.setStyle(defaultStyle);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);

        // Set hover effect
        button.setOnMouseEntered(e -> {
            if (button != activeButton) button.setStyle(hoverStyle);
        });
        button.setOnMouseExited(e -> {
            if (button != activeButton) button.setStyle(defaultStyle);
        });

        // Set action for when the button is clicked
        button.setOnAction(e -> {
            if (activeButton != null) activeButton.setStyle(defaultStyle);  // Reset previous active button
            button.setStyle(activeStyle);  // Apply active style to the clicked button
            activeButton = button;
            displayArticlesForGroup(text, primaryStage);
        });

        return button;
    }


    private Button createStyledButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        button.setPrefWidth(100);
        button.setPrefHeight(35);
        button.setFont(Font.font("Arial", 15));
        button.setOnAction(action);
        return button;
    }

    private void displayArticlesForGroup(String groupName,Stage primaryStage) {
        // Clear previous articles
        articleContainerVBox.getChildren().clear();

        for (Article article : ARTICLE_LIST) {
            if(article.hasGroup(groupName) || groupName.equals("General")) {
                // Create VBox for each article with padding and border
                VBox articleBox = new VBox(5);
                articleBox.setPadding(new Insets(10));
                articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                articleBox.setAlignment(Pos.TOP_LEFT);

                // Create HBox for title and level
                HBox titleLevelBox = new HBox(10);
                Label titleLabel = new Label(article.getTitle());
                titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-fill: #8b0000;");
                titleLabel.setFont(Font.font("Arial", 17));

                Label levelLabel = new Label("(" + article.getLevel() + ")");
                levelLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                levelLabel.setFont(Font.font("Arial", 14));

                titleLevelBox.getChildren().addAll(titleLabel, levelLabel);
                titleLevelBox.setAlignment(Pos.TOP_LEFT);

                // Create the 3-dots button for options
                Button optionsButton = new Button("...");
                optionsButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px;");
                optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton,primaryStage)); // Pass button reference

                // Create HBox for title, level, and options button
                HBox titleOptionsBox = new HBox();
                titleOptionsBox.getChildren().addAll(titleLevelBox, optionsButton);
                HBox.setHgrow(titleLevelBox, Priority.ALWAYS); // Make titleLevelBox grow horizontally
                titleOptionsBox.setAlignment(Pos.TOP_RIGHT); // Align the options button to the right

                // Add titleOptionsBox and abstract to articleBox (VBox)
                articleBox.getChildren().addAll(titleOptionsBox, new Label(article.getAbs()));

                // Add the articleBox (for each article) to the main VBox
                articleContainerVBox.getChildren().add(articleBox);
            }

        }
    }


    private void showPreviousScreen(Stage primaryStage) {
        InstructorPage instructorPage = new InstructorPage();
        try{
            instructorPage.start(primaryStage);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void displayArticlesForSearch(String searchText,Stage primaryStage)
    {
        articleContainerVBox.getChildren().clear();

        for (Article article : ARTICLE_LIST) {
            if(article.hasKeyword(searchText)) {
                // Create VBox for each article with padding and border
                VBox articleBox = new VBox(5);
                articleBox.setPadding(new Insets(10));
                articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                articleBox.setAlignment(Pos.TOP_LEFT);

                // Create HBox for title and level
                HBox titleLevelBox = new HBox(10);
                Label titleLabel = new Label(article.getTitle());
                titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-fill: #8b0000;");
                titleLabel.setFont(Font.font("Arial", 17));

                Label levelLabel = new Label("(" + article.getLevel() + ")");
                levelLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                levelLabel.setFont(Font.font("Arial", 14));

                titleLevelBox.getChildren().addAll(titleLabel, levelLabel);
                titleLevelBox.setAlignment(Pos.TOP_LEFT);

                // Create the 3-dots button for options
                Button optionsButton = new Button("...");
                optionsButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px;");
                optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton,primaryStage)); // Pass button reference

                // Create HBox for title, level, and options button
                HBox titleOptionsBox = new HBox();
                titleOptionsBox.getChildren().addAll(titleLevelBox, optionsButton);
                HBox.setHgrow(titleLevelBox, Priority.ALWAYS); // Make titleLevelBox grow horizontally
                titleOptionsBox.setAlignment(Pos.TOP_RIGHT); // Align the options button to the right

                // Add titleOptionsBox and abstract to articleBox (VBox)
                articleBox.getChildren().addAll(titleOptionsBox, new Label(article.getAbs()));

                // Add the articleBox (for each article) to the main VBox
                articleContainerVBox.getChildren().add(articleBox);
            }
        }
    }

    // Show options for updating or deleting an article
    private void showArticleOptions(Article article, Button optionsButton,Stage primaryStage) {
        // Create a ContextMenu (popup menu)
        ContextMenu contextMenu = new ContextMenu();

        // Create the Delete Article menu item
        MenuItem deleteItem = new MenuItem("Delete Article");
        deleteItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteItem.setOnAction(e -> {
            ARTICLE_LIST.removeArticle(article);
            displayArticlesForGroup("General",primaryStage);
            contextMenu.hide(); // Hide the context menu after action
        });

        // Create the Update Article menu item
        MenuItem updateItem = new MenuItem("Update Article");
        updateItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        updateItem.setOnAction(e -> {
            System.out.println("Updating article: " + article.getUID());
            selectedArticle = article;
            showCreateArticleScreen(primaryStage); //go to article screen
            contextMenu.hide(); // Hide the context menu after action
        });

        // Add the menu items to the context menu
        contextMenu.getItems().addAll(deleteItem, updateItem);

        // Show the context menu relative to the clicked 3-dots button
        contextMenu.show(optionsButton, Side.BOTTOM, 0, 0);
    }

    private void showCreateGroup(VBox sidebar,Stage primaryStage) {
        // Create a new Stage (window) for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Make the pop-up modal
        popupStage.setTitle("Create New Group");

        // Create a VBox layout for the pop-up content
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(15));
        popupLayout.setAlignment(Pos.CENTER);

        // Create the TextField for entering the group name
        TextField groupNameField = new TextField();
        groupNameField.setPromptText("Enter Group Name");  // Set placeholder text
        groupNameField.setPrefWidth(200);

        // Create the "Create" button
        Button createButton = new Button("Create");
        createButton.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
        createButton.setPrefWidth(100);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility

        // Handle "Create" button action
        createButton.setOnAction(event -> {
            String groupName = groupNameField.getText();
            if (!groupName.isEmpty()) {
                // Handle group creation logic here
                if(!GROUP_LIST.contains(groupName)) {
                    GROUP_LIST.addGroup(groupName);
                    System.out.println("Group Created: " + groupName);
                    sidebar.getChildren().add(createGroupButton(groupName,primaryStage));
                    // Close the pop-up after creation
                    popupStage.close();
                }

                errorLabel.setText("Group " + groupName + " already exists");


            } else {
                // You can show an alert or error message if the field is empty
                errorLabel.setText("Group name cannot be empty.");
            }
        });

        // Add the TextField and button to the layout
        popupLayout.getChildren().addAll(new Label("Group Name:"), groupNameField,errorLabel, createButton);

        // Create a Scene for the pop-up window and set it to the stage
        Scene popupScene = new Scene(popupLayout, 300, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Show the pop-up and wait until it is closed
    }

    private void showDeleteGroup(VBox sidebar) {
        // Create a new Stage (window) for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Make the pop-up modal
        popupStage.setTitle("Delete Group");

        // Create a VBox layout for the pop-up content
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(15));
        popupLayout.setAlignment(Pos.CENTER);

        // Create the TextField for entering the group name to delete
        TextField groupNameField = new TextField();
        groupNameField.setPromptText("Enter Group Name to Delete");  // Set placeholder text
        groupNameField.setPrefWidth(200);

        // Create the "Delete" button
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
        deleteButton.setPrefWidth(100);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);  // Set text color to red for visibility

        // Handle "Delete" button action
        deleteButton.setOnAction(event -> {
            String groupName = groupNameField.getText();
            if (!groupName.isEmpty()) {
                // Handle group deletion logic here
                if(GROUP_LIST.contains(groupName) && !groupName.equalsIgnoreCase("General")) {
                    GROUP_LIST.removeGroup(groupName); // Assuming removeGroup is a method in GROUP_LIST
                    System.out.println("Group Deleted: " + groupName);
                    // Remove the corresponding button from the sidebar
                    sidebar.getChildren().removeIf(node -> {
                        if (node instanceof Button) {
                            Button button = (Button) node;
                            return button.getText().equals(groupName); // Check if the button text matches
                        }
                        return false;
                    });
                    // Close the pop-up after deletion
                    popupStage.close();
                } else {
                    if(groupName.equalsIgnoreCase("General")) {
                        errorLabel.setText("Cannot delete " + groupName);
                    }
                    else{
                        errorLabel.setText("Group " + groupName + " does not exist.");
                    }

                }
            } else {
                // Show an alert or error message if the field is empty
                errorLabel.setText("Group name cannot be empty.");
            }
        });

        // Add the TextField and button to the layout
        popupLayout.getChildren().addAll(new Label("Group Name:"), groupNameField, errorLabel, deleteButton);

        // Create a Scene for the pop-up window and set it to the stage
        Scene popupScene = new Scene(popupLayout, 300, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Show the pop-up and wait until it is closed
    }





    private void showRestoreArticles(Stage primaryStage) {
        RestoreArticles restoreArticles = new RestoreArticles();
        try{
            restoreArticles.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showCreateArticleScreen(Stage primaryStage) {
        CreateArticle createArticle = new CreateArticle();
        try{
            createArticle.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showBackupScreen(Stage primaryStage) {
        BackupArticles backupArticles = new BackupArticles();
        try{
            backupArticles.start(primaryStage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
