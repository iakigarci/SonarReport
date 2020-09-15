package configuration;

import java.util.ArrayList;
/**
 * Main entry point
 */
public final class ReportCommandLine {

    /**
     * Private constructor to not be able to instantiate it.
     */
    private ReportCommandLine(){}

    /**
     * Main method of report branch. This method has to be called to start the report task
     * @param   reportList  array of arrays with the projects list, with their configuration parameters
     * @param   exportList  list of export configuration parameter
     */
    public static void main(String[][] reportList, String[] exportList)  {
        // main catches all exceptions
        try {
            // We use different method because it can be called outside main 
            execute(reportList, exportList);

        } catch (Exception e) {
            // it logs all the stack trace
            System.out.println("Error in main " + e);
            System.exit(-1);
        }
    }

    public static void execute( String[][] reportList, String[] exportList){
        
        ReportConfiguration reportConf;
        ArrayList<ReportConfiguration> reportConfList = new ArrayList<ReportConfiguration>();
        
        for (int i = 0; i < reportList.length; i++) {
            reportConf = new ReportConfiguration();
            reportConf = reportConf.create(reportList[i]);
            reportConfList.add(reportConf);
        }
        
        ExportConfiguration exportConf = new ExportConfiguration();
        exportConf = exportConf.create(exportList);
        SonarRequestList sonarRList = SonarRequestList.getSonarRequestList();
        sonarRList.setExportConfiguration(exportConf);
        
        // TODO: These filters have to be parameterized
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

        sonarRList.execute(reportConfList, pIssueFilter, pMetricFilter);
        System.out.println("Report generation: SUCCESS");
    }

}