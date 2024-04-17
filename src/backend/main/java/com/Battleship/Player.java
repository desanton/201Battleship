package backend.main.java.com.Battleship;

public class Player {
    
    Set<Ship> userShips = new HashSet<>();

    Set<Coordinates> attackPoints = new HashSet<>();

    private Board board = new Board();

    void placeAllShips() {
        //?
    } 

    private boolean placeShip(int x1, int y1, int x2, int y2, char type, int size) {
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

        board.placeShipOnBoard(x1, y1, x2, y2);
        userShips.add(new Ship(tempShip, type));
        return true;
    }

    private boolean placeAirCraftCarrier(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 'A', 5);
    }

    private boolean placeBattleship(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 'B', 4);
    }

    private boolean placeCruiser(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 'C', 3);
    }

    private boolean placeSubmarine(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 'S', 3);
    }

    private boolean placeDestroyer(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 'D', 2);
    }

    //user class handles attack()
}