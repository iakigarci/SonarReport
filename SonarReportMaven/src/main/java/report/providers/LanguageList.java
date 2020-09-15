package providers;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Language;

/**
 * 
 * @author GARCI
 * Project associated language list
 */
public class LanguageList extends AbstractProvider {

    /**
     * Language list
     */
    private ArrayList<Language> languageList;

    public LanguageList(ReportConfiguration projectRequest) {
        super(projectRequest);
        languageList = new ArrayList<Language>();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<Language> getLanguageList() {
        String str2 = api.getRequest("GET_LANGUAGES");
        String str = String.format(str2, SonarQubeServer.getSonarQubeServer().getUrl());
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
