package Battleship;

import java.util.List;
import java.util.Scanner;

public class User extends Player {
	public static boolean got;
	public static boolean userDone;
	
//	private Opponent opp;

    public User(GameControl gameControl) {
        super(gameControl);
       
    }
    
//    public void setOpp (Opponent opp) {
//    	this.opp = opp;
//    }
    
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
            	// update number of sunken ships
            	sunkenShips++;
            	System.out.println("SunkenShips: " + sunkenShips);
            	
            	// check if all ships were sunk
            	if (sunkenShips == 5) {
            		System.out.println("COMPUTER WON!");
            		System.out.println("USER LOST");
            		MainGame.endGame = true;
            		status = 5;
            	}
            }
            
            
        }
        
        return status;
    }
    
    @Override
    protected void performAttack() { 
    	System.out.println("user performAttack");
    	MainGame.lock.lock();
    	while(!got) {
    		try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("GOT: TRUE");
//    	try {
//    		sleep(1000);
//    		
//    	} catch (InterruptedException e) {
//    		e.printStackTrace();
//    	}
    	got = false;
    	System.out.println("GOT: BACK TO FALSE");
    	while(!userDone) {
    		
    	}
    	userDone = false;
    	MainGame.lock.unlock();
    	System.out.println("USER MOVE DONE");
  
    }
    	
    public int getUserAttack(Coordinates c) {
    	
    	// implement code to get user coordinates from front end
    	// attack code
    	//System.out.println("valid attack");
    	
     	//attackPoints.add(c);
		int status = MainGame.opp.setOpponentAttack(c);
		//System.out.println("OPP BOARD");
		//opp.board.displayBoard();
		//valid = true;
		System.out.println("USER ATTACK RETURNING");
		userDone = true;
		return status;    	
    
     }
    
    public int getUserAttackTerminal(Coordinates c) {
    	
    	Scanner scan = new Scanner(System.in);
        // Implementation of User's attack logic
    	 System.out.println("Enter coordinates to attack (x y)");
         String input = scan.nextLine();
         
         
         // split the input into x and y coordinates
         String[] coordinates = input.split(" ");
         
         boolean valid = false;
         
         while (!valid) {
        	 
        	 coordinates = input.split(" ");
        	 
        	 // invalid input
             if (coordinates.length != 2) {
                 System.out.println("Invalid input. Please enter two coordinates separated by space.");
                 input = scan.nextLine();
                 continue; 
             }
             
             try {
             	
                 int x = Integer.parseInt(coordinates[0]);
                 int y = Integer.parseInt(coordinates[1]);
                 c = new Coordinates(x, y);
                 
//                 System.out.print("USER ATTACK POINTS: \n [");
//                 for (Coordinates coord: attackPoints) {
//                 	System.out.print( "(" + coord.x + " " + coord.y + ") ");
//                 }
//                 System.out.println("]");
                 
                 // check if input is in bounds
                 if (x < 0 || x > 9 || y < 0 || y > 9) {
    	            	System.out.println("Invalid input. Please enter coordinates within bounds.");
    	            	input = scan.nextLine();
    	                continue; 
    	         // check if coordinate was already attacked
                 } else if (alreadyAttacked(c)) {
                 	System.out.println("Already attacked. Please enter a new coordinate");
                 		input = scan.nextLine();
    	                continue; 
    	         // valid attack
                 } else {
                 	
                 	System.out.println("valid attack");
                 	attackPoints.add(c);
    				int status = MainGame.opp.setOpponentAttack(new Coordinates(x, y));
    				System.out.println("OPP BOARD");
    				MainGame.opp.board.displayBoard();
    				valid = true;
    				
    				return status;

                 }
             
             } catch (NumberFormatException e) {
             	// not a valid number
                 System.out.println("Invalid input. Please enter valid integer coordinates.");
                 input = scan.nextLine();
                 continue; 
             } finally {
            	 scan.close();
             }
         }
         
         // wont reach
         return -1; 
         
         
    }
     
}
