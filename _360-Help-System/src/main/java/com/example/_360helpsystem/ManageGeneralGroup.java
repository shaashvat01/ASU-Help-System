package com.example._360helpsystem;

/*******
 * <p> ManageGeneralGroup Class </p>
 *
 * <p> Description: This class provides a user interface to manage a specific group in the help system.
 * It includes functionalities to view articles, add/remove users, manage permissions, and handle access requests
 * for the selected group. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

import Backend.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example._360helpsystem.CreateAdminAccount.*;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

public class ManageGeneralGroup extends Application {

    private VBox mainContentArea;
    private String groupName;

    @Override
    public void start(Stage primaryStage) {
        // Default to "General" group if no group name is provided
        System.out.println("Current Group - "+this.groupName);
        initialize(primaryStage,this.groupName);
    }

    public void initialize(Stage primaryStage, String groupName) {

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        Button createArticleBtn = new Button("Create Article");
        createArticleBtn.setVisible(false);
        createArticleBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createArticleBtn.setFont(Font.font("Arial", 15));
        createArticleBtn.setPrefWidth(150);
        createArticleBtn.setPrefHeight(35);
        createArticleBtn.setOnAction(e -> showCreateArticleScreen(primaryStage));

        HBox leftBox = new HBox(backButton);
        leftBox.setAlignment(Pos.CENTER_LEFT);

        HBox rightBox = new HBox(createArticleBtn);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        HBox.setHgrow(rightBox, Priority.ALWAYS);
        topBar.getChildren().addAll(leftBox, rightBox);

        Button articlesButton = createSidebarButtonWithIcon("Articles", "📄");
        Button addUserButton = createSidebarButtonWithIcon("Add User", "➕");
        Button removeUserButton = createSidebarButtonWithIcon("Remove User", "➖");
        Button permissionsButton = createSidebarButtonWithIcon("Permissions", "🔑");
        Button AccessRequestsButton = createSidebarButtonWithIcon("Access Requests", "🔑");

        articlesButton.setOnAction(e -> showArticlesForGroup());
        addUserButton.setOnAction(e -> showAddUserScreen());
        removeUserButton.setOnAction(e -> showRemoveUserScreen());
        permissionsButton.setOnAction(e -> showPermissionsScreen());
        AccessRequestsButton.setOnAction(e -> showRequestScreen());

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(170);  // Fixed width for sidebar
        sidebar.getChildren().addAll(articlesButton, addUserButton, removeUserButton, permissionsButton, AccessRequestsButton);

        // Set up a ScrollPane for the main content area with fixed dimensions
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(700);  // Fixed width for content area
        scrollPane.setPrefHeight(600); // Fixed height for content area
        scrollPane.setStyle("-fx-background-color: transparent;");

        mainContentArea = new VBox(20);
        mainContentArea.setAlignment(Pos.TOP_CENTER);
        mainContentArea.setPrefSize(700, 630);
        mainContentArea.setStyle("-fx-background-color: white;"); // Set white background for content area

        scrollPane.setContent(mainContentArea); // Add main content area to scroll pane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox mainLayout = new HBox(sidebar, scrollPane);
        mainLayout.setSpacing(20); // Optional spacing between sidebar and content

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Manage " + groupName + " Group");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initially display articles for the given group
        showArticlesForGroup();
    }

    private void showCreateArticleScreen(Stage primaryStage) {
        CreateArticle createArticle = new CreateArticle();
        try{
            createArticle.start(primaryStage);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private Button createSidebarButtonWithIcon(String text, String icon) {
        Button button = new Button(icon + " " + text);
        button.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: CENTER_LEFT;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPadding(new Insets(10, 10, 10, 10));
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }

    private void showArticlesForGroup() {
        mainContentArea.getChildren().clear();

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

                articleBox.getChildren().addAll(titleLevelBox, new Label(article.getAbs()));
                mainContentArea.getChildren().add(articleBox);
            }
        }
    }


    // Method to load the "Add User" section in the main content area
    private void showAddUserScreen() {
        mainContentArea.getChildren().clear();

        Text nameTitle = new Text("Add User");
        nameTitle.setFont(Font.font("Arial", 24));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter user name");
        nameField.setMaxWidth(300);

        Button addButton = new Button("Add User");
        addButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        addButton.setFont(Font.font("Arial", 18));
        addButton.setOnAction(e -> addUser(nameField.getText()));

        VBox addUserContent = new VBox(10, nameTitle, nameField, addButton);
        addUserContent.setAlignment(Pos.CENTER);

        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        mainContentArea.getChildren().addAll(topSpacer, addUserContent, bottomSpacer);
    }

    // Method to load the "Remove User" section in the main content area
    private void showRemoveUserScreen() {
        mainContentArea.getChildren().clear();

        Text nameTitle = new Text("Remove User");
        nameTitle.setFont(Font.font("Arial", 24));

        TextField nameField = new TextField();
        nameField.setPromptText("Enter user name");
        nameField.setMaxWidth(300);

        Button removeButton = new Button("Remove User");
        removeButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        removeButton.setFont(Font.font("Arial", 18));
        removeButton.setOnAction(e -> removeUser(nameField.getText()));

        VBox removeUserContent = new VBox(10, nameTitle, nameField, removeButton);
        removeUserContent.setAlignment(Pos.CENTER);

        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        mainContentArea.getChildren().addAll(topSpacer, removeUserContent, bottomSpacer);
    }

    // Method to load the "Permissions" section in the main content area
    private void showPermissionsScreen() {
        mainContentArea.getChildren().clear();

        GridPane permissionsLayout = new GridPane();
        permissionsLayout.setPadding(new Insets(20));
        permissionsLayout.setVgap(20);
        permissionsLayout.setHgap(20);
        permissionsLayout.setAlignment(Pos.CENTER);

        // Headers
        Label usernameHeader = new Label("Username");
        Label roleHeader = new Label("Roles");
        Label actionsHeader = new Label("Actions");

        permissionsLayout.add(usernameHeader, 0, 0);
        permissionsLayout.add(roleHeader, 1, 0, 2, 1); // Span 2 columns for roleHeader
        permissionsLayout.add(actionsHeader, 3, 0, 2, 1); // Span 2 columns for actionsHeader

        // Role and Action labels
        Label studentLabel = new Label("S");
        Label instructorLabel = new Label("I");
        Label viewLabel = new Label("Read");
        Label adminRightsLabel = new Label("Admin");

        permissionsLayout.add(studentLabel, 1, 1);
        permissionsLayout.add(instructorLabel, 2, 1);
        permissionsLayout.add(viewLabel, 3, 1);
        permissionsLayout.add(adminRightsLabel, 4, 1);

        int rowIndex = 2;

        for (String username : GROUP_LIST.getGroup(this.groupName).getUsers()) {
            if (!username.equals(CURRENT_USER.getUserName())) {
                Label usernameLabel = new Label(username);

                CheckBox studentCheckbox = new CheckBox();
                studentCheckbox.setDisable(true);

                CheckBox instructorCheckbox = new CheckBox();
                instructorCheckbox.setDisable(true);

                CheckBox viewCheckbox = new CheckBox();
                viewCheckbox.setDisable(true);

                CheckBox adminRightsCheckbox = new CheckBox();
                adminRightsCheckbox.setDisable(true);

                if (USER_LIST.findUser(username).isInstructor()) {
                    instructorCheckbox.setSelected(true);
                }
                if (USER_LIST.findUser(username).isStudent()) {
                    studentCheckbox.setSelected(true);
                }

                Button grantAdminButton = new Button();
                if (GROUP_LIST.getGroup(this.groupName).isAdmin(username)) {
                    adminRightsCheckbox.setSelected(true);
                    grantAdminButton.setText("Remove Admin");
                } else {
                    viewCheckbox.setSelected(true);
                    grantAdminButton.setText("Grant Admin");
                }

                grantAdminButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                grantAdminButton.setOnAction(e -> {
                    if (adminRightsCheckbox.isSelected()) {
                        removeAdmin(username, adminRightsCheckbox, viewCheckbox, grantAdminButton);
                    } else {
                        makeAdmin(username, adminRightsCheckbox, viewCheckbox, grantAdminButton);
                    }
                });

                permissionsLayout.add(usernameLabel, 0, rowIndex);
                permissionsLayout.add(studentCheckbox, 1, rowIndex);
                permissionsLayout.add(instructorCheckbox, 2, rowIndex);
                permissionsLayout.add(viewCheckbox, 3, rowIndex);
                permissionsLayout.add(adminRightsCheckbox, 4, rowIndex);
                permissionsLayout.add(new HBox(10, grantAdminButton), 5, rowIndex);

                if (USER_LIST.findUser(username).isStudent() && !USER_LIST.findUser(username).isInstructor()) {
                    grantAdminButton.setVisible(false);
                }

                rowIndex++;
            }
        }

        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        VBox wrapper = new VBox(topSpacer, permissionsLayout, bottomSpacer);
        wrapper.setAlignment(Pos.CENTER);

        mainContentArea.getChildren().add(wrapper);
    }

    private void showRequestScreen() {
        mainContentArea.getChildren().clear();

        // Header Labels
        Label usernameHeader = new Label("Username");
        usernameHeader.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label articleHeader = new Label("Article");
        articleHeader.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label actionsHeader = new Label("Actions");
        actionsHeader.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // VBox for usernames
        VBox usernameColumn = new VBox(10);
        usernameColumn.setAlignment(Pos.TOP_LEFT);
        usernameColumn.getChildren().add(usernameHeader);

        // VBox for articles
        VBox articleColumn = new VBox(10);
        articleColumn.setAlignment(Pos.TOP_CENTER);
        articleColumn.getChildren().add(articleHeader);

        // VBox for action buttons
        VBox actionsColumn = new VBox(10);
        actionsColumn.setAlignment(Pos.TOP_RIGHT);
        actionsColumn.getChildren().add(actionsHeader);

        // Populate the columns with data from ACCESS_LIST
        for (Access access : ACCESS_LIST) {
            if (access.getGroups().contains(this.groupName)) {
                // Username
                Text usernameText = new Text(access.getUsername());
                usernameText.setFont(Font.font("Arial", 16));

                // Article name and level
                Text articleNameText = new Text(access.getArticleTitle());
                articleNameText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                articleNameText.setFill(Color.web("#8b0000"));
                HBox articleBox = new HBox(articleNameText);

                // Buttons
                Button acceptButton = new Button("Accept");
                Button rejectButton = new Button("Reject");

                // Set button actions
                acceptButton.setOnAction(event -> handleAccept(access));
                rejectButton.setOnAction(event -> handleReject(access));

                // HBox for individual request buttons
                HBox buttonBox = new HBox(10, acceptButton, rejectButton);
                buttonBox.setAlignment(Pos.CENTER_LEFT);

                // Add elements to respective VBoxes
                usernameColumn.getChildren().add(usernameText);
                articleColumn.getChildren().add(articleBox);
                actionsColumn.getChildren().add(buttonBox);
            }
        }

        // Create the HBox to hold the three columns
        HBox requestsBox = new HBox(50, usernameColumn, articleColumn, actionsColumn); // Adjust spacing between columns
        requestsBox.setAlignment(Pos.TOP_CENTER);

        // Add the requestsBox to the mainContentArea
        mainContentArea.getChildren().add(requestsBox);
    }



    // Helper methods for handling accept/reject actions
    private void handleAccept(Access access) {
        // Logic to accept the request
        addUser(access.getUsername());
        System.out.println("Accepted: " + access.getUsername());
        ACCESS_LIST.removeAccess(access); // Example: Remove the access from list after accepting
        showRequestScreen(); // Refresh the screen
    }

    private void handleReject(Access access) {
        // Logic to reject the request
        System.out.println("Rejected: " + access.getUsername());
        ACCESS_LIST.removeAccess(access); // Example: Remove the access from list after rejecting
        showRequestScreen(); // Refresh the screen
    }

    private void showPreviousScreen(Stage primaryStage) {
        ArticlesPage articlesPage = new ArticlesPage();
        InstructorsArticlePage instructorsArticlePage = new InstructorsArticlePage();
        try {
            if(CURRENT_USER.isAdmin())
            {
                articlesPage.start(primaryStage);
            }
            else{
                instructorsArticlePage.start(primaryStage);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addUser(String username) {
        Group group = GROUP_LIST.getGroup(groupName);
        User user = USER_LIST.findUser(username);

        if (user != null) {
            if(group.getUsers().contains(username)) {
                System.out.println("User is already added to group");
            }
            else{
                group.addUser(user);
                System.out.println("Added user - "+user.getUserName());
            }

        }
        else{
            System.out.println("User does not exist");
        }
    }

    public void removeUser(String username) {
        Group group = GROUP_LIST.getGroup(groupName);
        User user = USER_LIST.findUser(username);
        if (user != null && group.getUsers().contains(username)) {
            group.removeUser(user);
            System.out.println("removed user - "+username);
        }
        else {
            System.out.println("User does not exist");
        }
    }

    public void makeAdmin(String username,CheckBox adminCheckBox,CheckBox viewCheckBox,Button grantAdminButton)
    {
        GROUP_LIST.getGroup(this.groupName).addAdmin(username);
        adminCheckBox.setSelected(true);
        viewCheckBox.setSelected(false);
        grantAdminButton.setText("Remove Admin");
    }

    public void removeAdmin(String username,CheckBox adminCheckBox,CheckBox viewCheckBox,Button grantAdminButton)
    {
        GROUP_LIST.getGroup(this.groupName).removeAdmin(username);
        adminCheckBox.setSelected(false);
        viewCheckBox.setSelected(true);
        grantAdminButton.setText("Grant Admin");
    }

    public void setGroup(String group,Stage primaryStage) {
        this.groupName = group;
        start(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
