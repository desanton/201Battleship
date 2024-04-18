package backend.main.java.com.Battleship;

/**
 * The board class will store and update all coordinates in a 2D array
 */


public class Board {

    public Coordinates[][] board; 

    public Board() {
        // initialize empty board
        board = new Coordinates[10][10]; 
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                board[x][y] = Coordinates(x, y);
            }
        }
    }

    public placeShipOnBoard(Coordinates C1, Coordinates C2) {
        // assuming the coordinates are within bounds and conflict with other pre-existing ships
        int x1 = C1.x;
    	int x2 = C2.x;
    	int y1 = C1.y;
    	int y2 = C2.y;
        
        if (x1 == x2) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                board[x1][y].s = 1;
            }
        }
        else if (y1 == y2) {
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                board[x][y1].s = 1;
            }
        } else {
            // do nothing
        }
    }

    public updateCoordStatus(Coordinates c, int s){ 
        board[c.x][c.y].setStatus(s);
    }

    // for backend testing
    public void displayBoard() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }
    }

}

