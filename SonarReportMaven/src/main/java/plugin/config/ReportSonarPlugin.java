package config;

import org.sonar.api.Plugin;

import ws.ReportWs;
import ws.web.ReportPluginPageDefinition;

public class ReportSonarPlugin implements Plugin {
    public void define(Context context) {
        context.addExtension(ReportWs.class);
        context.addExtension(ReportPluginPageDefinition.class);
    }
}