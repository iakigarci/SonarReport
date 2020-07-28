package models;

import java.util.ArrayList;

/**
 * A directory or file of a Sonar project
 */
public class Component {

    /**
     * ID in SonarQube
     */
    private String id;
    
    /**
     * Filename
     */
    private String name;
    
    /**
     * Path to the file
     */
    private String path;
    
    /**
     * Component type
     */
    private String qualifier;
    
    /**
     * List of metrics and their values the current file
     */
    private ArrayList<Measure> measures;

    public Component(String id, String name, String path, ArrayList<Measure> measures, String qualifier) {
        super();
        this.id = id;
        this.name = name;
        this.path = path;
        this.measures = measures;
        this.qualifier = qualifier;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    
}
