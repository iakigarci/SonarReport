package factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import configuration.ReportConfiguration;
import providers.AbstractProvider;

public class ProviderFactory{
	
	private ReportConfiguration reportConf;
	
	public ProviderFactory(ReportConfiguration reportCOnf) {
		super();
		this.reportConf = reportCOnf;
	}

	public <T extends AbstractProvider> T create(Class<T> providerClass){
		Constructor<T>[] constructors = (Constructor<T>[]) providerClass.getConstructors();
		T provider = null;
		
		if (null != constructors && constructors.length == 1) {
			try {
				provider = constructors[0].newInstance(reportConf);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return provider;
	}
}
