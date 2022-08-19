package bg.softuni.newSnakeGame.repository;

import bg.softuni.newSnakeGame.model.entity.Info;
import bg.softuni.newSnakeGame.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

    Optional<Info> findByPlayer(Player player);

    Optional<Info> findByPassword(String password);

    Optional<Info> findByUsername(String username);

    Optional<Info> findByUsernameAndPassword(String username, String password);

}
