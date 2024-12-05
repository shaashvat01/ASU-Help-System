package Testing;

import Backend.Article;
import Backend.ArticleList;

public class ArticleListManualTester {
    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("Manual Testing: ArticleList Class");

        // Initialize ArticleList and sample articles
        ArticleList articleList = new ArticleList();
        Article article1 = new Article(101, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,basics",
                "This is the body of the article.", "www.example.com", "group1");
        Article article2 = new Article(102, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,advanced",
                "This is the body of the advanced article.", "www.example2.com", "group2");

        articleList.addArticle(article1);
        articleList.addArticle(article2);

        // Test Case 1: Verify Articles Added
        System.out.println("\nTest Case 1: Verify Articles Added");
        System.out.println("Expected Article Count: 2");
        System.out.println("Actual Article Count: " + articleList.getSize());

        if (articleList.getSize() == 2) {
            System.out.println("Test Case 1 Passed!");
        } else {
            System.out.println("Test Case 1 Failed!");
        }
        System.out.println("===================================================================");

        // Test Case 2: Retrieve Article by UID
        System.out.println("\nTest Case 2: Retrieve Article by UID");
        System.out.println("Searching for Article UID: 102");
        Article retrievedArticle = articleList.getArticleByUID(102);
        System.out.println("Expected Title: Advanced Java");
        System.out.println("Actual Title: " + (retrievedArticle != null ? retrievedArticle.getTitle() : "Not Found"));

        if (retrievedArticle != null && retrievedArticle.getTitle().equals("Advanced Java")) {
            System.out.println("Test Case 2 Passed!");
        } else {
            System.out.println("Test Case 2 Failed!");
        }
        System.out.println("===================================================================");

        // Test Case 3: Remove Article and Verify
        System.out.println("\nTest Case 3: Remove Article and Verify");
        articleList.removeArticle(article1);
        System.out.println("Expected Article Count: 1");
        System.out.println("Actual Article Count: " + articleList.getSize());

        if (articleList.getSize() == 1 && articleList.getArticleByUID(101) == null) {
            System.out.println("Test Case 3 Passed!");
        } else {
            System.out.println("Test Case 3 Failed!");
        }
        System.out.println("===================================================================");
    }
}
