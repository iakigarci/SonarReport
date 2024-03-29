package factories;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import configuration.ExportConfiguration;
import exporters.CSVExporter;
import models.Report;
import utils.StringManager;

/**
 * Factory to create different export methods
 * @author GARCI
 *
 */
public class ReportFactory {
    // TODO: add more export methods to use this fields
    /** 
     * Property for the word report filename.
     * */
    private static final String REPORT_FILENAME = "report.output";
    /** Property for the CSV report filename. */
    private static final String CSV_FILENAME = "csv.output";
    /** Property for the CSV report filename. */
    private static final String MD_FILENAME = "markdown.output";
    /** Property for the excel report filename. */
    private static final String ISSUES_FILENAME = "issues.output";
    /** Placeholder for the base directory of reporting. */
    private static final String BASEDIR = "BASEDIR";
    /** Placeholder for the date of reporting. */
    private static final String DATE = "DATE";
    /** Placeholder for the name of the project. */
    private static final String NAME = "NAME";
    
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
    
    /**
     * Create different files with selected filter
     * 
     * @param pReportList   a list of export methods
     * @throws IOException
     */
    public void createFiles(List<Report> pReportList) throws IOException {
        //TODO Have to add a if for each new export method
        if (exportConfiguration.isEnableCSV()) {
            CSVExporter csvExporter = new CSVExporter(exportConfiguration);
            String fileName = formatFilename(CSV_FILENAME, exportConfiguration.getOutput(), "INDABA");
            csvExporter.createFiles(pReportList, fileName);
        }
    }

    /**
     * Build a filename with given parameters following a format
     * 
     * @param propertyName  name of the main field
     * @param baseDir   direction
     * @param projectName   sonarqube project name
     * @return  formated filename
     */
    public static String formatFilename(final String propertyName, final String baseDir, final String projectName) {
        // construct the filename by replacing date and name
        return StringManager.getProperty(propertyName)
                .replaceFirst(BASEDIR, Matcher.quoteReplacement(baseDir))
                .replace(DATE, new SimpleDateFormat(StringManager.DATE_PATTERN).format(new Date()))
                .replace(NAME, projectName);
    }
}
