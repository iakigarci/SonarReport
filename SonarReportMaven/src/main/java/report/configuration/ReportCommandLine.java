package configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main entry point
 */
public final class ReportCommandLine {


    /** Logger of this class */
    private static final Logger LOGGER = Logger.getLogger(ReportCommandLine.class.getName());

    /**
     * Private constructor to not be able to instantiate it.
     */
    private ReportCommandLine(){}

    /**
     * Main method.
     * See help message for more information about using this program.
     * Entry point of the program.
     * @param args Arguments that will be preprocessed.
     */
    public static void main(final String[] args)  {
        // main catches all exceptions
        try {
            // We use different method because it can be called outside main (for example, in from ReportSonarPlugin)
            execute(args);

        } catch (Exception e) {
            // it logs all the stack trace
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            System.exit(-1);
        }
    }

    public static void execute(final String[] args){
        // Log message.
        String message;
        LOGGER.info("ARGS:" + args);

        // Parse command line arguments.
        SonarRequestList sonarRList = SonarRequestList.getSonarRequestList();
        ArrayList<ArrayList<String>> projectList = new ArrayList<ArrayList<String>>();
        ArrayList<String> arr1 = new ArrayList<String>();
        ArrayList<String> arr2 = new ArrayList<String>();
        String[] conf = new String[] {"noauth", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT", "com.c0nrad.darkchess:darkchess"};
        String[] conf2 = new String[] {"noauth", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT", "com.c0nrad.darkchess:darkchess:src/main/java/com/c0nrad/darkchess/engine/FogEngine.java"};
        Collections.addAll(arr1, conf);
        Collections.addAll(arr2, conf2);
        projectList.add(arr1);
        projectList.add(arr2);
        
        ArrayList<String> pIssueFilter = new ArrayList<String>() {{
            add("");
            add("");
            add("");
            }
        };
        
        ArrayList<String> pMetricFilter = new ArrayList<String>() {{
            add("nlock");
            }
        };
        
        ExportConfiguration exportConfiguration = new ExportConfiguration(true,"prueba.csv");
        sonarRList.setExportConfiguration(exportConfiguration);
        
        sonarRList.execute(projectList, pIssueFilter, pMetricFilter);
        
        message = "Report generation: SUCCESS";
        System.out.println(message);
        LOGGER.info(message);
    }

}