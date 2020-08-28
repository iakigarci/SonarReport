package exporters;

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

    public abstract void create(ArrayList<Report> pReportList) throws IOException;

    public void create(ArrayList<Report> pReportList, String dirName) throws IOException {
        // TODO Auto-generated method stub
        
    }

    
}
