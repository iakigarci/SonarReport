package web;

import org.sonar.api.Plugin;

public class ReportSonarPlugin implements Plugin {
    
    @Override
    public void define(Context context) {
        context.addExtension(PluginPageDefinition.class);
    }
}
