package backend.main.java.com.Battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battleship")
/**
 * 
 * Handles HTTP Requests between Javascript api
 * 
 * 
 */
public class BattleshipController {
    private final BattleshipService battleshipService;

    @Autowired
    public BattleshipController(BattleshipService battleshipService) {
        this.battleshipService = battleshipService;
    }

    @PostMapping("/attack")
    public ResponseEntity<Void> attack(@RequestBody AttackRequest request) {
        // Pass the attack request to the battleship service to process the attack
        battleshipService.processAttack(request);
        return ResponseEntity.ok().build();
    }
    // Add other controller methods for game actions
}
