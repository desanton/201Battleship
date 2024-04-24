package backend.main.java.com.Battleship;

import java.util.List;

public class User extends Player {

    public User() {
        super();
    }
    
    // used for the opponent/computer to attack user board
    public int setUserAttack(Coordinates c) {
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
            	// update all ship coordinates on board if ship sinks
            	status = 2;
            	shipCoordinates = sunk.getShipCoordinates(sunk.type);
            	for (Coordinates coord: shipCoordinates) {
            		board.updateCoordStatus(coord, 2);
            	}
            }
            
        }
        
        return status;
    }

//    // used to get coordinate from user and attack opponent board
//    public boolean getUserAttack(AttackRequest request) {
//        Coordinate c = request.c;
//        // get user attack
//            // TODO: check if coordinates are within bounds
//
//        
//        // TODO: check if coordinates are within bounds
//        if(c.x < 0 || c.x > 9 || c.y < 0 || c.y > 9){
//            // coordinates are out of bounds
//            return false;
//        }
//    
//        // attack opponent board
//
//        // add this coordinate to the User's attacked points set
//        attackPoints.add(c);
//
//
//        return setOpponentAttack(c);
//    }

