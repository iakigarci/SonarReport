package configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Report;

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
    public static void main(String[] reportList, String[] exportList)  {
        // main catches all exceptions
        try {
            // We use different method because it can be called outside main (for example, in from ReportSonarPlugin)
            execute(reportList, exportList);

        } catch (Exception e) {
            // it logs all the stack trace
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            System.exit(-1);
        }
    }

    public static void execute( String[] reportList, String[] exportList){
        
        ReportConfiguration reportConf = new ReportConfiguration();
        reportConf = reportConf.create(reportList);
        System.out.println("ReportConfiguration completed");
        
        ExportConfiguration exportConf = new ExportConfiguration();
        exportConf.create(exportList);
        System.out.println("ExportConfiguration completed");

        SonarRequestList sonarRList = SonarRequestList.getSonarRequestList();
        sonarRList.setExportConfiguration(exportConf);
        
        
        
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
        ArrayList<ReportConfiguration> l = new ArrayList<ReportConfiguration>();
        l.add(reportConf);
        sonarRList.execute(l, pIssueFilter, pMetricFilter);
        
        String message = "Report generation: SUCCESS";
        System.out.println(message);
        LOGGER.info(message);
    }

}