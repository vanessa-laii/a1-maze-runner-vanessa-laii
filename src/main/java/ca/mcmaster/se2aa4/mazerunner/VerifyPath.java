package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VerifyPath {
    private static int column;
    private static int row;
    private static char orientation = 'E';
    private static int EntranceColumn = 0;
    private static int EntranceRow;
    private static int ExitColumn;
    private static int ExitRow;

    private static final Logger logger = LogManager.getLogger();

    public void pathChecker(char[][] mazeArray, String pathCheck, char Start){
        traverse(mazeArray, pathCheck, Start);
    }

    private String getCanonical(String pathCheck) {
        if (pathCheck.matches("[FLR]+")) {
            return pathCheck;
        }

        String[] path = pathCheck.split(" ");
        StringBuilder canonicalPath = new StringBuilder();

        for (String step : path) {
            char num = step.charAt(0);
            int count = Character.getNumericValue(num);

            if (count > 0) {
                char direction = step.charAt(1);
                for (int i = 0; i < count; i++) {
                    canonicalPath.append(direction);
                }
            } 
        }
        return canonicalPath.toString();
    }


    private void turnRight(){
        switch (orientation){
            case 'N':
                orientation = 'E';
                break;
            case 'E':
                orientation = 'S';
                break;
            case 'S':
                orientation = 'W';
                break;
            case 'W':
                orientation = 'N';
                break;
        }
    }

    private void turnLeft(){
        switch (orientation){
            case 'N':
                orientation = 'W';
                break;
            case 'E':
                orientation = 'N';
                break;
            case 'S':
                orientation = 'E';
                break;
            case 'W':
                orientation = 'S';
                break;
        }
    }



    private void forward(){
        if (orientation == 'N') row--;
        else if (orientation == 'E') column++;
        else if (orientation == 'S') row++;
        else column--;
    }

    private void entrance(char[][] mazeArray){
        for (int row = 0; row < mazeArray.length; row ++){
            if (mazeArray[row][0] != '#'){
                EntranceRow = row;
                break;
            }
        }
    }
    private void exit(char[][] mazeArray){
        column = mazeArray[0].length -1 ;

        for (int row =0; row < mazeArray.length; row ++){
            if (mazeArray[row][column] != '#'){
                ExitRow = row;
                ExitColumn = column;
                break;
            }
        }
    }
    
    

    private static char currentLocation (char[][] mazeArray){
        return mazeArray[row][column];
    }



    private void traverse(char[][] mazeArray, String pathCheck, char Start){
        pathCheck = getCanonical(pathCheck);
        entrance(mazeArray);
        exit(mazeArray);

        if (Start == 'W'){
            row = ExitRow;
            column = ExitColumn;
            orientation = 'W';
            boolean walking = true;
            
            System.out.println("Checking from East to West");
            for (int i=0; i<pathCheck.length(); i++){
                if (pathCheck.charAt(i) == 'R') turnRight();
                else if (pathCheck.charAt(i) == 'L') turnLeft();
                else if (pathCheck.charAt(i) == 'F') forward();
                if (currentLocation(mazeArray) == '#'){
                    System.out.println("Hit a wall, path is incorrect");
                    walking = false;
                    break;
                }
    
            }
    
            if ((walking) && (row == EntranceRow && column == EntranceColumn )){
                System.out.println("Path verified, successfully finished maze");
            }
            else if (walking){
                System.out.println("Did not reach the end of the maze, path is incorrect");
            }
            System.out.println("");
        }

        
        else {
            row = EntranceRow;
            column = EntranceColumn;
            boolean walking = true;
    
            System.out.println("Checking from West to East");
            for (int i=0; i<pathCheck.length(); i++){
                if (pathCheck.charAt(i) == 'R') turnRight();
                else if (pathCheck.charAt(i) == 'L') turnLeft();
                else if (pathCheck.charAt(i) == 'F') forward();
                //if theres two consecutive, need to rotate before moving 
                if (currentLocation(mazeArray) == '#'){
                    System.out.println("Hit a wall, path is incorrect");
                    walking = false;
                    break;
                }
    
            }
    
            if ((walking) && (row == ExitRow && column == ExitColumn )){
                System.out.println("Path verified, successfully finished maze");
            }
            else if (walking){
                System.out.println("Did not reach the end, path is incorrect");
            }
            System.out.println("");
        }
        
        
    } 
}
