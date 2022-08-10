package bg.softuni.SnakeGame.game;

import java.util.Random;

public class Game {

    private Dot[][] gameField;

    private String direction;

    private int snakeLength;

    private int playerI;
    private int playerJ;

    private int appleI;
    private int appleJ;

    private int score;

    private boolean isGameOver;

    public Game() {
        this.gameField = new Dot[16][16];
        this.direction = "right";

        this.snakeLength = 3;

        this.playerI = 0;
        this.playerJ = 0;

        this.score = 0;


        this.isGameOver = false;

        initGameField();
        initSnakeHead();
    }

    public void move(){
        if(!this.isGameOver) {
            if (this.direction.equals("up")) {
                if (this.playerI - 1 >= 0) {

                  int newI = this.playerI - 1;
                  int newJ = this.playerJ;

                  if (newI == this.appleI && newJ == this.appleJ){
                      this.score++;
                      this.snakeLength++;
                      generateApple();
                      increaseTailValue();
                  }

                  if (this.gameField[newI][newJ].getType().equals("tail")){
                      this.isGameOver = true;
                  }

                    this.gameField[playerI][playerJ].setType("tail");
                    this.gameField[newI][newJ].setType("head");
                    this.gameField[newI][newJ].setValue(this.snakeLength);

                    this.playerI = newI;
                    this.playerJ = newJ;
                } else {
                    this.isGameOver = true;
                }
            }

            if (this.direction.equals("down")) {
                if (this.playerI + 1 < 16) {

                    int newI = this.playerI + 1;
                    int newJ = this.playerJ;

                    if (newI == this.appleI && newJ == this.appleJ){
                        this.score++;
                        this.snakeLength++;
                        increaseTailValue();
                        generateApple();
                    }
                    if (this.gameField[newI][newJ].getType().equals("tail")){
                        this.isGameOver = true;
                    }

                    this.gameField[playerI][playerJ].setType("tail");
                    this.gameField[newI][newJ].setType("head");
                    this.gameField[newI][newJ].setValue(this.snakeLength);

                    this.playerI = newI;
                    this.playerJ = newJ;
                } else {
                    this.isGameOver = true;
                }
            }
            if (this.direction.equals("right")) {
                if (this.playerJ + 1 < 16) {

                    int newI = this.playerI;
                    int newJ = this.playerJ + 1;

                    if (newI == this.appleI && newJ == this.appleJ){
                        this.score++;
                        this.snakeLength++;
                        increaseTailValue();
                        generateApple();
                    }
                    if (this.gameField[newI][newJ].getType().equals("tail")){
                        this.isGameOver = true;
                    }

                    this.gameField[playerI][playerJ].setType("tail");
                    this.gameField[newI][newJ].setType("head");
                    this.gameField[newI][newJ].setValue(this.snakeLength);

                    this.playerI = newI;
                    this.playerJ = newJ;
                } else {
                    this.isGameOver = true;
                }
            }

            if (this.direction.equals("left")) {
                if (this.playerJ - 1 >= 0) {

                    int newI = this.playerI;
                    int newJ = this.playerJ - 1;

                    if (newI == this.appleI && newJ == this.appleJ){
                        this.score++;
                        this.snakeLength++;
                        increaseTailValue();
                        generateApple();
                    }
                    if (this.gameField[newI][newJ].getType().equals("tail")){
                        this.isGameOver = true;
                    }

                    this.gameField[playerI][playerJ].setType("tail");
                    this.gameField[newI][newJ].setType("head");
                    this.gameField[newI][newJ].setValue(this.snakeLength);

                    this.playerI = newI;
                    this.playerJ = newJ;
                } else {
                    this.isGameOver = true;
                }
            }
            reduceTailValue();
        }
    }
    public void reduceTailValue(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
               if (this.gameField[i][j].getType().equals("tail")){
                   this.gameField[i][j].setValue(this.gameField[i][j].getValue() - 1);
                   if (this.gameField[i][j].getValue() <= 0){
                       this.gameField[i][j].setType("space");
                   }
               }
            }
        }
    }
    public void increaseTailValue(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (this.gameField[i][j].getType().equals("tail")){
                    this.gameField[i][j].setValue(this.gameField[i][j].getValue() + 1);
                }
            }
        }
    }

    public void goUp() {
        this.direction = "up";
    }

    public void goDown() {
        this.direction = "down";
    }

    public void goRight() {
        this.direction = "right";
    }

    public void goLeft() {
        this.direction = "left";
    }

    public void initGameField() {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Dot dot = new Dot();
                dot.setType("space");
                this.gameField[i][j] = dot;
            }
        }
    }

    public void initSnakeHead() {
        Dot dot = new Dot();
        dot.setType("head");
        dot.setValue(3);
        this.gameField[0][0] = dot;
    }

    public void print() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(this.gameField[i][j].getType() + ": "
                        + this.gameField[i][j].getValue() + ", ");
                //  + " ["+ i+ " "+j+"] ");
            }
            System.out.println();
        }
        System.out.println(this.direction);
    }

    public void gamePrint() {
        if(!this.isGameOver) {

            System.out.println("score: "+this.score);
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    switch (this.gameField[i][j].getType()) {
                        case "space":
                            System.out.print("_");
                            break;
                        case "head":
                            System.out.print("X");
                            break;
                        case "tail":
                            System.out.print("o");
                            break;
                        case "apple":
                            System.out.print("@");
                            break;
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
        }else {
            System.out.println("game over");
        }
    }

    public void generateApple() {

        boolean success = false;

        while (!success) {
            int i = new Random().nextInt(15);
            int j = new Random().nextInt(15);

            Dot theDot = this.gameField[i][j];
            if (theDot.getType().equals("space")) {
                theDot.setType("apple");
                this.appleI =i;
                this.appleJ =j;
                success = true;
            }
        }

    }

    public Dot[][] getGameField() {
        return gameField;
    }

    public void setGameField(Dot[][] gameField) {
        this.gameField = gameField;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(int snakeLength) {
        this.snakeLength = snakeLength;
    }

    public int getPlayerI() {
        return playerI;
    }

    public void setPlayerI(int playerI) {
        this.playerI = playerI;
    }

    public int getPlayerJ() {
        return playerJ;
    }

    public void setPlayerJ(int playerJ) {
        this.playerJ = playerJ;
    }
}
