package com.example._360helpsystem;

/*******
 * <p> InstructorsArticlePage Class </p>
 *
 * <p> Description: This class provides the UI for instructors to manage articles
 * and groups in the help system. It includes functionalities for viewing,
 * searching, filtering, creating, and updating articles, as well as managing groups. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

import Backend.*;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example._360helpsystem.CreateAdminAccount.*;
import static com.example._360helpsystem.CreateAdminAccount.ACCESS_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;
import static com.example._360helpsystem.ArticlesPage.selectedArticle;


public class InstructorsArticlePage extends Application {

    private VBox articleContainerVBox; // The container to display the articles
    private Button activeButton = null;
//    public static Article selectedArticle = null;

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

        for (Group grp : GROUP_LIST) {
            if(grp.getUsers().contains(CURRENT_USER.getUserName())) {}
            HBox groupButton = createGroupButton(grp.getName(), primaryStage);
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

        Button createGrpBtn = new Button("Create Group");
        createGrpBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createGrpBtn.setPrefWidth(125);
        createGrpBtn.setPrefHeight(35);
        createGrpBtn.setFont(Font.font("Arial", 15));
        createGrpBtn.setOnAction(e -> showCreateGroup(sidebar,primaryStage));

        Button deleteGrpBtn = new Button("Delete Group");
        deleteGrpBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteGrpBtn.setPrefWidth(125);
        deleteGrpBtn.setPrefHeight(35);
        deleteGrpBtn.setFont(Font.font("Arial", 15));
        deleteGrpBtn.setOnAction(e -> showDeleteGroup(sidebar));

        HBox articleButtons = new HBox(20,createGrpBtn,deleteGrpBtn ,createArticleBtn, backupBtn, restoreBtn);
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

        ArrayList<CheckBox> grpFilters = new ArrayList<>();
        ArrayList<CheckBox> levelFilters = new ArrayList<>();
        VBox filterPanel = createFilterPanel(levelFilters, grpFilters);
        searchButton.setOnAction(e -> performSearch(primaryStage,searchField.getText(),levelFilters,grpFilters));

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


    private HBox createGroupButton(String text, Stage primaryStage) {
        // Main button with group name
        Button groupNameButton = new Button(text);

        // Default and active styles for the group name button
        String defaultStyle = "-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-background-radius: 15; -fx-padding: 10 0 10 0; -fx-border-color: transparent;"; // Border removed

        groupNameButton.setStyle(defaultStyle);
        groupNameButton.setMaxWidth(Double.MAX_VALUE);
        groupNameButton.setAlignment(Pos.CENTER);

        // Three-dots button for options
        Button optionsButton = new Button("...");
        optionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 19px;");

        // Create a ContextMenu with a single "Manage" option
        ContextMenu contextMenu = new ContextMenu();
        MenuItem manageItem = new MenuItem("Manage");
        manageItem.setOnAction(e -> showManageDialog(primaryStage, text)); // Opens the manage dialog
        contextMenu.getItems().add(manageItem);

        // Show the context menu when the three-dots button is clicked
        optionsButton.setOnAction(e -> contextMenu.show(optionsButton, Side.BOTTOM, 0, 0));

        // Container for the group name button and options button
        HBox groupButtonContainer = new HBox(groupNameButton, optionsButton);
        groupButtonContainer.setAlignment(Pos.CENTER);
        groupButtonContainer.setStyle("-fx-background-color: #333; -fx-border-color: white; -fx-border-radius: 15; "
                + "-fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;"); // Outer border only

        // Add hover effect
        String hoverStyle = "-fx-background-color: #444; -fx-border-color: white; -fx-border-radius: 15; "
                + "-fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;"; // Hover style

        groupButtonContainer.setOnMouseEntered(e -> groupButtonContainer.setStyle(hoverStyle));
        groupButtonContainer.setOnMouseExited(e -> {
            if (groupNameButton.equals(activeButton)) {
                // Keep active style if this is the active button
                groupButtonContainer.setStyle("-fx-background-color: #333; -fx-border-color: transparent; "
                        + "-fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;");
            } else {
                // Reset to default style otherwise
                groupButtonContainer.setStyle("-fx-background-color: #333; -fx-border-color: white; "
                        + "-fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;");
            }
        });

        // Set action for when the container (HBox) is clicked
        groupButtonContainer.setOnMouseClicked(e -> {
            // Reset the border for the previously active button's container
            if (activeButton != null) {
                ((HBox) activeButton.getParent()).setStyle("-fx-background-color: #333; -fx-border-color: white; "
                        + "-fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;");
            }

            // Update styles for the clicked button's container
            groupButtonContainer.setStyle("-fx-background-color: #333; -fx-border-color: transparent; "
                    + "-fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;");

            activeButton = groupNameButton; // Update active button reference
            displayArticlesForGroup(text, primaryStage); // Display articles for the selected group
        });

        return groupButtonContainer;
    }

    private void showManageDialog(Stage primaryStage, String groupName) {
        ManageGeneralGroup generalGroup = new ManageGeneralGroup();
        try {
            generalGroup.setGroup(groupName,primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    private void displayArticlesForGroup(String groupName, Stage primaryStage) {
        articleContainerVBox.getChildren().clear();

        for (Article article : ARTICLE_LIST) {
            if (article.hasGroup(groupName)) {
                VBox articleBox = new VBox(5);
                articleBox.setPadding(new Insets(10));
                articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                articleBox.setAlignment(Pos.TOP_LEFT);

                HBox titleLevelBox = new HBox(10);
                Label titleLabel = new Label(article.getTitle());
                titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-fill: #8b0000;");
                titleLabel.setFont(Font.font("Arial", 17));

                Label levelLabel = new Label("(" + article.getLevel() + ")");
                levelLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                levelLabel.setFont(Font.font("Arial", 14));

                titleLevelBox.getChildren().addAll(titleLabel, levelLabel);
                titleLevelBox.setAlignment(Pos.TOP_LEFT);

                Button optionsButton = new Button("...");
                optionsButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px;");
                optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton, primaryStage, "defaultOption", true));


                HBox titleOptionsBox = new HBox(titleLevelBox);
                HBox.setHgrow(titleLevelBox, Priority.ALWAYS);
                titleOptionsBox.setAlignment(Pos.TOP_RIGHT);

                if(GROUP_LIST.getGroup(groupName).getUsers().contains(CURRENT_USER.getUserName()))
                {
                    titleOptionsBox.getChildren().addAll(optionsButton);
                }

                articleBox.getChildren().addAll(titleOptionsBox, new Label(article.getAbs()));
                articleContainerVBox.getChildren().add(articleBox);
            }
        }
    }

    private void displayArticlesForSearch(String searchText, Stage primaryStage) {
        articleContainerVBox.getChildren().clear();

        // Filter articles based on search text
        List<Article> filteredArticles = ARTICLE_LIST.getArticles().stream()
                .filter(article -> article.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                        article.getAuthor().toLowerCase().contains(searchText.toLowerCase()) ||
                        article.getKeywords().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());

        // Display search results
        Label groupLabel = new Label("Search Results");
        groupLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        articleContainerVBox.getChildren().add(groupLabel);

        // Display articles by level count
        Map<String, Long> levelCounts = filteredArticles.stream()
                .collect(Collectors.groupingBy(Article::getLevel, Collectors.counting()));

        levelCounts.forEach((level, count) -> {
            Label levelLabel = new Label(level + ": " + count + " articles");
            levelLabel.setFont(Font.font("Arial", 14));
            articleContainerVBox.getChildren().add(levelLabel);
        });

        // Display articles in short form
        int sequenceNumber = 1;
        for (Article article : filteredArticles) {
            VBox articleBox = new VBox(5);
            articleBox.setPadding(new Insets(10));
            articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
            articleBox.setAlignment(Pos.TOP_LEFT);

            HBox titleLevelBox = new HBox(10);
            Label titleLabel = new Label(sequenceNumber + ". Title: " + article.getTitle());
            titleLabel.setFont(Font.font("Arial", 14));
            titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #8b0000;");

            Label levelLabel = new Label("(" + article.getLevel() + ")");
            levelLabel.setFont(Font.font("Arial", 14));
            levelLabel.setStyle("-fx-text-fill: gray;");

            titleLevelBox.getChildren().addAll(titleLabel, levelLabel);
            titleLevelBox.setAlignment(Pos.TOP_LEFT);

            Label abstractLabel = new Label("Abstract: " + article.getAbs());
            abstractLabel.setWrapText(true);
            abstractLabel.setFont(Font.font("Arial", 14));

            Button requestAccessButton = new Button("View Article");
            requestAccessButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
            requestAccessButton.setFont(Font.font("Arial", 14));
            requestAccessButton.setOnAction(e -> showArticleDetails(article));

            HBox optionsBox = new HBox(requestAccessButton);
            optionsBox.setAlignment(Pos.TOP_RIGHT);

            articleBox.getChildren().addAll(titleLevelBox, abstractLabel, optionsBox);
            articleContainerVBox.getChildren().add(articleBox);

            sequenceNumber++;
        }
    }

    private void showArticleDetails(Article article) {
        // Create a new stage for the details window
        Stage detailStage = new Stage();
        detailStage.setTitle("Article Details - " + article.getTitle());

        // Article details layout
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));
        detailsLayout.setAlignment(Pos.TOP_LEFT);

        // Title
        Label titleLabel = new Label("Title: " + article.getTitle());
        titleLabel.setFont(Font.font("Arial", 18));
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Level
        Label levelLabel = new Label("Level: " + article.getLevel());
        levelLabel.setFont(Font.font("Arial", 14));
        levelLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #7f8c8d;");

        // Abstract
        Label abstractHeading = new Label("Abstract:");
        abstractHeading.setFont(Font.font("Arial", 14));
        abstractHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label abstractLabel = new Label(article.getAbs());
        abstractLabel.setWrapText(true);

        // Keywords
        Label keywordsHeading = new Label("Keywords:");
        keywordsHeading.setFont(Font.font("Arial", 14));
        keywordsHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label keywordsLabel = new Label(article.getKeywords());
        keywordsLabel.setWrapText(true);

        // Reference Links
        Label referenceLinksHeading = new Label("Reference Links:");
        referenceLinksHeading.setFont(Font.font("Arial", 14));
        referenceLinksHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label referenceLinksLabel = new Label(article.getLinks());
        referenceLinksLabel.setWrapText(true);

        // Body or Content
        Label bodyHeading = new Label("Body:");
        bodyHeading.setFont(Font.font("Arial", 14));
        bodyHeading.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label bodyLabel = new Label(article.getBody());
        if(article.getSecurity().equals("Protected"))
        {
            bodyLabel.setText(new Encryption().decryptBody(article));
        }

        bodyLabel.setWrapText(true);

        // Add all details to the layout
        detailsLayout.getChildren().addAll(
                titleLabel,
                levelLabel,
                abstractHeading, abstractLabel,
                keywordsHeading, keywordsLabel,
                referenceLinksHeading, referenceLinksLabel,
                bodyHeading, bodyLabel
        );

        // Create and set the scene
        Scene detailScene = new Scene(detailsLayout, 600, 500);
        detailStage.setScene(detailScene);
        detailStage.show();
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


    // Show options for updating or deleting an article
    private void showArticleOptions(Backend.Article article, Button button, Stage stage, String option, boolean flag) {
        // Create a ContextMenu (popup menu)
        ContextMenu contextMenu = new ContextMenu();

        // Define menu items
        MenuItem deleteItem = new MenuItem("Delete Article");
        deleteItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        deleteItem.setOnAction(e -> {
            // Example delete logic
            ARTICLE_LIST.removeArticle(article);
            System.out.println("Deleted article: " + article.getTitle());
            contextMenu.hide();
        });

        MenuItem updateItem = new MenuItem("Update Article");
        updateItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        updateItem.setOnAction(e -> {
            System.out.println("Updating article: " + article.getTitle());
            showCreateArticleScreen(stage);
            contextMenu.hide();
        });

        MenuItem viewItem = new MenuItem("View Article");
        viewItem.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        viewItem.setOnAction(e -> {
            System.out.println("Viewing article: " + article.getTitle());
            showArticleDetails(article);
            contextMenu.hide();
        });

        // Add menu items based on conditions
        if (flag) {
            contextMenu.getItems().addAll(deleteItem, updateItem, viewItem);
        } else {
            contextMenu.getItems().add(viewItem);
        }

        // Show the context menu relative to the button
        contextMenu.show(button, Side.BOTTOM, 0, 0);
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

        // Create checkboxes for Special Group
        CheckBox specialGroupCheckBox = new CheckBox("Special Group");

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

                if (!GROUP_LIST.contains(groupName)) {
                    GROUP_LIST.addGroup(new Group(groupName,specialGroupCheckBox.isSelected()));
                    System.out.println("Group created - "+groupName+"-"+specialGroupCheckBox.isSelected());
                    sidebar.getChildren().add(createGroupButton(groupName, primaryStage));

                    // Close the pop-up after creation
                    popupStage.close();
                } else {
                    errorLabel.setText("Group " + groupName + " already exists");
                }
            } else {
                // Show error message if the field is empty
                errorLabel.setText("Group name cannot be empty.");
            }
        });

        // Add the TextField, checkboxes, and button to the layout
        popupLayout.getChildren().addAll(new Label("Group Name:"), groupNameField, specialGroupCheckBox,
                errorLabel, createButton);

        // Create a Scene for the pop-up window and set it to the stage
        Scene popupScene = new Scene(popupLayout, 300, 200);
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
                if(GROUP_LIST.contains(groupName)) {
                    if(GROUP_LIST.getGroup(groupName).isSpecial())
                    {
                        if(GROUP_LIST.getGroup(groupName).isAdmin(CURRENT_USER.getUserName()))
                        {
                            GROUP_LIST.removeGroup(groupName); // Assuming removeGroup is a method in GROUP_LIST
                            System.out.println("Group Deleted: " + groupName);
                            // Remove the corresponding button from the sidebar
                            sidebar.getChildren().removeIf(node -> {
                                if (node instanceof HBox) {
                                    HBox hBox = (HBox) node;
                                    if (hBox.getChildren().get(0) instanceof Button) {
                                        Button groupButton = (Button) hBox.getChildren().get(0);
                                        return groupButton.getText().equals(groupName); // Check if the button text matches
                                    }
                                }
                                return false;
                            });
                            // Close the pop-up after deletion
                            popupStage.close();
                        }
                    }

                } else {
                    if(GROUP_LIST.getGroup(groupName).isSpecial()) {
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

    private VBox createFilterPanel(ArrayList<CheckBox> levelFilters, ArrayList<CheckBox> grpFilters) {
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
        levelFilters.add(allContentCheckBox);
        CheckBox beginnerCheckBox = new CheckBox("Beginner");
        levelFilters.add(beginnerCheckBox);
        CheckBox intermediateCheckBox = new CheckBox("Intermediate");
        levelFilters.add(intermediateCheckBox);
        CheckBox advancedCheckBox = new CheckBox("Advanced");
        levelFilters.add(advancedCheckBox);
        CheckBox expertCheckBox = new CheckBox("Expert");
        levelFilters.add(expertCheckBox);


        VBox contentLevelOptions = new VBox(10, allContentCheckBox, beginnerCheckBox, intermediateCheckBox, advancedCheckBox, expertCheckBox);

        // Groups heading and checkboxes
        Label groupLabel = new Label("Groups:");
        groupLabel.setFont(Font.font("Arial", 14));
        groupLabel.setStyle("-fx-font-weight: bold;");

        List<CheckBox> grpCheckBoxes = new ArrayList<>();
        for(Group g : GROUP_LIST)
        {
            CheckBox checkBox = new CheckBox(g.getName());
            checkBox.setFont(Font.font("Arial", 14));
            grpCheckBoxes.add(checkBox);
            grpFilters.add(checkBox);
        }



        VBox groupOptions = new VBox(10);
        groupOptions.getChildren().addAll(grpCheckBoxes);


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
            for(CheckBox checkBox : grpCheckBoxes)
            {
                checkBox.setSelected(false);
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Arial", 16));
        saveButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        saveButton.setPrefWidth(100);
        saveButton.setId("saveButton");

        HBox buttonLayout = new HBox(20, clearButton, saveButton);
        buttonLayout.setAlignment(Pos.CENTER);

        // Add all elements to the filter panel
        filterPanel.getChildren().addAll(contentLevelLabel, contentLevelOptions, groupLabel, groupOptions/**searchByLabel, searchByOptions**/, buttonLayout);
        return filterPanel;
    }

    private void performSearch(Stage primaryStage, String searchText, ArrayList<CheckBox> levelFilters, ArrayList<CheckBox> grpFilters) {
        articleContainerVBox.getChildren().clear();

        ArrayList<String> selectedGrps = new ArrayList<>();
        ArrayList<String> selectedLevels = new ArrayList<>();
        boolean all = false;

        // Extract selected levels
        for (CheckBox checkBox : levelFilters) {
            if (checkBox.isSelected()) {
                selectedLevels.add(checkBox.getText());
                if (checkBox.getText().equalsIgnoreCase("all")) { // Case-insensitive check for "all"
                    all = true;
                }
            }
        }
        System.out.println("List of selected levels - " + selectedLevels);

        // Extract selected groups
        for (CheckBox checkBox : grpFilters) {
            if (checkBox.isSelected()) {
                selectedGrps.add(checkBox.getText());
            }
        }
        System.out.println("List of selected groups - " + selectedGrps);

        // Perform the article search
        ArrayList<Article> textResults = ArticleSearcher.searchArticles(ARTICLE_LIST.getArticles(), searchText);
        ArrayList<Article> filteredResults = new ArrayList<>();

        for (Article article : textResults) {
            boolean matchLevel = selectedLevels.contains(article.getLevel()) || selectedLevels.isEmpty();
            boolean matchGroup = selectedGrps.stream().anyMatch(article::hasGroup) || all || selectedGrps.isEmpty();

            if (matchLevel && matchGroup) {
                filteredResults.add(article);
            }
        }

        System.out.println("Result articles - ");
        for (Article article : filteredResults) {
            System.out.println(article.getTitle() + "-" + article.getAbs());
        }

        // Display active group

        Label groupLabel = new Label("Active Group: " + String.join(", ", selectedGrps));
        if(all)
        {
            groupLabel.setText("All");
        }

        groupLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        articleContainerVBox.getChildren().add(groupLabel);

        // Count articles by level and display
        Map<String, Long> levelCounts = filteredResults.stream()
                .collect(Collectors.groupingBy(Article::getLevel, Collectors.counting()));

        levelCounts.forEach((level, count) -> {
            Label levelLabel = new Label(level + ": " + count + " articles");
            levelLabel.setFont(Font.font("Arial", 14));
            articleContainerVBox.getChildren().add(levelLabel);
        });

        // Display filtered articles
        int sequenceNumber = 1; // Start with 1 instead of 0
        for (Article article : filteredResults) {
            VBox articleBox = new VBox(5);
            articleBox.setPadding(new Insets(10));
            articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
            articleBox.setAlignment(Pos.TOP_LEFT);

            Label titleLabel = new Label(sequenceNumber + ". Title: " + article.getTitle());
            titleLabel.setFont(Font.font("Arial", 14));
            titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #8b0000;");

            Label authorLabel = new Label("Author(s): " + article.getAuthor());
            authorLabel.setFont(Font.font("Arial", 14));

            Label abstractLabel = new Label("Abstract: " + article.getAbs());
            abstractLabel.setWrapText(true);
            abstractLabel.setFont(Font.font("Arial", 14));

            HBox optionsBox = new HBox();
            optionsBox.setAlignment(Pos.TOP_RIGHT);

            boolean hasAccess = article.getGroups().stream()
                    .anyMatch(group -> GROUP_LIST.contains(group) &&
                            GROUP_LIST.getGroup(group).getUsers().contains(CURRENT_USER.getUserName()));


            // Create the 3-dots button for options
            Button optionsButton = new Button("...");
            optionsButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px;");
            optionsButton.setOnAction(e -> showArticleOptions(article, optionsButton, primaryStage, "defaultOption", true));

            optionsBox.getChildren().add(optionsButton);

            articleBox.getChildren().addAll(titleLabel, authorLabel, abstractLabel, optionsBox);
            articleContainerVBox.getChildren().add(articleBox);

            sequenceNumber++;
        }
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