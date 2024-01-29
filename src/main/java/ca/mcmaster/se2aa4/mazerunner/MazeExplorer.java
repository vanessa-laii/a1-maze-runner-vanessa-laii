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


    public void getFactorizedPath(char [][] mazeArray){
        traverse(mazeArray);
        factorizedPath();
        System.out.println(factorizedPath);
    }


    private void factorizedPath(){
        int count = 1;
        char currentChar = 'F';
        for (int i= 1; i<canonicalPath.length(); i++){
            if (canonicalPath.charAt(i) == currentChar){
                count++;
            }
            else{
                if (count>1) factorizedPath += Integer.toString(count);
                factorizedPath += currentChar;
                factorizedPath += " ";

                count = 1; 
                currentChar = canonicalPath.charAt(i);
            }
        }
        factorizedPath += Integer.toString(count);
        factorizedPath += currentChar;
    }
    
    public static void turnRight(){
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

    public static void turnLeft(){
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



    public static void forward(){
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
    
    
    public static char lookRight(char[][] mazeArray){
        if (orientation == 'N')
            return mazeArray[row][column+1];
        else if (orientation == 'E')
            return mazeArray[row+1][column];
        else if (orientation == 'S')
            return mazeArray[row][column-1];
        else
            return mazeArray[row-1][column];
       
    }

    public static char lookForward(char[][] mazeArray){
        if (orientation == 'N')
            return mazeArray[row-1][column];
        else if (orientation == 'E')
            return mazeArray[row][column+1];
        else if (orientation == 'S')
            return mazeArray[row+1][column];
        else 
            return mazeArray[row][column-1];

    }


    public static char lookLeft(char[][] mazeArray){
        if (orientation == 'N')
            return mazeArray[row][column-1];
        else if (orientation == 'E')
            return mazeArray[row-1][column];
        else if (orientation == 'S')
            return mazeArray[row][column+1];
        else return mazeArray[row+1][column];
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