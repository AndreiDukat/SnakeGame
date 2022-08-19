package bg.softuni.newSnakeGame.repository;

import bg.softuni.newSnakeGame.model.entity.Info;
import bg.softuni.newSnakeGame.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findById(Long id);

    Optional<Player> findByInfo(Info info);
}
