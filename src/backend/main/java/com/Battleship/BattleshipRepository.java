package backend.main.java.com.Battleship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleshipRepository extends JpaRepository<Game, String> {
}