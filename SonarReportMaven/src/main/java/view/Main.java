package view;

import java.util.ArrayList;
import java.util.Collections;


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
        ArrayList<ArrayList<String>> projectList = new ArrayList<ArrayList<String>>();
        ArrayList<String> arr1 = new ArrayList<String>();
        ArrayList<String> arr2 = new ArrayList<String>();
        String[] conf = new String[] {"noauth", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT", "com.c0nrad.darkchess:darkchess"};
        String[] conf2 = new String[] {"noauth", "darkchess", "master", "iaki", "1.0.3-SNAPSHOT", "com.c0nrad.darkchess:darkchess:src/main/java/com/c0nrad/darkchess/engine/FogEngine.java"};
        Collections.addAll(arr1, conf);
        Collections.addAll(arr2, conf2);
        projectList.add(arr1);
        projectList.add(arr2);
        
        ArrayList<String> pIssueFilter = new ArrayList<String>() {{
            add("aa");
            }
        };
        
        ArrayList<String> pMetricFilter = new ArrayList<String>() {{
            add("aa");
            }
        };
        

        sonarRList.execute(projectList, pIssueFilter, pMetricFilter);
    }

}
