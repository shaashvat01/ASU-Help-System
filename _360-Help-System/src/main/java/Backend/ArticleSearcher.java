package Backend;

import java.util.ArrayList;
import java.util.List;

public class ArticleSearcher {
    public static List<Article> searchArticles(List<Article> articles, String query) {
        List<Article> matchingArticles = new ArrayList<>();
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

    private static boolean isNumeric(String query) {
        // Check if the query can be parsed as a number
        try {
            Long.parseLong(query);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
