package backend.main.java.com.Battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BattleshipService {
    private final BattleshipRepository battleshipRepository;
    public ShipController Engine;

    @Autowired
    public BattleshipService(BattleshipRepository battleshipRepository) {
        this.battleshipRepository = battleshipRepository;
        Engine = new ShipController();
    }

    //Assume ShipController has started running
    public void processAttack(AttackRequest request) {
        // Implement attack logic here
        String outcomeOfAttack = Engine.UserAttackSuccess(request);
        //TODO: Send this outcome back to frontend
        // Example: Save attack position to the database
        //battleshipRepository.saveAttackPosition(request.getGameId(), request.getPlayerId(), request.getRow(), request.getColumn());
    }

    // Implement other methods for game logic
}
