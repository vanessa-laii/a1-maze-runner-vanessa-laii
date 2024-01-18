package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;


public class Main {

    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
        
        Configuration config = new Configuration(args);
        Maze maze = new Maze(args);
        Control controls = new Control(args);
        VerifyPath verify = new VerifyPath(args);
        Path path = new Path(args);

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}


