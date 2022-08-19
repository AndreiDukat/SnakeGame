package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.model.DTO.PLayerRegisterDTO;
import bg.softuni.newSnakeGame.model.DTO.PlayerLoginDTO;
import bg.softuni.newSnakeGame.model.service.LoginService;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    private LoginService loginService;
    private LoggedPlayer loggedPlayer;

    public LoginController(LoginService loginService, LoggedPlayer loggedPlayer) {
        this.loginService = loginService;
        this.loggedPlayer = loggedPlayer;
    }

    @ModelAttribute("playerLoginDTO")
    public PlayerLoginDTO initLoginDTO(){
        return new PlayerLoginDTO();
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer() {
        return this.loggedPlayer;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid PlayerLoginDTO playerLoginDTO,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !this.loginService.login(playerLoginDTO)) {

            System.out.println("bindingResult.hasErrors()");
            System.out.println(playerLoginDTO.toString());

            redirectAttributes.addFlashAttribute("playerLoginDTO", playerLoginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.playerLoginDTO", bindingResult);

            return "redirect:/login";
        }

        System.out.println("loggedPlayer username: "+this.loginService.getLoggedPlayer().getUsername());
        System.out.println("loggedPlayer id: "+this.loginService.getLoggedPlayer().getId());
        System.out.println(this.loginService.getLoggedPlayer().isLogged());
        updateLoggedPlayer();
        return "redirect:/home";

    }


    public void updateLoggedPlayer(){
        this.loggedPlayer = this.loginService.getLoggedPlayer();
    }

}
