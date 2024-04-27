package Battleship;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Opponent extends Player {
	public static boolean gotOpp;
	public static boolean oppDone;
	public static boolean gotOppAttack;

	
	public Opponent(GameControl gameControl) {
		super (gameControl);
		gotOpp = false;
		oppDone = false;
		gotOppAttack = false;
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
                	if (coord.x == c.x && coord.y == c.y) {
                		sink = ship.updateHitCount();
                		sunk = ship;
                		break;
                	}
                }
            }

            // if hit leads to sink
            if (sink) {
            	
            	// update status
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
            		
            		// game end status
            		status = 5;
            	}
            }
        }
        oppDone = true;
        return status;
    }
//    @Override
//    protected void performAttack() {
//    	MainGame.lock.lock();
//    	int status = getOpponentAttack();
////    	MainGame.user.board.displayBoard();
//    	try {
//			sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	MainGame.lock.unlock();
//    	System.out.println("OPP MOVE DONE");
//    }
////    
    @Override
    protected void performAttack() {
    	
    	MainGame.lock.lock();
    	// wait to get into opp servlet
    	while(!gotOpp) {
    		try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	System.out.println("GOTOPP: TRUE");
    	gotOpp = false;
    	System.out.println("GOTOPP: BACK TO FALSE");
    	
    	// wait for servlet to get opp attack
    	while (!gotOppAttack) {
    		try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	gotOppAttack = false;
    	
    	// wait until opp attack is done
    	while(!oppDone) {
    		try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	MainGame.lock.unlock();
    	
    	System.out.println("OPP MOVE DONE");
    }
    
    
    public Coordinates getOpponentAttack() {
        // Implementation of Opponent's attack logic
        Random random = new Random(); 

        int x = random.nextInt(10); 
        int y = random.nextInt(10); 

        Coordinates c = new Coordinates(x, y); 

        // check if already attacked
        while (alreadyAttacked(c)) {
        	// generate new coordinates
            x = random.nextInt(10);
            y = random.nextInt(10); 
            c = new Coordinates(x, y); 
        }

        System.out.println("OPPONENT ATTACK: x: " +  x + ", y: " + y);

        // attack user
//        return MainGame.user.setUserAttack(c);
        attackPoints.add(c);
        gotOppAttack = true;
        return c;
    }
    
}