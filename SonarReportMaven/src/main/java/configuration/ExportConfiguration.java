package configuration;

public class ExportConfiguration {
    
    private boolean enableCSV;
    
    private String filePath;


    public ExportConfiguration(boolean enableCSV, String filePath) {
        super();
        this.enableCSV = enableCSV;
        this.filePath = filePath;
    }

    public boolean isEnableCSV() {
        return enableCSV;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    

}
