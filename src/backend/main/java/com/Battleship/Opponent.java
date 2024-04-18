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
    public boolean setOpponentAttack(Coordinates c) {

        if (board.board[c.x][c.y].getStatus() == 0) {
            // miss
            board.updateCoordStatus(c, 3);
            return false;
        }
        else if (board.board[c.x][c.y].getStatus() == 1) {
            // hit
            board.updateCoordStatus(c, 4);
            
            // get ship information
            boolean sink = false;
            List<Coordinates> shipCoordinates;
            
            // check and update if hit causes ship to sink
            for (Ship ship: playerShips) {
                shipCoordinates = ship.getShipCoordinates(ship.type);
                if (shipCoordinates.contains(c)){
                    sink = ship.updateHitCount();
                    break;
                }
            }

            // update coordinate on board if ship sinks
            if (sink) {
                board.updateCoordStatus(c, 2);
            }
            
        }
        return true;
    }


    public boolean getOpponentAttack() {
        
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        int x = random.nextInt(10);

        seed = System.currentTimeMillis();
        random = new Random(seed);
        int y = random.nextInt(10);

        Coordinates c = new Coordinates(x, y);
        while (attackPoints.contains(c)) {
            seed = System.currentTimeMillis();
            random = new Random(seed);
            x = random.nextInt(10);

            seed = System.currentTimeMillis();
            random = new Random(seed);
            y = random.nextInt(10);

            c = new Coordinates(x, y);
        }

        return user.setUserAttack(c);
    }
    
    public static void main(String [] args) {
    	System.out.println("initializing user");
    	User user = new User();
    	System.out.println("initializing opp");
    	Opponent opp = new Opponent(user);
    }
}
