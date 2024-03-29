package factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import configuration.ReportConfiguration;
import providers.AbstractProvider;

/**
 * Create Provider classes
 * @author GARCI
 *
 */
public class ProviderFactory {

    /**
     * ReportConfiguration instance
     */
    private ReportConfiguration reportConf;

    public ProviderFactory(ReportConfiguration reportCOnf) {
        super();
        this.reportConf = reportCOnf;
    }

    /**
     * Creates a Provider class with a Factory
     * 
     * @param <T>   
     * @param providerClass class intansce
     * @return  Provider class instance
     */
    public <T extends AbstractProvider> T create(Class<T> providerClass) {
        Constructor<T>[] constructors = (Constructor<T>[]) providerClass.getConstructors();
        T provider = null;

        if (null != constructors && constructors.length == 1) {
            try {
                provider = constructors[0].newInstance(reportConf);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return provider;
    }
}
