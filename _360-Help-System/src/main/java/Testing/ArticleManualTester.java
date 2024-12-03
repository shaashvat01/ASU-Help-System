package Testing;

import Backend.Article;

public class ArticleManualTester {
    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("Manual Testing: Article Class");

        // Test Case 1: Creating a new Article and verifying its properties
        System.out.println("\nTest Case 1: Verify Article Creation");
        Article article = new Article(101, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "programming,java",
                "This is the body of the article.", "www.example.com", "group1,group2");

        System.out.println("Expected UID: 101");
        System.out.println("Actual UID: " + article.getUID());
        System.out.println("Expected Title: Java Basics");
        System.out.println("Actual Title: " + article.getTitle());
        System.out.println("Expected Keywords: programming,java");
        System.out.println("Actual Keywords: " + article.getKeywords());

        if (article.getUID() == 101
                && article.getTitle().equals("Java Basics")
                && article.getKeywords().equals("programming,java")) {
            System.out.println("Test Case 1 Passed!");
        } else {
            System.out.println("Test Case 1 Failed!");
        }
        System.out.println("===================================================================");

        // Test Case 2: Updating an Article's title and verifying
        System.out.println("\nTest Case 2: Update Article Title");
        article.setTitle("Advanced Java");
        System.out.println("Expected Title: Advanced Java");
        System.out.println("Actual Title: " + article.getTitle());

        if (article.getTitle().equals("Advanced Java")) {
            System.out.println("Test Case 2 Passed!");
        } else {
            System.out.println("Test Case 2 Failed!");
        }
        System.out.println("===================================================================");

        // Test Case 3: Check if Article has a specific keyword
        System.out.println("\nTest Case 3: Verify Keyword Presence");
        String keywordToCheck = "java";
        System.out.println("Checking keyword: " + keywordToCheck);
        boolean hasKeyword = article.hasKeyword(keywordToCheck);
        System.out.println("Expected: true");
        System.out.println("Actual: " + hasKeyword);

        if (hasKeyword) {
            System.out.println("Test Case 3 Passed!");
        } else {
            System.out.println("Test Case 3 Failed!");
        }
        System.out.println("===================================================================");
    }
}
