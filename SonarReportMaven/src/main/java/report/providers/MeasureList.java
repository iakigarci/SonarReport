package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Measure;

public class MeasureList extends AbstractProvider {

    /**
     * Measure list
     */
    private ArrayList<Measure> measureL;
    
    /**
     * Selected metrics
     */
    private String metrics;
    /**
     * Default metrics, if the entry is wrong or empty
     */
    private final static String DEFAULT_METRICS = "ncloc,complexity,violations";
    
    public MeasureList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    /**
     * Create a measure list with metric filter
     * 
     * @return  measure list
     */
    public List<Measure> createMeasureList() {
        if (metrics.isEmpty()) {
            metrics = DEFAULT_METRICS;
        }
        String str = String.format(api.getRequest("GET_MEASURES"), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey() , metrics);
        final JsonObject jo = request(str);
        measureL = new ArrayList<>(Arrays.asList(getGson().fromJson(jo.get(COMPONENT_FIELD).getAsJsonObject().get(MEASURES_FIELD), Measure[].class)));

        return measureL;
    }

    public List<Measure> getList() {
        return measureL;
    }

    public void setMetricFilterList(String metricList) {
        this.metrics = metricList;
    }
}
