package bg.softuni.newSnakeGame.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player_stats")
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "games_count")
    private long gamesCount;

    @Column(name = "average_score")
    private double averageScore;

//    @Column(name = "best_score")
//    private int bestScore;

    @Column(name = "easy_best_score")
    private int easyBestScore;

    @Column(name = "normal_best_score")
    private int normalBestScore;

    @Column(name = "hard_best_score")
    private int hardBestScore;

    @Transient
    private List<Integer> allScores;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;


    public Stats(long gamesCount, double averageScore, int easyBestScore, int normalBestScore, int hardBestScore) {
        this.gamesCount = gamesCount;
        this.averageScore = averageScore;
        this.easyBestScore = easyBestScore;
        this.normalBestScore = normalBestScore;
        this.hardBestScore = hardBestScore;
        this.allScores = new ArrayList<>();
    }

    public Stats() {
        this.gamesCount = 0;
        this.averageScore = 0;
        this.easyBestScore = 0;
        this.normalBestScore = 0;
        this.hardBestScore = 0;
        this.allScores = new ArrayList<>();
    }

    public void calculateAverageScore() {
        double scoreSum = 0;

        for (Integer score : this.allScores) {
            scoreSum += score;
        }
        this.averageScore = scoreSum / this.allScores.size();
    }

    public void addScore(int score) {
        this.allScores.add(this.allScores.size(), score);
//        for (Integer allScore : allScores) {
//            System.out.println(allScore);
//        }
    }

    public void increaseGamesCount() {
//        System.out.println("games count = "+this.gamesCount);
        this.gamesCount += 1;
//        System.out.println("this.gamesCount+=1, games count = "+this.gamesCount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(long gamesCount) {
        this.gamesCount = gamesCount;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Integer> getAllScores() {
        return allScores;
    }

    public void setAllScores(List<Integer> allScores) {
        this.allScores = allScores;
    }

    public Player getPlayer() {
        return player;
    }

    public int getEasyBestScore() {
        return easyBestScore;
    }

    public void setEasyBestScore(int easyBestScore) {
        this.easyBestScore = easyBestScore;
    }

    public int getNormalBestScore() {
        return normalBestScore;
    }

    public void setNormalBestScore(int normalBestScore) {
        this.normalBestScore = normalBestScore;
    }

    public int getHardBestScore() {
        return hardBestScore;
    }

    public void setHardBestScore(int hardBestScore) {
        this.hardBestScore = hardBestScore;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", gamesCount=" + gamesCount +
                ", averageScore=" + averageScore +
                ", easyBestScore=" + easyBestScore +
                ", normalBestScore=" + normalBestScore +
                ", hardBestScore=" + hardBestScore +
                ", allScores=" + allScores +
                ", player=" + player +
                '}';
    }
}
