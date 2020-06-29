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
    private ArrayList<Language> languageList;
    /**
     * List of components in the project and their metrics
     */
    
	public Report(String projectName, String projectAuthor, String projectDate, String branch,
			ArrayList<Language> languageList) {
		super();
		this.projectName = projectName;
		this.projectAuthor = projectAuthor;
		this.projectDate = projectDate;
		this.branch = branch;
		this.languageList = languageList;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = ("El proyecto es: "+projectName+" "+projectAuthor+" "+projectDate+" "+branch+" "+languageList.toString());
		return super.toString();
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
    
    
}
