package providers;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Component;
import models.Language;

public class ComponentList extends AbstractProvider {


    private ArrayList<Component> componentList;
    
    public ComponentList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }

    public ArrayList<Component> execute() {
        String str = String.format(API.GET_COMPONENTS.getCall(), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey(), "DIR, BRC, FIL");
        final JsonObject jo = request(str);
       componentList = new ArrayList<Component>(Arrays.asList(getGson().fromJson(jo.get(COMPONENT_FIELD), Component[].class)));

        return componentList;
    }
}
