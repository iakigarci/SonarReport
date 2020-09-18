package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Component;

/**
 * 
 * @author GARCI
 * Project associated component list
 */
public class ComponentList extends AbstractProvider {

    /**
     * Component list
     */
    private ArrayList<Component> componentL;

    public ComponentList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    /**
     * Make the call to the API and creates the Component list with the response
     * 
     * @return  component list
     */
    public List<Component> createComponentList() {
        String str = String.format(api.getRequest("GET_COMPONENTS"), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey(), "DIR, BRC, FIL");
        final JsonObject jo = request(str);
        componentL = new ArrayList<>(Arrays.asList(getGson().fromJson(jo.get(COMPONENT_FIELD), Component[].class)));

        return componentL;
    }
    
    public List<Component> getList() {
        return componentL;
    }
    
}
