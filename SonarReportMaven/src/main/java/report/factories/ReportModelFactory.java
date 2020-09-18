package factories;


import java.util.ArrayList;

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
        Report report;
        SonarRequestList sonarRequest = SonarRequestList.getSonarRequestList();
        ProviderFactory providerF = new ProviderFactory(reportConf);
        LanguageList languageList = providerF.create(LanguageList.class);
        IssueList issueList = providerF.create(IssueList.class);
        MetricList metricList = providerF.create(MetricList.class);
        MeasureList measureList = providerF.create(MeasureList.class);
        report = new Report(reportConf);

        //Set lists
        report.setLanguageList(languageList.createLanguageList());
        report.setIssueList(issueList.createIssueList((ArrayList<String>) sonarRequest.getIssueFilter()));
        report.setMetricList(metricList.createMetricList((ArrayList<String>) sonarRequest.getMetricFilter()));
        measureList.setMetricFilterList(metricList.getIdsAsString());
        report.setMeasureList(measureList.createMeasureList());

        return report;
    }
}
