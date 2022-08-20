package bg.softuni.newSnakeGame;

import bg.softuni.newSnakeGame.model.entity.Player;
import bg.softuni.newSnakeGame.repository.PlayerRepository;
import bg.softuni.newSnakeGame.service.RegisterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerRepositoryTest {

    private PlayerRepository playerRepository;

    @Test
    void testGetById(){
        Player player = new Player("tester_1", "@test1", "111");

        this.playerRepository.save(player);
        long id =1;
        when(this.playerRepository.findById(id))
                .thenReturn(Optional.of(player));

        Assertions.assertEquals(player, playerRepository.findById(id));

    }
}
