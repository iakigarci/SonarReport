package configuration;

import utils.CommandLineManager;
import utils.StringManager;

public class ExportConfiguration {
    
    private boolean enableCSV;
    
    private String output;

    public ExportConfiguration(boolean enableCSV, String output) {
        super();
        this.enableCSV = enableCSV;
        this.setOutput(output);
    }

    public ExportConfiguration() {
        // TODO Auto-generated constructor stub
    }

    public ExportConfiguration create (String[] pArgs) {
        final CommandLineManager commandLineManager = new CommandLineManager();
        commandLineManager.parse(pArgs);

        return new ExportConfiguration(
                !commandLineManager.hasOption("f"), 
                commandLineManager.getOptionValue("o", StringManager.getProperty(StringManager.DEFAULT_OUTPUT))
        );
    }
    
    public boolean isEnableCSV() {
        return enableCSV;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }



    

}
