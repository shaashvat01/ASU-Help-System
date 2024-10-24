package Backend;

public class Article {
    private long UID;
    private String title;
    private String author;
    private String level;
    private String security;
    private String abs;
    private String keywords;
    private String body;
    private String links;
    private String group;

    public Article(long UID, String title, String author, String level, String security, String abs, String keywords, String body, String links, String group) {
        this.UID = UID;
        this.title = title;
        this.author = author;
        this.level = level;
        this.security = security;
        this.abs = abs;
        this.keywords = keywords;
        this.body = body;
        this.links = links;
        this.group = group;
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
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
}
