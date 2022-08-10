     document.addEventListener("keypress", processKeyPress);

    var gameField = new GameField();

    initTable();

    function initTable(){

    for(let i = 0; i<16; i++){
    let row = [];

        for(let j = 0; j<16; j++){
            let dot = new Dot('space', 0);
            row[j] = dot;
        }

    gameField.table[i] = row;
    }
        initSnakeHead();
        generateApple();
        console.log(gameField);
    }

    function GameField(){
    this.table = [];
    this.direction = 'right';
    this.score = 0;
    this.snakeLength = 3;
    this.playerI =  0;
    this.playerJ = 0;
    this.appleI= 0;
    this.appleJ= 0;
    this.isGameOver = false;
    }

    function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function generateApple(){

        let i = getRandomInt(0, 15);
        let j = getRandomInt(0, 15);

        let success = false;

        while(!success){
            i = getRandomInt(0, 15);
            j = getRandomInt(0, 15);

            if(gameField.table[i][j].type == 'space'){
                gameField.table[i][j] = new Dot('apple', 0);
                gameField.appleI = i;
                gameField.appleJ = j;
                success = true;
            }
        }
    }

    function initSnakeHead(){
        gameField.table[0][0] = new Dot('head', gameField.snakeLength);
    }

    function Dot(type, value){
    this.type = type;
    this.value = value;
    }

    function printTable(){

    var divTable = document.getElementById("showTable");

    if(divTable.querySelector("table") == null){
    var theTable = document.createElement("table");

    for(let i=0; i < gameField.table.length; i++){

    var theRow = document.createElement("tr");
    theTable.appendChild(theRow);

    for(let j=0; j < gameField.table[i].length; j++) {

    var theCell = document.createElement("td");
    var theText = document.createTextNode(gameField.table[i][j]);
    theCell.className = gameField.table[i][j].type;
    theCell.textContent = "---.";
    //theCell.appendChild(theText);
    theRow.appendChild(theCell);
    }

    }

    let score = document.createElement("span")
    score.textContent = ' score: ' + gameField.score + ' ';

    var divTable = document.getElementById("showTable");
    divTable.appendChild(theTable);
    divTable.appendChild(score);
    }else{
        updateTable();
    }
    }

    function updateTable(){
        let div = document.getElementById("showTable");
        div.querySelector("table").remove();
        div.querySelector("span").remove();
        printTable();

    }

    function move(){

        if(!gameField.isGameOver){

            if(gameField.direction == 'up'){
                if(gameField.playerI - 1 >= 0){

                    let newI = gameField.playerI -1;
                    let newJ = gameField.playerJ;

                    if(newI == gameField.appleI && newJ == gameField.appleJ){
                        gameField.score += 1;
                        gameField.snakeLength += 1;
                        generateApple();
                        increaseTailValue();
                    }

                    if( gameField.table[newI][newJ].type == 'tail'){
                        gameField.isGameOver = true;
                    }

                    gameField.table[gameField.playerI][gameField.playerJ].type = 'tail';
                    gameField.table[newI][newJ].type = 'head';
                    gameField.table[newI][newJ].value = gameField.snakeLength;

                    gameField.playerI = newI;
                    gameField.playerJ = newJ;

                }else{
                    gameField.isGameOver = true;
                }
            }
        }

        if(gameField.direction == 'down'){
                if(gameField.playerI + 1 < 16){

                    let newI = gameField.playerI  + 1;
                    let newJ = gameField.playerJ;

                    if(newI == gameField.appleI && newJ == gameField.appleJ){
                        gameField.score += 1;
                        gameField.snakeLength += 1;
                        generateApple();
                        increaseTailValue();
                    }

                    if( gameField.table[newI][newJ].type == 'tail'){
                        gameField.isGameOver = true;
                    }

                    gameField.table[gameField.playerI][gameField.playerJ].type = 'tail';
                    gameField.table[newI][newJ].type = 'head';
                    gameField.table[newI][newJ].value = gameField.snakeLength;

                    gameField.playerI = newI;
                    gameField.playerJ = newJ;

                }else{
                    gameField.isGameOver = true;
                }
        }

        	if(gameField.direction == 'right'){
                if(gameField.playerJ + 1 < 16){

                    let newI = gameField.playerI;
                    let newJ = gameField.playerJ + 1;

                    if(newI == gameField.appleI && newJ == gameField.appleJ){
                        gameField.score += 1;
                        gameField.snakeLength += 1;
                        generateApple();
                        increaseTailValue();
                    }

                    if( gameField.table[newI][newJ].type == 'tail'){
                        gameField.isGameOver = true;
                    }

                    gameField.table[gameField.playerI][gameField.playerJ].type = 'tail';
                    gameField.table[newI][newJ].type = 'head';
                    gameField.table[newI][newJ].value = gameField.snakeLength;

                    gameField.playerI = newI;
                    gameField.playerJ = newJ;

                }else{
                    gameField.isGameOver = true;
                }
            }

            	if(gameField.direction == 'left'){
                if(gameField.playerJ - 1 >= 0){

                    let newI = gameField.playerI;
                    let newJ = gameField.playerJ - 1;

                    if(newI == gameField.appleI && newJ == gameField.appleJ){
                        gameField.score += 1;
                        gameField.snakeLength += 1;
                        generateApple();
                        increaseTailValue();
                    }

                    if( gameField.table[newI][newJ].type == 'tail'){
                        gameField.isGameOver = true;
                    }

                    gameField.table[gameField.playerI][gameField.playerJ].type = 'tail';
                    gameField.table[newI][newJ].type = 'head';
                    gameField.table[newI][newJ].value = gameField.snakeLength;

                    gameField.playerI = newI;
                    gameField.playerJ = newJ;

                }else{
                    gameField.isGameOver = true;
                }
            }
            reduceTailValue();
    }

