package configuration;

import java.util.ArrayList;

import factories.ReportModelFactory;
import models.Language;
import models.Report;

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
        reportList = new ArrayList<Report>();
    }

    public static SonarRequestList getSonarRequestList() {
        return miSonarRequestList;
    }

    public void execute(ArrayList<ArrayList<String>> projectList, ArrayList<String> pIssueFilter, ArrayList<String> pMetricFilter) throws IllegalStateException {
        
        ArrayList<ReportConfiguration> pReportConfList = createReportConfigurationList(projectList);
        issueFilter = pIssueFilter;
        metricFilter = pMetricFilter;
        
        SonarQubeServer.getSonarQubeServer().init("http://localhost:9000", false, 0, 0, 0, 0, false);
        if (pReportConfList.isEmpty()) {
            throw new IllegalStateException("Porfavor, otorga los parametros necesarios");
        }

        ReportModelFactory reportModelFactory = new ReportModelFactory();

        for (ReportConfiguration reportConf : pReportConfList) {
            reportModelFactory = new ReportModelFactory(reportConf);
            Report report = reportModelFactory.create();
            reportList.add(report);
        }
    }

    private ArrayList<ReportConfiguration> createReportConfigurationList(ArrayList<ArrayList<String>> projectList) {
        ArrayList<ReportConfiguration> pReportConfList = new ArrayList<ReportConfiguration>();
        
        for (int i = 0; i < projectList.size(); i++) {
            ArrayList<String> array = projectList.get(i);
            ReportConfiguration conf = new ReportConfiguration(array.get(0), array.get(1), array.get(2), array.get(3), array.get(4), array.get(5));
            pReportConfList.add(conf);
        }
        return pReportConfList;
    }

    public ArrayList<Report> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }

    public ArrayList<String> getIssueFilter() {
        return issueFilter;
    }

    public void setIssueFilter(ArrayList<String> issueFilter) {
        this.issueFilter = issueFilter;
    }

    public ArrayList<String> getMetricFilter() {
        return metricFilter;
    }

    public void setMetricFilter(ArrayList<String> metricFilter) {
        this.metricFilter = metricFilter;
    }

    public ExportConfiguration getExportConfiguration() {
        return exportConfiguration;
    }

    public void setExportConfiguration(ExportConfiguration exportConfiguration) {
        this.exportConfiguration = exportConfiguration;
    }
    
    
}
