package view;

import configuration.ReportCommandLine;
import tools.PluginStringManager;
import utils.StringManager;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] projectArray = new String[]{
                "-t", "darkchess" ,
                "-p", "com.c0nrad.darkchess:darkchess",
                "-b", "master",
                "-a", "Administrator",
                "-v", "1.0.3-SNAPSHOT",
                "-k", "com.c0nrad.darkchess:darkchess" };
        String[][] projectArrays = new String[][] { projectArray };
        
        ReportCommandLine.execute(
                projectArrays
                ,new String[]{
                "-o", "C:\\sonarqube-8.4.1.35646\\temp\\reportindaba\\"
                });
    }

}
