package providers;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Language;

public class LanguageList extends AbstractProvider {

    private ArrayList<Language> languageList;
    private static final String LANGUAGES_FIELD = "languages";

    public LanguageList(ReportConfiguration projectRequest) {
        super(projectRequest);
        languageList = new ArrayList<Language>();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<Language> getLanguageList() {
        String str = String.format(API.GET_LANGUAGES.getCall(), SonarQubeServer.getSonarQubeServer().getUrl());
        final JsonObject jo = request(str);
        final Language[] languagesList = getGson().fromJson(jo.get(LANGUAGES_FIELD), Language[].class);

        // put data in a map to access it faster
        for (Language language : languagesList) {
            languageList.add(language);
        }

        return languageList;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String str = ("Lista de lenguages: ");
        for (Language language : languageList) {
            str = str + language.getName();
        }
        return str;
    }
}
