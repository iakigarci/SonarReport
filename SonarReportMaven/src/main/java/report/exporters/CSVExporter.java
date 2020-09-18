package exporters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import configuration.ExportConfiguration;
import models.Measure;
import models.Report;

/**
 * 
 * @author GARCI
 * Exports a csv file with data from a report list
 */
public class CSVExporter extends AbstractExporter {

    /**
     * Map  String: metric key  ArrayList: issue value
     */
    private HashMap<String, ArrayList<String>> issueMap = new HashMap<>(); 
    
    public CSVExporter(ExportConfiguration exportConfiguration) {
        super(exportConfiguration);
    }

    @Override
    public File createFiles(List<Report> pReportList, String fileName) throws IOException {
//        File file = null;
//        try {
//            final String filePath = exportConfiguration.getOutput()+"\\"+pReportList.get(0).getProjectName();
//            final Report report = pReportList.get(0);
//            // Opening file
//            file = new File(filePath);
//            System.out.println(filePath);
//            file.mkdir();
//            FileWriter outputfile = new FileWriter(file);
//            
//            try(CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.EXCEL.withDelimiter('\t'))){
//                String line = "a";
//                csvPrinter.printRecord(line);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file;
        File dir = null;
        try {
            String path = super.exportConfiguration.getOutput();
            dir = new File(path);
            dir.mkdir();
            
            System.out.println("Filename: " + fileName);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Author", "Version", "Branch", "ComponentKey", "LanguageList", "MetricList"));
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
            csvPrinter.close();
            writer.close();
            csvPrinter.flush();
        }catch (Exception e) {
            System.out.println("Error ZipFolder :" + e);
        }
        return dir;
    }
    
    public void createMeasureReport(List<Report> pReportList, String dirName) {
        try {
            ArrayList<String> headers = new ArrayList<>();
            headers.add("Metric");
            for (int i = 0; i < pReportList.size(); i++) {
                headers.add("Measure_"+i);
            }
            
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(dirName ));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            
            csvPrinter.printRecord(headers);
            ArrayList<Measure> measureList = (ArrayList<Measure>) pReportList.get(0).getMeasureList();
            for (int j = 0; j < measureList.size(); j++) {
                Measure measure = measureList.get(j);
                ArrayList<String> arr = new ArrayList<>();
                arr.add(measure.getValue());
                issueMap.put(measure.getKey(), arr);
            }
            
            for (int i = 1; i < pReportList.size(); i++) {
                measureList = (ArrayList<Measure>) pReportList.get(i).getMeasureList();
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

    
}
