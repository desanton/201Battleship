package Battleship;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainGame{
	public static User user;
	public static Opponent opp;
	public static GameControl gameControl;
	
	public static boolean endGame = false;
	public static Lock lock = new ReentrantLock();
//	private static boolean started = false;

	
	public static void startGame() {

		gameControl = new GameControl();
        user = new User(gameControl);
        opp = new Opponent(gameControl);

        user.start();
        opp.start();
		
	}
	
//	 public static void main(String[] args) {
//        gameControl = new GameControl();
//        User user = new User(gameControl);
//        Opponent opponent = new Opponent(gameControl);
//
//        user.start();
//        opponent.start();
//    }
	 
//	public static void main(String[] args) {
//		
//		endGame = false; // game just started
//        Scanner scan = new Scanner(System.in);
//
//
//        while (!endGame) {
//        	
//            System.out.println("Enter coordinates to attack (x y)");
//            String input = scan.nextLine();
//            
//            
//            // split the input into x and y coordinates
//            String[] coordinates = input.split(" ");
//            
//            // invalid input
//            if (coordinates.length != 2) {
//                System.out.println("Invalid input. Please enter two coordinates separated by space.");
//                continue; 
//            }
//            
//            try {
//            	
//                int x = Integer.parseInt(coordinates[0]);
//                int y = Integer.parseInt(coordinates[1]);
//                Coordinates c = new Coordinates(x, y);
//                
//                System.out.print("USER ATTACK POINTS: \n [");
//                for (Coordinates coord: user.attackPoints) {
//                	System.out.print( "(" + coord.x + " " + coord.y + ") ");
//                }
//                System.out.println("]");
//                
//                // check if input is in bounds
//                if (x < 0 || x > 9 || y < 0 || y > 9) {
//	            	System.out.println("Invalid input. Please enter coordinates within bounds.");
//	                continue; 
//                } else if (user.alreadyAttacked(c)) {
//                	System.out.println("Already attacked. Please enter a new coordinate");
//	                continue; 
//                } else {
//                	
//                	System.out.println("valid attack");
//					opp.setOpponentAttack(new Coordinates(x, y));
//					System.out.println("OPP BOARD");
//					opp.board.displayBoard();
//					
//					opp.getOpponentAttack();
//					System.out.println("USER BOARD");
//					user.board.displayBoard();
//					user.attackPoints.add(c);
//                }
//            
//            } catch (NumberFormatException e) {
//            	// not a valid number
//                System.out.println("Invalid input. Please enter valid integer coordinates.");
//                continue; 
//            }
//        }
//        
//        System.out.println("Exiting game. Goodbye!");
//        scan.close();
//    }
}
