package bg.softuni.newSnakeGame.repository;

import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.model.entity.Role;
import bg.softuni.newSnakeGame.model.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByPlayer(Player player);
}
