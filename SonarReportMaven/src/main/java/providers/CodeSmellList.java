package providers;

import java.util.ArrayList;
import java.util.List;

import models.Issue;

public class CodeSmellList extends IssueList {

    private ArrayList<Issue> codeSmellList;
    
    public CodeSmellList() {
        codeSmellList = new ArrayList<Issue>();
    }
    
    @Override
    public List<Object> getList() {
        // TODO Auto-generated method stub
        return null;
    }

}
