package providers;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Language;
import models.Metric;

public class MetricList extends AbstractProvider {

    private ArrayList<Metric> metricList;
    
    private static final String METRICS_FIELD = "metrics";
    
    public MetricList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    public ArrayList<Metric> getMetricList() {
        String str = String.format(API.GET_METRICS.getCall(), SonarQubeServer.getSonarQubeServer().getUrl());
        final JsonObject jo = request(str);
        metricList = new ArrayList<Metric>(Arrays.asList(getGson().fromJson(jo.get(METRICS_FIELD), Metric[].class)));

        return metricList;
    }

}