function reduceTailValue(){
    for (let i = 0; i < 16; i++){
        for (let j = 0; j < 16; j++){
            if(gameField.table[i][j].type == 'tail'){

                gameField.table[i][j].value -= 1;

                if(gameField.table[i][j].value <= 0){
                    gameField.table[i][j].type = 'space';
                }
            }
        }
    }
    updateTable();
}

function increaseTailValue(){
 for (let i = 0; i < 16; i++){
        for (let j = 0; j < 16; j++){
            if(gameField.table[i][j].type == 'tail'){
                gameField.table[i][j].value += 1;
            }
        }
    }
    updateTable();
}

function delay(time) {
  return new Promise(resolve => setTimeout(resolve, time));
}

function gameOver(){
    console.error('game over!')
    let div = document.getElementById("showTable");
    div.querySelector("table").remove();

    let errorDiv = document.createElement("div");
    errorDiv.className = 'gameOverDiv';

    let errorMessage = document.createElement("span");
    errorMessage.textContent = 'Game Over';
    errorMessage.className = 'gameOverMsg';

    let continueBtn = document.createElement("a");
    continueBtn.href = '/home';
    continueBtn.textContent = 'Continue';
    continueBtn.className = 'continueBtn'

    errorDiv.appendChild(errorMessage);
    errorDiv.appendChild(continueBtn);
    console.log(errorDiv);
    div.appendChild(errorDiv);

    fetch("/game",
        {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({score: gameField.score})
    })
    .then(function(res){ console.log(res) })
    .catch(function(res){ console.log(res) })
}

async function play(){

    printTable();

    while(!gameField.isGameOver){
        move();
        updateTable();
        await delay(200);
    }
    gameOver();

}

function processKeyPress(event){
    if(event.key == 'w'){
        if(gameField.direction != 'down'){
            gameField.direction = 'up';
        }
    }
    if(event.key == 's'){
        if(gameField.direction != 'up'){
            gameField.direction = 'down';
        }
    }
    if(event.key == 'd'){
        if(gameField.direction != 'left'){
            gameField.direction = 'right';
        }
    }
    if(event.key == 'a'){
        if(gameField.direction != 'right'){
            gameField.direction = 'left';
        }
    }

}