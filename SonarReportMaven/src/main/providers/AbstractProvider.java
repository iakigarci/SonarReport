package providers;

import configuration.ReportConfiguration;
import configuration.API;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractProvider {

	protected ReportConfiguration projectRequest;
	
	protected API api;
	
	protected Gson gson;
	
	public JsonObject request(final String request)
            throws BadSonarQubeRequestException, SonarQubeException {
        // do the request to the server and return a string answer
        final String raw = stringRequest(request);

        // prepare json
        final JsonElement json;

        // verify that the server response was correct
        try {
            json = getGson().fromJson(raw, JsonElement.class);
        } catch (Exception e) {
            // log exception's message
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new BadSonarQubeRequestException("Server answered: " + raw +
                    StringManager.SPACE + e.getMessage());
        }

        // get the json object version
        final JsonObject jsonObject;

        try {
            jsonObject = json.getAsJsonObject();
        } catch (NullPointerException e) {
            throw new BadSonarQubeRequestException("Empty server response, reason might be : " +
                    "server certificate not in JRE/JDK truststore, ...");
        }

        // verify if an error occurred
        isErrorFree(jsonObject);

        return jsonObject;
    }

    /**
     * Get the raw string response
     * @param request the raw server of the request
     * @return the server's response as a string
     * @throws SonarQubeException When SonarQube server is not callable.
     * @throws BadSonarQubeRequestException if SonarQube Server sent an error
     */
    protected String stringRequest(final String request) throws SonarQubeException, BadSonarQubeRequestException {
        // prepare the request by replacing some relevant special characters
        // replace spaces
        String preparedRequest = request.replace(" ", "%20");
        // replace + characters
        preparedRequest = preparedRequest.replaceAll("\\+", "%2B");

        // launch the request on SonarQube server and retrieve resources into a string
        return RequestManager.getInstance().get(preparedRequest, this.token);
    }
    
}
