package models;

/**
 * The metric measure 
 */
public class Measure {
    
    /**
     * key of the metric
     */
    private String key;
    /**
     * Value of the metric
     */
    private String value;
    
    public Measure(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
}
