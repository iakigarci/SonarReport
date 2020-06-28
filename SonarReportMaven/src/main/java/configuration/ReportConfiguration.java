package configuration;

public class ReportConfiguration {

	private String token;
	private String project;
	private String branch;
	private String author;
	private String version;
	
	public ReportConfiguration(String token, String project, String branch, String author, String version) {
		super();
		this.token = token;
		this.project = project;
		this.branch = branch;
		this.author = author;
		this.version = version;
	}
	
	public String getToken() {
		return token;
	}
	public String getProject() {
		return project;
	}
	public String getBranch() {
		return branch;
	}
	public String getAuthor() {
		return author;
	}
	public String getVersion() {
		return version;
	}
	
	
}
