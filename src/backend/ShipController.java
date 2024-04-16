package backend;

public class ShipController{
    // create user & opponent objects
    private User user = new User();
    private Opponent opponent = new Opponent();

    // total number of ships for 1 player
    private final int totalNumShips = 5;


    /*
     *  where the whole game is running
    */
    public void runGame(){
        
    }


    /*
     *  if attack() returns T, tell user success
     *  else tell user failed attack
     *  maybe we should do 
    */
    public String attackSuccess(){
        String output;

        if(user.attack()){
            output = "Successful attack!";
        }
        else{
            output = "Unsuccessful attack.";
        }

        return output;
    }

    /* 
     *  call on every turn to check for winners
     *  check if size of attacked set = total # ships
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
     *  param: get result from determineWinner()
     *  save game stats
     *  tell user congrats/better luck next time
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
     *  constructor
     */
    public ShipController(){

    }

}

