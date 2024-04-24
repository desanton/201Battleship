package Battleship;

import java.util.Scanner;

public class MainGame {
	
	public static boolean endGame;
	
	public void initGame() {
		
	}
	
	public void startGame() {
		
	}
	
	public static void main(String[] args) {
		
		endGame = false; // game just started
        Scanner scan = new Scanner(System.in);
        System.out.println("Initializing user");
        User user = new User();
        
        System.out.println("Initializing opponent");
        Opponent opp = new Opponent(user);

        while (!endGame) {
        	
            System.out.println("Enter coordinates to attack (x y)");
            String input = scan.nextLine();
            
            
            // split the input into x and y coordinates
            String[] coordinates = input.split(" ");
            
            // invalid input
            if (coordinates.length != 2) {
                System.out.println("Invalid input. Please enter two coordinates separated by space.");
                continue; 
            }
            
            try {
            	
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                
                // check if input is in bounds
                if (x < 0 || x > 9 || y < 0 || y > 9) {
	            	System.out.println("Invalid input. Please enter coordinates within bounds.");
	                continue; 
                } else {
					opp.setOpponentAttack(new Coordinates(x, y));
					opp.board.displayBoard();
                }
            
            } catch (NumberFormatException e) {
            	// not a valid number
                System.out.println("Invalid input. Please enter valid integer coordinates.");
                continue; 
            }
        }
        
        System.out.println("Exiting game. Goodbye!");
        scan.close();
    }
}
