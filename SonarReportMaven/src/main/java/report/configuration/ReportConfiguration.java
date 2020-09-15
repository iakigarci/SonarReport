package configuration;


import utils.CommandLineManager;
import utils.StringManager;

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
    
    public ReportConfiguration() {}
    
    /**
     * Creates a ReportConfiguration instance with the given parameters. 
     * 
     * @param   pArgs :
     *  t   token
     *  p   project
     *  b   branch
     *  k   key
     *  v   version
     *  
     * @return  ReportConfiguration instance
     */
    public ReportConfiguration create (String[] pArgs) {
        final CommandLineManager commandLineManager = new CommandLineManager();
        commandLineManager.parse(pArgs);

        // Final result to return.
        final String branch = commandLineManager.getOptionValue("b", StringManager.NO_BRANCH);
        return new ReportConfiguration(
                commandLineManager.getOptionValue("t", StringManager.getProperty(StringManager.SONAR_TOKEN)),
                commandLineManager.getOptionValue("p", StringManager.EMPTY),
                branch.isEmpty()?StringManager.NO_BRANCH:branch,
                commandLineManager.getOptionValue("a", StringManager.getProperty(StringManager.DEFAULT_AUTHOR)),
                commandLineManager.getOptionValue("v", StringManager.EMPTY),
                commandLineManager.getOptionValue("k", StringManager.EMPTY)
        );
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
