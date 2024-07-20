package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Tools.MazeFactory;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        
        MazeFactory mazeFactory = new MazeFactory();
        mazeFactory.makeDecision(args);

        logger.info("End of MazeRunner");
    }


}


