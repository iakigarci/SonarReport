package ws;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.sonar.api.config.Configuration;
import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.RequestHandler;
import org.sonar.api.server.ws.Response;


import java.io.File;


import configuration.ReportCommandLine;
import factories.ReportFactory;
import tools.PluginStringManager;
import tools.ZipFolder;
import utils.StringManager;

import java.io.IOException;
import java.nio.file.Files;

public class ExportTask implements RequestHandler {

    // Sonarqube configuration
    private final Configuration config;

    /**
     * public constructor
     * @param config sonarqube configuration
     */
    ExportTask(Configuration config){
        this.config = config;
    }

    /**
     * handle a request, write output in response stream.
     * @param request
     * @param response
     */
    public void handle(Request request, Response response) throws IOException,
            OpenXML4JException, XmlException{
        // Get project key
        String projectKey = request.getParam(PluginStringManager.getProperty("api.report.args.key")).getValue();
        // Getting stream and change headers
        Response.Stream stream = response.stream();

        // Get a temp folder
        String outputDirectory = System.getProperty("user.dir") + "\\temp\\reportindaba\\";
        System.out.println(outputDirectory);
        // Last line create file instead of folder, we delete file to put folder at the same place later

        // Start generation, re-using standalone script
        try {
            final Request.StringParam pBranch = request.getParam(PluginStringManager.getProperty("api.report.args.branch"));
            String[] projectArray = new String[]{
                    "-t", request.getParam(PluginStringManager.getProperty("api.report.args.token")).getValue(),
                    "-p", projectKey,
                    "-b", pBranch.isPresent()?pBranch.getValue(): StringManager.NO_BRANCH,
                    "-a", request.getParam(PluginStringManager.getProperty("api.report.args.author")).getValue(),
                    "-v", "1.0.3-SNAPSHOT",
                    "-k", projectKey };
            String[][] projectArrays = new String[][] { projectArray };
            
            ReportCommandLine.execute(
                    projectArrays
                    ,new String[]{
                    "-o", outputDirectory
                    });

            stream.setMediaType("application/zip");
            String filename = ReportFactory.formatFilename("zip.report.output", "", projectKey);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + '"');

            // generate zip output and send it
            String zipPath = System.getProperty("user.dir") + "\\temp\\reportindaba" + ".zip";
            ZipFolder.pack(outputDirectory, zipPath);
            File zip = new File(zipPath);
            FileUtils.copyFile(zip, stream.output());
            Files.deleteIfExists(zip.toPath());
        } catch (Exception e) {
            com.google.gson.Gson gson = new com.google.gson.Gson();
            gson.toJson(PluginStringManager.getProperty("api.tokenerror"));
            System.out.println("Error: ExportTask" + e);
        }

        System.out.println("<Ending ExportTask");
    }
}