package Battleship;

public class GameControl {
    private boolean userTurn = true; 

    public synchronized void userMove() throws InterruptedException {
        while (!userTurn) {
            wait();
        }
        System.out.println("USER MOVE");
    }

    public synchronized void opponentMove() throws InterruptedException {
        while (userTurn) {
            wait(); 
        }
        System.out.println("OPP MOVE");
    }

    public synchronized void toggleTurn() {
    	System.out.println("toggle turns");
        userTurn = !userTurn;
        notifyAll(); 
    }
}
