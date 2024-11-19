package Backend;

import java.util.ArrayList;
import java.util.List;

/*******
 * <p> ArticleSearcher Class </p>
 *
 * <p> Description: This class provides functionality to search through a list of articles
 * based on a query. It supports both numeric UID-based searches and case-insensitive
 * text searches on fields like title, author, and abstract. </p>
 *
 * @version 1.00, 2024-11-19
 * @author Team - Th15
 *
 */

public class ArticleSearcher {

    // Searches for articles matching the given query
    public static ArrayList<Article> searchArticles(ArrayList<Article> articles, String query) {
        ArrayList<Article> matchingArticles = new ArrayList<>();
        boolean isNumericQuery = isNumeric(query);

        for (Article article : articles) {
            // Check for UID match if the query is numeric
            if (isNumericQuery && Long.parseLong(query) == article.getUID()) {
                matchingArticles.add(article);
                continue; // No need to check other fields
            }

            // Convert query and fields to lowercase for case-insensitive text search
            String lowerCaseQuery = query.toLowerCase();

            if (article.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                    article.getAuthor().toLowerCase().contains(lowerCaseQuery) ||
                    article.getAbs().toLowerCase().contains(lowerCaseQuery)) {
                matchingArticles.add(article);
            }
        }

        return matchingArticles;
    }

    // Checks if the query can be parsed as a number
    private static boolean isNumeric(String query) {
        try {
            Long.parseLong(query);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
