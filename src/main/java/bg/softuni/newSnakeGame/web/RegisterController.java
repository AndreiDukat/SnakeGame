package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.model.DTO.PLayerRegisterDTO;
import bg.softuni.newSnakeGame.model.service.RegisterService;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private RegisterService registerService;
    private LoggedPlayer loggedPlayer;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
        this.loggedPlayer = this.registerService.getLoggedPlayer();
    }

    @ModelAttribute("playerRegisterDTO")
    public PLayerRegisterDTO initRegisterDTO() {
        return new PLayerRegisterDTO();
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer() {
        return this.loggedPlayer;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid PLayerRegisterDTO playerRegisterDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.registerService.register(playerRegisterDTO)) {

            System.out.println(bindingResult.hasErrors());
            System.out.println(playerRegisterDTO.toString());

            redirectAttributes.addFlashAttribute("playerRegisterDTO", playerRegisterDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.playerRegisterDTO", bindingResult);

            return "redirect:/register";
        }

         System.out.println("loggedPlayer username: "+this.registerService.getLoggedPlayer().getUsername());
         System.out.println(this.registerService.getLoggedPlayer().isLogged());
         updateLoggedPlayer();
        return "redirect:/home";
    }

    public void updateLoggedPlayer(){
        this.loggedPlayer = this.registerService.getLoggedPlayer();
    }
}
