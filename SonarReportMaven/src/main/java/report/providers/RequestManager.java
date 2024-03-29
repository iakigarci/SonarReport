package providers;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.sonarqube.ws.client.GetRequest;
import org.sonarqube.ws.client.HttpConnector;
import org.sonarqube.ws.client.WsResponse;

import utils.StringManager;

/**
 * Manage http requests.
 */
public final class RequestManager {

    /**
     * Instance of the singleton
     */
    private static RequestManager ourInstance = null;
    /**
     * System property for proxy host
     */
    public static final String STR_PROXY_HOST = "https.proxyHost";

    /**
     * System property for proxy port
     */
    public static final String STR_PROXY_PORT = "https.proxyPort";

    /**
     * System property for proxy user
     */
    public static final String STR_PROXY_USER = "https.proxyUser";

    /**
     * System property for proxy password
     */
    public static final String STR_PROXY_PASS = "https.proxyPassword";

    public static final String QUERY_CHAR = "?";
    public static final String ANCHOR_CHAR = "#";

    /**
     * Use of private constructor to singletonize this class
     */
    private RequestManager() {}

    /**
     * Return the unique instance
     * 
     * @return the singleton
     */
    public static synchronized RequestManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new RequestManager();
        }
        return ourInstance;
    }

    /**
     * Return the baseUrl from a string URL
     * 
     * @return the baseUrl as string
     */
    private static String extractBaseUrl(String url) {
        if (url != null) {
            int queryPosition = url.indexOf(QUERY_CHAR);
            if (queryPosition <= 0) {
                queryPosition = url.indexOf(ANCHOR_CHAR);
            }

            if (queryPosition >= 0) {
                url = url.substring(0, queryPosition);
            }
        }

        return substringBeforeLast(url, "/");
    }

    public static String substringBeforeLast(String str, String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Execute a get http request
     * 
     * @param url server to request
     * @return response as string
     * @throws SonarQubeException When SonarQube server is not callable.
     * @throws BadSonarQubeRequestException if SonarQube Server sent an error
     */
    public String get(final String url) {
        // Initialize connexion information.
        if (url == null) {
            throw new NullPointerException("URl parameter is null or empty");
        }
        final String baseUrl = extractBaseUrl(url);
        final String path = url.replace(baseUrl, "");
        final String proxyHost = System.getProperty(STR_PROXY_HOST, StringManager.EMPTY);
        final String proxyPort = System.getProperty(STR_PROXY_PORT, StringManager.EMPTY);
        final String proxyUser = System.getProperty(STR_PROXY_USER, StringManager.EMPTY);
        final String proxyPass = System.getProperty(STR_PROXY_PASS, StringManager.EMPTY);

        // Initialize http connector builder.
        final HttpConnector.Builder builder = HttpConnector.newBuilder().userAgent("admin").url(baseUrl);

        // TODO Set SonarQube authentication token.
        // if(!StringManager.getProperty(StringManager.SONAR_TOKEN).equals(token)) {
        // builder.credentials(token, null);
        // }

        // Set proxy settings.
        if (!proxyHost.isEmpty()) {
            int proxyUsedPort;
            try {
                proxyUsedPort = Integer.valueOf(proxyPort);
            } catch (NumberFormatException wrongPort) {
                proxyUsedPort = 80;
            }

            final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyUsedPort));

            builder.proxy(proxy);

            if (!proxyUser.isEmpty()) {
                builder.proxyCredentials(proxyUser, proxyPass);
            }
        }

        // Execute the request.
        final HttpConnector httpConnector = builder.build();
        WsResponse response = null;
        try {
            response = httpConnector.call(new GetRequest(path));
        } catch (Exception e) {
            System.out.println("Impossible to reach SonarQube instance.");
        }

        if (response == null) {
            throw new NullPointerException("API Response is null");
        }
        switch (response.code()) {
            case 401:
                System.out.println(
                        "Unauthorized error sent by SonarQube server (code 401), please provide a valid authentication token to cnesreport.");
                break;
            case 403:
                System.out.println(
                        "Insufficient privileges error sent by SonarQube server (code 403), please check your permissions in SonarQube configuration.");
                break;
            case 404:
                System.out.println(String.format(
                        "Not found error sent by SonarQube server (code 404, URL %s, Error %s), please check cnesreport compatibility with your SonarQube server version.",
                        response.requestUrl(), response.content()));
                break;
            default:
                break;
        }

        return response.content();
    }
}
