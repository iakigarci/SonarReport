package providers;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

import configuration.ReportConfiguration;
import configuration.SonarQubeServer;
import models.Metric;

public class MetricList extends AbstractProvider {

    /**
     * Metric list
     */
    private ArrayList<Metric> metricList;
    
    public MetricList(ReportConfiguration projectRequest) {
        super(projectRequest);
    }
    
    /**
     * Create a list with metrics from a project
     * 
     * @param   pMetricFilter 
     * @return  metric list
     */
    public ArrayList<Metric> create(ArrayList<String> pMetricFilter) {
        String str = String.format(api.getRequest("GET_METRICS"), SonarQubeServer.getSonarQubeServer().getUrl());
        final JsonObject jo = request(str);
        metricList = new ArrayList<Metric>(Arrays.asList(getGson().fromJson(jo.get(METRICS_FIELD), Metric[].class)));

        return metricList;
    }
    
    /**
     * Get IDs as a string list
     * 
     * @return  ID list as string
     */
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
