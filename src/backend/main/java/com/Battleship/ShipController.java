package backend.main.java.com.Battleship;

public class ShipController {
    
    
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


    public void determineWinner() {

    }

    public void endGame() {

    }
}


