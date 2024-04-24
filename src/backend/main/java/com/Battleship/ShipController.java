package backend.main.java.com.Battleship;

public class ShipController {
    // create user & opponent objects
    private User user = new User();
    private Opponent opponent = new Opponent();
    public static boolean endGame;

    // total number of ships for 1 player
    private final int totalNumShips = 5;


    /*
     * where the whole game is running
     * begins after the login/registration page
    */
    public void runGame(){
        //start game, instantiate boards
        
        //repeat until someones loses
            //If user's turn
                //AttackRequest
            //If opponent's turn
                //Random attack
            //Output outcome
    }

    

    /*
     * if attack() returns T, tell user success
     * else tell user failed attack
     * maybe we should do 
    */
    public String UserAttackSuccess(AttackRequest request){
        String output;

        if(user.getUserAttack()){
            output = "Successful attack!";
        }
        else{
            output = "Unsuccessful attack.";
        }

        return output;
    }

    /* 
     * call on every turn to check for winners
     * check if size of attacked set = total # ships
     * return 1 for user win, 0 for opponent win, -1 for no winner yet
    */
    public int determineWinner(){
        // get size of the sunkenShips set from user
        if(user.sunkenShips.size == totalNumShips){
            return 1;
        }
        else if(opponent.sunkenShips.size == totalNumShips){
            return 0;
        }
        return -1;
    }
    
    /*
     * param: get result from determineWinner()
     * save game stats
     * tell user congrats/better luck next time
    */
    public String endGame(int w){
        int winner = determineWinner();

        if(winner == 1){
            return "You win!";
        } else if (winner == 0){
            return "You lose.";
        }

        return "Could not determine winner.";
    }


    /*
     * constructor
     */
    public ShipController(){
    }

    public void endGame() {

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


