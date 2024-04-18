package backend.main.java.com.Battleship;

public class Player {
    
    Set<Ship> userShips = new HashSet<>();

    Set<Coordinates> attackPoints = new HashSet<>();

    private Board board = new Board();

    void placeAllShips() {
        //?
    } 

    private boolean placeShip(Coordinates C1, Coordinates C2, char type, int size) {
        int x1 = C1.x; int y1 = C1.y; int x2 = C2.x; int y2 = C2.y;
        
        if (Math.abs(x2 - x1) != size - 1 && Math.abs(y2 - y1) != size - 1) return false;
        List<Coordinates> tempShip = new ArrayList<>();

        if (x1 == x2) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                tempShip.add(new Coordinates(x1, y));
            }
        } else if (y1 == y2) {
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                tempShip.add(new Coordinates(x, y1));
            }
        } else {
            return false;
        }

        for (int i = 0; i < tempShip.size(); i++) {
            Coordinates c = tempShip.get(i);
            for (int j = 0; j < shipsList.size(); j++) {
                Ship ship = shipsList.get(j);
                // if ship has coordinate
                if (ship.contains(c)) {
                    return false; // false if coordinate is taken
                }
            }
        }

        board.placeShipOnBoard(C1, C2);
        userShips.add(new Ship(tempShip, type));
        return true;
    }

    private boolean placeAirCraftCarrier(Coordinates C1, Coordinates C2) {
        return placeShip(C1, C2, 'A', 5);
    }

    private boolean placeBattleship(Coordinates C1, Coordinates C2) {
        return placeShip(C1, C2, 'B', 4);
    }

    private boolean placeCruiser(Coordinates C1, Coordinates C2) {
        return placeShip(C1, C2, 'C', 3);
    }

    private boolean placeSubmarine(Coordinates C1, Coordinates C2) {
        return placeShip(C1, C2,'S', 3);
    }

    private boolean placeDestroyer(Coordinates C1, Coordinates C2) {
        return placeShip(C1, C2, 'D', 2);
    }

    //user class handles attack()
}