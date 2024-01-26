package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;

public class MazeConstructor{

    private char[][] mazeArray;

    public char[][] getMazeArray(){
        return mazeArray;
    }

    private static final Logger logger = LogManager.getLogger();

    public void mazeBuilder(String filePath){
        int height = getHeight(filePath);
        int width = getWidth(filePath);
        int row = 0;

        char [][] maze = new char [height][width];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine())!= null) {
                for (int column = 0; column < width; column++) {
                    if (line.charAt(column) == ' ') {
                        maze[row][column] = ' ';
                    }
                    else if (line.charAt(column) == '#') {
                        maze[row][column] = '#';
                    }
                }
                row++;
            }

        }

        catch(IOException e) {
            logger.error("/!\\ An error has occurred /!\\", e);
        }
        this.mazeArray = maze;
    }

    public int getWidth(String filePath){
        int width = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            width = reader.readLine().length();
        }
        catch (IOException e) {
            logger.error("/!\\ An error has occurred /!\\", e);
        }
        return width;
    }

    public int getHeight(String filePath) {
        int height = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) height++;
        } catch (IOException e) {
            logger.error("/!\\ An error has occurred /!\\", e);
        }
        return height;
    }

}