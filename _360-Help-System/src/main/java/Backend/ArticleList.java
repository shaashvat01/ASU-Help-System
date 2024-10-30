/*******
 * <p> ArticleList Class </p>
 *
 * <p> Description: This class manages a collection of articles within the help system.
 * It provides methods to add, remove, and search for articles based on specific criteria.
 * The class operates as a utility for managing help content effectively. </p>
 *
 * @version 1.00, 2024-10-30
 * author Team - Th15
 *
 *******/

package Backend;

import java.util.ArrayList;
import java.util.Iterator;

public class ArticleList implements Iterable<Article> {
    // List of articles managed by this class.
    private ArrayList<Article> articles;

    // Default constructor initializing the articles list.
    public ArticleList() {
        articles = new ArrayList<>();
    }

    // Getter for the list of articles.
    public ArrayList<Article> getArticles() {
        return articles;
    }

    // Adds an article to the list.
    public void addArticle(Article article) {
        articles.add(article);
    }

    // Removes an article from the list.
    public void removeArticle(Article article) {
        articles.remove(article);
    }

    // Returns the number of articles in the list.
    public int getSize() {
        return articles.size();
    }

    // Retrieves an article by its UID.
    public Article getArticleByUID(long UID) {
        for (Article article : articles) {
            if (article.getUID() == UID) {
                return article;
            }
        }
        return null;
    }

    // Checks if a specific article exists in the list.
    public boolean contains(Article article) {
        return articles.contains(article);
    }

    // Provides an iterator for the articles list.
    @Override
    public Iterator<Article> iterator() {
        return articles.iterator();
    }
}
