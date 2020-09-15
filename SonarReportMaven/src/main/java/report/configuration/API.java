package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import providers.AbstractProvider;

/**
 * 
 * @author GARCI
 * Manages API requests, calling associated property file
 */
public class API {
    
    /**
     * Property file name where are API url
     */
    private static final String REQUESTS_PROPERTIES = "request.properties";
    /**
     * Property instance
     */
    private static Properties requests = new Properties();
    /**
     * Singleton instance
     */
    private static API miAPI = null;


    static {
        // Need of the local classloader to read inner properties file.
        final ClassLoader classLoader = AbstractProvider.class.getClassLoader();

        // load properties file as a stream
        try (InputStream input = classLoader.getResourceAsStream(REQUESTS_PROPERTIES)){
            if(input!=null) {
                // load properties from the stream in an adapted structure
                requests.load(input);
            }
        } catch (IOException e) {
            // it logs all the stack trace
            System.out.println("API error: " + e);
        }
    }
    
    /**
     * Private constructor to not be able to instantiate it.
     */
    private API() {}
    
    /**
     * @return API instance
     */
    public static API getAPI() {
        if (miAPI == null) {
            miAPI = new API();
        }
        return miAPI;
    }

    /**
     * Get the associated API url
     * 
     * @param property
     * @return API url
     */
    public String getRequest(final String property) {
        return requests.getProperty(property);
    }

}
