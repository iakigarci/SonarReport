package models;

/**
 * The metric measure 
 */
public class Measure {
    
    /**
     * key of the metric
     */
    private String metric;
    /**
     * Value of the metric
     */
    private String value;
    
    public Measure(String metric, String value) {
        super();
        this.metric = metric;
        this.value = value;
    }

    public String getKey() {
        return metric;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.metric + "-" + this.value;
    }
}
