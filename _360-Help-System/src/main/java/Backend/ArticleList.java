package Backend;

import java.util.ArrayList;

public class ArticleList {
    private ArrayList<Article> articles;

    public ArticleList() {
        articles = new ArrayList<>();
    }
    public ArrayList<Article> getArticles() {
        return articles;
    }
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
    public void addArticle(Article article) {
        articles.add(article);
    }
    public void removeArticle(Article article) {
        articles.remove(article);
    }
}
