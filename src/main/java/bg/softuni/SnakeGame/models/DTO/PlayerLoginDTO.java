package bg.softuni.SnakeGame.models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PlayerLoginDTO {

    @Size(min = 3, max = 10)
    @NotBlank
    private String username;

    @Size(min = 3, max = 10)
    @NotBlank
    private String password;

    public PlayerLoginDTO(){}

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

    @Override
    public String toString() {
        return "PlayerLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
