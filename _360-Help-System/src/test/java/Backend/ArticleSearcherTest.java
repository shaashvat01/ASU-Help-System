package Backend;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the ArticleSearcher class.
 *
 * This class verifies the functionality of the searchArticles method,
 * ensuring that articles are correctly retrieved based on different criteria.
 */
class ArticleSearcherTest {

    /**
     * Test case: Search articles by UID.
     *
     * This test verifies that the searchArticles method retrieves the correct
     * article when searching by a unique identifier (UID).
     */
    @Test
    void testSearchArticlesByUID() {
        ArrayList<Article> articles = new ArrayList<>();
        // Add sample articles to the list
        articles.add(new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "programming,java",
                "Body of article", "link.com", "group1"));
        articles.add(new Article(2, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,oop",
                "Body of article", "link2.com", "group2"));

        // Search for an article with UID "1"
        ArrayList<Article> result = ArticleSearcher.searchArticles(articles, "1");

        // Assert the search result
        assertEquals(1, result.size(), "Only one article should match the UID search.");
        assertEquals(1, result.get(0).getUID(), "The UID of the result should match the search query.");
        assertEquals("Java Basics", result.get(0).getTitle(), "The title should match the expected article.");
    }

    /**
     * Test case: Search articles by title.
     *
     * This test verifies that the searchArticles method retrieves all articles
     * containing the search keyword in their titles.
     */
    @Test
    void testSearchArticlesByTitle() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "programming,java",
                "Body of article", "link.com", "group1"));
        articles.add(new Article(2, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,oop",
                "Body of article", "link2.com", "group2"));

        // Search for articles containing "java" in their title
        ArrayList<Article> result = ArticleSearcher.searchArticles(articles, "java");

        // Assert the search result
        assertEquals(2, result.size(), "Two articles should match the title search.");
    }

    /**
     * Test case: Search articles by author.
     *
     * This test verifies that the searchArticles method retrieves the correct
     * articles when searching by the author's name.
     */
    @Test
    void testSearchArticlesByAuthor() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "programming,java",
                "Body of article", "link.com", "group1"));
        articles.add(new Article(2, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,oop",
                "Body of article", "link2.com", "group2"));

        // Search for articles by "John"
        ArrayList<Article> result = ArticleSearcher.searchArticles(articles, "John");

        // Assert the search result
        assertEquals(1, result.size(), "Only one article should match the author search.");
        assertEquals("John Doe", result.get(0).getAuthor(), "The author should match the expected name.");
    }

    /**
     * Test case: Search articles by abstract.
     *
     * This test verifies that the searchArticles method retrieves the correct
     * articles when searching by the content in their abstract.
     */
    @Test
    void testSearchArticlesByAbstract() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "programming,java",
                "Body of article", "link.com", "group1"));
        articles.add(new Article(2, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,oop",
                "Body of article", "link2.com", "group2"));

        // Search for articles containing "deep dive" in their abstract
        ArrayList<Article> result = ArticleSearcher.searchArticles(articles, "deep dive");

        // Assert the search result
        assertEquals(1, result.size(), "Only one article should match the abstract search.");
        assertEquals("Advanced Java", result.get(0).getTitle(), "The title should match the expected article.");
    }

    /**
     * Test case: No matching articles.
     *
     * This test verifies that the searchArticles method returns an empty list
     * when no articles match the search query.
     */
    @Test
    void testSearchArticlesNoMatch() {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "programming,java",
                "Body of article", "link.com", "group1"));
        articles.add(new Article(2, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,oop",
                "Body of article", "link2.com", "group2"));

        // Search for articles with no matching keyword
        ArrayList<Article> result = ArticleSearcher.searchArticles(articles, "Python");

        // Assert the search result
        assertEquals(0, result.size(), "No articles should match the search query.");
    }
}
