package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class MazeConstructor{


    public char[][] buildMaze(String filePath){
        int height = getHeight(filePath);
        int width = getWidth(filePath);

        char [][] maze = new char [height][width];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            int row = 0;
            while ((line = reader.readLine())!= null) {
                for (int column = 0; column < width; column++) {
                    char currentChar = line.charAt(column);
                    maze[row][column] = currentChar;
                }
                row++;
            }

        }
        catch(IOException e) {
        }
        return maze;
    }

    private int getWidth(String filePath){
        int width = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            width = reader.readLine().length();
        }
        catch (IOException e) {
        }
        return width;
    }

    private int getHeight(String filePath) {
        int height = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) height++;
        } catch (IOException e) {
        }
        return height;
    }

}