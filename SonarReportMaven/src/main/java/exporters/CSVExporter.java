package exporters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import configuration.ExportConfiguration;
import models.Report;

public class CSVExporter extends AbstractExporter {

    public CSVExporter(ExportConfiguration exportConfiguration) {
        super(exportConfiguration);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void create(ArrayList<Report> pReportList) throws IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(super.exportConfiguration.getFilePath()));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Author", "Version", "Branch"));
            
            for(Report report : pReportList) {
                csvPrinter.printRecord(report.getProjectName(), report.getProjectAuthor(), report.getProjectDate(), report.getBranch());
            }
            csvPrinter.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
