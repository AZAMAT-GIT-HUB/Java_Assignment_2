import java.util.Random;
import java.util.SplittableRandom;

public class TicTacToe_3 {

    // Define enum for cell states
    public enum Cell {X, O, E}
    // Declare board and player turn variables
    private  Cell[][] board;
    private boolean humanFirst;
    // Define board size
    private static final int BOARD_SIZE = 3;

    // Constructor to initialize the game
    public TicTacToe_3(boolean humanFirst){
        this.humanFirst = humanFirst;
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        // initialize the Board:
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = Cell.E; // E represents empty cell
            }
        }
    }

    // Crete play Game method:
    // and this Method to start and control the game
    public void playGame(){
        Random random = new Random();
        boolean gameOver = false;
        boolean isHumanTurn = humanFirst;

        // Run the game loop until the game is over
        while(!gameOver){
            // Print the current board state
            printBoard();
            if(isHumanTurn){
                // Human player's turn:
                System.out.println("\nHuman turn, Enter here:");
                int row  = random.nextInt(3);
                int col = random.nextInt(3);
                //call function MakeMove:
                makeMove(row , col, Cell.X);
                if(isWin(row, col, Cell.X)){  // Check if human wins
                    gameOver = true;
                    printBoard();
                    System.out.println("\nCongratulation! You Win!");
                }// if all cells are filled, the gameOver is True:
                else if (isDraw()) {  // Check for a draw
                    gameOver = true;
                    printBoard();
                    System.out.println("It's a draw!");
                }
                else{
                    isHumanTurn = false;
                }
            }
            else {
                // Computer's turn:
                System.out.println("\nComputer's turn: ");
                // Computer makes a move to win if possible
                if(!makeWinningMove()){ // Try to make a winning move
                    // If no winning move, make a random move
                    int rowCom, colCom;
                    do{
                        rowCom = random.nextInt(3);
                        colCom = random.nextInt(3);

                    }while(!isValidMove(rowCom, colCom));
                    makeMove(rowCom, colCom, Cell.O);  // Make the move
                }
                // Check if computer wins
                if(isWin(board, Cell.O)){
                    gameOver = true;
                    printBoard();
                    System.out.println("Computer wins!");
                } else if (isDraw()) {  // Check for a draw
                    gameOver = true;
                    printBoard();
                    System.out.println("It's draw!");
                }else{
                    isHumanTurn = true;
                }
            }
        }
    }
    // Method to check if a move is valid
    private boolean isValidMove(int row, int col){
        return row >= 0 && row < BOARD_SIZE && col >=0 && col < BOARD_SIZE && board[row][col] == Cell.E;
    }

    // Method to make a move
    private void makeMove(int row, int col, Cell player){
        board[row][col] = player;
    }


    // This method checks for a win condition based on the specific row and column where a move
    // was made by a player. It's used within the loop where the human player makes a move,
    // and it checks if that move results in a win for the player.
    private boolean isWin(int row, int col, Cell player){
        if(board[row][0] == player && board[row][1] == player && board[row][2] == player){
            return true;
        }

        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true;
        }
        if( row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player ){
            return true;
        }
        if(row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] ==player){
            return true;

        }
        return false;
    }


    // This method checks for a win condition based on the entire game board.
    // It iterates through rows, columns, and diagonals to see if any of them contain a
    // winning sequence of cells.
    // This method is used after the computer makes its move, to check if the computer has won.
    private boolean isWin(Cell[][] board, Cell player){
        for(int i = 0; i < BOARD_SIZE; i++){
            if((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)){
                return true;
            }
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }


        return false;
    }

    // Method to check for a draw
    private boolean isDraw(){
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                if(board[row][col] == Cell.E){
                    return false;
                }
            }

        }
        return true;
    }

    // Method to check for a draw
    public void printBoard(){
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            if (row < BOARD_SIZE) {
                System.out.println("-----------");
            }
        }
    }

    // Method to make a winning move for the computer
    private boolean makeWinningMove(){
        // Check row
        for(int i = 0; i < BOARD_SIZE; i++){
            if(board[i][0] == Cell.O && board[i][1] == Cell.O && board[i][2] == Cell.E) {
                makeMove(i, 2, Cell.O);
                return true;
            }
            if(board[i][0] == Cell.O && board[i][2] == Cell.O && board[i][1] == Cell.E){
                makeMove(i, 1, Cell.O);
                return true;
            }
            if(board[i][1] == Cell.O && board[i][2] == Cell.O && board[i][0] == Cell.E){
                makeMove(i, 0, Cell.O);
                return true;
            }
        }
        // Check column:
        for(int j = 0; j < BOARD_SIZE; j++){
            if (board[0][j] == Cell.O && board[1][j] == Cell.O && board[2][j] == Cell.E) {
                makeMove(2, j, Cell.O);
                return true;
            }
            if (board[0][j] == Cell.O && board[2][j] == Cell.O && board[1][j] == Cell.E) {
                makeMove(1, j, Cell.O);
                return true;
            }

            if (board[1][j] == Cell.O && board[2][j] == Cell.O && board[0][j] == Cell.E) {
                makeMove(0, j, Cell.O);
                return true;
            }
        }

        // Check diagonals:
        if (board[0][0] == Cell.O && board[1][1] == Cell.O && board[2][2] == Cell.E) {
            makeMove(2, 2, Cell.O);
            return true;
        }

        if (board[0][0] == Cell.O && board[2][2] == Cell.O && board[1][1] == Cell.E) {
            makeMove(1, 1, Cell.O);
            return true;
        }

        if (board[1][1] == Cell.O && board[2][2] == Cell.O && board[0][0] == Cell.E) {
            makeMove(0, 0, Cell.O);
            return true;
        }

        //Check anti-diagonal:
        if (board[0][2] == Cell.O && board[1][1] == Cell.O && board[2][0] == Cell.E) {
            makeMove(2, 0, Cell.O);
            return true;
        }
        if (board[2][0] == Cell.O && board[1][1] == Cell.O && board[0][2] == Cell.E) {
            makeMove(0, 2, Cell.O);
            return true;
        }
        if (board[2][0] == Cell.O && board[0][2] == Cell.O && board[1][1] == Cell.E) {
            makeMove(1, 1, Cell.O);
            return true;
        }

        return false;
    }


    // Main method to start the game
    public static void main(String[] args){
        System.out.println("\nWelcome to Tic-Tac-Toe!");
        System.out.println("\nE = Empty\n");
        TicTacToe_3 game = new TicTacToe_3(true); // Computer plays first
        game.playGame();
    }
}