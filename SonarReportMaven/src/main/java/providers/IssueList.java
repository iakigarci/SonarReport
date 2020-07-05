package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Issue;
import models.Language;

public class IssueList extends AbstractProvider {
    
    private static final String ISSUE_FIELD = "issues";
    
    private ArrayList<Issue> issueList;
    
    public IssueList(ReportConfiguration projectRequest) {
        super(projectRequest);
        issueList = new ArrayList<Issue>();
    }

    public List<Issue> getIssueList() {
        
        String str = String.format(API.GET_ISSUES.getCall(), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey(), "BUG");
        final JsonObject jo = request(str);
        issueList = new ArrayList<Issue>(Arrays.asList(getGson().fromJson(jo.get(ISSUE_FIELD), Issue[].class)));
        return issueList;
    }
    
    
    public List<Issue> getList() {
        return issueList;
    };
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String str = ("Lista de issue: ");
        for (Issue issue : issueList) {
            str = str + ","+issue.getKey() + " " + issue.getMetric() + " " + issue.getMessage();
        }
        return str;
    }
}
