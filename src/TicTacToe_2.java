import java.util.Scanner;

// Create TicTacToe_2 Class
public class TicTacToe_2 {
    // Initialize enum type
    public enum Cell {X, O, E}

    // create board size
    private static final int BOARD_SIZE = 3;
    Cell[][] board;
    Cell currentPlayer;


    // create constructor:
    public TicTacToe_2() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = Cell.X;
        intialBoard();

    }

    // create initial Board
    public void intialBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = Cell.E;
            }
        }
    }

    // create printBoard method to display
    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            if (row < BOARD_SIZE) {
                System.out.println("-----------");
            }
        }

    }

    // Method to start the game and handle player and computer turns
    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Tic-Tac-Toe Game!");
        System.out.println("E = Empty\n");
        printBoard();

        System.out.print("Do you want to play first? (Y/N): ");
        char choice = scanner.next().toUpperCase().charAt(0);
        boolean humanFirst = (choice == 'Y');

        // Determine starting player
        if (humanFirst) {
            currentPlayer = Cell.X;

        }else{
            currentPlayer = Cell.O;
        }

        int moverLeft = BOARD_SIZE * BOARD_SIZE;
        while(moverLeft > 0 ){
            // Human's turn if currentPlayer is X, Computer's turn if currentPlayer is O
            if(currentPlayer == Cell.X){
                // Human's turn:
                System.out.print("Your turn: ( row[0-2] col[0-2] ): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Check if move is valid
                if(isValidMove(row, col)){
                    board[row][col] = currentPlayer;
                    printBoard();
                    // Check if human wins
                    if(checkWinner()){
                        System.out.println("\nCongratulations, You Win!");
                        return;
                    }
                    currentPlayer = Cell.O; // Switch to Computer's turn
                    moverLeft--;
                }
                else{
                    System.out.println("Invalid move! Try again..");
                }
            }else{
                // Computer's turn:
                System.out.println("\nComputer's turn: ");
                int[] computerMove = getComputerMove();
                board[computerMove[0]][computerMove[1]] = currentPlayer;
                printBoard();
                // Check if computer wins
                if(checkWinner()){
                    System.out.println("\nComputer win! Computer win! Computer win!");
                    return;
                }
                currentPlayer = Cell.X;
                moverLeft--;
            }
        }
        System.out.println("It's a draw");
    }


    // Method to check if a move is valid
    private boolean isValidMove(int row, int col){
        return row >=0 && row <BOARD_SIZE && col >=0 && col < BOARD_SIZE && board[row][col] == Cell.E;
    }


    // Method to check if a player has won
    private boolean checkWinner(){
        for(int i = 0; i < BOARD_SIZE; i++){
            if(board[i][0] != Cell.E && board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                return true; // row check
            }
            if(board[0][i] != Cell.E && board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                return true; // column check
            }
        }
        if(board[0][0] != Cell.E && board[0][0] == board[1][1] && board[0][0] == board[2][2]){
            return true;

        }

        if(board[0][2] != Cell.E && board[0][2] == board[1][1] && board[0][2] == board[2][0]){
            return true;
        }
        return false;
    }

    // Method to get the computer's move
    private int[] getComputerMove(){
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                if(board[row][col] == Cell.E){
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }


    // Main method to start the game
    public static void main(String[] args) {
        TicTacToe_2 gameplay = new TicTacToe_2();
        gameplay.play();


    }
}