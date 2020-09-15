package factories;


import configuration.ReportConfiguration;
import configuration.SonarRequestList;
import models.Report;
import providers.IssueList;
import providers.LanguageList;
import providers.MeasureList;
import providers.MetricList;

public class ReportModelFactory {

    /**
     * ReportConfiguration instance
     */
    private ReportConfiguration reportConf;
    /**
     * Generated report
     */
    private Report report;
    

    public ReportModelFactory(ReportConfiguration reportConf) {
        super();
        this.reportConf = reportConf;
    }

    public ReportModelFactory() {}

    /**
     * Creates the report instance, initializing all the providers
     * 
     * @return  Gemerated report
     */
    public Report create() {
        SonarRequestList sonarRequest = SonarRequestList.getSonarRequestList();
        ProviderFactory providerF = new ProviderFactory(reportConf);
        LanguageList languageList = providerF.create(LanguageList.class);
        IssueList issueList = providerF.create(IssueList.class);
        MetricList metricList = providerF.create(MetricList.class);
        MeasureList measureList = providerF.create(MeasureList.class);
        report = new Report(reportConf);

        report.setLanguageList(languageList.getLanguageList());
        report.setIssueList(issueList.getIssueList(sonarRequest.getIssueFilter()));
        report.setMetricList(metricList.create(sonarRequest.getMetricFilter()));
        measureList.setMetricList(metricList.getIdsAsString());
        report.setMeasureList(measureList.create());
        // System.out.println(report.toString());

        return report;
    }
}
