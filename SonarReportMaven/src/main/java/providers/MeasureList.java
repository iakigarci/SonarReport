package providers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Language;
import models.Measure;

public class MeasureList extends AbstractProvider {

    private ArrayList<Measure> measureList;
    private String metrics;
    private static final String MEASURES_FIELD = "measures";
    
    public MeasureList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    public ArrayList<Measure> getMeasureList() {
        if (metrics.isEmpty() || metrics == null) {
            metrics = "ncloc,complexity,violations";
        }
        String str = String.format(API.GET_MEASURES.getCall(), SonarQubeServer.getSonarQubeServer().getUrl(), metrics);
        final JsonObject jo = request(str);
        measureList = new ArrayList<Measure>(Arrays.asList(getGson().fromJson(jo.get(MEASURES_FIELD), Measure[].class)));

        return measureList;
    }

    public String getMetricList() {
        return metrics;
    }

    public void setMetricList(String metricList) {
        this.metrics = metricList;
    }
}
