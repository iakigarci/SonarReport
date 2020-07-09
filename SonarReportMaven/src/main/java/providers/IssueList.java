package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Issue;

public class IssueList extends AbstractProvider {
    
    private static final String ISSUE_FIELD = "issues";
    
    private ArrayList<Issue> issueList;
    
    public IssueList(ReportConfiguration projectRequest) {
        super(projectRequest);
        issueList = new ArrayList<Issue>();
    }

   /**
    * Generates the API calls and returns the issue list with filters
    * @param args array of String arrays. [0] status list, [1] severities list, [2] type list
    * @return Issue's list
    */
    public ArrayList<Issue> getIssueList(ArrayList<String> args) {
        String str = null;
        try {
            str = String.format(API.GET_ISSUES.getCall(), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey(), args.get(0), args.get(1), args.get(2));
        }catch (Exception e) {
            throw new IllegalStateException("Los argumentos no son validos");
        }
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
