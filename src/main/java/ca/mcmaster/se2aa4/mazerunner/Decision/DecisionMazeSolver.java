package ca.mcmaster.se2aa4.mazerunner.Decision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Tools.CLIParser;
import ca.mcmaster.se2aa4.mazerunner.Tools.Maze;
import ca.mcmaster.se2aa4.mazerunner.Tools.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.Tools.Path;

public class DecisionMazeSolver implements Decision{
    private static final Logger logger = LogManager.getLogger(); 

    @Override
    public void makeDecision(Maze maze, String[] args){
        try{
            CLIParser cliParser = new CLIParser(args);
            String method = cliParser.getMethod();
            Path path = MazeFactory.solveMaze(method, maze);
            System.out.println(path.getFactorizedForm());
        } catch (Exception e) {
            System.err.println("DecisionMazeSolver failed.  Reason: " + e.getMessage());
            logger.error("DecisionMazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }
    }

}
