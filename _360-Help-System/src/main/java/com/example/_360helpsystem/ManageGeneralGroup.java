package com.example._360helpsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManageGeneralGroup extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        Button createArticleBtn = new Button("Create Article");
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
        articlesButton.setOnAction(e -> showArticlesScreen(primaryStage));

        Button addUserButton = createSidebarButtonWithIcon("Add User", "➕");
        addUserButton.setOnAction(e -> showAddUserScreen(primaryStage));

        Button removeUserButton = createSidebarButtonWithIcon("Remove User", "➖");
        removeUserButton.setOnAction(e -> showRemoveUserScreen(primaryStage));

        Button permissionsButton = createSidebarButtonWithIcon("Permissions", "🔑");
        permissionsButton.setOnAction(e -> showPermissionsScreen(primaryStage));

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 5, 10, 5));
        sidebar.setStyle("-fx-background-color: #333;");
        sidebar.setPrefWidth(160);
        sidebar.getChildren().addAll(articlesButton, addUserButton, removeUserButton, permissionsButton);

        VBox mainContentArea = new VBox(20);
        mainContentArea.setAlignment(Pos.CENTER);
        mainContentArea.setPadding(new Insets(20));

        HBox mainLayout = new HBox(sidebar, mainContentArea);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(mainLayout);
        root.setStyle("-fx-background-color: #f8f5f3;");

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Manage General Group");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showArticlesScreen(Stage primaryStage) {
    }

    private void showPermissionsScreen(Stage primaryStage) {
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

        // Role labels under header
        Label studentLabel = new Label("S");
        Label instructorLabel = new Label("I");
        Label adminLabel = new Label("A");

        // Action labels under header
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

            // Role checkboxes (set as non-interactive)
            CheckBox studentCheckbox = new CheckBox();
            studentCheckbox.setDisable(true);

            CheckBox instructorCheckbox = new CheckBox();
            instructorCheckbox.setDisable(true);

            CheckBox adminCheckbox = new CheckBox();
            adminCheckbox.setSelected(true);  // Assuming admin role is selected by default
            adminCheckbox.setDisable(true);

            // Action checkboxes (set as non-interactive)
            CheckBox viewCheckbox = new CheckBox();
            viewCheckbox.setDisable(true);

            CheckBox adminRightsCheckbox = new CheckBox();
            adminRightsCheckbox.setDisable(true);

            // Buttons for granting options
            Button grantViewButton = new Button("Grant Read Access");
            grantViewButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
            grantViewButton.setOnAction(e -> viewCheckbox.setSelected(true));  // Grant read access

            Button grantAdminButton = new Button("Grant Admin Access");
            grantAdminButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
            grantAdminButton.setOnAction(e -> adminRightsCheckbox.setSelected(true));  // Grant admin access

            // Add components to layout
            permissionsLayout.add(usernameLabel, 0, rowIndex);
            permissionsLayout.add(studentCheckbox, 1, rowIndex);
            permissionsLayout.add(instructorCheckbox, 2, rowIndex);
            permissionsLayout.add(adminCheckbox, 3, rowIndex);
            permissionsLayout.add(viewCheckbox, 4, rowIndex);
            permissionsLayout.add(adminRightsCheckbox, 5, rowIndex);
            permissionsLayout.add(new HBox(10, grantViewButton, grantAdminButton), 6, rowIndex);

            rowIndex++;
        }

        // Back Button
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));

        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(permissionsLayout);

        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }



    private void showCreateArticleScreen(Stage primaryStage) {
        CreateArticle createArticle = new CreateArticle();
        try {
            createArticle.start(primaryStage);
        } catch (Exception ex) {
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

    private void showAddUserScreen(Stage primaryStage) {
        VBox addUserLayout = new VBox(10);
        addUserLayout.setPadding(new Insets(20));
        addUserLayout.setAlignment(Pos.CENTER);

        Text nameTitle = new Text("Name");
        nameTitle.setFont(Font.font("Arial", 24));
        addUserLayout.getChildren().add(nameTitle);

        TextField nameField = new TextField();
        nameField.setMaxWidth(300);
        addUserLayout.getChildren().add(nameField);

        Button addButton = new Button("Add User");
        addButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        addButton.setFont(Font.font("Arial", 18));
        addUserLayout.getChildren().add(addButton);

        addButton.setOnAction(e -> System.out.println("User added with name: " + nameField.getText()));

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));

        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(addUserLayout);

        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));

        Scene addUserScene = new Scene(root, 800, 600);
        primaryStage.setScene(addUserScene);
    }

    private void showRemoveUserScreen(Stage primaryStage) {
        VBox addUserLayout = new VBox(10);
        addUserLayout.setPadding(new Insets(20));
        addUserLayout.setAlignment(Pos.CENTER);

        Text nameTitle = new Text("Name");
        nameTitle.setFont(Font.font("Arial", 24));
        addUserLayout.getChildren().add(nameTitle);

        TextField nameField = new TextField();
        nameField.setMaxWidth(300);
        addUserLayout.getChildren().add(nameField);

        Button addButton = new Button("Remove User");
        addButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        addButton.setFont(Font.font("Arial", 18));
        addUserLayout.getChildren().add(addButton);

        addButton.setOnAction(e -> System.out.println("User removed with name: " + nameField.getText()));

        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> start(primaryStage));

        BorderPane root = new BorderPane();
        root.setTop(backButton);
        root.setCenter(addUserLayout);

        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setMargin(backButton, new Insets(5, 0, 0, 5));

        Scene addUserScene = new Scene(root, 800, 600);
        primaryStage.setScene(addUserScene);
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

