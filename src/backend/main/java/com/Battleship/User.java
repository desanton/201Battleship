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


            // QUESTION::: should we create sets (attackPoints, userShips, etc) in the 
            //             User & Opponent classes bc otherwise we r accessing the same
            //             one for both players i think
            // - erika
        // add this coordinate to the User's attacked points set
        attackPoints.add(C);


        return setOpponentAttack(c);

    }
}
