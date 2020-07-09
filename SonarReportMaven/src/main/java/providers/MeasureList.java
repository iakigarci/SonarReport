package providers;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Measure;

public class MeasureList extends AbstractProvider {

    private ArrayList<Measure> measureList;
    private String metrics;

    
    public MeasureList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    public ArrayList<Measure> create() {
        if (metrics.isEmpty() || metrics == null) {
            metrics = "ncloc,complexity,violations";
        }
        String str = String.format(API.GET_MEASURES.getCall(), SonarQubeServer.getSonarQubeServer().getUrl(), super.projectRequest.getComponentKey() , metrics);
        final JsonObject jo = request(str);
        measureList = new ArrayList<Measure>(Arrays.asList(getGson().fromJson(jo.get(COMPONENT_FIELD).getAsJsonObject().get(MEASURES_FIELD), Measure[].class)));

        return measureList;
    }

    public String getMeasureList() {
        return metrics;
    }

    public void setMetricList(String metricList) {
        this.metrics = metricList;
    }
}
