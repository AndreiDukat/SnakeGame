package bg.softuni.newSnakeGame.repository;

import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.model.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

    Optional<Stats> findByPlayer(Player player);

    List<Stats> findTop10ByOrderByEasyBestScoreDesc();

    List<Stats> findTop10ByOrderByNormalBestScoreDesc();

    List<Stats>  findTop10ByOrderByHardBestScoreDesc();

    List<Stats>  findTop10ByOrderByGamesCountDesc();
}
