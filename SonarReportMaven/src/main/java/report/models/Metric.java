package models;

public class Metric {

    /**
     * Represents the sonarqube's key of the metric
     */
    private String key;
    /**
     * The metric's rule
     */
    private String name;
    /**
     * The metric's type 
     * Possible values: INT, FLOAT, PERCENT, BOOL
     */
    private String type;
    /**
     * Short description of the metric
     */
    private String description;
    /**
     * Represents metric domain
     */
    private String domain;
    /**
     * The status of the metric
     */
    private boolean qualitative;

    public Metric(String key, String name, String type, String description, String domain, boolean qualitative) {
        super();
        this.key = key;
        this.name = name;
        this.type = type;
        this.description = description;
        this.domain = domain;
        this.qualitative = qualitative;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isQualitative() {
        return qualitative;
    }
    
    @Override
    public String toString() {
        return this.key;
    }

}
