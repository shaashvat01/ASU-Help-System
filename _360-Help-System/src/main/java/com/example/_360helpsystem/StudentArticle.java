package com.example._360helpsystem;

/*******
 * <p> StudentArticle Class </p>
 *
 * <p> Description: This class provides a user interface for students to view and interact with
 * available articles. Students can search, filter, and request access to articles based on their
 * access permissions. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

import Backend.*;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example._360helpsystem.CreateAdminAccount.*;
import static com.example._360helpsystem.SignIn.CURRENT_USER;

public class StudentArticle extends Application {

    private VBox articleContainerVBox; // The container to display the articles

    @Override
    public void start(Stage primaryStage) {
        // Top bar with back button on the left and search components centered
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10, 10, 10, 10));

        // Create circular back button using ButtonStyleUtil
        Button backButton = ButtonStyleUtil.createCircularBackButton();
        backButton.setOnAction(e -> showPreviousScreen(primaryStage));

        // Container for back button on the left
        HBox backButtonContainer = new HBox(backButton);
        backButtonContainer.setAlignment(Pos.CENTER_LEFT);

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search articles...");
        searchField.setPrefWidth(250);
        searchField.setPrefHeight(30);

        // Search Button
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        searchButton.setFont(Font.font("Arial", 15));
        searchButton.setPrefHeight(29);



        // Filter Button
        Button filterButton = new Button("Filter");
        filterButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        filterButton.setFont(Font.font("Arial", 15));
        filterButton.setPrefHeight(29);

        //Reset search button
        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        resetButton.setFont(Font.font("Arial", 15));
        resetButton.setPrefHeight(29);
        resetButton.setOnAction(e -> displayGeneralArticles());

        // Centered layout for search bar, search button, and filter button
        HBox searchBox = new HBox(5, searchField, searchButton, filterButton,resetButton);
        searchBox.setAlignment(Pos.CENTER);

        // Spacer regions on either side to center-align the searchBox
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // Add back button container, left spacer, searchBox, and right spacer to the top bar
        topBar.getChildren().addAll(backButtonContainer, leftSpacer, searchBox, rightSpacer);
        topBar.setAlignment(Pos.CENTER);

        // Create a ScrollPane to hold the VBox containing articles
        ScrollPane scrollPane_Article = new ScrollPane();
        scrollPane_Article.setFitToWidth(true);
        scrollPane_Article.setFitToHeight(true);
        scrollPane_Article.setStyle("-fx-background-color: transparent;");

        // VBox to hold the article container (which will display the articles dynamically)
        articleContainerVBox = new VBox(10);
        articleContainerVBox.setPrefSize(800, 600);
        articleContainerVBox.setStyle("-fx-background-color: white;");

        scrollPane_Article.setContent(articleContainerVBox);
        scrollPane_Article.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane_Article.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox articleSection = new VBox(20, scrollPane_Article);
        articleSection.setAlignment(Pos.TOP_CENTER);
        articleSection.setPadding(new Insets(20));

        // Main layout with only the search bar and article section
        BorderPane mainContent = new BorderPane();
        mainContent.setTop(topBar);
        mainContent.setCenter(articleSection);
        mainContent.setStyle("-fx-background-color: #f8f5f3;");

        // Filter options panel (hidden initially)
        ArrayList<CheckBox> grpFilters = new ArrayList<>();
        ArrayList<CheckBox> levelFilters = new ArrayList<>();
        VBox filterPanel = createFilterPanel(levelFilters, grpFilters);

        searchButton.setOnAction(e -> performSearch(searchField.getText(),levelFilters,grpFilters));

        // StackPane to overlay filter panel on the main content
        StackPane root = new StackPane(mainContent, filterPanel);
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Student Article Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Slide filter panel in and out
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(300), filterPanel);
        slideIn.setFromX(900);
        slideIn.setToX(0);

        TranslateTransition slideOut = new TranslateTransition(Duration.millis(300), filterPanel);
        slideOut.setFromX(0);
        slideOut.setToX(900);

        // Set action for filter button to slide in the filter panel
        filterButton.setOnAction(e -> {
            filterPanel.setVisible(true);
            slideIn.play();
        });

        // Set action for save button to slide out and hide the filter panel
        Button saveButton = (Button) filterPanel.lookup("#saveButton");
        saveButton.setOnAction(e -> slideOut.play());
        slideOut.setOnFinished(e -> filterPanel.setVisible(false));

        // Display all articles in the "General" group by default
        displayGeneralArticles();
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

        CheckBox beginnerCheckBox = new CheckBox("Beginner");
        levelFilters.add(beginnerCheckBox);
        CheckBox intermediateCheckBox = new CheckBox("Intermediate");
        levelFilters.add(intermediateCheckBox);
        CheckBox advancedCheckBox = new CheckBox("Advanced");
        levelFilters.add(advancedCheckBox);
        CheckBox expertCheckBox = new CheckBox("Expert");
        levelFilters.add(expertCheckBox);


        VBox contentLevelOptions = new VBox(10, beginnerCheckBox, intermediateCheckBox, advancedCheckBox, expertCheckBox);

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

    private void performSearch(String searchText, ArrayList<CheckBox> levelFilters, ArrayList<CheckBox> grpFilters) {
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
            boolean matchGroup = selectedGrps.stream().anyMatch(article::hasGroup) || selectedGrps.isEmpty();

            if (matchLevel && matchGroup) {
                filteredResults.add(article);
            }
        }

        new Update_DB().storeSearch(searchText,selectedLevels,selectedGrps);
        SEARCH_HISTORY.add("Search Request= "+searchText+"; Levels = "+selectedLevels+"; Groups = "+selectedGrps);

        System.out.println("Result articles - ");
        for (Article article : filteredResults) {
            System.out.println(article.getTitle() + "-" + article.getAbs());
        }

        // Display active group
        Label groupLabel = new Label("Active Group: " + String.join(", ", selectedGrps));
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

            if ("Protected".equalsIgnoreCase(article.getSecurity()) || "Public".equalsIgnoreCase(article.getSecurity())) {
                if (hasAccess) {
                    Button viewArticle = new Button("View Article");
                    viewArticle.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                    viewArticle.setFont(Font.font("Arial", 14));
                    viewArticle.setOnAction(e -> {
                        try {
                            showArticleDetails(article);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    optionsBox.getChildren().add(viewArticle);
                } else {
                    Button requestAccessButton = new Button("Request Access");
                    requestAccessButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                    requestAccessButton.setFont(Font.font("Arial", 14));
                    int finalSequenceNumber = sequenceNumber;
                    requestAccessButton.setOnAction(e -> {
                        try {
                            requestAccess(article, finalSequenceNumber);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    optionsBox.getChildren().add(requestAccessButton);
                }
            }

            articleBox.getChildren().addAll(titleLabel, authorLabel, abstractLabel, optionsBox);
            articleContainerVBox.getChildren().add(articleBox);

            sequenceNumber++;
        }
    }

    private void showPreviousScreen(Stage primaryStage) {
        StudentPage studentPage = new StudentPage();
        try {
            studentPage.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Display articles in the "General" group
    public void displayGeneralArticles() {
        System.out.println("Displaying General Articles");
        articleContainerVBox.getChildren().clear();
        int sequenceNumber = 0;
        for (Article article : ARTICLE_LIST) {
                VBox articleBox = new VBox(5);
                articleBox.setPadding(new Insets(10));
                articleBox.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0; -fx-background-color: white;");
                articleBox.setAlignment(Pos.TOP_LEFT);

                HBox titleLevelBox = new HBox(10);
                Label sequenceNumberLabel = new Label(String.valueOf(sequenceNumber));
                sequenceNumberLabel.setFont(Font.font("Arial", 17));

                Label titleLabel = new Label(article.getTitle());
                titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 17px; -fx-text-fill: #8b0000;");
                titleLabel.setFont(Font.font("Arial", 17));

                Label levelLabel = new Label("(" + article.getLevel() + ")");
                levelLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                levelLabel.setFont(Font.font("Arial", 14));


                Label grpLabel = new Label("["+article.getGroup()+"]");
                grpLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                grpLabel.setFont(Font.font("Arial", 14));

                titleLevelBox.getChildren().addAll(sequenceNumberLabel,titleLabel, levelLabel,grpLabel);
                titleLevelBox.setAlignment(Pos.TOP_LEFT);

                Label abstractLabel = new Label(article.getAbs());
                abstractLabel.setWrapText(true);

                Button requestAccessButton = new Button("Request Access");
                requestAccessButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                requestAccessButton.setFont(Font.font("Arial", 14));
                requestAccessButton.setOnAction(e -> {
                    try {
                        requestAccess(article,sequenceNumberLabel);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                Button viewArticle = new Button("View Article");
                viewArticle.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
                viewArticle.setFont(Font.font("Arial", 14));
                viewArticle.setOnAction(e -> {
                    try {
                        showArticleDetails(article);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                HBox optionsBox = new HBox();
                optionsBox.setAlignment(Pos.TOP_RIGHT);
                articleBox.getChildren().addAll(titleLevelBox, abstractLabel, optionsBox);

                boolean hasAccess = false;

                if(article.getSecurity().equals("Protected"))
                {
                    for(String g : article.getGroups())
                    {
                        if(GROUP_LIST.contains(g) && GROUP_LIST.getGroup(g).getUsers().contains(CURRENT_USER.getUserName()))
                        {
                            hasAccess = true;
                        }
                    }

                    if(hasAccess)
                    {
                        optionsBox.getChildren().addAll(viewArticle);
                    }
                    else{
                        optionsBox.getChildren().addAll(requestAccessButton);
                    }
                }

                if(article.getSecurity().equals("Public"))
                {
                    for(String g : article.getGroups())
                    {
                        if(GROUP_LIST.contains(g) && GROUP_LIST.getGroup(g).getUsers().contains(CURRENT_USER.getUserName()))
                        {
                            hasAccess = true;
                        }
                    }

                    if(hasAccess)
                    {
                        optionsBox.getChildren().addAll(viewArticle);
                    }
                    else{
                        optionsBox.getChildren().addAll(requestAccessButton);
                    }
                }



                articleContainerVBox.getChildren().add(articleBox);
                sequenceNumber++;
        }
    }

    private void showArticleDetails(Article article) throws Exception {
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
        Label bodyLabel = new Label();
        if(article.getSecurity().equals("Protected"))
        {
            bodyLabel.setText(new Encryption().decryptBody(article));
        }
        else{
            bodyLabel.setText(article.getBody());
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

    public void requestAccess(Article article,Label sequence) throws Exception
    {
        Stage detailStage = new Stage();

        VBox accessLayout = new VBox(10);
        accessLayout.setPadding(new Insets(20));
        accessLayout.setAlignment(Pos.CENTER);

        Label messageLabel = new Label("Do you want to request access to article no - "+sequence.getText()+"?");
        messageLabel.setFont(Font.font("Arial", 14));
        messageLabel.setStyle("-fx-text-fill: #2c3e50;");

        Button confirmButton = new Button("Yes");
        confirmButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        confirmButton.setFont(Font.font("Arial", 14));
        confirmButton.setOnAction(e -> {
            // Add new access to the list
            Access access = new Access(CURRENT_USER.getUserName(), article.getTitle(), article.getGroups());
            if(!ACCESS_LIST.getAccessList().contains(access))
            {
                ACCESS_LIST.addAccess(access);
            }

            detailStage.close();
        });

        accessLayout.getChildren().addAll(messageLabel,confirmButton);


        // Create and set the scene
        Scene detailScene = new Scene(accessLayout, 400, 200);
        detailStage.setScene(detailScene);
        detailStage.show();

    }

    public void requestAccess(Article article,int sequence) throws Exception
    {
        Stage detailStage = new Stage();

        VBox accessLayout = new VBox(10);
        accessLayout.setPadding(new Insets(20));
        accessLayout.setAlignment(Pos.CENTER);

        Label messageLabel = new Label("Do you want to request access to article no - "+sequence+"?");
        messageLabel.setFont(Font.font("Arial", 14));
        messageLabel.setStyle("-fx-text-fill: #2c3e50;");

        Button confirmButton = new Button("Yes");
        confirmButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        confirmButton.setFont(Font.font("Arial", 14));
        confirmButton.setOnAction(e -> {
            // Add new access to the list
            Access access = new Access(CURRENT_USER.getUserName(), article.getTitle(), article.getGroups());
            if(!ACCESS_LIST.getAccessList().contains(access))
            {
                ACCESS_LIST.addAccess(access);
            }

            detailStage.close();
        });

        accessLayout.getChildren().addAll(messageLabel,confirmButton);


        // Create and set the scene
        Scene detailScene = new Scene(accessLayout, 400, 200);
        detailStage.setScene(detailScene);
        detailStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
