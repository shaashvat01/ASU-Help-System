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

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
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

    // Implement the Iterable interface
    @Override
    public Iterator<Article> iterator() {
        return articles.iterator();
    }
}
