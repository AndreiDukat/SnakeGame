package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.exception.PlayerNotLoggedException;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ProfileController {

    private LoggedPlayer loggedPlayer;

    public ProfileController(LoggedPlayer loggedPlayer) {
        this.loggedPlayer = loggedPlayer;
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer(){
        return this.loggedPlayer;
    }

    @GetMapping("/profile")
    public String profile(){

        if(this.loggedPlayer.isLogged()){
            return "profile";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }
}
