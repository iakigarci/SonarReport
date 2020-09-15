package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;

import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Metric;

public class MetricList extends AbstractProvider {

    /**
     * Metric list
     */
    private ArrayList<Metric> metricL;
    
    public MetricList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    /**
     * Create a list with metrics from a project
     * 
     * @param   pMetricFilter 
     * @return  metric list
     */
    public List<Metric> create(ArrayList<String> pMetricFilter) {
        String str = String.format(api.getRequest("GET_METRICS"), SonarQubeServer.getSonarQubeServer().getUrl());
        final JsonObject jo = request(str);
        metricL = new ArrayList<>(Arrays.asList(getGson().fromJson(jo.get(METRICS_FIELD), Metric[].class)));

        return metricL;
    }
    
    /**
     * Get IDs as a string list
     * 
     * @return  ID list as string
     */
    public String getIdsAsString() {
        StringBuilder str = new StringBuilder();
        for (Metric metric : metricL) {
            if (!metric.getKey().equals("new_development_cost")) {
                str.append(metric.getKey());
                str.append(',');
            }
        }
        return str.toString();
    }
    
    public List<Metric> getMetricList() {
        return metricL;
    }

    public void setMetricList(List<Metric> metricList) {
        this.metricL = (ArrayList<Metric>) metricList;
    }

}
