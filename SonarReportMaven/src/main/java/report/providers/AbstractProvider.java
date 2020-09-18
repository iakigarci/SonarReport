package providers;

import configuration.ReportConfiguration;
import configuration.API;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author GARCI
 * Abstract class to have different providers
 */
public abstract class AbstractProvider {

    /**
     * Report settings
     */
    protected ReportConfiguration projectRequest;
    /**
     * API instance to make calls
     */
    protected API api;
    /**
     * Gson to make Json calls
     */
    protected Gson gson = new Gson();
    /**
     * Static measure field for API calls
     */
    protected static final String MEASURES_FIELD = "measures";
    /**
     * Static component field for API calls
     */
    protected static final String COMPONENT_FIELD = "component";
    /**
     * Static language field for API calls
     */
    protected static final String LANGUAGES_FIELD = "languages";
    /**
     * Static metric field for API calls
     */
    protected static final String METRICS_FIELD = "metrics";
    

    public AbstractProvider(ReportConfiguration projectRequest) {
        super();
        this.projectRequest = projectRequest;
        api = API.getAPI();
    }

    /**
     * Request an url to SonarQube API with request parameter
     * 
     * @param   request API url
     * @return  JsonObject with SonarQube response to the request
     */
    public JsonObject request(String request) {
        // do the request to the server and return a string answer
        final String raw = stringRequest(request);

        // prepare json
        JsonElement json;

        // verify that the server response was correct
        try {
            json = getGson().fromJson(raw, JsonElement.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("a");
            json = null;
        }

        JsonObject jsonObject = null;

        try {
            jsonObject = json.getAsJsonObject();
        } catch (NullPointerException e) {
            System.out.println(
                    "Empty server response, reason might be : " + "server certificate not in JRE/JDK truststore, ...");
        }
        return jsonObject;
    }

    /**
     * Get the raw string response
     * 
     * @param   request the raw server of the request
     * @return  the server's response as a string
     */
    protected String stringRequest(String request) {
        // prepare the request by replacing some relevant special characters
        // replace spaces
        String preparedRequest = request.replace(" ", "%20");
        // replace + characters
        preparedRequest = preparedRequest.replaceAll("\\+", "%2B");

        // launch the request on SonarQube server and retrieve resources into a string
        return RequestManager.getInstance().get(preparedRequest);
    }

    public ReportConfiguration getProjectRequest() {
        return projectRequest;
    }

    public API getApi() {
        return api;
    }

    public Gson getGson() {
        return gson;
    }

}
