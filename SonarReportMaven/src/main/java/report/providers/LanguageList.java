package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private ArrayList<Language> languageL;

    public LanguageList(ReportConfiguration projectRequest) {
        super(projectRequest);
        languageL = new ArrayList<>();
    }

    public List<Language> createLanguageList() {
        String str = String.format(api.getRequest("GET_LANGUAGES"), SonarQubeServer.getSonarQubeServer().getUrl());
        final JsonObject jo = request(str);
        languageL = new ArrayList<>(Arrays.asList(getGson().fromJson(jo.get(LANGUAGES_FIELD), Language[].class)));

        return languageL;
    }

    public List<Language> getList() {
        return languageL;
    }

    @Override
    public String toString() {
        String str = ("Lista de lenguages: ");
        for (Language language : languageL) {
            str = str + language.getName();
        }
        return str;
    }
}
