package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Configuration {
    private static final Logger logger = LogManager.getLogger();

    public static void Configure(String[] args){
        logger.info("** Starting Maze Runner");
        
        Configuration config = new Configuration();
        config.getMazeFile(args);
        config.getFilename();
        MazeExplorer explorer = new MazeExplorer();
        MazeConstructor maze = new MazeConstructor();
        logger.info("**** Computing path");
        try{
            maze.mazeBuilder(config.getFilename());
            char [][] mazeArray = maze.getMazeArray();
            explorer.traverse(mazeArray);
            logger.info("** End of MazeRunner");
        }

        catch(Exception e){
            logger.error("/!\\ An error has occurred /!\\");
        }
    }

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


