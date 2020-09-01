package exporters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import configuration.ExportConfiguration;
import models.Report;

public abstract class AbstractExporter {

    protected ExportConfiguration exportConfiguration;
    

    public AbstractExporter(ExportConfiguration exportConfiguration) {
        super();
        this.exportConfiguration = exportConfiguration;
    }
    public abstract File create(ArrayList<Report> pReportList, String path) throws IOException;
    
}
