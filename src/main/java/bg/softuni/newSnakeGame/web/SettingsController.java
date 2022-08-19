package bg.softuni.newSnakeGame.web;

import bg.softuni.newSnakeGame.exception.PlayerNotLoggedException;
import bg.softuni.newSnakeGame.model.DTO.ChangeUsernameDTO;
import bg.softuni.newSnakeGame.model.DTO.PlayerLoginDTO;
import bg.softuni.newSnakeGame.model.service.SettingsService;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SettingsController {

    private SettingsService settingsService;
    private LoggedPlayer loggedPlayer;

    public SettingsController(SettingsService settingsService, LoggedPlayer loggedPlayer) {
        this.settingsService = settingsService;
        this.loggedPlayer = loggedPlayer;
    }
    @ModelAttribute("changeUsernameDTO")
    public ChangeUsernameDTO initChangeUsernameDTO(){
        return new ChangeUsernameDTO();
    }

    @ModelAttribute("loggedPlayer")
    public LoggedPlayer initLoggedPlayer(){
        return this.loggedPlayer;
    }

    @GetMapping("/settings")
    public String settings(){
        if(this.loggedPlayer.isLogged()){
            return "settings";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }

    }

    @PostMapping("/settings")
    public String game (@RequestBody String jsonString)throws Exception{

        JSONObject jsonObject = new JSONObject(jsonString);

        String difficulty = jsonObject.getString("difficulty");

        this.settingsService.changeDifficulty(difficulty);

        return "redirect:/settings";
    }

    @GetMapping("/settings/change-username")
    public String changeUsername(){
        if(this.loggedPlayer.isLogged()){
            return "change-username";
        }else {
            throw new PlayerNotLoggedException("player is not logged");
        }
    }

    @PostMapping("/settings/change-username")
    public String changeUsername(@Valid ChangeUsernameDTO changeUsernameDTO,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.settingsService.changeUsername(changeUsernameDTO)) {

            System.out.println("bindingResult.hasErrors()");
            System.out.println(changeUsernameDTO.toString());

            redirectAttributes.addFlashAttribute("changeUsernameDTO", changeUsernameDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.changeUsernameDTO", bindingResult);

            return "redirect:/settings/change-username";
        }
        return "redirect:/home";
    }
}
