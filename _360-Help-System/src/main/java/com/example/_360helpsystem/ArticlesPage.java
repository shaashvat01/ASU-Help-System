package com.example._360helpsystem;

import Backend.Article;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;
import static com.example._360helpsystem.CreateAdminAccount.GROUP_LIST;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

public class ArticlesPage extends Application {

    private VBox articleContainerVBox; // The container to display the articles
    private Button activeButton = null;
    public static Article selectedArticle = null;

    @Override
    public void start(Stage primaryStage) {

        // Create a ScrollPane to hold the VBox containing articles
        ScrollPane scrollPane_Article = new ScrollPane();
        scrollPane_Article.setFitToWidth(true); // Ensure the ScrollPane fits the width of the content
        scrollPane_Article.setFitToHeight(true); // Ensure the ScrollPane also fits the height of the window

        // ScrollPane styling (optional)
        scrollPane_Article.setStyle("-fx-background-color: transparent;");

        // Create a ScrollPane to hold the VBox containing groups
        ScrollPane scrollPane_Groups = new ScrollPane();
        scrollPane_Groups.setFitToWidth(true); // Ensure the ScrollPane fits the width of the content
        scrollPane_Groups.setFitToHeight(true); // Ensure the ScrollPane also fits the height of the window

        // ScrollPane styling (optional)
        scrollPane_Groups.setStyle("-fx-background-color: transparent;");

        // Create the sidebar with groups
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);  // Adjust the sidebar width

        Label grpTitle = new Label("Groups");
        grpTitle.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px;");
        grpTitle.setMaxWidth(Double.MAX_VALUE);
        grpTitle.setPadding(new Insets(10, 0, 10, 0)); // Even padding for left and right
        grpTitle.setAlignment(Pos.CENTER);  // Center the label text


        sidebar.getChildren().add(grpTitle);

        for(String grpName : GROUP_LIST)
        {
            HBox groupButton = createGroupButton(grpName,primaryStage);
            sidebar.getChildren().add(groupButton);
        }

        scrollPane_Groups.setContent(sidebar); // Add VBox to ScrollPane
        scrollPane_Groups.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane_Groups.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Disable horizontal scrollbar

        // Create article-related buttons (Create Article, Backup, Restore)
        Button createArticleBtn = new Button("Create Article");
        createArticleBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createArticleBtn.setPrefWidth(150);
        createArticleBtn.setPrefHeight(35);
        createArticleBtn.setFont(Font.font("Arial", 15));
        createArticleBtn.setOnAction(e -> showCreateArticleScreen(primaryStage));

        Button backupBtn = new Button("Backup");
        backupBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        backupBtn.setPrefWidth(100);
        backupBtn.setPrefHeight(35);
        backupBtn.setFont(Font.font("Arial", 15));
        backupBtn.setOnAction(e -> showBackupScreen(primaryStage));

        Button restoreBtn = new Button("Restore");
        restoreBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        restoreBtn.setPrefWidth(100);
        restoreBtn.setPrefHeight(35);
        restoreBtn.setFont(Font.font("Arial", 15));
        restoreBtn.setOnAction(e -> showRestoreArticles(primaryStage));

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

        HBox articleButtons = new HBox(20);
        articleButtons.setAlignment(Pos.CENTER);
        articleButtons.setPadding(new Insets(0, 0, 0, 0));

        if(CURRENT_USER != null)
        {
            if(CURRENT_USER.isAdmin() || CURRENT_USER.isInstructor())
            {
                // HBox to hold article-related buttons
                articleButtons.getChildren().addAll(createGrpBtn,deleteGrpBtn);
            }

        }

        // HBox to hold article-related buttons
        articleButtons.getChildren().addAll(createArticleBtn, backupBtn, restoreBtn);


        // VBox to hold the article container (which will display the articles dynamically)
        articleContainerVBox = new VBox(10); // This will hold dynamically added articles
        articleContainerVBox.setPrefSize(700, 565); // Set preferred size
        articleContainerVBox.setStyle("-fx-background-color: white;");

