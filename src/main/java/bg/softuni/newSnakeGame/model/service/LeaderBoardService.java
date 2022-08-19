package bg.softuni.newSnakeGame.model.service;

import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.model.entity.Stats;
import bg.softuni.newSnakeGame.repository.PlayerRepository;
import bg.softuni.newSnakeGame.repository.StatsRepository;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderBoardService {

    private LoggedPlayer loggedPlayer;
    private PlayerRepository playerRepository;
    private StatsRepository statsRepository;

    public LeaderBoardService(LoggedPlayer loggedPlayer, PlayerRepository playerRepository, StatsRepository statsRepository) {
        this.loggedPlayer = loggedPlayer;
        this.playerRepository = playerRepository;
        this.statsRepository = statsRepository;
    }

    public List<Player> getTop10PlayersByEasyBestScore(){
        List<Stats> top10Stats = this.statsRepository.findTop10ByOrderByEasyBestScoreDesc();

        List<Player> top10Players = new ArrayList<>();

        for (Stats stat : top10Stats) {
            Optional<Player> playerOptional = this.playerRepository.findById(stat.getId());
            if(!playerOptional.isPresent()){
                return null;
            }
            Player player = playerOptional.get();
            top10Players.add(player);
        }
        return top10Players;
    }
    public List<Player> getTop10PlayersByNormalBestScore(){
        List<Stats> top10Stats = this.statsRepository.findTop10ByOrderByNormalBestScoreDesc();

        List<Player> top10Players = new ArrayList<>();

        for (Stats stat : top10Stats) {
            Optional<Player> playerOptional = this.playerRepository.findById(stat.getId());
            if(!playerOptional.isPresent()){
                return null;
            }
            Player player = playerOptional.get();
            top10Players.add(player);
        }
        return top10Players;
    }

    public List<Player> getTop10PlayersByHardBestScore(){
        List<Stats> top10Stats = this.statsRepository.findTop10ByOrderByHardBestScoreDesc();

        List<Player> top10Players = new ArrayList<>();

        for (Stats stat : top10Stats) {
            Optional<Player> playerOptional = this.playerRepository.findById(stat.getId());
            if(!playerOptional.isPresent()){
                return null;
            }
            Player player = playerOptional.get();
            top10Players.add(player);
        }
        return top10Players;
    }

    public List<Player> getTop10PlayersByGamesCount(){
        List<Stats> top10Stats = this.statsRepository.findTop10ByOrderByGamesCountDesc();

        List<Player> top10Players = new ArrayList<>();

        for (Stats stat : top10Stats) {
            Optional<Player> playerOptional = this.playerRepository.findById(stat.getId());
            if(!playerOptional.isPresent()){
                return null;
            }
            Player player = playerOptional.get();
            top10Players.add(player);
        }
        return top10Players;
    }
}
