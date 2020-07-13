package exporters;

import java.io.IOException;
import java.util.ArrayList;

import configuration.ExportConfiguration;
import models.Report;

public abstract class AbstractExporter {

    protected ExportConfiguration exportConfiguration;
    
    protected final static String dirName = System.getProperty("user.dir") + "\\report_out\\";
    
    public AbstractExporter(ExportConfiguration exportConfiguration) {
        super();
        this.exportConfiguration = exportConfiguration;
    }

    public abstract void create(ArrayList<Report> pReportList) throws IOException;
}
