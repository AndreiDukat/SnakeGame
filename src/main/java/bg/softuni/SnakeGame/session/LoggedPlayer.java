package bg.softuni.SnakeGame.session;

import bg.softuni.SnakeGame.models.entity.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedPlayer {

    private Long id;

    private String username;

    private String password;

    private Integer bestScore;

    private boolean isLogged;

    public LoggedPlayer() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.bestScore = null;
        this.isLogged = false;
    }

    public void login(Player player){
        this.id = player.getId();
        this.username = player.getUsername();
        this.bestScore = player.getBestScore();
        this.isLogged = true;
    }

    public void logout(){
        this.id = null;
        this.username = null;
        this.password = null;
        this.bestScore = null;
        this.isLogged = false;
    }

    public String getUsername() {
        return this.username;
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

    public Integer getBestScore() {
        return bestScore;
    }

    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
    }

    public boolean isLogged() {
        return this.isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoggedPlayer{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bestScore=" + bestScore +
                ", isLogged=" + isLogged +
                '}';
    }
}
