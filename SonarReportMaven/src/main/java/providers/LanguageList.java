package providers;

import java.util.ArrayList;
import java.util.Arrays;

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
        languageList = new ArrayList<Language>(Arrays.asList(getGson().fromJson(jo.get(LANGUAGES_FIELD), Language[].class)));

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
