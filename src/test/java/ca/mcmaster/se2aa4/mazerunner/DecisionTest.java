package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcmaster.se2aa4.mazerunner.Decision.DecisionBenchmark;
import ca.mcmaster.se2aa4.mazerunner.Tools.*;
public class DecisionTest {

    @Test
    public void testDecisionPathValidator(){
        try{
            Maze maze = new Maze("./examples/straight.maz.txt");
            Path path = new Path("4F");

            assertTrue( maze.validatePath(path));

            Path path2 = new Path("4R");
            
            assertFalse(maze.validatePath(path2));
        } 
        catch (Exception e){
        }
    }

    @Test
    public void testDecisionMazeSolver(){
        try{
            Maze maze = new Maze("./examples/small.maz.txt");

            Path righthand = MazeFactory.solveMaze("righthand", maze);
            assertTrue(maze.validatePath(righthand));

            Path BFS = MazeFactory.solveMaze("BFS", maze);
            assertTrue(maze.validatePath(BFS));

            Path tremaux = MazeFactory.solveMaze("tremaux", maze);
            assertTrue(maze.validatePath(tremaux));

        }
        catch (Exception e){
        }
    }

    @Test
    public void testDecisionBenchmark() {
        DecisionBenchmark decisionBenchmark = new DecisionBenchmark();

        decisionBenchmark.setMazeTime(100.00);
        decisionBenchmark.setMethodTime(200.00);
        decisionBenchmark.setBaselineTime(150.00);
        decisionBenchmark.setSpeedUpSteps("Test speedup steps");
        decisionBenchmark.setRoundedSpeedup("Test rounded speedup");

        assertEquals(100.00, decisionBenchmark.getMazeTime());
        assertEquals(200.00, decisionBenchmark.getMethodTime());
        assertEquals(150.00, decisionBenchmark.getBaselineTime());
        assertEquals("Test speedup steps", decisionBenchmark.getSpeedUpSteps());
        assertEquals("Test rounded speedup", decisionBenchmark.getRoundedSpeedup());
    }
  


}
