package backend.main.java.com.Battleship;
public class Coordinates {
    // x coordinate
    int x;
    
    // y coordinate
    int y;

    // status of coordinate
    /*
     * 0 = empty
     * 1 = occupied
     * 2 = sunken ship
     * 3 = attacked empty
     * 4 = attacked occupied
     */
    int s;

    /*
     * CONSTRUCTOR
     * param: 2 integers -> x and y value
     * set x & y values
     */
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
        s = 0;
    }
}
