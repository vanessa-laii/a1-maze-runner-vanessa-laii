package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
        
        Configuration config = new Configuration();
        config.getMazeFile(args);
        config.getFilename();


        MazeExplorer explorer = new MazeExplorer();
        MazeConstructor maze = new MazeConstructor();
        logger.info("**** Computing path");

        try{
            maze.MazeBuilder(config.getFilename());
            char [][] mazeArray = maze.getMazeArray();
            explorer.traverse(mazeArray);

            System.out.println("The canonical path is " + explorer.getCanonicalPath());
        }
        catch (Exception e){
            System.out.println("Exception is" + e.getMessage());
            logger.error("/!\\ An error has occurred /!\\");
        }

        logger.info("** End of MazeRunner");
    }

}

//interface for file stuff
//interface for algo to find the path 