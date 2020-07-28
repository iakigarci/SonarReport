package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import providers.AbstractProvider;

/**
 * Public class which contains all public String used by internal classes
 */
public final class StringManager {

    /** Just an empty string. */
    public static final String EMPTY = "";
    /** Just a tabulation. */
    public static final String TAB = "\t";
    /** Just a space. */
    public static final String SPACE = " ";
    /** Placeholder for no-branch. */
    public static final String NO_BRANCH = "%";
    /** Just a space for URI. */
    public static final String URI_SPACE = "%20";
    /** Name for properties' file about report. */
    public static final String REPORT_PROPERTIES = "report.properties";
    /** Name of the property giving the server server. */
    public static final String SONAR_URL = "sonar.url";
    /** Name of the property giving the token to authenticate to SonarQube. */
    public static final String SONAR_TOKEN = "sonar.token";
    /** Logged message when there are too much issues to export. */
    public static final String ISSUES_OVERFLOW_MSG = "log.overflow.msg";
    /** Pattern to format the date. */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    /** Default path to the target diretory for report files. */
    public static final String DEFAULT_OUTPUT = "report.path";
    /** Default language for the report. */
    public static final String DEFAULT_LANGUAGE = "report.locale";
    /** Default name for the author. */
    public static final String DEFAULT_AUTHOR = "report.author";

    /** Logger for StringManager. */
    private static final Logger LOGGER = Logger.getLogger(StringManager.class.getCanonicalName());

    /** Contain all the properties related to the report. */
    private static Properties properties;

    /** Contains internationalized fields. */
    private static ResourceBundle messages;

    /** Unique instance of this class (singleton). */
    private static StringManager ourInstance = null;

    //
    // Static initialization block for reading .properties
    //

    /**
     * Private constructor to singletonize the class.
     */
    private StringManager() {}

    /**
     * Get the singleton
     *
     * @return unique instance of StringManager
     */
    public static synchronized StringManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new StringManager();
        }
        return ourInstance;
    }

    /**
     * Give the value of the property corresponding to the key passed as parameter. It gives only
     * properties related to the report.
     * 
     * @param property Key of the property you want.
     * @return The value of the property you want as a String.
     */
    public static String getProperty(final String property) {
        return properties.getProperty(property);
    }

    /**
     * Change the locale and reload messages
     * 
     * @param language String in lowercase
     * @param country String in upper case
     */
    public static synchronized void changeLocale(final String language, final String country) {
        // change locale
        Locale currentLocale = new Locale(language, country);
        // reload messages
        messages = ResourceBundle.getBundle("messages", currentLocale);
    }

    /**
     * Change the locale and reload messages
     * 
     * @param language String containing both the language and country, e.g. en_US
     */
    public static void changeLocale(String language) {
        String[] locale = language.split("_");

        try {
            changeLocale(locale[0], locale[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE,
                    "Unable to change the locale due to malformed command line parameter : " + language, e);
        }
    }

    /**
     * Return string corresponding to the given key according the locale
     * 
     * @param key name of the property in the bundle messages
     * @return a String
     */
    public static String string(final String key) {
        return messages.getString(key);
    }


}