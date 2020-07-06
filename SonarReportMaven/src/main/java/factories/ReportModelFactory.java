package factories;

import java.util.ArrayList;

import configuration.ReportConfiguration;
import configuration.SonarRequestList;
import models.Issue;
import models.Report;
import providers.IssueList;
import providers.LanguageList;

public class ReportModelFactory {

    private ReportConfiguration reportConf;
    private Report report;

    public ReportModelFactory(ReportConfiguration reportConf) {
        super();
        this.reportConf = reportConf;
    }

    public ReportModelFactory() {}


    public Report create() {
        SonarRequestList sonarRequest = SonarRequestList.getSonarRequestList();
        ProviderFactory providerF = new ProviderFactory(reportConf);
        LanguageList languageList = providerF.create(LanguageList.class);
        IssueList issueList = providerF.create(IssueList.class);
        report = new Report("darkchess", reportConf.getAuthor(), reportConf.getVersion(), reportConf.getBranch(), reportConf.getComponentKey());

        report.setLanguageList(languageList.getLanguageList());
        report.setIssueList((ArrayList<Issue>) issueList.getIssueList(sonarRequest.getIssueFilter()));
        System.out.println(report.toString());

        return report;
    }
}
