package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
        
        Configuration config = new Configuration(args);
        MazeExplorer explorer = new MazeExplorer();

        logger.info("**** Computing path");

        String path = explorer.canonicalPath();
        String facPath = explorer.factorizedPath();

        
        System.out.println("The Canonical Path is " + path);
        System.out.println("The Factorized Path is " + facPath);


        logger.info("** End of MazeRunner");
    }

}