package bg.softuni.SnakeGame.game;

public class Dot {

    private String type;

    private int value;

    public Dot() {
        this.type = "space";
        value = 0;
    }

    public Dot(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
