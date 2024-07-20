package ca.mcmaster.se2aa4.mazerunner.Decision;

import ca.mcmaster.se2aa4.mazerunner.Tools.Maze;

public interface Decision {
    /**
     * Make a decision of what is required.
     *
     * @param maze Maze to solve
     * @param args Command Line Argument
     */
    void makeDecision(Maze maze, String[] args);
}
