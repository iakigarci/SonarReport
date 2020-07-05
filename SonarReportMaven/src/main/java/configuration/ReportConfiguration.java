package configuration;

public class ReportConfiguration {

    private String token;
    private String project;
    private String branch;
    private String author;
    private String version;
    private String componentKey;

    public ReportConfiguration(String token, String project, String branch, String author, String version, String key) {
        super();
        this.token = token;
        this.project = project;
        this.branch = branch;
        this.author = author;
        this.version = version;
        this.componentKey = key;
    }

    public String getToken() {
        return token;
    }

    public String getProject() {
        return project;
    }

    public String getBranch() {
        return branch;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getComponentKey() {
        return componentKey;
    }

    public void setComponentKey(String componentKey) {
        this.componentKey = componentKey;
    }


}
