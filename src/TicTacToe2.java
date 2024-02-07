import java.util.Scanner;

public class TicTacToe2 {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private static final int BOARD_SIZE = 3;
    private char[][] board;
    private char currentPlayer;
    public TicTacToe2() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X; // Player X starts by default
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col]);
                if (col < BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < BOARD_SIZE - 1) {
                System.out.println("---------");
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");

        // Ask the player if they want to go first or second
        System.out.print("Do you want to go first? (Y/N): ");
        char choice = scanner.next().toUpperCase().charAt(0);
        boolean humanFirst = (choice == 'Y');

        if (!humanFirst) {
            currentPlayer = PLAYER_O; // Computer starts if human doesn't want to go first
        }

        int movesLeft = BOARD_SIZE * BOARD_SIZE;

        while (movesLeft > 0) {
            if (currentPlayer == PLAYER_X && humanFirst || currentPlayer == PLAYER_O && !humanFirst) {
                // Human's turn
                System.out.println("\nYour turn (row[1-3] col[1-3]): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    printBoard();
                    if (checkWinner()) {
                        System.out.println("Congratulations! You win!");
                        return;
                    }
                    currentPlayer = currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
                    movesLeft--;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } else {
                // Computer's turn
                System.out.println("\nComputer's turn:");
                int[] computerMove = getComputerMove();
                board[computerMove[0]][computerMove[1]] = currentPlayer;
                printBoard();
                if (checkWinner()) {
                    System.out.println("Computer wins!");
                    return;
                }
                currentPlayer = currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
                movesLeft--;
            }
        }

        System.out.println("It's a draw!");
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY;
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true; // Row check
            }
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return true; // Column check
            }
        }
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true; // Diagonal check (top-left to bottom-right)
        }
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true; // Diagonal check (top-right to bottom-left)
        }
        return false;
    }

    private int[] getComputerMove() {
        // Simple strategy: Look for an empty cell and return its coordinates
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // No empty cell found (shouldn't happen in a valid game)
    }

    public static void main(String[] args) {
        TicTacToe2 game = new TicTacToe2();
        game.play();
    }
}
