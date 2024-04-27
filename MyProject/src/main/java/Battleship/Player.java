package Battleship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public abstract class Player extends Thread{
    
    protected Vector<Ship> playerShips;
    protected Set<Coordinates> attackPoints;

    protected Board board;
    protected int sunkenShips;
    private static int numBoards = 0;
    protected GameControl gameControl;

    public Player(GameControl gameControl) {
        sunkenShips = 0;
        playerShips = new Vector<Ship>();
        attackPoints = new HashSet<Coordinates>();
        board = getBoard();
        board.displayBoard();
        this.gameControl = gameControl;
        numBoards++;
    }
    
    protected abstract void performAttack();

    private boolean placeShip(Coordinates C1, Coordinates C2, char type, int size) {
        int x1 = C1.x; int y1 = C1.y; int x2 = C2.x; int y2 = C2.y;
        
        if (Math.abs(x2 - x1) != size - 1 && Math.abs(y2 - y1) != size - 1) return false;
        List<Coordinates> tempShip = new ArrayList<>();

        // add coordinates to ship
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
            for (int j = 0; j < playerShips.size(); j++) {
                Ship ship = playerShips.get(j);
                // if ship has coordinate
                if (ship.coordinates.contains(c)) {
                    return false; // false if coordinate is taken
                } else {
                	
                }
            }
        }

        // 
        playerShips.add(new Ship(tempShip, type, size));
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

    // user class handles attack()
    // hard coded boards
    private Board getBoard(){
    	
    	System.out.println("in getBoard()");
        Board board = new Board();

        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        int rand = (random.nextInt(4) + numBoards) % 4;

        System.out.println("rand: " + rand);

        Coordinates A1, A2, B1, B2, C1, C2, S1, S2, D1, D2;

        if (rand == 0) {

            A1 = new Coordinates(0, 9);
            A2 = new Coordinates(4, 9);
            B1 = new Coordinates(4, 4);
            B2 = new Coordinates(7, 4);
            C1 = new Coordinates(2, 4);
            C2 = new Coordinates(2, 6);
            S1 = new Coordinates(7, 0);
            S2 = new Coordinates(7, 2);
            D1 = new Coordinates(7, 7);
            D2 = new Coordinates(8, 7);

        }
        else if (rand == 1) {
            A1 = new Coordinates(2, 3);
            A2 = new Coordinates(2, 7);
            B1 = new Coordinates(5, 9);
            B2 = new Coordinates(8, 9);
            C1 = new Coordinates(6, 6);
            C2 = new Coordinates(8, 6);
            S1 = new Coordinates(4, 4);
            S2 = new Coordinates(6, 4);
            D1 = new Coordinates(4, 6);
            D2 = new Coordinates(4, 7);

        }
        else if (rand == 2) {
            A1 = new Coordinates(5, 0);
            A2 = new Coordinates(9, 0);
            B1 = new Coordinates(2, 3);
            B2 = new Coordinates(2, 6);
            C1 = new Coordinates(0, 0);
            C2 = new Coordinates(2, 0);
            S1 = new Coordinates(8, 1);
            S2 = new Coordinates(8, 3);
            D1 = new Coordinates(4, 4);
            D2 = new Coordinates(5, 4);

        } else { // rand == 3
            A1 = new Coordinates(4, 0);
            A2 = new Coordinates(4, 4);
            B1 = new Coordinates(5, 3);
            B2 = new Coordinates(8, 3);
            C1 = new Coordinates(5, 2);
            C2 = new Coordinates(7, 2);
            S1 = new Coordinates(5, 1);
            S2 = new Coordinates(7, 1);
            D1 = new Coordinates(5, 4);
            D2 = new Coordinates(6, 4);

        }

        placeAirCraftCarrier(A1, A2);
        placeBattleship(B1, B2);
        placeCruiser(C1, C2);
        placeSubmarine(S1, S2);
        placeDestroyer(D1, D2); 
        
        board.placeShipOnBoard(A1, A2);
        board.placeShipOnBoard(B1, B2);
        board.placeShipOnBoard(C1, C2);
        board.placeShipOnBoard(S1, S2);
        board.placeShipOnBoard(D1, D2);
        
        
        return board;

    }
    
    public boolean alreadyAttacked(Coordinates c) {

    	for (Coordinates coord: attackPoints) {
    		if (c.x == coord.x && c.y == coord.y) {
    			return true;
    		}
    	}
    	return false;
    }

    public Ship getShip(Coordinates c) {
    	// assumption: only called for valid coords in ships
    	Ship sunkShip = new Ship();
    	
    	// iterate through user ships
    	for (Ship ship: playerShips) {
    		
    		// find ship with matching coordinate
    		List<Coordinates> shipList = ship.getShipCoordinates(ship.type);
    		for (Coordinates coord: shipList) {
    			
    			// should always return some ship
        		if (c.x == coord.x && c.y == coord.y) {
        			return ship;
        		}
        	}
    	}
    	// will not happen
    	return sunkShip;
    }
    
    @Override
    public void run() {
        while (!MainGame.endGame) {
            try {
                if (this instanceof User) {
                	
                    gameControl.userMove();
                } else {
//                	System.out.println("OPP MOVE");
                    gameControl.opponentMove(); // Check if it's the opponent's turn.
                }
                
                performAttack(); // Perform the attack logic

                gameControl.toggleTurn(); // Switch turns
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GAME LOOP ENDED");
    }


}
