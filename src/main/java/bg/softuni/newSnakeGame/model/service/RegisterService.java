package bg.softuni.newSnakeGame.model.service;

import bg.softuni.newSnakeGame.model.DTO.PLayerRegisterDTO;
import bg.softuni.newSnakeGame.model.entity.Info;
import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.repository.InfoRepository;
import bg.softuni.newSnakeGame.repository.PlayerRepository;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    private PlayerRepository playerRepository;
    private InfoRepository infoRepository;
    private LoggedPlayer loggedPlayer;

    public RegisterService(PlayerRepository playerRepository, InfoRepository infoRepository, LoggedPlayer loggedPlayer) {
        this.playerRepository = playerRepository;
        this.infoRepository = infoRepository;
        this.loggedPlayer = loggedPlayer;
    }

    public boolean register(PLayerRegisterDTO playerRegisterDTO) {

        if (!playerRegisterDTO.getPassword().equals(playerRegisterDTO.getConfirmPassword())) {
            return false;
        }

        Optional<Info> infoOptionalByPassword = this.infoRepository.findByPassword(playerRegisterDTO.getPassword());
        Optional<Info> infoOptionalByUsername = this.infoRepository.findByUsername(playerRegisterDTO.getUsername());

        if (!infoOptionalByPassword.isPresent() && !infoOptionalByUsername.isPresent()) {

            Player player = new Player(
                    playerRegisterDTO.getUsername(),
                    playerRegisterDTO.getEmail(),
                    playerRegisterDTO.getPassword()
            );

            System.out.println(player.toString());

            this.playerRepository.save(player);

            this.loggedPlayer.login(player);

//            System.out.println("Registered player name: " + this.loggedPlayer.getUsername());

            return true;
        }

        return false;
    }

    public LoggedPlayer getLoggedPlayer() {
        return this.loggedPlayer;
    }
}
