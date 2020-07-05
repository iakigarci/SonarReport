package providers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Issue;
import models.Language;

public abstract class IssueList extends AbstractProvider {
    
    public IssueList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }

    public List<List<Object>> getIssueList(String componentKey) {
        BugList bugList = new BugList();
        CodeSmellList codeSmellList = new CodeSmellList();
        VulneravilityList vulneravilityList = new VulneravilityList();
        SecurityHotspotList securityList = new SecurityHotspotList();
        
        
        String str = String.format(API.GET_ISSUES.getCall(), SonarQubeServer.getSonarQubeServer().getUrl(), componentKey, "");
        final JsonObject jo = request(str);
        final Language[] languagesList = getGson().fromJson(jo.get(LANGUAGES_FIELD), Language[].class);
    }
    
    public abstract List<Object> getList() ;
}
