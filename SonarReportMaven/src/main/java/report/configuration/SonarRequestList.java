package configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import factories.ReportFactory;
import factories.ReportModelFactory;
import models.Report;
import tools.PluginStringManager;
import utils.StringManager;

public class SonarRequestList {

    /**
     * List of generated reports
     */
    private ArrayList<Report> reportList;
    
    /**
     * Export settings configuration
     */
    private ExportConfiguration exportConfiguration;
        
    /**
     * Selected issue list for the report
     */
    private ArrayList<String> issueFilter;
    
    /**
     * Selected metric list for the report
     */
    private ArrayList<String> metricFilter;
    

    private static final SonarRequestList miSonarRequestList = new SonarRequestList();

    private SonarRequestList() {
        reportList = new ArrayList<>();
    }

    public static SonarRequestList getSonarRequestList() {
        return miSonarRequestList;
    }

    /**
     * 
     * @param projectList project list with a list of project attributes
     * @param pIssueFilter  array of String. [0] severities list, [1] resolution list, [2] type list
     * @param pMetricFilter metric key list
     * @throws IllegalStateException
     */
    public void execute(List<ReportConfiguration> projectList, List<String> pIssueFilter, List<String> pMetricFilter) throws IllegalStateException {
        
        issueFilter = (ArrayList<String>) pIssueFilter;
        metricFilter = (ArrayList<String>) pMetricFilter;
        
        SonarQubeServer.getSonarQubeServer().init(StringManager.getProperty("sonar.url"), false, 0, Integer.parseInt(PluginStringManager.getProperty("plugin.since")), 0, 0, true);
        if (projectList.isEmpty()) {
            throw new IllegalStateException("Please, give the correct parameters.");
        }
        ReportModelFactory reportModelFactory;
        for (ReportConfiguration reportConf : projectList) {
            reportModelFactory = new ReportModelFactory(reportConf);
            Report report = reportModelFactory.create();
            reportList.add(report);
        }
        
        ReportFactory reportF = new ReportFactory(exportConfiguration);
        try {
            reportF.createFiles(reportList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private ArrayList<ReportConfiguration> createReportConfigurationList(ArrayList<ArrayList<String>> projectList) {
//        ArrayList<ReportConfiguration> pReportConfList = new ArrayList<ReportConfiguration>();
//        
//        for (int i = 0; i < projectList.size(); i++) {
//            ArrayList<String> array = projectList.get(i);
//            ReportConfiguration conf = new ReportConfiguration(array.get(0), array.get(1), array.get(2), array.get(3), array.get(4), array.get(5));
//            pReportConfList.add(conf);
//        }
//        return pReportConfList;
//    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }

    public List<String> getIssueFilter() {
        return issueFilter;
    }

    public void setIssueFilter(List<String> issueFilter) {
        this.issueFilter = (ArrayList<String>) issueFilter;
    }

    public List<String> getMetricFilter() {
        return metricFilter;
    }

    public void setMetricFilter(List<String> metricFilter) {
        this.metricFilter = (ArrayList<String>) metricFilter;
    }

    public ExportConfiguration getExportConfiguration() {
        return exportConfiguration;
    }

    public void setExportConfiguration(ExportConfiguration exportConfiguration) {
        this.exportConfiguration = exportConfiguration;
    }
    
    
}
