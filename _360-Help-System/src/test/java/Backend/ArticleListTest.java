package Backend;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArticleListTest {

    /**
     * Test case: Verify adding an article to the list.
     */
    @Test
    void testAddArticle() {
        ArticleList articleList = new ArticleList();
        Article article = new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,programming",
                "Body of article", "link.com", "group1");

        articleList.addArticle(article);

        assertEquals(1, articleList.getSize(), "The size of the article list should be 1 after adding an article.");
        assertTrue(articleList.contains(article), "The article list should contain the added article.");
    }

    /**
     * Test case: Verify removing an article from the list.
     */
    @Test
    void testRemoveArticle() {
        ArticleList articleList = new ArticleList();
        Article article = new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,programming",
                "Body of article", "link.com", "group1");

        articleList.addArticle(article);
        articleList.removeArticle(article);

        assertEquals(0, articleList.getSize(), "The size of the article list should be 0 after removing the article.");
        assertFalse(articleList.contains(article), "The article list should not contain the removed article.");
    }

    /**
     * Test case: Verify retrieving an article by UID.
     */
    @Test
    void testGetArticleByUID() {
        ArticleList articleList = new ArticleList();
        Article article1 = new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,programming",
                "Body of article", "link.com", "group1");
        Article article2 = new Article(2, "Advanced Java", "Jane Doe", "Advanced", "High",
                "Deep dive into Java", "java,oop",
                "Body of article", "link2.com", "group2");



















        articleList.addArticle(article1);
        articleList.addArticle(article2);

        Article result = articleList.getArticleByUID(2);

        assertNotNull(result, "The retrieved article should not be null.");
        assertEquals(2, result.getUID(), "The UID of the retrieved article should match the search UID.");
        assertEquals("Advanced Java", result.getTitle(), "The title of the retrieved article should match.");
    }
}