        scrollPane_Article.setContent(articleContainerVBox); // Add VBox to ScrollPane
        scrollPane_Article.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane_Article.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Disable horizontal scrollbar
        // VBox to hold the article buttons and container
        VBox articleSection = new VBox(20, articleButtons, scrollPane_Article);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        // Back Button using the ButtonStyleUtil class
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage)); // Call the showPreviousScreen method

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search articles...");
        searchField.setPrefWidth(250);  // Adjust the width of the search field
        searchField.setPrefHeight(30);

        // Search Button with "Search" text
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        searchButton.setFont(Font.font("Arial", 15));
        searchButton.setPrefHeight(29);
        searchButton.setOnAction(e -> displayArticlesForSearch(searchField.getText(),primaryStage));

        // Search bar layout
        HBox searchBar = new HBox(5, searchField, searchButton);
        searchBar.setAlignment(Pos.CENTER);
        searchBar.setPadding(new Insets(10, 0, 0, 240));


        // Top bar layout with Back, Search, and Logout buttons
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 0, 10));
        topBar.setSpacing(10);

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(createArticleBtn);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        topBar.getChildren().addAll(leftBox, searchBar, rightBox);
        topBar.setAlignment(Pos.CENTER);
        HBox.setHgrow(rightBox, Priority.ALWAYS); // Make the right box grow to fit

        // Main layout with sidebar and main content area (article section)
        HBox mainLayout = new HBox(scrollPane_Groups, articleSection);

        // Create the root layout and set the top bar and main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        displayArticlesForGroup("General",primaryStage);
    }

    private void showPreviousScreen(Stage primaryStage) {
        AdminPage adminPage = new AdminPage();
        try{
            adminPage.start(primaryStage);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private HBox createGroupButton(String text, Stage primaryStage) {
        // Main button with group name
        Button groupNameButton = new Button(text);

        // Default, hover, and active styles without an inner border
        String defaultStyle = "-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10 0 10 0;";  // Padding for uniformity

        String hoverStyle = "-fx-background-color: #555; -fx-text-fill: white; -fx-font-size: 19px; "
                + "-fx-background-radius: 15; "
                + "-fx-padding: 10 0 10 0;";

        String activeStyle = "-fx-background-color: #222; -fx-text-fill: white; -fx-font-size: 19px; -fx-background-radius: 15;";  // Active style for clicked button

        groupNameButton.setStyle(defaultStyle);
        groupNameButton.setMaxWidth(Double.MAX_VALUE);
        groupNameButton.setAlignment(Pos.CENTER);

        // Set action for when the button is clicked
        groupNameButton.setOnAction(e -> {
            if (activeButton != null) activeButton.setStyle(defaultStyle);  // Reset previous active button
            groupNameButton.setStyle(activeStyle);  // Apply active style to the clicked button
            activeButton = groupNameButton;
            displayArticlesForGroup(text, primaryStage);
        });

        // Three-dots button for options
        Button optionsButton = new Button("...");
        optionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 19px;");

        // Create a ContextMenu with a single "Manage" option
        ContextMenu contextMenu = new ContextMenu();
        MenuItem manageItem = new MenuItem("Manage");
        manageItem.setOnAction(e -> showManageDialog(primaryStage)); // Opens the manage dialog
        contextMenu.getItems().add(manageItem);

        // Show the context menu when the three-dots button is clicked
        optionsButton.setOnAction(e -> contextMenu.show(optionsButton, Side.BOTTOM, 0, 0));

        // Container for the group name button and options button
        HBox groupButtonContainer = new HBox(groupNameButton, optionsButton);
        groupButtonContainer.setAlignment(Pos.CENTER);
        groupButtonContainer.setStyle("-fx-background-color: #333; -fx-border-color: white; -fx-border-radius: 15; "
                + "-fx-background-radius: 15; -fx-padding: 10; -fx-spacing: 10;"); // Outer border only

        // Apply hover effect to the entire HBox container
        groupButtonContainer.setOnMouseEntered(e -> groupButtonContainer.setStyle(hoverStyle));
        groupButtonContainer.setOnMouseExited(e -> groupButtonContainer.setStyle(defaultStyle));

        return groupButtonContainer;
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
                    GROUP_LIST.removeGroup(groupName); // Ensure GROUP_LIST has remove() method

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
                } else {
                    if(groupName.equalsIgnoreCase("General")) {
                        errorLabel.setText("Cannot delete " + groupName);
                    }
                    else {
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


    private void showManageDialog(Stage primaryStage) {
        ManageGeneralGroup manageGeneralGroup = new ManageGeneralGroup("Admin");
        try{
            manageGeneralGroup.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to dynamically display articles for a specific group
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



        // Add the menu items to the context menu
        contextMenu.getItems().addAll(deleteItem);

        // Show the context menu relative to the clicked 3-dots button
        contextMenu.show(optionsButton, Side.BOTTOM, 0, 0);
    }

    private void showCreateGroup(VBox sidebar, Stage primaryStage) {
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

        // Create checkboxes for General Group and Special Group
        CheckBox generalGroupCheckBox = new CheckBox("General Group");
        CheckBox specialGroupCheckBox = new CheckBox("Special Group");

        // Ensure only one checkbox can be selected at a time
        generalGroupCheckBox.setOnAction(e -> {
            if (generalGroupCheckBox.isSelected()) {
                specialGroupCheckBox.setSelected(false);
            }
        });

        specialGroupCheckBox.setOnAction(e -> {
            if (specialGroupCheckBox.isSelected()) {
                generalGroupCheckBox.setSelected(false);
            }
        });

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
                // Check if a group type is selected
                if (!generalGroupCheckBox.isSelected() && !specialGroupCheckBox.isSelected()) {
                    errorLabel.setText("Please select a group type.");
                    return;
                }

                if (!GROUP_LIST.contains(groupName)) {
                    String groupType = generalGroupCheckBox.isSelected() ? "General" : "Special";

                    // Handle group creation logic here
                    GROUP_LIST.addGroup(groupName);
                    GROUP_LIST.addGroup(groupName);
                    System.out.println("Group Created: " + groupName + " (" + groupType + ")");

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
        popupLayout.getChildren().addAll(new Label("Group Name:"), groupNameField,
                generalGroupCheckBox, specialGroupCheckBox,
                errorLabel, createButton);

        // Create a Scene for the pop-up window and set it to the stage
        Scene popupScene = new Scene(popupLayout, 300, 200);
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
        CreateArticle createArticle = new CreateArticle("Admin");
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
