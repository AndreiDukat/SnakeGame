package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.exception.PlayerNotLoggedException;
import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.service.LeaderBoardService;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class LeaderboardController {

    private LoggedPlayer loggedPlayer;
    private LeaderBoardService leaderBoardService;

    public LeaderboardController(LoggedPlayer loggedPlayer, LeaderBoardService leaderBoardService) {
        this.loggedPlayer = loggedPlayer;
        this.leaderBoardService = leaderBoardService;
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer(){
        return this.loggedPlayer;
    }

    @ModelAttribute("top10EasyPlayers")
    public List<Player> initTop10EasyPlayers(){
        return this.leaderBoardService.getTop10PlayersByEasyBestScore();
    }
    @ModelAttribute("top10NormalPlayers")
    public List<Player> initTop10NormalPlayers(){
        return this.leaderBoardService.getTop10PlayersByNormalBestScore();
    }
    @ModelAttribute("top10HardPlayers")
    public List<Player> initTop10HardPlayers(){
        return this.leaderBoardService.getTop10PlayersByHardBestScore();
    }
    @ModelAttribute("top10CountPlayers")
    public List<Player> initTop10CountPlayers(){
        return this.leaderBoardService.getTop10PlayersByGamesCount();
    }



    @GetMapping("/leaderboards")
    public String leaderboards(){
        if(this.loggedPlayer.isLogged()){
            return "leaderboards";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }

    }

    @GetMapping("/leaderboards/easy")
    public String easyLeaderboard(){
        if(this.loggedPlayer.isLogged()){
            return "easy-leaderboard";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }

    }
    @GetMapping("/leaderboards/normal")
    public String normalLeaderboard(){
        if(this.loggedPlayer.isLogged()){
            return "normal-leaderboard";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }
    @GetMapping("/leaderboards/hard")
    public String hardLeaderboard(){

        if(this.loggedPlayer.isLogged()){
            return "hard-leaderboard";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }
    @GetMapping("/leaderboards/count")
    public String gamesCountLeaderboard(){
        if(this.loggedPlayer.isLogged()){
            return "games-count-leaderboard";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }


}
