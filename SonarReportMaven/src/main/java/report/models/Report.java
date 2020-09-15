package models;

import java.util.ArrayList;
import java.util.List;

import configuration.ReportConfiguration;

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
    
    /**
     * Language list
     */
    private ArrayList<Language> languageList;
    /**
     * Issue list
     */
    private ArrayList<Issue> issueList;
    /**
     * Metric list
     */
    private ArrayList<Metric> metricList;
    /**
     * Measure list
     */
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
    
    public Report(ReportConfiguration pReportConf) {
        super();
        this.projectName = pReportConf.getProject();
        this.projectAuthor = pReportConf.getAuthor();
        this.projectDate = pReportConf.getVersion();
        this.branch = pReportConf.getBranch();
        this.componentKey = pReportConf.getComponentKey();
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

    public List<Language> getLanguageList() {
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

    public void setLanguageList(List<Language> languageList) {
        this.languageList = (ArrayList<Language>) languageList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = (ArrayList<Issue>) issueList;
    }

    public String getComponentKey() {
        return componentKey;
    }

    public List<Metric> getMetricList() {
        return metricList;
    }

    public void setMetricList(List<Metric> metricList) {
        this.metricList = (ArrayList<Metric>) metricList;
    }

    public List<Measure> getMeasureList() {
        return measureList;
    }

    public void setMeasureList(List<Measure> measureList) {
        this.measureList = (ArrayList<Measure>) measureList;
    }

    @Override
    public String toString() {
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

        return str.toString();
    }

    
}
