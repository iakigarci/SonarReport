package configuration;

public enum API {

	GET_LANGUAGES("%s/api/languages/list"),
	MAX_PER_PAGE_SONARQUBE("500");
	
	private String call;
	
	API(String pString) {
		call = pString;
	}
	
	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}
}
