package ca.mcmaster.se2aa4.mazerunner.Decision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Tools.*;

public class DecisionPathValidator implements Decision{

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void makeDecision(Maze maze, String[] args) {
        try{
            logger.info("Validating path");
            CLIParser cliParser = new CLIParser(args);
            Path path = cliParser.getVerifyPath();
            if (maze.validatePath(path)) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        } catch (Exception e) {
            System.err.println("DecisionPathValidator failed.  Reason: " + e.getMessage());
            logger.error("DecisionPathValidator failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }


    }

}
