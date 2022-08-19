package bg.softuni.newSnakeGame.model.entity;

import bg.softuni.newSnakeGame.model.emuns.PlayerRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "player_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PlayerRoleEnum role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    public Role() {
        setToUser();
    }

    public void setToUser(){
        this.role = PlayerRoleEnum.USER;
    }

    public void setToAdmin(){
        this.role = PlayerRoleEnum.ADMIN;
    }

    public PlayerRoleEnum getRole() {
        return role;
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
