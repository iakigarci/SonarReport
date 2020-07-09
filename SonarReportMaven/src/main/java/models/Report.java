package models;

import java.util.ArrayList;

public class Report {

    /**
     * Name of the project/report
     */
    private String projectName;
    /**
     * Name of the author
     */
    private String projectAuthor;
    /**
     * Date to write in the project
     */
    private String projectDate;
    /**
     * List of quality profiles used in the project
     */
    private String branch;
    /**
     * Component key of the report
     */
    private String componentKey;
    
    private ArrayList<Language> languageList;
    
    private ArrayList<Issue> issueList;
    
    private ArrayList<Metric> metricList;
    
    private ArrayList<Measure> measureList;

    /**
     * List of components in the project and their metrics
     */

    public Report(String projectName, String projectAuthor, String projectDate, String branch, String componentKey) {
        super();
        this.projectName = projectName;
        this.projectAuthor = projectAuthor;
        this.projectDate = projectDate;
        this.branch = branch;
        this.componentKey = componentKey;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder str = new StringBuilder("");
        str.append("El proyecto es: ");
        str.append(projectName + "\n");
        str.append("Autor: " + projectAuthor + "\n");
        str.append("Branch: " + branch + "\n");
        str.append("Component Key: " + componentKey + "\n");
        str.append("LanguageList: " + languageList.size()+ " " + languageList.toString() + "\n");
        str.append("IssueList: "+ issueList.size()+ " " + issueList.toString() + "\n");
        str.append("MetricList: "+ metricList.size()+ " " + metricList.toString() + "\n");
        str.append("MeasureList: "+ measureList.size()+ " " + measureList.toString() + "\n");

        String text = str.toString();
        return text;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectAuthor() {
        return projectAuthor;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public String getBranch() {
        return branch;
    }

    public ArrayList<Language> getLanguageList() {
        return languageList;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectAuthor(String projectAuthor) {
        this.projectAuthor = projectAuthor;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setLanguageList(ArrayList<Language> languageList) {
        this.languageList = languageList;
    }

    public void setIssueList(ArrayList<Issue> issueList) {
        this.issueList = issueList;
    }

    public String getComponentKey() {
        return componentKey;
    }

    public ArrayList<Metric> getMetricList() {
        return metricList;
    }

    public void setMetricList(ArrayList<Metric> metricList) {
        this.metricList = metricList;
    }

    public ArrayList<Measure> getMeasureList() {
        return measureList;
    }

    public void setMeasureList(ArrayList<Measure> measureList) {
        this.measureList = measureList;
    }


    
}
