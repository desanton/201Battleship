package backend;

public class ShipController{

    // where the whole game is running
    public void startGame(){
        //create user & opponent objects
        User user = new User();
    }


    // if attack() returns T, tell user success
    // else tell user failed attack
    // maybe we should do 
    public String attackSuccess(User user){
        String output;

        if(user.attack()){
            output = "Successful attack!";
        }
        else{
            output = "Unsuccessful attack.";
        }

        return output;
    }


    // call on every turn to check for winners
    // check if size attacked set = total # ships
    // return 1 for user win, 0 for opponent win
    public determineWinner(){

    }
    
    // param: get result from determineWinner()
    // save game stats
    // tell user congrats/better luck next time
    public endGame(int w){

    }

}

