package models;

public class Comment {

    // Comment's key
    private String key;
    // Comment's author login.
    private String login;
    // Comment's text.
    private String text;
    // Comment's markdown text.
    private String markdown;
    // Comment's updatable status.
    private boolean updatable;
    // Comment's creation date.
    private String createdAt;

    public Comment(String key, String login, String text, String markdown, boolean updatable, String createdAt) {
        super();
        this.key = key;
        this.login = login;
        this.text = text;
        this.markdown = markdown;
        this.updatable = updatable;
        this.createdAt = createdAt;
    }

    public String getKey() {
        return key;
    }

    public String getLogin() {
        return login;
    }

    public String getText() {
        return text;
    }

    public String getMarkdown() {
        return markdown;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public String getCreatedAt() {
        return createdAt;
    }


}
