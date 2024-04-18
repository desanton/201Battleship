package backend.main.java.com.Battleship;
import java.util.Random;

public class Opponent extends Player {

    public Opponent() {
        super();
        attackPoints = new Vector<Coordinates>();
        oppShips = new Vector<Ship>();
        board = new Board();
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

        if (board[c.x][c.y].getStatus() == 0) {
            // miss
            board.updateCoordStatus(c, 3);
            return false;
        }
        else if (board[c.x][c.y].getStatus() == 1) {
            // hit
            board.updateCoordStatus(c, 4);
            
            // get ship information
            boolean sink = false;
            Set<Coordinates> shipCoordinates;
            
            // check and update if hit causes ship to sink
            for (Ship ship: oppShips) {
                shipCoordinates = ship.getCoordinates();
                if (shipCoordinates.contains(c)){
                    sink = ship.updateHitCount();
                    break;
                }
            }

            // update coordinate on board if ship sinks
            if (sink) {
                board.updateCoordStatus(c, 2);
            }
            return true;
        }
    }


    public boolean getOpponentAttack() {
        
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        int x = random.nextInt(10);

        seed = System.currentTimeMillis();
        random = new Random(seed);
        int y = random.nextInt(10);

        Coordinate c = new Coordinates(x, y);
        while (attackPoints.contains(c)) {
            seed = System.currentTimeMillis();
            random = new Random(seed);
            x = random.nextInt(10);

            seed = System.currentTimeMillis();
            random = new Random(seed);
            y = random.nextInt(10);

            c = new Coordinates(x, y);
        }

        return setUserAttack(c);
    }
}
