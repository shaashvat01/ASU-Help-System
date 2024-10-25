package Backend;

import java.util.ArrayList;
import java.util.Iterator;

public class ArticleList implements Iterable<Article> {
    private ArrayList<Article> articles;

    public ArticleList() {
        articles = new ArrayList<>();
    }

    // Getter and Setter methods
    public ArrayList<Article> getArticles() {
        return articles;
    }

    // Add or remove an article
    public void addArticle(Article article) {
        articles.add(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    // Return the number of articles
    public int getSize() {
        return articles.size();
    }

    public Article getArticleByUID(long UID) {
        for (Article article : articles) {
            if (article.getUID() == UID) {
                return article;
            }
        }
        return null;
    }

    public boolean contains(Article article) {
        return articles.contains(article);
    }

    // Implement the Iterable interface
    @Override
    public Iterator<Article> iterator() {
        return articles.iterator();
    }
}
