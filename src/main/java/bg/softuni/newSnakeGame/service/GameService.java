package bg.softuni.newSnakeGame.service;

import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.model.entity.Stats;
import bg.softuni.newSnakeGame.repository.InfoRepository;
import bg.softuni.newSnakeGame.repository.PlayerRepository;
import bg.softuni.newSnakeGame.repository.StatsRepository;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GameService {

    private PlayerRepository playerRepository;
    private InfoRepository infoRepository;
    private LoggedPlayer loggedPlayer;
    private StatsRepository statsRepository;

    public GameService(PlayerRepository playerRepository, InfoRepository infoRepository, LoggedPlayer loggedPlayer, StatsRepository statsRepository) {
        this.playerRepository = playerRepository;
        this.infoRepository = infoRepository;
        this.loggedPlayer = loggedPlayer;
        this.statsRepository = statsRepository;
    }

    public boolean updateBestScore(int score) {

        Optional<Stats> statsOptional = this.statsRepository.findById(this.loggedPlayer.getStatsId());

        Stats stats;

        if (statsOptional.isPresent()) {
            stats = statsOptional.get();
        } else {
//           System.out.println("false statsOptional.isPresent()");
            return false;
        }

        stats.setAllScores(this.loggedPlayer.getAllScores());
        stats.addScore(score);
        stats.increaseGamesCount();
        stats.calculateAverageScore();

        switch (this.loggedPlayer.getDifficulty()) {

            case EASY:
                if (this.loggedPlayer.getEasyBestScore() < score) {
                    this.loggedPlayer.setEasyBestScore(score);
                    stats.setEasyBestScore(score);
                }
                break;
            case NORMAL:
                if (this.loggedPlayer.getNormalBestScore() < score) {
                    this.loggedPlayer.setNormalBestScore(score);
                    stats.setNormalBestScore(score);
                }
                break;
            case HARD:
                if (this.loggedPlayer.getHardBestScore() < score) {
                    this.loggedPlayer.setHardBestScore(score);
                    stats.setHardBestScore(score);
                }
                break;

        }

        this.loggedPlayer.setAllScores(stats.getAllScores());
        this.loggedPlayer.setAverageScore(stats.getAverageScore());
        this.loggedPlayer.setGamesCount(stats.getGamesCount());

        this.statsRepository.save(stats);
        return true;
    }
}
