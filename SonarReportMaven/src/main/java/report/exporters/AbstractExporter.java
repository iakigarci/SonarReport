package exporters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import configuration.ExportConfiguration;
import models.Report;

/**
 * 
 * @author GARCI
 * Abstract class to create different export methods
 */
public abstract class AbstractExporter {

    /**
     * Export settings
     */
    protected ExportConfiguration exportConfiguration;
    

    public AbstractExporter(ExportConfiguration exportConfiguration) {
        super();
        this.exportConfiguration = exportConfiguration;
    }
    
    /**
     * Create a File from a report list, and export to a path
     * @param   pReportList list of generated reports
     * @param   path    file output
     * @return  generated file
     * @throws IOException
     */
    public abstract File create(List<Report> pReportList, String path) throws IOException;
    
}
