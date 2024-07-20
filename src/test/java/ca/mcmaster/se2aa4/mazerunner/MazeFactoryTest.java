package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcmaster.se2aa4.mazerunner.Tools.*;

public class MazeFactoryTest {
    
    @Test
    public void testMakeDecisionVerify() {
        String[] args = {"-i", "./examples/straight.maz.txt", "-p", "4F"};
        try {
            CLIParser cliParser = new CLIParser(args);
            assertEquals(true, cliParser.getIsVerify());
        } catch (Exception e) {
            System.err.println("Failed to parse arguments: " + e.getMessage());
        }
    }

    @Test
    public void testMakeDecisionBaseline() {
        String[] args = {"-i", "./examples/straight.maz.txt", "-method", "BFS", "-baseline", "righthand"};
        try {
            CLIParser cliParser = new CLIParser(args);
            assertEquals(true, cliParser.getIsBaseline());
        } catch (Exception e) {
            System.err.println("Failed to parse arguments: " + e.getMessage());
        }
    }

    @Test
    public void testMakeDecisionSolve() {
        String[] args = {"-i", "./examples/straight.maz.txt", "-method", "BFS"};
        try {
            CLIParser cliParser = new CLIParser(args);
            assertEquals(false, cliParser.getIsVerify());
            assertEquals(false, cliParser.getIsBaseline());
            assertEquals(true, cliParser.getIsSolve());
        } catch (Exception e) {
            System.err.println("Failed to parse arguments: " + e.getMessage());
        }
    }
}
