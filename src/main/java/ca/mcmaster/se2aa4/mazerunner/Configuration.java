package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Configuration {
    private static final Logger logger = LogManager.getLogger();

    private String filename;
    public String getFilename(){
        return filename;
    }

    public void getMazeFile(String[] args){
        try{
            Options options = new Options();
            options.addOption("i", true, "option reaction to i");
            CommandLineParser parser = new DefaultParser();

            CommandLine cmd = parser.parse(options, args);
            filename = cmd.getOptionValue("i");
        }
        catch(Exception e){
            logger.error("/!\\ An error has occurred /!\\");
        }
    }
}


