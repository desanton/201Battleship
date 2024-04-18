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
        if(c.x < 0 || c.x > 9 || c.y < 0 || c.y > 9){
            // coordinates are out of bounds
            return false;
        }
    

        // attack opponent board

        // add this coordinate to the User's attacked points set
        attackPoints.add(C);


        return setOpponentAttack(c);

    }
}
