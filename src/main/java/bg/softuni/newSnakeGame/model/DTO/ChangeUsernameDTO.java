package bg.softuni.newSnakeGame.model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangeUsernameDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    public String newUsername;

    @NotBlank
    @Size(min = 3, max = 20)
    public String password;

    public ChangeUsernameDTO(){}

    public ChangeUsernameDTO(String newUsername, String password) {
        this.newUsername = newUsername;
        this.password = password;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
