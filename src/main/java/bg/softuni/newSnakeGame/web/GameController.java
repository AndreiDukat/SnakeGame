package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.exception.PlayerNotLoggedException;
import bg.softuni.newSnakeGame.model.service.GameService;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameController {

    private final LoggedPlayer loggedPlayer;
    private final GameService gameService;

    public GameController(LoggedPlayer loggedPlayer, GameService gameService) {
        this.loggedPlayer = loggedPlayer;
        this.gameService = gameService;
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer(){
        return this.loggedPlayer;
    }

    @GetMapping("/game")
    public String game(){
        if(this.loggedPlayer.isLogged()){
            return "game";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }

    @PostMapping("/game")
    public String game (@RequestBody String jsonString)throws Exception{

        JSONObject jsonObject = new JSONObject(jsonString);

        int score = jsonObject.getInt("score");

        this.gameService.updateBestScore(score);

        return "redirect:/home";
    }
}
