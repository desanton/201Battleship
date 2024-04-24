package backend.main.java.com.Battleship;
import java.util.Set;

public class Ship {
    // name of type, like "destroyer", "submarine", etc.
    char type;

    // in units of game board (i.e. destroyer occupies 2 spots on game board, so size = 2)
    int size;

    // number of hits on the ship
    int hitCount;
    
    // set of all the coordinates that the boat takes up
    List<Coordinates> coordinates;
    
    public Ship() {}

    // constructor
    public Ship( List<Coordinates> coordinates, char type, int size) {
    	this.type = type;
    	this.coordinates = coordinates;
    	this.size = size;
        hitCount = 0;
    }

    public List<Coordinates> getShipCoordinates(char type){
//    	System.out.println("coordintes: ");

        return coordinates;
    }

    // returns true if ship is sunk 
    public boolean updateHitCount() {
    	
        hitCount++;
        System.out.println("Size: " + size + ", Hits: " + hitCount);
        return (hitCount == size);
    }
}
