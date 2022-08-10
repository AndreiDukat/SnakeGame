package bg.softuni.SnakeGame.web;

import bg.softuni.SnakeGame.Service.PlayerService;
import bg.softuni.SnakeGame.models.DTO.PlayerLoginDTO;
import bg.softuni.SnakeGame.models.DTO.PlayerRegisterDTO;
import bg.softuni.SnakeGame.models.entity.Player;
import bg.softuni.SnakeGame.models.exception.PlayerNotLoggedException;
import bg.softuni.SnakeGame.session.LoggedPlayer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private PlayerService playerService;

    public Controller(PlayerService playerService) {
        this.playerService = playerService;
    }



    @ModelAttribute("playerRegisterDTO")
    public PlayerRegisterDTO initRegisterDTO(){
        return new PlayerRegisterDTO();
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer() {
        return getLoggedPlayer();
    }
    @ModelAttribute("playerLoginDTO")
    public PlayerLoginDTO initLoginDTO(){
        return new PlayerLoginDTO();
    }
    @ModelAttribute("top10Players")
    public List<Player> initTop10Players(){
        return this.playerService.top10Players();
    }


    @GetMapping("/home")
    public String home(){
        if(getLoggedPlayer().isLogged()) {
            return "home";
        }else {
            throw new PlayerNotLoggedException("You should login or register to continue.");
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/game")
    public String game(){
        if(getLoggedPlayer().isLogged()) {
            return "game";
        }else {
            throw new PlayerNotLoggedException("You should login or register before playing.");
        }
    }

    @PostMapping("/login")
    public String login(@Valid PlayerLoginDTO playerLoginDTO,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !this.playerService.login(playerLoginDTO)){

            redirectAttributes.addFlashAttribute("playerLoginDTO", playerLoginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.playerLoginDTO", bindingResult);

            return "redirect:/login";
        }

        return "redirect:/home";
    }



    @PostMapping("/register")
    public String register(@Valid PlayerRegisterDTO playerRegisterDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("playerRegisterDTO", playerRegisterDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.playerRegisterDTO", bindingResult);

            return "redirect:/register";
        }
        this.playerService.register(playerRegisterDTO);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
        this.playerService.logout();
        return "redirect:/";
    }

    public LoggedPlayer getLoggedPlayer(){
        return this.playerService.getLoggedPlayer();
    }

    @PostMapping("/game")
    public void updatePlayerScore(@RequestBody String jsonString)throws Exception{ // jsonString: "{"score":16}"

        JSONObject jsonObject = new JSONObject(jsonString);

        int score = jsonObject.getInt("score");

        if(getLoggedPlayer().getBestScore() < score){
            this.playerService.updatePlayerScore(score);
        }

    }
}
