package exporters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import configuration.ExportConfiguration;
import models.Measure;
import models.Report;

public class CSVExporter extends AbstractExporter {

    private HashMap<String, ArrayList<String>> issueMap = new HashMap<String, ArrayList<String>>(); 
    
    public CSVExporter(ExportConfiguration exportConfiguration) {
        super(exportConfiguration);
    }

    @Override
    public void create(ArrayList<Report> pReportList, String dirName) throws IOException {
        try {
            System.out.println(dirName);
            File dir = new File(dirName);
            dir.mkdir();
            
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(dirName));
            System.out.println(" writer");
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Author", "Version", "Branch", "ComponentKey", "LanguageList", "MetricList"));
            System.out.println("csvPrint");
            for(Report report : pReportList) {
                csvPrinter.printRecord(report.getProjectName(), 
                        report.getProjectAuthor(), 
                        report.getProjectDate(), 
                        report.getBranch(),
                        report.getComponentKey(),
                        report.getLanguageList().toString(),
                        report.getMetricList().toString()
                );
            }
            csvPrinter.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void createMeasureReport(ArrayList<Report> pReportList, String dirName) {
        try {
            ArrayList<String> headers = new ArrayList<String>();
            headers.add("Metric");
            for (int i = 0; i < pReportList.size(); i++) {
                headers.add("Measure_"+i);
            }
            
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(dirName ));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            
            csvPrinter.printRecord(headers);
            
            
            
            ArrayList<Measure> measureList = pReportList.get(0).getMeasureList();
            for (int j = 0; j < measureList.size(); j++) {
                Measure measure = measureList.get(j);
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(measure.getValue());
                issueMap.put(measure.getKey(), arr);
            }
            
            for (int i = 1; i < pReportList.size(); i++) {
                measureList = pReportList.get(i).getMeasureList();
                for (int j = 0; j < measureList.size(); j++) {
                    Measure measure = measureList.get(j);
                    addToList(measure);
                }
            }
            
            for(Entry<String, ArrayList<String>> entry : issueMap.entrySet()) {
                ArrayList<String> measures = new ArrayList<String>();
                measures.add(entry.getKey());
                for (int i = 0; i < entry.getValue().size(); i++) {
                    measures.add(entry.getValue().get(i));
                }
                csvPrinter.printRecord(measures);
            }
            
            csvPrinter.flush();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized  void addToList(Measure measure) {
        ArrayList<String> valueList = issueMap.get(measure.getKey());
        
        if (valueList != null) {
            valueList.add(measure.getValue());
        }
    }

    @Override
    public void create(ArrayList<Report> pReportList) throws IOException {
        // TODO Auto-generated method stub
        
    }
    
}
