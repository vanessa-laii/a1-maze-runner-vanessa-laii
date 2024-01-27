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
    public String getFileName(){
        return filename;
    }
    
    private String pathName;
    public String getPathName(){
        return pathName;
    }

    public static void configure(String[] args){
        try{
            logger.info("** Starting Maze Runner");
            Configuration config = new Configuration();
            MazeExplorer explorer = new MazeExplorer();
            MazeConstructor maze = new MazeConstructor();
            logger.info("**** Computing path");
            config.getMazeFile(args);
            char [][] mazeArray = maze.buildMaze(config.getFileName());
            explorer.traverse(mazeArray);
            logger.info("** End of MazeRunner");
        }

        catch(Exception e){
            logger.error("/!\\ An error has occurred /!\\", e);
        }
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
            logger.error("/!\\ An error has occurred /!\\", e);
        }
    }

    // public void getMazePath(String[] args){
    //     try{
    //         Options options = new Options();
    //         options.addOption("p", true, "path validator p");
    //         CommandLineParser parser = new DefaultParser();

    //         CommandLine cmd = parser.parse(options, args);
    //         if (cmd.hasOption("p")) {
    //             pathName = cmd.getOptionValue("p");
    //         }
    //     }
    //     catch(Exception e){
    //         logger.error("/!\\ An error has occurred /!\\", e);
    //     }
    
    // }
}



