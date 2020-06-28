package configuration;

public class SonarQubeServer {

	private String url;
    /** Status for server **/
    private boolean status;
    /** Number for major version **/
    private int versionMajor;
    /** Number for minor version **/
    private int versionMinor;
    /** Number for revision version **/
    private int versionRevision;
    /** Number for build version **/
    private int versionBuild;
    /** True if cnesreport support this SonarQube version **/
    private boolean supported;
    
    private static SonarQubeServer miSonarQubeServer;
    
    public static SonarQubeServer getSonarQubeServer() {
    	if( miSonarQubeServer==null ) {
    		miSonarQubeServer = new SonarQubeServer();
    	}
    	return miSonarQubeServer;
    }

    private SonarQubeServer() {
    	
    }
    
	private void init(String url, boolean status, int versionMajor, int versionMinor, int versionRevision,
			int versionBuild, boolean supported) {
		this.url = url;
		this.status = status;
		this.versionMajor = versionMajor;
		this.versionMinor = versionMinor;
		this.versionRevision = versionRevision;
		this.versionBuild = versionBuild;
		this.supported = supported;
	}
}
