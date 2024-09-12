import java.util.Random;

public class Minesweeper {
    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;

        // Call methods to initialize the board and place mines
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    private void initializeBoard() {
        // TODO: Implement this method
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                board[i][j] = ' ';
                revealed[i][j] = false;
                mines[i][j] = false;
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        // TODO: Implement this method
        Random rand = new Random();
        int placedMines = 0;
        while(placedMines < numMines){
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if(!mines[r][c]){
                mines[r][c] = true;
                placedMines++;
            }
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        // TODO: Implement this method
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!mines[r][c]) {
                    int adjacentMines = 0;
                    for (int i = r - 1; i <= r + 1; i++) {
                        for (int j = c - 1; j <= c + 1; j++) {
                            if (i >= 0 && i < rows && j >= 0 && j < cols && mines[i][j]) {
                                adjacentMines++;
                            }
                        }
                    }
                    board[r][c] = (char) (adjacentMines + '0');
                }
            }
        }
    }

    // Method to display the current state of the board
    public void displayBoard() {
        // TODO: Implement this method
        System.out.println("Current Board: ");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(revealed[i][j]){
                    System.out.print(board[i][j] + " ");
                }
                else{
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        // TODO: Implement this method
        if(action.equals("reveal")){
            if(mines[row][col]){
                setGameOver(true);
            }
            else{
                revealCell(row, col);
            }
        }
        else if(action.equals("flag")){
            flagCell(row,col);
        }
        else if(action.equals("unflag")){
            unflagCell(row,col);
        }
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        // TODO: Implement this method
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(!mines[r][c] && !revealed[r][c]){
                    return false;
                }
            }
        }
        return true;
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        // TODO: Implement this method
        return mines[row][col];
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        // TODO: Implement this method
        if(row < 0 || col < 0 || row >= rows || col >= cols || revealed[row][col]){
            return;
        }
        revealed[row][col] = true;
        if(board[row][col] == '0'){
            for(int r = row - 1; r <= row + 1; r++){
                for(int c = col - 1; c <= col + 1; c++){
                    if(r >= 0 && r < rows && c >= 0 && c < cols){
                        revealCell(r, c);
                    }
                }
            }
        }
    }

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        // TODO: Implement this method
        if(!revealed[row][col]){
            board[row][col] = 'F';
        }
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        // TODO: Implement this method
        if(board[row][col] == 'F'){
            board[row][col] = ' ';
        }
    }
}
