package configuration;

import utils.CommandLineManager;
import utils.StringManager;

/**
 * 
 * @author GARCI
 * Handles the export configuration of the report
 */
public class ExportConfiguration {
    
    /**
     * Enable CSV export
     */
    private boolean enableCSV;
    /**
     * Output path of the export files
     */
    private String output;

    public ExportConfiguration(boolean enableCSV, String output) {
        super();
        this.enableCSV = enableCSV;
        this.setOutput(output);
        System.out.println("Output exportConf: " + output);
    }

    public ExportConfiguration() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a ExportConfiguration instance with the given parameters. 
     * 
     * @param   pArgs argument list 
     *  f   enableCSV
     *  o   output
     * @return      ExportConfiguration instance   
     */
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
