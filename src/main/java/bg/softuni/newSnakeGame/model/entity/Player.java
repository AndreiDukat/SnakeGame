package bg.softuni.newSnakeGame.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Info info;


    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Stats stats;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Settings settings;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Role role;

    public Player(String username, String email, String password) {
        this.info = new Info(username, email, password);
        this.stats = new Stats();
        this.settings = new Settings();
        this.role = new Role();

        this.info.setPlayer(this);
        this.stats.setPlayer(this);
        this.settings.setPlayer(this);
        this.role.setPlayer(this);
    }

    public Player() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return this.info.getPassword();
    }

    public String getUsername() {
        return this.info.getUsername();
    }
}
