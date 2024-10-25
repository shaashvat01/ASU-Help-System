package Backend;

import java.util.ArrayList;

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

    public Article(long UID, String title, String author, String level, String security, String abs, String keywords, String body, String links, String groups) {
        this.keywords = new ArrayList<>();
        this.groups = new ArrayList<>();

        this.UID = UID;
        this.title = title;
        this.author = author;
        this.level = level;
        this.security = security;
        this.abs = abs;
        String[] splitKeywords = keywords.split(",");

        // Add each keyword to the ArrayList
        for (String keyword : splitKeywords) {
            assert false;
            this.keywords.add(keyword.trim()); // Use trim() to remove any leading/trailing spaces
        }
        this.body = body;
        this.links = links;
        String[] splitGroups = groups.split(",");

        // Add each keyword to the ArrayList
        for (String keyword : splitGroups) {
            assert false;
            this.groups.add(keyword.trim()); // Use trim() to remove any leading/trailing spaces
        }
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
        return String.join(",", groups);
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

}
