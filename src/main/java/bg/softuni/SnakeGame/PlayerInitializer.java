package bg.softuni.SnakeGame;

import bg.softuni.SnakeGame.models.DTO.PlayerRegisterDTO;
import bg.softuni.SnakeGame.models.entity.Player;
import bg.softuni.SnakeGame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Random;

@Component
public class PlayerInitializer implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerInitializer(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("initializing random players...");
//       for (int i = 0; i < 9; i++){
//           Player player = new Player(randomString(), randomString());
//           player.setBestScore(new Random().nextInt(100));
//           this.playerRepository.save(player);
//           System.out.println("#init player " + player.getUsername()+" was added to the database");
//       }

    }

    public String randomString(){
        byte[] array = new byte[5]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
