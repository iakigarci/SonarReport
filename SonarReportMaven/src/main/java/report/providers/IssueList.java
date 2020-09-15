package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Issue;

/**
 * 
 * @author GARCI
 * Project associated issue list
 */
public class IssueList extends AbstractProvider {
    
    private static final String ISSUE_FIELD = "issues";
    /**
     * Issue list
     */
    private ArrayList<Issue> issueList;
    
    public IssueList(ReportConfiguration projectRequest) {
        super(projectRequest);
        issueList = new ArrayList<Issue>();
    }

   /**
    * Make the call to the API and creates the Issue list with the response
    * 
    * @param    args    array of String arrays. [0] severities list, [1] resolution list, [2] type list
    * @return   issue's list
    */
    public ArrayList<Issue> getIssueList(ArrayList<String> args) {
        String str = null;
        try {
            str = String.format(api.getRequest("GET_ISSUES"), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey(), args.get(0), args.get(1), args.get(2));
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
