package Backend;

import java.util.ArrayList;

/*******
 * <p> Article Class </p>
 *
 * <p> Description: This class defines the structure and properties of an article in the help system.
 * Articles contain information such as title, author, level, security, abstract, keywords,
 * body, and other metadata relevant to managing help content within the system. </p>
 *
 * <p> Articles serve as the main content managed by users with specific roles, allowing them
 * to categorize, update, and secure information as needed. </p>
 *
 * @version 1.00, 2024-10-30
 * @author Team - Th15
 *
 *******/

public class Article {
    private long UID;
    private String title;
    private String author;
    private String level;
    private String security;
    private String abs;
    private ArrayList<String> keywords;
    private String body;
    private String links;
    private ArrayList<String> groups;


    public Article(long UID, String title, String author, String level, String security,
                   String abs, String keywords, String body, String links, String groups) {
        this.keywords = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.UID = UID;
        this.title = title;
        this.author = author;
        this.level = level;
        this.security = security;
        this.abs = abs;

        // Split and add keywords
        if (keywords != null && !keywords.isEmpty()) {
            for (String keyword : keywords.split(",")) {
                this.keywords.add(keyword.trim());
            }
        }

        this.body = body;
        this.links = links;

        // Split and add groups
        if (groups != null && !groups.isEmpty()) {
            for (String group : groups.split(",")) {
                this.groups.add(group.trim());
            }
        }

        System.out.println("Article created with groups: " + this.groups);
    }


    public long getUID() {
        return UID;
    }
    public void setUID(long UID) {
        this.UID = UID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getSecurity() {
        return security;
    }
    public void setSecurity(String security) {
        this.security = security;
    }
    public String getAbs() {
        return abs;
    }
    public void setAbs(String abs) {
        this.abs = abs;
    }
    public String getKeywords() {
        return String.join(",", keywords);
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getLinks() {
        return links;
    }
    public void setLinks(String links) {
        this.links = links;
    }
    public String getGroup() {
        StringBuilder result = new StringBuilder();
        for (String group : this.groups) {
            if (result.length() > 0) {
                result.append(","); // Append a comma only after the first group
            }
            result.append(group);
        }
        return result.toString();
    }
    public ArrayList<String> getGroups() {
        return groups;
    }
    public boolean hasGroup(String group) {
        for(String grpName : groups)
        {
            if(grpName.equals(group))
            {
                return true;
            }
        }
        return false;
    }

    // method to replace article
    public void replaceArticle(Article article) {
        this.UID = article.UID;
        this.title = article.title;
        this.author = article.author;
        this.level = article.level;
        this.security = article.security;
        this.abs = article.abs;
        this.keywords = article.keywords;
        this.body = article.body;
        this.links = article.links;
        this.groups = article.groups;
    }

    public boolean hasKeyword(String keyword) {
        return this.keywords.contains(keyword);
    }

}