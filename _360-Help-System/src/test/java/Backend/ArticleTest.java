package Backend;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    /**
     * Test case: Verify `hasGroup` method.
     */
    @Test
    void testHasGroup() {
        Article article = new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,programming",
                "Body of article", "link.com", "group1,group2");

        assertTrue(article.hasGroup("group1"), "The article should belong to 'group1'.");
        assertFalse(article.hasGroup("group3"), "The article should not belong to 'group3'.");
    }

    /**
     * Test case: Verify `setTitle` method updates the title correctly.
     */
    @Test
    void testSetTitle() {
        Article article = new Article(1, "Old Title", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,programming",
                "Body of article", "link.com", "group1");

        article.setTitle("New Title");
        assertEquals("New Title", article.getTitle(), "The article title should be updated to 'New Title'.");
    }

    /**
     * Test case: Verify `getKeywords` method returns the correct keywords.
     */
    @Test
    void testGetKeywords() {
        Article article = new Article(1, "Java Basics", "John Doe", "Beginner", "Low",
                "Introduction to Java", "java,programming,oop",
                "Body of article", "link.com", "group1");

        assertEquals("java,programming,oop", article.getKeywords(), "The keywords should match the expected value.");
    }
}
