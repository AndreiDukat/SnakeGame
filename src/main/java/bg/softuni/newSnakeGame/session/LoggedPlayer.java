package bg.softuni.newSnakeGame.session;

import bg.softuni.newSnakeGame.model.emuns.GameDifficultyEnum;
import bg.softuni.newSnakeGame.model.emuns.PlayerRoleEnum;
import bg.softuni.newSnakeGame.model.entity.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class LoggedPlayer {

    private Long id;
    private String username;
    private String password;
    private String email;

    private Integer easyBestScore;
    private Integer normalBestScore;
    private Integer hardBestScore;
    private Double averageScore;
    private Long gamesCount;
    private List<Integer> allScores;

    private GameDifficultyEnum difficulty;

    private PlayerRoleEnum role;

    private boolean isLogged;

    private Long infoId;
    private Long statsId;
    private Long settingsId;
    private Long roleId;

    public LoggedPlayer() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.email = null;
        this.easyBestScore = null;
        this.normalBestScore = null;
        this.hardBestScore = null;
        this.averageScore = null;
        this.gamesCount = null;
        this.allScores = new ArrayList<>();
        this.difficulty = null;
        this.role = null;
        this.infoId = null;
        this.statsId = null;
        this.settingsId = null;
        this.roleId = null;
        this.isLogged = false;
    }

    public void login(Player player) {

        this.id = player.getId();

        this.username = player.getInfo().getUsername();
        this.password = player.getInfo().getPassword();
        this.email = player.getInfo().getEmail();

        this.easyBestScore = player.getStats().getEasyBestScore();
        this.normalBestScore = player.getStats().getNormalBestScore();
        this.hardBestScore = player.getStats().getHardBestScore();
        this.averageScore = player.getStats().getAverageScore();
        this.gamesCount = player.getStats().getGamesCount();
        this.allScores = player.getStats().getAllScores();

        this.difficulty = player.getSettings().getDifficulty();

        this.role = player.getRole().getRole();

        this.infoId = player.getInfo().getId();
        this.statsId = player.getStats().getId();
        this.settingsId = player.getSettings().getId();
        this.roleId = player.getRole().getId();

        this.isLogged = true;
    }

    public void logout() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.email = null;
        this.easyBestScore = null;
        this.normalBestScore = null;
        this.hardBestScore = null;
        this.averageScore = null;
        this.gamesCount = null;
        this.allScores = new ArrayList<>();
        this.difficulty = null;
        this.role = null;
        this.infoId = null;
        this.statsId = null;
        this.settingsId = null;
        this.roleId = null;
        this.isLogged = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Long getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(Long gamesCount) {
        this.gamesCount = gamesCount;
    }

    public GameDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public PlayerRoleEnum getRole() {
        return role;
    }

    public void setRole(PlayerRoleEnum role) {
        this.role = role;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getStatsId() {
        return statsId;
    }

    public void setStatsId(Long statsId) {
        this.statsId = statsId;
    }

    public Long getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(Long settingsId) {
        this.settingsId = settingsId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public List<Integer> getAllScores() {
        return allScores;
    }

    public void setAllScores(List<Integer> allScores) {
        this.allScores = allScores;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getEasyBestScore() {
        return easyBestScore;
    }

    public void setEasyBestScore(Integer easyBestScore) {
        this.easyBestScore = easyBestScore;
    }

    public Integer getNormalBestScore() {
        return normalBestScore;
    }

    public void setNormalBestScore(Integer normalBestScore) {
        this.normalBestScore = normalBestScore;
    }

    public Integer getHardBestScore() {
        return hardBestScore;
    }

    public void setHardBestScore(Integer hardBestScore) {
        this.hardBestScore = hardBestScore;
    }
}
