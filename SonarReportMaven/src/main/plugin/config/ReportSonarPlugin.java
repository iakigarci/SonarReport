package config;

import org.sonar.api.Plugin;

import config.web.PluginPageDefinition;

public class ReportSonarPlugin implements Plugin {
    
    public void define(Context context) {
        context.addExtension(PluginPageDefinition.class);
    }
}