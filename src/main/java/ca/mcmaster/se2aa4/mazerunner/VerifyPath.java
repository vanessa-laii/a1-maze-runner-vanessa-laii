package ca.mcmaster.se2aa4.mazerunner;


public class VerifyPath {
    private int column;
    private int row;
    private char orientation = 'E';
    private int EntranceColumn = 0;
    private int EntranceRow;
    private int ExitColumn;
    private int ExitRow;

    public void pathChecker(char[][] mazeArray, String pathCheck){
        if ((traverseWE(mazeArray, pathCheck) == false) || traverseEW(mazeArray, pathCheck) == false){
            System.out.println("Incorrect Path");
        }
        else {
            System.out.println("Correct Path");
        }
    }

    private String getCanonical(String pathCheck) {
        if (pathCheck.matches("[FLR]+")) {
            return pathCheck;
        }
        String[] path = pathCheck.split(" ");
        StringBuilder canonicalPath = new StringBuilder();

        for (String step : path) {
            if (step.length() >= 1) {
                char num = step.charAt(0);
                if (Character.isDigit(num)) {
                    int count = Character.getNumericValue(num);
                    char direction = step.charAt(1);
                    for (int i = 0; i < count; i++) {
                        canonicalPath.append(direction);
                    }
                } 
                else canonicalPath.append(num);
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
    



    private boolean traverseWE(char[][] mazeArray, String pathCheck){
        pathCheck = getCanonical(pathCheck);
        entrance(mazeArray);
        exit(mazeArray);

        row = EntranceRow;
        column = EntranceColumn;
        boolean walking = true;
        
        for (int i=0; i<pathCheck.length(); i++){
            if (mazeArray[row][column] == '#'){
                walking = false;
                return false;
            }
            if (pathCheck.charAt(i) == 'R') turnRight();
            else if (pathCheck.charAt(i) == 'L') turnLeft();
            else if (pathCheck.charAt(i) == 'F') forward();

        }

        if ((walking) && (row == ExitRow && column == ExitColumn )){
            return true;
        }
        else if (walking){
            return false;
        }
        return false;
    }
        
    //(entrance to exit)
    private boolean traverseEW(char[][] mazeArray, String pathCheck){
        pathCheck = getCanonical(pathCheck);
        entrance(mazeArray);
        exit(mazeArray);
        orientation = 'W';
        
        row = ExitRow;
        column = ExitColumn;

        boolean walking = true;
        for (int i=0; i<pathCheck.length(); i++){
            if (mazeArray[row][column] == '#'){
                walking = false;
                break;
            }
            if (pathCheck.charAt(i) == 'R') turnRight();
            else if (pathCheck.charAt(i) == 'L') turnLeft();
            else if (pathCheck.charAt(i) == 'F') forward();

        }

        if ((walking) && (row == EntranceRow && column == EntranceColumn )){
            return true;
        }
        else if (walking){
            return false;
        }
        return false;
        
    }
} 

