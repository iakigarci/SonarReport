package providers;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.API;
import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Metric;

public class MetricList extends AbstractProvider {

    private ArrayList<Metric> metricList;
    
    public MetricList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    public ArrayList<Metric> create() {
        String str = String.format(API.GET_METRICS.getCall(), SonarQubeServer.getSonarQubeServer().getUrl());
        final JsonObject jo = request(str);
        metricList = new ArrayList<Metric>(Arrays.asList(getGson().fromJson(jo.get(METRICS_FIELD), Metric[].class)));

        return metricList;
    }
    
    public String getIdsAsString() {
        String str = "";
        for (Metric metric : metricList) {
            if (!metric.getKey().equals("new_development_cost")) {
                str +=  metric.getKey() + ',' ;
            }
        }
        return str;
    }
    
    public ArrayList<Metric> getMetricList() {
        return metricList;
    }

    public void setMetricList(ArrayList<Metric> metricList) {
        this.metricList = metricList;
    }

}
