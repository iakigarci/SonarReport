package factories;

import java.lang.reflect.InvocationTargetException;

import configuration.ReportConfiguration;
import models.Report;
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
		ProviderFactory providerF = new ProviderFactory(reportConf);
		LanguageList languageList = providerF.create(LanguageList.class);
		report = new Report("darkchess", reportConf.getAuthor(), reportConf.getVersion(), reportConf.getBranch(), languageList.getLanguageList()) 

		
		System.out.println(report);
		
		return report;
	}
}
