package com.ObjektorienteradProgrammeringsmetodik;
/* Assignment: Robot1.0
 * for Objektorienterad programmeringsmetodik at Umeå universitet
 * Author: Susan Kronberg
 * CS: id19skg
 * Date: 2020-04-16
 */

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    /***
     * Position Contructor
     * @param x - x coordinates represent horizontal, string index of maze
     * @param y - y coordinates represent vertical, array index of maze
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * getX() - get x coordinate for a position
     * @return x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * getY() - get y coordinate for a position
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * get position south of current position
     * @return new position with coordinates for southern position
     */
    public Position getPosToSouth(){
        // southern position is represented by increased index in an arraylist
        this.y = y+1;
        return new Position(x,y);
    }

    /**
     * get position north of current position
     * @return new position with coordinates for northern position
     */
    public Position getPosToNorth(){
        // northern position is represented by decreased index in an arraylist
        this.y = y-1;
        return new Position(x,y);
    }

    /**
     *  get position east of a position
     * @return new position with coordinates to eastern position
     */
    public Position getPosToEast(){
        // eastern position is represented by increased index of a string
        this.x = x+1;
        return new Position(x,y);
    }
    public Position getPosToWest(){
        // western position is represented by decreased index of a string
        this.x = x-1;
        return new Position(x,y);

    }

    /**
     * see if two position objects have the same coordinates
     * @param o - position object to compare
     * @return boolean
     */
    public boolean equals(Object o){
        // if o is a position object assign data to new object and compare x and y values
        if( o instanceof Position) {
            Position p2 =(Position) o;
            // true if p2s x and y values equals to compared objects x and y values
            return p2.x==x&&p2.y==y;
        }
        else {
            return false;
        }
    }


    /**
     * hashcode for position class
     * @return hashcode for position
     */
    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
