package com.ObjektorienteradProgrammeringsmetodik;
/* Assignment: Robot1.0
 * for Objektorienterad programmeringsmetodik at Umeå universitet
 * Author: Susan Kronberg
 * CS: id19skg
 * Date: 2020-04-16
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class RobotTest {


    public static void main(String[] args){
        System.out.println("This is a program that simulates a robot randomly traveling through a maze");
        // initiate reader object
        Reader mazeFile;
        try {
            // construct reader object
            mazeFile= new FileReader("Maze.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("File was not found");
            return;
        }

        // create maze with reader object
        Maze maze = new Maze(mazeFile);
        //create random robot to travel through maze
        RandomRobot robot = new RandomRobot(maze);
        System.out.println("Starting point is marked by 'S");
        System.out.println("Goal is marked by 'G'");
        System.out.println("Walls are marked by '*'");
        System.out.println("The robot can only walk on blank space");
        System.out.println("Top left corner has coordinates (0,0)");
        int x = robot.getPosition().getX();
        int y = robot.getPosition().getY();
        System.out.println("Robot is currently at starting point: (" +x+ "," +y+ ")");


        // move robot until goal is reached
        while (!robot.hasReachedGoal()){
            robot.move();
            // print position after a move
            x = robot.getPosition().getX();
            y = robot.getPosition().getY();
            System.out.println("Moving to: (" +x+ "," +y+ ")");
            robot.hasReachedGoal();
        }
        System.out.println("Robot has reached goal!");



    }

    /**
     * help method to print out a given position
     * @param p position to print out
     */
    private static void printPos(Position p){
        int x = p.getX();
        int y = p.getY();
        System.out.println("Moving to (" +x+ "," +y+ ")");
    }


}


