package bg.softuni.SnakeGame.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;


    @Column(name = "best_score")
    private int bestScore;

    public Player(){}

    public Player(String username, String password) {

        this.username = username;
        this.password = password;

        this.bestScore = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bestScore=" + bestScore +
                '}';
    }
}
