package providers;

import java.util.ArrayList;

import configuration.ReportConfiguration;
import models.Language;

public class LanguageList extends AbstractProvider{
	
	private ArrayList<Language> languageList;
	
	public LanguageList(ReportConfiguration projectRequest) {
		super(projectRequest);
		languageList = new ArrayList<Language>();
		// TODO Auto-generated constructor stub
	}

	
	
}
