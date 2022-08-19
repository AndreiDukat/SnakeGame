package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.exception.PlayerNotLoggedException;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final LoggedPlayer loggedPlayer;

    public HomeController(LoggedPlayer loggedPlayer) {
        this.loggedPlayer = loggedPlayer;
    }


    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer() {
        return this.loggedPlayer;
    }

    @GetMapping("/home")
    public String home(){
        if(this.loggedPlayer.isLogged()){
            return "home";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }
}
