package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Configuration {
    private static final Logger logger = LogManager.getLogger();
    private static String filename;
    private static String pathCheck;


    public static void configure(String[] args){
        
        try{
            Options options = new Options();
            options.addOption("i", true, "option reacting to -i");
            options.addOption("p", true, "option reacting to -p");
            CommandLineParser parser = new DefaultParser();
    
            CommandLine cmd = parser.parse(options, args);
            filename = cmd.getOptionValue("i");
    
            MazeConstructor maze = new MazeConstructor();
            MazeExplorer explorer = new MazeExplorer();
            VerifyPath verifyPath = new VerifyPath();
            char [][] mazeArray = maze.buildMaze(filename);
    
            //for factorized paths, user must enter it with quotation marks
            if (cmd.hasOption("p")) {
                pathCheck = cmd.getOptionValue("p");
                verifyPath.pathChecker(mazeArray, pathCheck);
            }
            else if (cmd.hasOption("i")){
                explorer.getFactorizedPath(mazeArray);
            }
    
        }
        catch (Exception e){
            logger.info ("** Starting Maze Runner");
            logger.info ("**** Reading the maze from file");
            logger.error("/!\\ An error has occurred /!\\");
            logger.info ("**** Computing path");
            logger.info("PATH NOT COMPUTED");
            logger.info ("** End of Maze Runner");
        }
        
    }   
}


