package backend.main.java.com.Battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BattleshipService {
    private final BattleshipRepository battleshipRepository;

    @Autowired
    public BattleshipService(BattleshipRepository battleshipRepository) {
        this.battleshipRepository = battleshipRepository;
    }

    // Implement other methods for game logic
}
