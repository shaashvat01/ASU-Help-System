package com.example._360helpsystem;

import Backend.Article;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example._360helpsystem.CreateAdminAccount.ARTICLE_LIST;

public class ManageGeneralGroup extends Application {

    private VBox mainContentArea;
    private String groupName;

    @Override
    public void start(Stage primaryStage) {
        // Default to "General" group if no group name is provided
        initialize(primaryStage, "General");
    }

    public void initialize(Stage primaryStage, String groupName) {
        this.groupName = groupName;

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        Button createArticleBtn = new Button("Create Article");
        createArticleBtn.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        createArticleBtn.setFont(Font.font("Arial", 15));
        createArticleBtn.setPrefWidth(150);
        createArticleBtn.setPrefHeight(35);

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

        articlesButton.setOnAction(e -> showArticlesForGroup());
        addUserButton.setOnAction(e -> showAddUserScreen());
        removeUserButton.setOnAction(e -> showRemoveUserScreen());
        permissionsButton.setOnAction(e -> showPermissionsScreen());

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);  // Fixed width for sidebar
        sidebar.getChildren().addAll(articlesButton, addUserButton, removeUserButton, permissionsButton);

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
        addButton.setOnAction(e -> System.out.println("User added with name: " + nameField.getText()));

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
        removeButton.setOnAction(e -> System.out.println("User removed with name: " + nameField.getText()));

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
        permissionsLayout.add(roleHeader, 1, 0);
        permissionsLayout.add(actionsHeader, 2, 0);

        // Role and Action labels
        Label studentLabel = new Label("S");
        Label instructorLabel = new Label("I");
        Label adminLabel = new Label("A");
        Label viewLabel = new Label("Read");
        Label adminRightsLabel = new Label("Admin");

        permissionsLayout.add(studentLabel, 1, 1);
        permissionsLayout.add(instructorLabel, 2, 1);
        permissionsLayout.add(adminLabel, 3, 1);
        permissionsLayout.add(viewLabel, 4, 1);
        permissionsLayout.add(adminRightsLabel, 5, 1);

        int rowIndex = 2;
        String[] mockUsers = {"User1", "User2"};

        for (String username : mockUsers) {
            Label usernameLabel = new Label(username);

            CheckBox studentCheckbox = new CheckBox();
            studentCheckbox.setDisable(true);

            CheckBox instructorCheckbox = new CheckBox();
            instructorCheckbox.setDisable(true);

            CheckBox adminCheckbox = new CheckBox();
            adminCheckbox.setSelected(true);
            adminCheckbox.setDisable(true);

            CheckBox viewCheckbox = new CheckBox();
            viewCheckbox.setDisable(true);

            CheckBox adminRightsCheckbox = new CheckBox();
            adminRightsCheckbox.setDisable(true);

            Button grantViewButton = new Button("Grant Read Access");
            grantViewButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
            grantViewButton.setOnAction(e -> viewCheckbox.setSelected(true));

            Button grantAdminButton = new Button("Grant Admin Access");
            grantAdminButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
            grantAdminButton.setOnAction(e -> adminRightsCheckbox.setSelected(true));

            permissionsLayout.add(usernameLabel, 0, rowIndex);
            permissionsLayout.add(studentCheckbox, 1, rowIndex);
            permissionsLayout.add(instructorCheckbox, 2, rowIndex);
            permissionsLayout.add(adminCheckbox, 3, rowIndex);
            permissionsLayout.add(viewCheckbox, 4, rowIndex);
            permissionsLayout.add(adminRightsCheckbox, 5, rowIndex);
            permissionsLayout.add(new HBox(10, grantViewButton, grantAdminButton), 6, rowIndex);

            rowIndex++;
        }

        Region topSpacer = new Region();
        VBox.setVgrow(topSpacer, Priority.ALWAYS);

        Region bottomSpacer = new Region();
        VBox.setVgrow(bottomSpacer, Priority.ALWAYS);

        VBox wrapper = new VBox(topSpacer, permissionsLayout, bottomSpacer);
        wrapper.setAlignment(Pos.CENTER);

        mainContentArea.getChildren().add(wrapper);
    }

    private void showPreviousScreen(Stage primaryStage) {
        ArticlesPage articlesPage = new ArticlesPage();
        try {
            articlesPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
