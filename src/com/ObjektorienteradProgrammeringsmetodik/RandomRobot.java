package com.ObjektorienteradProgrammeringsmetodik;
/* Assignment: Robot1.0
 * for Objektorienterad programmeringsmetodik at Umeå universitet
 * Author: Susan Kronberg
 * CS: id19skg
 * Date: 2020-04-16
 */

import java.util.Random;

public class RandomRobot {
    private Position position;
    private Position previousPosition;
    private Maze maze;

    /***
     * RandomRobot Construtor
     * @param maze uses a maze object as input
     */
    public RandomRobot(Maze maze){
        // store maze
        this.maze = maze;
        //set starting position for robot
        this.position = maze.getStart();
    }

    /**
     * move robot randomly through a maze, can only walk south, north, east, or west if those positions
     * are movable. Can only return to previous position if no other position is available
     */
    public void move(){
        // initiate random object
        Random randomMove = new Random();

        // get x and y coordinates for current position
        int x = position.getX();
        int y = position.getY();

        // initiate position objects for each direction and set coordinates for each direction
        Position west = new Position(x, y);
        west = west.getPosToWest();
        Position east = new Position(x, y);
        east = east.getPosToEast();
        Position south = new Position(x, y);
        south = south.getPosToSouth();
        Position north = new Position(x, y);
        north = north.getPosToNorth();

        // indicator for if a move has been made, initial state is false
        boolean hasMoved = false;

        // if an object is stuck, aka no position is available except previous position, return to
        // previous position
        if(isStuck(west, north, east, south)){
            System.out.println("Can't move forward, must return!");
            setPosition(previousPosition);
            // a move has been made
            hasMoved = true;
        }

        while (!hasMoved){
            // get random number between 0 and 3
            int rand = randomMove.nextInt(4);

            if(rand == 0) {
                // try to move west
               // System.out.println("Trying to move west");
                if(!west.equals(previousPosition)) {
                    if (maze.isMovable(west)) {
                    //    System.out.println("Can move there!");
                        setPosition(west);
                        hasMoved = true;
                    } //else System.out.println("Not a path");
                } //else System.out.println("This is the previous position, can't move there");
            }


            if(rand == 1) {
               // System.out.println("Trying to move east");
                // try to move east
                if (!east.equals(previousPosition)) {
                    if (maze.isMovable(east)) {
                      //  System.out.println("Can move there!");
                        setPosition(east);
                        hasMoved = true;
                    } //else System.out.println("Not a path");
                } // else System.out.println("This is the previous position, can't move there");
            }

            if(rand == 2) {
                //System.out.println("Trying to move south");
                // try to move south
                if (!south.equals(previousPosition)) {
                    if (maze.isMovable(south)) {
                      //  System.out.println("Can move there!");
                        setPosition(south);
                        hasMoved = true;
                    }//else System.out.println("Not a path");
                }    //  else System.out.println("This is the previous position, can't move there");
            }


            if(rand == 3) {
               // System.out.println("Trying to move north");
                // try to move north
                if (!north.equals(previousPosition)) {
                    if (maze.isMovable(north)) {
                       // System.out.println("Can move there!");
                        setPosition(north);
                        hasMoved = true;
                    }//else System.out.println("Not a path");
                }  // else System.out.println("This is the previous position, can't move there");
            }


        }


    }

    /**
     * get current position of a robot object
     * @return position
     */
    public Position getPosition(){
        return position;
    }


    /**
     * see if a randomrobot object has reached goal in maze
     * @return boolean
     */
    public boolean hasReachedGoal(){
        // see if position is a goal
        if(maze.isGoal(position)){
            return true;
        } else return false;
    }

    /**
     * set position new position
     * @param newPosition new position for randomrobot
     */
    private void setPosition(Position newPosition){
        this.previousPosition = position;
        this.position = newPosition;
    }


    /**
     * see if return point has been reached
     * @param p1 one direction position
     * @param p2 another direction position
     * @param p3 another direction position
     * @param p4 another direction position
     * @return boolean
     */
    private boolean isStuck(Position p1, Position p2, Position p3, Position p4){
        // see if a position is movable or is the previous position for each position in parameters
        if(!maze.isMovable(p1) || p1.equals(previousPosition)){
            if(!maze.isMovable(p2) || p2.equals(previousPosition)){
                if(!maze.isMovable(p3) || p3.equals(previousPosition)){
                    if(!maze.isMovable(p4) || p4.equals(previousPosition)){
                        //return point has been reached
                        return true;
                    }
                }
            }
        }
        return false;

    }
}
