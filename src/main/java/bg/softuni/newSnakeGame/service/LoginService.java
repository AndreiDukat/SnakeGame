package bg.softuni.newSnakeGame.service;

import bg.softuni.newSnakeGame.model.DTO.PlayerLoginDTO;
import bg.softuni.newSnakeGame.model.entity.Info;
import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.repository.InfoRepository;
import bg.softuni.newSnakeGame.repository.PlayerRepository;
import bg.softuni.newSnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private PlayerRepository playerRepository;
    private InfoRepository infoRepository;
    private LoggedPlayer loggedPlayer;

    public LoginService(PlayerRepository playerRepository, InfoRepository infoRepository, LoggedPlayer loggedPlayer) {
        this.playerRepository = playerRepository;
        this.infoRepository = infoRepository;
        this.loggedPlayer = loggedPlayer;
    }

    public boolean login(PlayerLoginDTO playerLoginDTO){

        Optional<Info> infoOptional = this.infoRepository.findByPassword(
                playerLoginDTO.getPassword());

//        System.out.println(this.infoRepository.findByUsernameAndPassword(
//                playerLoginDTO.getUsername(),
//                playerLoginDTO.getUsername()
//        ));

        if(!infoOptional.isPresent()){
            System.out.println("!infoOptional.isPresent()");
            return false;
        }

        Info info = infoOptional.get();

        if (!info.getUsername().equals(playerLoginDTO.getUsername())){
            System.out.println("info username != DTO username");
            return false;
        }
        System.out.println("info: "+info.getUsername());

        Optional<Player> playerOptional = this.playerRepository.findByInfo(info);

        if (!playerOptional.isPresent()){
            System.out.println("!playerOptional.isPresent()");
            return false;
        }

        Player player = playerOptional.get();

//        System.out.println("player: "+player.getInfo().getUsername());

//        System.out.println("logged player: "+ this.loggedPlayer.getUsername());
        this.loggedPlayer.login(player);
//        System.out.println("new logged player: "+ this.loggedPlayer.getUsername());
        return true;
    }

    public LoggedPlayer getLoggedPlayer() {
        return this.loggedPlayer;
    }
}
