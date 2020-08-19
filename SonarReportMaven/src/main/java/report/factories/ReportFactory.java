package factories;

import java.io.IOException;
import java.util.ArrayList;

import configuration.ExportConfiguration;
import exporters.CSVExporter;
import models.Report;

public class ReportFactory {
    
    private ExportConfiguration exportConfiguration;

    public ReportFactory(ExportConfiguration exportConfiguration) {
        super();
        this.exportConfiguration = exportConfiguration;
    }

    public ExportConfiguration getExportConfiguration() {
        return exportConfiguration;
    }

    public void setExportConfiguration(ExportConfiguration exportConfiguration) {
        this.exportConfiguration = exportConfiguration;
    }
    
    public void createFiles(ArrayList<Report> pReportList) throws IOException {
        if (exportConfiguration.isEnableCSV()) {
            CSVExporter csvExporter = new CSVExporter(exportConfiguration);
            csvExporter.create(pReportList);
            csvExporter.createMeasureReport(pReportList);
        }
    }

    
}
