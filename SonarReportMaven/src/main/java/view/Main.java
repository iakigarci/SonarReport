package view;

import java.util.ArrayList;

import org.apache.commons.cli.*;

import configuration.ReportConfiguration;
import configuration.SonarRequestList;


public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // Options options = new Options();
        //
        // Option input = new Option("i", "input", true, "input file path");
        // input.setRequired(true);
        // options.addOption(input);
        //
        // Option output = new Option("o", "output", true, "output file");
        // output.setRequired(true);
        // options.addOption(output);
        //
        // CommandLineParser parser = new DefaultParser();
        // HelpFormatter formatter = new HelpFormatter();
        // CommandLine cmd = null;
        //
        // try {
        // cmd = parser.parse(options, args);
        // } catch (ParseException e) {
        // System.out.println(e.getMessage());
        // formatter.printHelp("utility-name", options);
        //
        // System.exit(1);
        // }
        //
        // String inputFilePath = cmd.getOptionValue("input");
        // String outputFilePath = cmd.getOptionValue("output");
        // System.out.println(inputFilePath);
        // System.out.println(outputFilePath);

        SonarRequestList sonarRList = SonarRequestList.getSonarRequestList();
        ReportConfiguration conf = new ReportConfiguration("noauth", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT", "com.c0nrad.darkchess:darkchess");
        ReportConfiguration conf2 = new ReportConfiguration("noauth", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT", "com.c0nrad.darkchess:src/main/java/com/c0nrad/darkchess/engine/FogEngine.java");
        ArrayList<ReportConfiguration> l = new ArrayList<ReportConfiguration>();
        

        l.add(conf);
        l.add(conf2);
        sonarRList.execute(l);
    }

}
