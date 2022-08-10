package bg.softuni.SnakeGame.Service;

import bg.softuni.SnakeGame.models.DTO.PlayerLoginDTO;
import bg.softuni.SnakeGame.models.DTO.PlayerRegisterDTO;
import bg.softuni.SnakeGame.models.entity.Player;
import bg.softuni.SnakeGame.repository.PlayerRepository;
import bg.softuni.SnakeGame.session.LoggedPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private LoggedPlayer loggedPlayer;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.loggedPlayer = new LoggedPlayer();
    }

    public boolean register(PlayerRegisterDTO playerRegisterDTO) {

        if (!playerRegisterDTO.getPassword().equals(playerRegisterDTO.getConfirmPassword())) {
            return false;
        }
        Optional<Player> playerOptional = this.playerRepository.findByPassword(playerRegisterDTO.getPassword());

        if (playerOptional.isEmpty()) {

            Player player = new Player(
                    playerRegisterDTO.getUsername(),
                    playerRegisterDTO.getPassword()
            );

            this.playerRepository.save(player);

            PlayerLoginDTO playerLoginDTO = mapEntityToLoginDTO(player);

            login(playerLoginDTO);
        }

        return false;
    }

    public boolean login(PlayerLoginDTO playerLoginDTO) {

        Optional<Player> playerOptional = this.playerRepository.findByUsernameAndPassword(
                playerLoginDTO.getUsername(), playerLoginDTO.getPassword());

        if (playerOptional.isEmpty()) {
            return false;
        }

        this.loggedPlayer.login(playerOptional.get());

        System.out.println("logged player name: " + this.loggedPlayer.getUsername());

        return true;
    }

    public void logout() {
        this.loggedPlayer.logout();
    }

    public Player mapRegisterDTOToEntity(PlayerRegisterDTO playerRegisterDTO) {

        Player player = new Player(playerRegisterDTO.getUsername(), playerRegisterDTO.getPassword());
        return player;
    }

    public Player mapLoginDTOToEntity(PlayerLoginDTO playerLoginDTO) {
        return new Player(playerLoginDTO.getUsername(), playerLoginDTO.getPassword());
    }

    public void updatePlayerScore(int score) {

        this.loggedPlayer.setBestScore(score);

        Optional<Player> playerOptional = this.playerRepository.findById(this.loggedPlayer.getId());

        Player player = new Player();

        if(playerOptional.isPresent()) {
            player = playerOptional.get();
        }

        player.setBestScore(score);

        this.playerRepository.save(player);

    }

    public LoggedPlayer getLoggedPlayer() {
        return this.loggedPlayer;
    }

    public PlayerLoginDTO mapEntityToLoginDTO(Player player) {
        PlayerLoginDTO playerLoginDTO = new PlayerLoginDTO();
        playerLoginDTO.setUsername(player.getUsername());
        playerLoginDTO.setPassword(player.getPassword());

        return playerLoginDTO;
    }

    public List<Player> top10Players() {
        return this.playerRepository.findTop10ByOrderByBestScoreDesc();
    }
}
