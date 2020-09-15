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
    /** True if the plugin support this SonarQube version **/
    private boolean supported;

    private static SonarQubeServer miSonarQubeServer;

    public static SonarQubeServer getSonarQubeServer() {
        if (miSonarQubeServer == null) {
            miSonarQubeServer = new SonarQubeServer();
        }
        return miSonarQubeServer;
    }

    private SonarQubeServer() {

    }

    public void init(String url, boolean status, int versionMajor, int versionMinor, int versionRevision,
            int versionBuild, boolean supported) {
        this.url = url;
        this.status = status;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionRevision = versionRevision;
        this.versionBuild = versionBuild;
        this.supported = supported;
    }

    public String getUrl() {
        return url;
    }

    public boolean isStatus() {
        return status;
    }

    public int getVersionMajor() {
        return versionMajor;
    }

    public int getVersionMinor() {
        return versionMinor;
    }

    public int getVersionRevision() {
        return versionRevision;
    }

    public int getVersionBuild() {
        return versionBuild;
    }

    public boolean isSupported() {
        return supported;
    }


}
