package configuration;

import java.util.ArrayList;

import factories.ReportModelFactory;
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
	
	public void execute(ArrayList<ReportConfiguration> pReportConfigurationList) throws IllegalStateException {
		
		if(pReportConfigurationList.isEmpty()) {
			throw new IllegalStateException("Porfavor, otorga los parametros necesarios");
		}
		
		ReportModelFactory reportModelFactory = new ReportModelFactory();
		
		for (int i = 0; i < pReportConfigurationList.size(); i++) {
			Report report = reportModelFactory(pReportConfigurationList.get(i))
		}
	}
}
