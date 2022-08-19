package bg.softuni.newSnakeGame.repository;

import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.model.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Optional<Settings> findByPlayer(Player player);
}
