package providers;

import java.util.ArrayList;
import java.util.List;

import models.Issue;

public class BugList extends IssueList {

    private ArrayList<Issue> bugList;
    
   public BugList() {
       bugList = new ArrayList<Issue>();
   }
   
    @Override
    public List<Object> getList() {
        // TODO Auto-generated method stub
        return null;
    }


}
