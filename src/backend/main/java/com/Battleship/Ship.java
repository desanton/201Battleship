package backend.main.java.com.Battleship;
import java.util.Set;

public class Ship {
    // name of type, like "destroyer", "submarine", etc.
    String type;

    // in units of game board (i.e. destroyer occupies 2 spots on game board, so size = 2)
    int size;

    // number of hits on the ship
    int hitCount;
    
    // set of all the coordinates that the boat takes up
    Set<Coordinates> coordinates;

    // constructor
    public Ship() {
        hitCount = 0;
    }

    public Set<Coordinates> getShipCoordinates(String type){
        return coordinates;
    }

    // returns true if ship is sunk 
    public boolean updateHitCount() {
        hitCount++;
        return (hitCount == size);
    }
}
