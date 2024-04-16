package backend.main.java.com.Battleship;
import java.util.Set;

public class Ship {
    // name of type, like "destroyer", "submarine", etc.
    String type;

    // in units of game board (i.e. destroyer occupies 2 spots on game board, so size = 2)
    int size;
    
    // set of all the coordinates that the boat takes up
    Set<Coordinates> coordinates;

    public Set<Coordinates> getShipCoordinates(String type){
        return coordinates;
    }
}
