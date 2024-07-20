package ca.mcmaster.se2aa4.mazerunner.Tools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Decision.*;
import ca.mcmaster.se2aa4.mazerunner.Solver.*;

public class MazeFactory {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Make a decision based the arguments provided.
     *
     * @param method Method to solve maze with
     * @param args Command Line arguments
     * @throws Exception If the arguments are not correct
     */  
    public void makeDecision (String[] args){

        try{
            Decision decision;
            CLIParser cliParser = new CLIParser(args);
            if (cliParser.getIsVerify()){
                decision = new DecisionPathValidator();
                decision.makeDecision(cliParser.getMaze(), args);
            }
            else if (cliParser.getIsBaseline()){
                decision = new DecisionBenchmark();
                decision.makeDecision(cliParser.getMaze(), args);
            }
            else{
                decision = new DecisionMazeSolver();
                decision.makeDecision(cliParser.getMaze(), args);
            }

        }
        catch (Exception e){
            System.err.println("MazeFactory failed.  Reason: " + e.getMessage());
            logger.error("MazeFactory failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }
        
    }

    /**
     * Solve provided maze with specified method.
     *
     * @param method Method to solve maze with
     * @param maze Maze to solve
     * @return Maze solution path
     * @throws Exception If provided method does not exist
     */
    public static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver = null;
        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "BFS" -> {
                logger.debug("BFS algorithm chosen.");
                solver = new BFSSolver();
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }

        logger.info("Computing path");
        return solver.solve(maze);
    }

}
