package ca.mcmaster.se2aa4.mazerunner;


public class MazeExplorer {

    private int column;
    private int row;
    private char orientation = 'E';
    private int EntranceColumn = 0;
    private int EntranceRow;
    private int ExitColumn;
    private int ExitRow;
    private String canonicalPath;
    private String factorizedPath;

    public String getCanonicalPath(){
        return canonicalPath;
    }

    private void findFactorized(){

    }

    public String getFactorizedOPath(){
        return factorizedPath;
    }

    // here the player will move across the maze in the direction
    
    private void getRight(){
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

    private void getLeft(){
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



    private void moveForward(){
        if (orientation == 'N'){
            row--;
        }
        else if (orientation == 'E'){
            column++;
        }
        else if (orientation == 'S'){
            row++;
        }
        else { //'W'
            column--;
        }
    }

    private void findEntrance(char[][] mazeArray){
        //array to store the Entrance Row and Exit Row
        for (int row = 0; row < mazeArray.length; row ++){
            if (mazeArray[row][0] != '#'){
                EntranceRow = row;
                break;
            }
        }

    }
    private void findExit(char[][] mazeArray){
        column = mazeArray[0].length -1;
        for (int row =0; row < mazeArray.length; row ++){
            if (mazeArray[row][column] != '#'){
                ExitRow = row;
                ExitColumn = column;
                break;
            }
        }
    }
    
    
    
    private boolean lookRight(char[][] mazeArray){
        if (orientation == 'N'){
            if (mazeArray[row][column+1] == '#'){
                return false;
            }
        }
        else if (orientation == 'E'){
            if (mazeArray[row+1][column] == '#'){
                return false;
            }
        }
        else if (orientation == 'S'){
            if (mazeArray[row][column-1] == '#'){
                return false;
            }
        }        
        else if (orientation == 'W'){
            if (mazeArray[row-1][column] == '#'){
                return false;
            }
        }

        return true;
        
    }

    private boolean lookForward(char[][] mazeArray){
        if (orientation == 'N'){
            if (mazeArray[row-1][column] == '#'){
                return false;
            }
        }
        else if (orientation == 'E'){
            if (mazeArray[row][column+1] == '#'){
                return false;
            }
        }
        else if (orientation == 'S'){
            if (mazeArray[row+1][column] == '#'){
                return false;
            }
        }        
        else if (orientation == 'W'){
            if (mazeArray[row][column-1] == '#'){
                return false;
            }
        }

        return true;
        
    }

    private boolean lookLeft(char[][] mazeArray){
        if (orientation == 'N'){
            if (mazeArray[row][column-1] == '#'){
                return false;
            }
        }
        else if (orientation == 'E'){
            if (mazeArray[row-1][column] == '#'){
                return false;
            }
        }
        else if (orientation == 'S'){
            if (mazeArray[row][column+1] == '#'){
                return false;
            }
        }        
        else if (orientation == 'W'){
            if (mazeArray[row+1][column] == '#'){
                return false;
            }
        }

        return true;
        
    }



    public void traverse(char[][] mazeArray){
        findEntrance(mazeArray);
        findExit(mazeArray);

        row = EntranceRow;
        column = EntranceColumn;
        canonicalPath = "";


        while(row != ExitRow || column != ExitColumn ){
            System.out.print(orientation);
            System.out.println(" ");
            
            if ( lookRight(mazeArray) ) {
                getRight();
                moveForward();
                canonicalPath = canonicalPath + "R";
                canonicalPath = canonicalPath + "F";
            }
            else if ( lookForward(mazeArray) ) {
                moveForward();
                canonicalPath = canonicalPath + "F";
            }
            else if ( lookLeft(mazeArray) ) {
                getLeft();
                moveForward();
                canonicalPath = canonicalPath + "L";
                canonicalPath = canonicalPath + "F";
            }
            else {
                getRight();
                getRight();
                canonicalPath = canonicalPath + "RR";
                moveForward();
                canonicalPath = canonicalPath + "F";
            }
        }
        
    }
    
}