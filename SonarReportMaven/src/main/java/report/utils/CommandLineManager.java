package utils;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Manage the command line by parsing it and providing preprocessed data.
 */
public class CommandLineManager {

    /** Possible options definition. */
    private Options options;
    /** Parser used by the manager. */
    private CommandLineParser parser;
    /** Formatter for automatic help. */
    private HelpFormatter helpFormatter;
    /** Contain the formatted cli. */
    private CommandLine commandLine;

    /** Option details for help. */
    private static final String[][] OPTIONS_DEFINITION = {
            {"h", "help", Boolean.FALSE.toString(), "Display this message."},
            {"s", "server", Boolean.TRUE.toString(), "Complete URL of the targeted SonarQube server."},
            {"t", "token", Boolean.TRUE.toString(), "SonarQube token of the SonarQube user who has permissions on the project."},
            {"p", "project", Boolean.TRUE.toString(), "SonarQube key of the targeted project."},
            {"v", "version", Boolean.TRUE.toString(), "Display current project version."},
            {"b", "branch", Boolean.TRUE.toString(), "Branch of the targeted project. Requires Developer Edition or sonarqube-community-branch-plugin. Default: usage of main branch."},
            {"k", "key", Boolean.TRUE.toString(), "Display current project key."},
            {"o", "output", Boolean.TRUE.toString(), "Output path for exported resources."},
            {"l", "language", Boolean.TRUE.toString(), "Language of the report. Values: en_US, fr_FR. Default: en_US."},
            {"a", "author", Boolean.TRUE.toString(), "Name of the report writer."},
            {"d", "date", Boolean.TRUE.toString(), "Date for the report. Default: current date."},
            {"c", "disable-conf", Boolean.FALSE.toString(), "Disable export of quality configuration used during analysis."},
            {"w", "disable-report", Boolean.FALSE.toString(), "Disable report generation."},
            {"e", "disable-spreadsheet", Boolean.FALSE.toString(), "Disable spreadsheet generation."},
            {"f", "disable-csv", Boolean.FALSE.toString(), "Disable CSV generation"},
            {"m", "disable-markdown", Boolean.FALSE.toString(), "Disable Markdown generation"},
            {"n", "template-markdown", Boolean.TRUE.toString(), "Path to the report template in markdown. Default: usage of internal template."},
            {"r", "template-report", Boolean.TRUE.toString(), "Path to the report template. Default: usage of internal template."},
            {"x", "template-spreadsheet", Boolean.TRUE.toString(), "Path to the spreadsheet template. Default: usage of internal template."}
    };

    /**
     * Default construct which initialize and set options.
     */
    public CommandLineManager() {
        configure();
    }

    /**
     * Set all members and prepare list of possible options
     */
    private void configure() {
        // Initialize values for members.
        options = new Options();
        parser = new DefaultParser();
        helpFormatter = new HelpFormatter();
        commandLine = null;

        // Add options
        for(final String[] option : OPTIONS_DEFINITION) {
            options.addOption(option[0], option[1], Boolean.valueOf(option[2]), option[3]);
        }

    }

    /**
     * Parse the provided command line.
     *
     * @param pArgs Arguments to parse.
     */
    public void parse(final String[] pArgs) {
        // Contains true if options are reliable
        boolean areOptionsCorrect = true;

        try {
            // Parse the command line.
            commandLine = parser.parse(options, pArgs);
        } catch (ParseException e) {
            areOptionsCorrect = false;
            System.out.println("Error CommandLine: " + e);
        }
        if (!areOptionsCorrect) {
            System.out.println("Incorrect");
            printHelp();
            throw new IllegalArgumentException("Illegal command line arguments");
        } else if (commandLine.hasOption("h")) {
            printHelp();
            System.exit(0);
        }
    }

    private void printHelp() {
        helpFormatter.printHelp(128, "java -jar reportindaba.jar",
                "Generate editable reports for SonarQube projects.\n\n", options,
                "\n\nPlease report issues", true);
    }

    /**
     * Check options compatibility:
     * + Options HELP cannot be mixed with other options.
     *
     * @param commandLine Parsed command line.
     * @return True if options respect requirements.
     */
    private boolean checkOptionsUse(final CommandLine commandLine) {
        // number of options which should be called alone
        int standaloneOptions = 0;
        // number of options without restriction
        int analysisOptions = 0;

        for (final Option option : commandLine.getOptions()) {
            if (option.getOpt().equals("h") || option.getOpt().equals("v")) {
                standaloneOptions++;
            } else {
                analysisOptions++;
            }
        }

        return (analysisOptions == 0 || standaloneOptions == 0) && standaloneOptions < 2;
    }

    /**
     * Provides arguments as a list.
     *
     * @return A List<String> with args.
     */
    public List<String> getArgs() {
        List<String> result;

        if (null != commandLine) {
            result = commandLine.getArgList();
        } else {
            result = new ArrayList<>();
        }

        return result;
    }

    /**
     * Determine if an option is contained in the cli.
     *
     * @param pOption Name of the option to retrieve.
     * @return True if the cli contains the option.
     */
    public boolean hasOption(final String pOption) {
        return commandLine != null && commandLine.hasOption(pOption);
    }

    /**
     * Return the value of the corresponding option.
     *
     * @param pOption Name of the option.
     * @return A string containing the value or an empty string.
     */
    public String getOptionValue(final String pOption) {
        return this.getOptionValue(pOption, "");
    }

    /**
     * Return the value of the corresponding option.
     *
     * @param pOption Name of the option.
     * @param pDefault Default value of the option.
     * @return A string containing the value or a default string.
     */
    public String getOptionValue(final String pOption, final String pDefault) {
        String result = pDefault;

        if (null != commandLine) {
            result = commandLine.getOptionValue(pOption, pDefault);
        }

        return result;
    }
}
