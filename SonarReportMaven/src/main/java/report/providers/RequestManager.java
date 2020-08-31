package providers;

/*
 * This file is part of cnesreport.
 *
 * cnesreport is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * cnesreport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with cnesreport. If not,
 * see <http://www.gnu.org/licenses/>.
 */

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
     * @param token token to authenticate to SonarQube
     * @return response as string
     * @throws SonarQubeException When SonarQube server is not callable.
     * @throws BadSonarQubeRequestException if SonarQube Server sent an error
     */
    public String get(final String url, final String token) {
        // Initialize connexion information.
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

        // Throws exception with advice user
        switch (response.code()) {
            case 401:
                System.out.println(
                        "Unauthorized error sent by SonarQube server (code 401), please provide a valid authentication token to cnesreport.");
            case 403:
                System.out.println(
                        "Insufficient privileges error sent by SonarQube server (code 403), please check your permissions in SonarQube configuration.");
            case 404:
                System.out.println(String.format(
                        "Not found error sent by SonarQube server (code 404, URL %s, Error %s), please check cnesreport compatibility with your SonarQube server version.",
                        response.requestUrl(), response.content()));
            default:
                break;
        }

        return response.content();
    }
}
