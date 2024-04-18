package backend.main.java.com.Battleship;

public class AttackRequest {
    int x;
    int y;
    Coordinates c;
    public AttackRequest(int x, int y) {
        this.x=x; this.y=y; c = new Coordinates(x,y);
    }
}
