package tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import providers.AbstractProvider;

/**
 * String manager for plugin
 */
public class PluginStringManager {
    private static Properties properties = new Properties();
    private PluginStringManager(){
        throw new IllegalStateException("Utility class");
    }
    static {
        final ClassLoader classLoader = AbstractProvider.class.getClassLoader();
        // load properties file as a stream
        try (
            InputStream input = classLoader.getResourceAsStream("plugin.properties")){
            if(input!=null) {
                // load properties from the stream in an adapted structure
                properties.load(input);
            }
        } catch (IOException e) {
            // it logs all the stack trace
            e.printStackTrace();
        }
    }

    public static String getProperty(String p){
        return properties.getProperty(p);
    }
}
