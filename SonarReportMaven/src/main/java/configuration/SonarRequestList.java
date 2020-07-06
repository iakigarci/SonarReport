package configuration;

import java.util.ArrayList;

import factories.ReportModelFactory;
import models.Language;
import models.Report;

public class SonarRequestList {

    private ArrayList<Report> reportList;

    private static final SonarRequestList miSonarRequestList = new SonarRequestList();

    private SonarRequestList() {
        reportList = new ArrayList<Report>();
    }

    public static SonarRequestList getSonarRequestList() {
        return miSonarRequestList;
    }

    public void execute(ArrayList<ReportConfiguration> pReportConfList) throws IllegalStateException {
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

    public ArrayList<Report> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }
    
}
