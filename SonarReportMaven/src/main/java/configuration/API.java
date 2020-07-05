package configuration;

public enum API {

    GET_LANGUAGES("%s/api/languages/list"), MAX_PER_PAGE_SONARQUBE("500"), 
    GET_ISSUES("%s/api/issues/search?componentKeys=%d&types=%f");

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
