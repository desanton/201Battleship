package backend.main.java.com.Battleship;

public class User extends Player {

    public User() {
        super();
    }
    
    // used for the opponent/computer to attack user board
    public boolean setUserAttack(Coordinate C) {
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
            Set<Coordinates> shipCoordinates;
            
            // check and update if hit causes ship to sink
            for (Ship ship: playerShips) {
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

    // used to get coordinate from user and attack opponent board
    public boolean getUserAttack(AttackRequest request) {
        Coordinate c = request.c;
        // get user attack
            // TODO: check if coordinates are within bounds

        
        // TODO: check if coordinates are within bounds
        if(c.x < 0 || c.x > 9 || c.y < 0 || c.y > 9){
            // coordinates are out of bounds
            return false;
        }
    
        // attack opponent board

        // add this coordinate to the User's attacked points set
        attackPoints.add(C);


        return setOpponentAttack(c);
    }
}
