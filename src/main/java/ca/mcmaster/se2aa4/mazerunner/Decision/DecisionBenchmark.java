package ca.mcmaster.se2aa4.mazerunner.Decision;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Tools.CLIParser;
import ca.mcmaster.se2aa4.mazerunner.Tools.Maze;
import ca.mcmaster.se2aa4.mazerunner.Tools.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.Tools.Path;

public class DecisionBenchmark implements Decision {
    private static final Logger logger = LogManager.getLogger();
    private double mazeTime;
    private double methodTime;
    private double baselineTime;
    private String speedUpSteps;
    private String roundedSpeedup;

    @Override
    public void makeDecision(Maze maze, String[] args){
        Timer(maze, args);
        System.out.println("Time to load the maze " + String.format("%.2f", this.mazeTime) + " ms");
        System.out.println("Time to explore the maze using method " + String.format("%.2f", this.methodTime) + " ms");
        System.out.println("Time to explore the maze using baseline " + String.format("%.2f", this.baselineTime) + " ms");
        System.out.println(this.speedUpSteps);
        System.out.println("Speedup: " + this.roundedSpeedup);
    }

    /**
     * Time the time required for loading the maze, explore the maze using method and baseline.
     *
     * @param maze Maze to solve
     * @param args Command Line Argument
     */
    private void Timer(Maze maze, String[] args){
        try{
            CLIParser cliParser = new CLIParser(args);
            long startTime = System.currentTimeMillis();
            cliParser.parse(args);
            long loadedMaze = System.currentTimeMillis();
            long elapsedTime = loadedMaze - startTime;

            this.mazeTime = (double) elapsedTime;

            String method = cliParser.getMethod();
            Path pathMethod = MazeFactory.solveMaze(method, maze);
            long solvedMethod = System.currentTimeMillis();
            long elapsedTime2 = solvedMethod - loadedMaze;
            this.methodTime = (double) elapsedTime2; 

            String baseline = cliParser.getBaseline();
            Path pathBaseline = MazeFactory.solveMaze(baseline, maze);
            long solvedBaseline = System.currentTimeMillis();
            long elapsedTime3 = solvedBaseline - elapsedTime2;
            this.baselineTime = (double) elapsedTime3; 

            long pathMethodSize = pathMethod.getPathSteps().size();
            long pathBaselineSize = pathBaseline.getPathSteps().size();
            double speedup = (double) pathBaselineSize / pathMethodSize;
            
            this.speedUpSteps = ("The number of steps in baseline " + pathBaselineSize + " and the number of steps in method " + pathMethodSize);
            this.roundedSpeedup = String.format("%.2f", speedup);

        } catch (Exception e){
            System.err.println("DecisionBenchmark failed.  Reason: " + e.getMessage());
            logger.error("DecisionBenchmark failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        } 
    }

    public double getMazeTime() {
        return this.mazeTime;
    }

    public void setMazeTime(double mazeTime) {
        this.mazeTime = mazeTime;
    }

    public double getMethodTime() {
        return this.methodTime;
    }

    public void setMethodTime(double methodTime) {
        this.methodTime = methodTime;
    }

    public double getBaselineTime() {
        return this.baselineTime;
    }

    public void setBaselineTime(double baselineTime) {
        this.baselineTime = baselineTime;
    }

    public String getSpeedUpSteps() {
        return this.speedUpSteps;
    }

    public void setSpeedUpSteps(String speedUpSteps) {
        this.speedUpSteps = speedUpSteps;
    }

    public String getRoundedSpeedup() {
        return this.roundedSpeedup;
    }

    public void setRoundedSpeedup(String roundedSpeedup) {
        this.roundedSpeedup = roundedSpeedup;
    }

}
