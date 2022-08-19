package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    private LoggedPlayer loggedPlayer;

    public LogoutController(LoggedPlayer loggedPlayer) {
        this.loggedPlayer = loggedPlayer;
    }

    @GetMapping("/logout")
    public String logout(){
        System.out.println(this.loggedPlayer.getUsername()+ " is logouting...");
        this.loggedPlayer.logout();
        return "index";
    }
}
