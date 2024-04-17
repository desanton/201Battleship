package backend.main.java.com.Battleship;

public class User extends Player {
    public User() {
        super();
    }
    
    // used for the opponent/computer to attack user board
    public boolean setUserAttack(Coordinate C) {
        
    }

    // used to get coordinate from user and attack opponent board
    public boolean getUserAttack() {
        Coordinate c;
        // get user attack
            // TODO: add logic to get coordinate from front end user input
            
            // TODO: check if coordinates are within bounds

        

        // attack opponent board
        return setOpponentAttack(c);

    }
}
