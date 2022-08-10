package bg.softuni.SnakeGame.models.DTO;
import javax.validation.constraints.*;

public class PlayerRegisterDTO {

    @Size(min = 3, max = 10)
    @NotBlank
    private String username;

    @Size(min = 3, max = 10)
    @NotBlank
    private String password;

    private String confirmPassword;

    public PlayerRegisterDTO(){}

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "PlayerRegisterDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
