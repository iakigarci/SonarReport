package configuration;

public enum API {

    GET_LANGUAGES("%s/api/languages/list"),
    MAX_PER_PAGE("500"), 
    GET_ISSUES("%s/api/issues/search?componentKeys=%s&severities=%s&resolutions=%s&types=%s&ps="+MAX_PER_PAGE.getCall()),
    GET_METRICS("%s/api/metrics/search?ps="+MAX_PER_PAGE.getCall()),
    GET_COMPONENTS("%s/api/components/tree?component=%s&qualifiers=%s"),
    GET_MEASURES("%s/api/measures/component?component=%s&metricKeys=%s");

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
