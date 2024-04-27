package Battleship;

public class Coordinates {
    // x coordinate
    public int x;
    
    // y coordinate
    public int y;

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
    
    @Override
    public boolean equals(Object obj) {
    	//System.out.println("here");
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        //System.out.println (other.x + " " + other.y);
        return x == other.x && y == other.y;
    }

    public void setStatus(int s){
        this.s = s;
    }

    public int getStatus() {
        return s;
    }
    
}

