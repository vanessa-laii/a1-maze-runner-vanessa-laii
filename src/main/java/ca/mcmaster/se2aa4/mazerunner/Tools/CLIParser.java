package ca.mcmaster.se2aa4.mazerunner.Tools;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CLIParser {
    private String method;
    private String baseline;
    private Path verifyPath;
    private Maze maze;
    private boolean isVerify;  
    private boolean isBaseline;
    private boolean isSolve;

    private static final Logger logger = LogManager.getLogger();

    public CLIParser(String[] args) throws ParseException {
        parse(args);
    }

    /**
     * Parse CLI
     *
     * @param args Command Line arguments
     */
    public void parse(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');
            maze = new Maze(filePath);
            if (cmd.getOptionValue("p") != null){
                verifyPath = new Path(cmd.getOptionValue("p"));
                isVerify = true;
            }
            else if (cmd.getOptionValue("baseline") != null){
                baseline = cmd.getOptionValue("baseline", "righthand");
                method = cmd.getOptionValue("method", "BFS");
                isBaseline = true;
            }
            else{
                method = cmd.getOptionValue("method", "BFS");
                isSolve = true;
            }
            
        } catch (Exception e) {
            logger.error("CLIParser failed.  Reason: " + e.getMessage());
        }
    }


    public boolean getIsVerify(){
        return isVerify;
    } 
    public boolean getIsBaseline(){
        return isBaseline;
    } 
    public boolean getIsSolve(){
        return isSolve;
    } 

    public Maze getMaze() {
        return maze;
    }

    public Path getVerifyPath() {
        return verifyPath;
    }

    public String getMethod() {
        return method;
    }

    public String getBaseline() {
        return baseline;
    }

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Path to be the baseline algorithm"));

        return options;
    }
}


