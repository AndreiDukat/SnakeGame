package bg.softuni.newSnakeGame.model.entity;

import bg.softuni.newSnakeGame.model.emuns.GameDifficultyEnum;

import javax.persistence.*;

@Entity
@Table(name = "player_settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private GameDifficultyEnum difficulty;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    public Settings() {
        normalDifficulty();
    }

    public void easyDifficulty(){
        this.difficulty = GameDifficultyEnum.EASY;
    }

    public void normalDifficulty(){
        this.difficulty = GameDifficultyEnum.NORMAL;
    }

    public void hardDifficulty(){
        this.difficulty = GameDifficultyEnum.HARD;
    }

    public GameDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
