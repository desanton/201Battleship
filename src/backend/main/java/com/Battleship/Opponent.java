package backend.main.java.com.Battleship;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Opponent extends Player {

    private User user;
	
	public Opponent(User user) {
        super();
        this.user = user;
    }

    /**
     * function used to set opponent ships
     * going to have 5 hard coded sets
     */
    public void initOppBoard(){

    }

    // function for user to attack opponent
    /**
     * param: Coordinates c of intended attack 
     * return: true if hit, false if miss
     */
    public int setOpponentAttack(Coordinates c) {
    	Ship sunk = new Ship();
    	
    	int status = -1;
        if (board.board[c.x][c.y].getStatus() == 0) {
            
        	// miss
        	System.out.println("miss");
            board.updateCoordStatus(c, 3);
            status = 3;
        }
        else if (board.board[c.x][c.y].getStatus() == 1) {
            
        	// hit
        	System.out.println("hit");
            board.updateCoordStatus(c, 4);
            status = 4;
            
            // get ship information
            boolean sink = false;
            List<Coordinates> shipCoordinates;
            
            // check and update if hit causes ship to sink
            for (Ship ship: playerShips) {
                shipCoordinates = ship.getShipCoordinates(ship.type);
                for (Coordinates coord: shipCoordinates) {
//                	System.out.println("comparing: " + coord.x + " = " +  c.x  + " and " 
//                								+  coord.y + " = " + c.y);
                	if (coord.x == c.x && coord.y == c.y) {
                		sink = ship.updateHitCount();
                		sunk = ship;
                		break;
                	}
                }
            }

            if (sink) {
            	
            	status = 2;
            	shipCoordinates = sunk.getShipCoordinates(sunk.type);
            	
            	// update all ship coordinates on board if ship sinks
            	for (Coordinates coord: shipCoordinates) {
            		board.updateCoordStatus(coord, 2);
            	}
            	
            	// update number of sunken ships
            	sunkenShips++;
            	System.out.println("SunkenShips: " + sunkenShips);
            	
            	// check if all ships were sunk
            	if (sunkenShips == 5) {
            		System.out.println("PLAYER WON!");
            		System.out.println("COMPUTER LOST");
            		MainGame.endGame = true;
            	}
            	
            }
            
        }
        
        return status;
    }


    public int getOpponentAttack() {
        
    	// get random x coordinate
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int x = random.nextInt(10);

        // get random y coordinate
        seed = System.currentTimeMillis();
        random = new Random(seed);
        int y = random.nextInt(10);
        
        // create coordinate object
        Coordinates c = new Coordinates(x, y);
        
        // redraw coordinates if it was already attacked
        while (attackPoints.contains(c)) {
            seed = System.currentTimeMillis();
            random = new Random(seed);
            x = random.nextInt(10);

            seed = System.currentTimeMillis();
            random = new Random(seed);
            y = random.nextInt(10);

            c = new Coordinates(x, y);
        }

        // attack user
        return user.setUserAttack(c);
    }
    
}
