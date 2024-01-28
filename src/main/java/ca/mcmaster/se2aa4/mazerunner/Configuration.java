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
            logger.info("** Starting Maze Runner");
            Configuration config = new Configuration();
            MazeExplorer explorer = new MazeExplorer();
            MazeConstructor maze = new MazeConstructor();
            logger.info("**** Computing path");
            config.getArgs(args);
            char [][] mazeArray = maze.buildMaze(filename);
            explorer.mazePaths(mazeArray);
            logger.info("** End of MazeRunner");
        }

        catch(Exception e){
            logger.error("/!\\ An error has occurred /!\\", e);
        }
    }


    public void getArgs(String[] args){
        try{
            Options options = new Options();
            options.addOption("i", true, "option reacting to -i");
            options.addOption("p", true, "option reacting to -p");
            CommandLineParser parser = new DefaultParser();

            CommandLine cmd = parser.parse(options, args);
            filename = cmd.getOptionValue("i");

            //for factorized paths, user must enter it with quotation marks
            if (cmd.hasOption("p")) {
                pathCheck = cmd.getOptionValue("p");
                VerifyPath verifyPath = new VerifyPath();
                logger.info("**** Verifying path");
                MazeConstructor maze = new MazeConstructor();
                char [][] mazeArray = maze.buildMaze(filename);
                verifyPath.pathChecker(mazeArray, pathCheck, 'E');
                verifyPath.pathChecker(mazeArray, pathCheck, 'W');

                logger.info("**** Path verified");


            }
        }
        catch(Exception e){
            logger.error("/!\\ An error has occurred /!\\", e);
        }
        
    }   
}



