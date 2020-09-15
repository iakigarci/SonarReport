package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of a metric with its comments. 
 */

public class Issue {

    /**
     * Severity of the corresponding rule
     */
    private String severity;
    /**
     *  Issue's key in Sonarqube
     */
    private String key;
    /**
     *  Associated metric
     */
    private String metric;
    /**
     *  Associated component
     */
    private String component;
    /** Associated project
     * 
     */
    private String project;
    /** Line of the issue
     * 
     */
    private String line;
    /**
     * Issue's status. 
     * Possible values: INFO, MINOR, MAJOR, CRITICAL, BLOCKER
     */
    private String status;
    /**
     * Issue's resolution
     * Possible values: FALSE-POSITIVE, WONTFIX, FIXED, REMOVED
     */
    private String resolution;
    /**
     *  Issue's effort;
     */
    private String effort;
    /**
     * Issue's type
     * Possible values: CODE_SMELL, BUG, VULNERABILITY, SECURITY_HOTSPOT
     */
    private String type;
    /**
     * Issue's message
     */
    private String message;
    /**
     * Issue's programming language
     */
    private String language;
    /**
     * Issue's associated comments
     */
    private ArrayList<Comment> comments;

    public Issue(String severity, String key, String metric, String component, String project, String line,
            String status, String resolution, String effort, String type, String message, String language,
            List<Comment> comments) {
        super();
        this.severity = severity;
        this.key = key;
        this.metric = metric;
        this.component = component;
        this.project = project;
        this.line = line;
        this.status = status;
        this.resolution = resolution;
        this.effort = effort;
        this.type = type;
        this.message = message;
        this.language = language;
        this.comments = (ArrayList<Comment>) comments;
    }

    public String getSeverity() {
        return severity;
    }

    public String getKey() {
        return key;
    }

    public String getMetric() {
        return metric;
    }

    public String getComponent() {
        return component;
    }

    public String getProject() {
        return project;
    }

    public String getLine() {
        return line;
    }

    public String getStatus() {
        return status;
    }

    public String getResolution() {
        return resolution;
    }

    public String getEffort() {
        return effort;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getLanguage() {
        return language;
    }

    public List<Comment> getComments() {
        return comments;
    }
    
    
    
}
