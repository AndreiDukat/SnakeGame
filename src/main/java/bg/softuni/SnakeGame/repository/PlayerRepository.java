package bg.softuni.SnakeGame.repository;

import bg.softuni.SnakeGame.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByUsername(String username);
    Optional<Player> findByPassword(String password);
    Optional<Player> findById(Long id);

    Optional<Player> findByUsernameAndPassword(String username, String password);

    List<Player> findTop10ByOrderByBestScoreDesc();

    //Optional<Player> updatePlayerScore();
}
