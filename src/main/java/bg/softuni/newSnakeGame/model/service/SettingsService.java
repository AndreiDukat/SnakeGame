package bg.softuni.newSnakeGame.model.service;

import bg.softuni.newSnakeGame.model.DTO.ChangeUsernameDTO;
import bg.softuni.newSnakeGame.model.emuns.GameDifficultyEnum;
import bg.softuni.newSnakeGame.model.entity.Info;
import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.model.entity.Settings;
import bg.softuni.newSnakeGame.repository.PlayerRepository;
import bg.softuni.newSnakeGame.repository.SettingsRepository;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingsService {

    private LoggedPlayer loggedPlayer;
    private PlayerRepository playerRepository;
    private SettingsRepository settingsRepository;

    public SettingsService(LoggedPlayer loggedPlayer, PlayerRepository playerRepository, SettingsRepository settingsRepository) {
        this.loggedPlayer = loggedPlayer;
        this.playerRepository = playerRepository;
        this.settingsRepository = settingsRepository;
    }

    public boolean changeDifficulty(String difficultyString) {

        Optional<Player> playerOptional = this.playerRepository.findById(this.loggedPlayer.getId());

        if (!playerOptional.isPresent()) {
            System.out.println(!playerOptional.isPresent());
            return false;
        }
        Player player = playerOptional.get();

        Optional<Settings> settingsOptional = this.settingsRepository.findByPlayer(player);

        if (!settingsOptional.isPresent()) {
            System.out.println("!settingsOptional.isPresent()");
            return false;
        }
        Settings settings = settingsOptional.get();

        switch (difficultyString) {
            case "easy":
                this.loggedPlayer.setDifficulty(GameDifficultyEnum.EASY);
                settings.easyDifficulty();
                break;
            case "normal":
                this.loggedPlayer.setDifficulty(GameDifficultyEnum.NORMAL);
                settings.normalDifficulty();
                break;
            case "hard":
                this.loggedPlayer.setDifficulty(GameDifficultyEnum.HARD);
                settings.hardDifficulty();
                break;
        }

        this.settingsRepository.save(settings);
        return true;
    }

    public boolean changeUsername(ChangeUsernameDTO changeUsernameDTO){

        if(!this.loggedPlayer.getPassword().equals(changeUsernameDTO.getPassword())){
           return false;
        }
        Optional<Player> playerOptional = this.playerRepository.findById(this.loggedPlayer.getId());

        if (!playerOptional.isPresent()){
            return false;
        }
        Player player = playerOptional.get();

        Info info = player.getInfo();
        info.setUsername(changeUsernameDTO.getNewUsername());

        player.setInfo(info);

        this.playerRepository.save(player);
        this.loggedPlayer.setUsername(changeUsernameDTO.getNewUsername());

        return true;
    }
}
