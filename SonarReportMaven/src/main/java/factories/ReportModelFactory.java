package factories;

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


	public Report create() {
		ProviderFactory providerF = new ProviderFactory();
		LanguageList languageList = providerF.
	}
}
