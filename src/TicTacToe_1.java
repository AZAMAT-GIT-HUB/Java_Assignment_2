import java.util.Scanner;

public class TicTacToe_1 {
    public enum Cell {X, O, E}

    private static final int BOARD_SIZE = 3;
    Cell[][] board;
    Cell currentPlayer;

    public TicTacToe_1() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = Cell.X;
        intialBoard();

    }
    public void intialBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = Cell.E;
            }
        }
    }
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
    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to Tic-Tac-Toe Game!");
        System.out.println("E = Empty\n");

        System.out.print("Do you want to play first? (Y/N): ");
        char choice = scanner.next().toUpperCase().charAt(0);
        boolean humanFirst = (choice == 'Y');


        if (humanFirst) {
            currentPlayer = Cell.X;

        }else{
            currentPlayer = Cell.O;
        }

        int moverLeft = BOARD_SIZE * BOARD_SIZE;
        while(moverLeft > 0 ){
            if(currentPlayer == Cell.X){
                // Human's turn:
                System.out.print("Your turn: ( row[0-2] col[0-2] ): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if(isValidMove(row, col)){
                    board[row][col] = currentPlayer;
                    printBoard();
                    if(checkWinner()){
                        System.out.println("\nCongratulations, You Win!");
                        return;
                    }
                    currentPlayer = Cell.O;
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

    private boolean isValidMove(int row, int col){
        return row >=0 && row <BOARD_SIZE && col >=0 && col < BOARD_SIZE && board[row][col] == Cell.E;
    }

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


    public static void main(String[] args) {
        TicTacToe_1 gameplay = new TicTacToe_1();
        gameplay.play();


    }
}
