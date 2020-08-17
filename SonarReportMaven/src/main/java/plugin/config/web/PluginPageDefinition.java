package config.web;

import org.sonar.api.web.page.Page;
import org.sonar.api.web.page.PageDefinition;
import org.sonar.api.web.page.Context;

public class PluginPageDefinition implements PageDefinition {

    public void define(Context context) {
          context
        .addPage(Page.builder("reportindaba/global_page")
          .setName("Global Page")
          .build());
    }
}
