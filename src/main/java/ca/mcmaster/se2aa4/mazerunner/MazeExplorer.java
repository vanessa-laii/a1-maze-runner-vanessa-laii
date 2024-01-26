package ca.mcmaster.se2aa4.mazerunner;


public class MazeExplorer {

    private int column;
    private int row;
    private char orientation = 'E';
    private int EntranceColumn = 0;
    private int EntranceRow;
    private int ExitColumn;
    private int ExitRow;
    private String canonicalPath="";
    private String factorizedPath = "";

    public void getCanonicalPath(){
        System.out.println("The canonical path is " + canonicalPath);
    }

    public void getFactorizedPath(){
        int count = 1;
        char currentChar = 'F';
        for (int i= 1; i< canonicalPath.length(); i++){
            if (canonicalPath.charAt(i) == currentChar){
                count++;
            }
            else{
                factorizedPath += Integer.toString(count);
                factorizedPath += currentChar;
                factorizedPath += " ";

                count = 1; 
                currentChar = canonicalPath.charAt(i);
            }
        }
        factorizedPath += Integer.toString(count);
        factorizedPath += currentChar;

        System.out.println("The factorized path is " + factorizedPath);

    }

    
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
    
    
    
    private boolean lookRight(char[][] mazeArray){
        if ((orientation == 'N') && (mazeArray[row][column+1] == '#') )
            return false;
        else if ((orientation == 'E') && (mazeArray[row+1][column] == '#'))
            return false;
        else if ((orientation == 'S') && (mazeArray[row][column-1] == '#'))
            return false;
        else if ((orientation == 'W') && (mazeArray[row-1][column] == '#'))
            return false;
        return true;
    }

    private boolean lookForward(char[][] mazeArray){
        if ((orientation == 'N') && (mazeArray[row-1][column] == '#') )
            return false;
        else if ((orientation == 'E') && (mazeArray[row][column+1] == '#'))
            return false;
        else if ((orientation == 'S') && (mazeArray[row+1][column] == '#'))
            return false;
        else if ((orientation == 'W') && (mazeArray[row][column-1] == '#'))
            return false;
        return true;        
    }

    private boolean lookLeft(char[][] mazeArray){
        if ((orientation == 'N') && (mazeArray[row][column-1] == '#') )
            return false;
        else if ((orientation == 'E') && (mazeArray[row-1][column] == '#'))
            return false;
        else if ((orientation == 'S') && (mazeArray[row][column+1] == '#'))
            return false;
        else if ((orientation == 'W') && (mazeArray[row+1][column] == '#'))
            return false;
        return true;               
    }



    public void traverse(char[][] mazeArray){
        entrance(mazeArray);
        exit(mazeArray);

        row = EntranceRow;
        column = EntranceColumn;


        while(row != ExitRow || column != ExitColumn ){
            
            if (lookRight(mazeArray)) {
                getRight();
                canonicalPath = canonicalPath + "R";
                moveForward();
                canonicalPath = canonicalPath + "F";
            }
            else if ( lookForward(mazeArray) ) {
                moveForward();
                canonicalPath = canonicalPath + "F";
            }
            else if ( lookLeft(mazeArray) ) {
                getLeft();
                canonicalPath = canonicalPath + "L";
                moveForward();
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

        getCanonicalPath();
        getFactorizedPath();
        
    }
    
}