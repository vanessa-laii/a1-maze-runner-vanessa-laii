package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Tools.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BFSSolverTest {
    @Test
    public void testBFSSolver() {

        try{
            Maze maze = new Maze("-i ./examples/tiny.maz.txt");
            Path path = new Path("3F L 4F R 3F");
            Path righthand = new Path("5F 2R 2F R 2F R 2F 2R 2F R 2F R 3F");
    
            assertEquals(path, MazeFactory.solveMaze("BFS", maze));
            assertNotEquals(righthand, MazeFactory.solveMaze("BFS", maze));
        }
        catch (Exception e){
        }
    }

    @Test
    public void testBFSSolver2() {

        try{
            Maze maze = new Maze("-i ./examples/small.maz.txt");
            Path path = new Path("3F L 4F R 3F");
            Path righthand = new Path("F R F 2R 2F R 2F R 2F 2R 4F R 2F R 4F 2R 2F R 4F R 2F R 2F 2R 2F L 2F L 4F R 2F R 2F 2R 4F R 2F R 2F 2R 2F R 2F R 4F R 2F L 2F R 2F L F");
    
            assertEquals(path, MazeFactory.solveMaze("BFS", maze));
            assertNotEquals(righthand, MazeFactory.solveMaze("BFS", maze));
        }
        catch (Exception e){
        }
    }

    @Test
    public void testBFSSolver3() {

        try{
            Maze maze = new Maze("-i ./examples/medium.maz.txt");
            Path path = new Path("F L 2F R 2F L 18F L 2F R 2F R 8F R 2F L 6F R 10F L 4F R 10F L 10F R 4F L F");
            Path righthand = new Path("F R 6F 2R 8F R 2F R 2F 2R 2F R 2F R 4F R 2F L 4F L 2F 2R 2F R 4F R 2F L 2F R 2F R 4F R 2F 2R 2F L 2F R 2F R 4F R 2F 2R 2F L 2F R 2F 2R 2F R 2F R 2F 2R 4F R 2F R 2F 2R 4F R 2F R 2F 2R 4F R 2F R 2F 2R 2F R 10F R 2F R 8F 2R 8F L 2F R 4F R 2F R 2F 2R 2F R 2F R 14F 2R 12F R 2F R 6F 2R 4F R 2F R 6F R 2F L 6F 2R 6F R 2F R 8F 2R 12F R 2F R 10F 2R 6F R 2F R 4F 2R 4F L 2F R 4F L 2F R 2F L 2F R 2F L 2F R 2F L 4F R 2F R 2F 2R 4F R 2F R 6F R 2F 2R 2F R 2F R 4F 2R 2F R 2F R 4F 2R 4F R 2F R 2F 2R 2F R 2F R 4F R 2F L 2F 2R 2F R 2F R 6F L 2F R 8F 2R 8F R 2F R 10F R 4F R 2F 2R 2F R 2F 2R 2F R 2F R 2F L 4F R 2F 2R 4F 2R 2F R 4F R 2F R 2F 2R 4F R 2F R 6F 2R 6F R 4F R 2F R 2F L 2F 2R 2F R 4F R 2F R 2F 2R 2F R 2F R 4F 2R 4F L 4F R 2F R 4F 2R 2F R 2F 2R 2F R 2F R 2F 2R 6F R 2F R 8F R 6F R 2F 2R 2F L 2F R F");
    
            assertEquals(path, MazeFactory.solveMaze("BFS", maze));
            assertNotEquals(righthand, MazeFactory.solveMaze("BFS", maze));
        }
        catch (Exception e){
        }
    }

    @Test
    public void verifyBFSpath() {

        try{
            Maze maze = new Maze("-i ./examples/giant.maz.txt");
            Path path = new Path("F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F");
    
            assertEquals(path, MazeFactory.solveMaze("BFS", maze));
            assertTrue(maze.validatePath(path));
        }
        catch (Exception e){
        }
    }

}
