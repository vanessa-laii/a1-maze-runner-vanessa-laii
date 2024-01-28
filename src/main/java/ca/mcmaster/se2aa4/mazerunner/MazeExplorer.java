package ca.mcmaster.se2aa4.mazerunner;


public class MazeExplorer {

    private static int column;
    private static int row;
    private static char orientation = 'E';
    private static int EntranceColumn = 0;
    private static int EntranceRow;
    private static int ExitColumn;
    private static int ExitRow;
    private String canonicalPath = "";
    private String factorizedPath = "";

    public void mazePaths(char [][] mazeArray){
        traverse(mazeArray);
        System.out.println("The canonical path is " + canonicalPath);
        getFactorizedPath();
    }


    private void getFactorizedPath(){
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
    
    
    private static char lookRight(char[][] mazeArray){
        if (orientation == 'N')
            return mazeArray[row][column+1];
        else if (orientation == 'E')
            return mazeArray[row+1][column];
        else if (orientation == 'S')
            return mazeArray[row][column-1];
        else if (orientation == 'W')
            return mazeArray[row-1][column];
        else
            return mazeArray[row][column];
    }

    private static char lookForward(char[][] mazeArray){
        if (orientation == 'N')
            return mazeArray[row-1][column];
        else if (orientation == 'E')
            return mazeArray[row][column+1];
        else if (orientation == 'S')
            return mazeArray[row+1][column];
        else if (orientation == 'W')
            return mazeArray[row][column-1];
        else
            return mazeArray[row][column];
    }


    private static char lookLeft(char[][] mazeArray){
        if (orientation == 'N')
            return mazeArray[row][column-1];
        else if (orientation == 'E')
            return mazeArray[row-1][column];
        else if (orientation == 'S')
            return mazeArray[row][column+1];
        else if (orientation == 'W')
            return mazeArray[row+1][column];
        else
            return mazeArray[row][column];
    }



    private void traverse(char[][] mazeArray){
        entrance(mazeArray);
        exit(mazeArray);

        row = EntranceRow;
        column = EntranceColumn;


        while(row != ExitRow || column != ExitColumn ){
            
            if (lookRight(mazeArray) != '#') {
                turnRight();
                canonicalPath = canonicalPath + "R";
                forward();
                canonicalPath = canonicalPath + "F";
            }
            else if ( lookForward(mazeArray) != '#' ) {
                forward();
                canonicalPath = canonicalPath + "F";
            }
            else if ( lookLeft(mazeArray) != '#' ) {
                turnLeft();
                canonicalPath = canonicalPath + "L";
                forward();
                canonicalPath = canonicalPath + "F";
            }
            else {
                turnRight();
                turnRight();
                canonicalPath = canonicalPath + "RR";
                forward();
                canonicalPath = canonicalPath + "F";
            }
        }
        
    } 
}