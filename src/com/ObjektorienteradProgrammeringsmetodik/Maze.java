package com.ObjektorienteradProgrammeringsmetodik;
/* Assignment: Robot1.0
 * for Objektorienterad programmeringsmetodik at Umeå universitet
 * Author: Susan Kronberg
 * CS: id19skg
 * Date: 2020-04-16
 */

import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Maze {
    private ArrayList<String> maze;
    private int rows = 0;
    private int cols = 0;


    /***
     * Maze Constructor - A maze can only and must include:
     * 'S' - one starting point
     * '*' - walls
     * 'G' - at least one goal
     * ' ' (blank space) - path
     *
     * @param r - reader of maze file
     */
    public Maze(Reader r) {
        // initiate ararylist to hold maze
        maze = new ArrayList<>();

        // scan reader file containing maze
        Scanner s = new Scanner(r);

        // initiate empty string to gather all characters in maze file
        String mazeChars = new String();

        while (s.hasNextLine()) {
            // each string line equates to all columns on a row in maze
            String line = s.nextLine();

            //use regular expressions to specify not allowed characters
            // all characters except S,G,*, and blank space
            String  illegalChars= "[^S|G|*|\\s]";

            //create pattern for regular expression
            Pattern checkIllegalChars = Pattern.compile(illegalChars);

            //match regular expression to each string line
            Matcher matchChars = checkIllegalChars.matcher(line);

            // if characters that are not allowed are found throw exception
            if(matchChars.find()){
                throw new IllegalArgumentException("Maze contains illegal characters");
            }

            //add lines to string
            mazeChars = mazeChars.concat(line);

            // add string to arraylist
            maze.add(line);

            //increase row counter
            this.rows++;

        }

        //initiate starting points and count them from string containing all chars from maze file
        int startingPoints = 0;
        for(int i = 0; i <= mazeChars.lastIndexOf("S"); i++){
            if(mazeChars.charAt(i) == 'S'){
                startingPoints ++;
            }
        }

        // test if maze contains required characters, throw exception if not
        if(startingPoints != 1){
            throw new IllegalArgumentException("Start requirements not met");
        }
        if(!mazeChars.contains(" ")){
            throw new IllegalArgumentException(" Maze has no movable path");
        }
        if(!mazeChars.contains("G")){
            throw new IllegalArgumentException("Maze has no goals");
        }
        if(!mazeChars.contains("*")){
            throw new IllegalArgumentException("Maze has no walls");
        }

        // print the maze
        printMaze();

    }

    /***
     * isMovable - to see if a position in maze is available/ walkable
     * @param p  - position to test
     * @return   - boolean
     */
    public boolean isMovable(Position p) {
        // get coordinates from position
        int x = p.getX();
        int y = p.getY();

        try{
            // get string stored in row, equals to index in arraylist
            String Line = maze.get(y);
            // get character stored in column, equals to index in string
            char potentialMove = Line.charAt(x);
            if(potentialMove == ' ' || potentialMove == 'G')
                return true;

        } catch (IndexOutOfBoundsException e){
            // catch if index of position is out of bounds, aka is not movable and does nothing
        }
        // returns false if position index is out of bounds and if potentialMove is not an allowed path
        return false;

    }


    /***
     * get start position in maze, indicated by char S
     * @return  position
     */
    public Position getStart(){
        // initiate position
        int x = 0;
        int y = 0;
        // go through arraylist
        for (int i = 0; i < rows; i++) {
            // get string stored in current index of arraylist
            String Line = maze.get(i);

            if(Line.contains("S")) {
                // index of character in string equals to column of start position
                x = Line.indexOf("S");
                // index of arraylist equals to row of start position
                y = i;
                break;
            }
        }
        return new Position(x,y);
    }

    /**
     * isGoal - to see if a position is goal in maze
     * @param p - position to test
     * @return  - boolean
     */
    public boolean isGoal(Position p){
        //get positions coordinates and store in separate variables
        // x indicates column
        int x = p.getX();
        //y indicates row
        int y = p.getY();
        // get string stored in row, equals to index in arraylist
        String Line = maze.get(y);
        // get character stored in column, equals to index in string
        char potentialGoal = Line.charAt(x);

        if(potentialGoal == 'G')
            return true;
        else
            return false;
    }

    /**
     * getNumRows - get number of rows in maze
     * @return  - number of rows
     */
    public int getNumRows() {
        return rows - 1;
    }


    /**
     * getNumCols - get number of columns in maze
     * @return - number of columns
     */
    public int getNumCols(){

        for(int i = 0; i < maze.size(); i++){
            // get string of first row
            String oneRow = maze.get(i);
            // string length is equivalent to number of columns in maze
            if(cols < oneRow.length()){
                this.cols = oneRow.length();
            }

        }

        return cols - 1;
    }

    /**
     *  help method to print a maze
     */
    private void printMaze(){
        for(int i = 0; i < rows; i++) {
            System.out.println(maze.get(i));
        }

    }




}


