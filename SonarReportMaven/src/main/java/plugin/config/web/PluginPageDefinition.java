package config.web;

import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.PageDefinition;

import tools.PluginStringManager;

import org.sonar.api.web.page.Context;

public class PluginPageDefinition implements PageDefinition {

    public void define(Context context) {
          Page.Builder page = Page.builder(PluginStringManager.getProperty("homepage.url"));
          page.setName(PluginStringManager.getProperty("homepage.name"));
          page.setScope(Page.Scope.GLOBAL);
          context.addPage(page.build());
    }
}
