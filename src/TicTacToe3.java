import java.util.Random;
public class TicTacToe3 {
    public enum Cell { X, O, EMPTY }
    private Cell[][] board;
    private boolean humanFirst;
    public TicTacToe3(boolean humanFirst) {
        this.humanFirst = humanFirst;
        board = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
    }
    public void playGame() {
        Random random = new Random();
        boolean gameOver = false;
        boolean isHumanTurn = humanFirst;

        while (!gameOver) {
            printBoard();
            if (isHumanTurn) {
                // Human player's turn
                System.out.println("\nYour turn. Enter row (0-2) and column (0-2):");
                // Human player's move (input required)
                // For simplicity, we'll assume human inputs are always valid
                int row = random.nextInt(3);
                int col = random.nextInt(3);
                makeMove(row, col, Cell.X);
                if (isWin(row, col, Cell.X)) {
                    gameOver = true;
                    printBoard();
                    System.out.println("\nCongratulations! You win!");
                } else if (isDraw()) {
                    gameOver = true;
                    printBoard();
                    System.out.println("It's a draw!");
                } else {
                    isHumanTurn = false;
                }
            } else {
                // Computer player's turn
                System.out.println("\nComputer's turn:");
                // Computer makes a move to win if possible
                if (!makeWinningMove()) {
                    // If no winning move, make a random move
                    int compRow, compCol;
                    do {
                        compRow = random.nextInt(3);
                        compCol = random.nextInt(3);
                    } while (!isValidMove(compRow, compCol));
                    makeMove(compRow, compCol, Cell.O);
                }
                // Check if computer wins
                if (isWin(board, Cell.O)) {
                    gameOver = true;
                    printBoard();
                    System.out.println("Computer wins!");
                } else if (isDraw()) {
                    gameOver = true;
                    printBoard();
                    System.out.println("It's a draw!");
                } else {
                    isHumanTurn = true;
                }
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == Cell.EMPTY;
    }

    private void makeMove(int row, int col, Cell player) {
        board[row][col] = player;
    }

    private boolean isWin(int row, int col, Cell player) {
        // Check row
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player)
            return true;
        // Check column
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player)
            return true;
        // Check diagonal
        if (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        // Check anti-diagonal
        if (row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;
        return false;
    }

    private boolean isWin(Cell[][] board, Cell player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    private boolean makeWinningMove() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == Cell.O && board[i][1] == Cell.O && board[i][2] == Cell.EMPTY) {
                makeMove(i, 2, Cell.O);
                return true;
            }
            if (board[i][0] == Cell.O && board[i][2] == Cell.O && board[i][1] == Cell.EMPTY) {
                makeMove(i, 1, Cell.O);
                return true;
            }
            if (board[i][1] == Cell.O && board[i][2] == Cell.O && board[i][0] == Cell.EMPTY) {
                makeMove(i, 0, Cell.O);
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == Cell.O && board[1][j] == Cell.O && board[2][j] == Cell.EMPTY) {
                makeMove(2, j, Cell.O);
                return true;
            }
            if (board[0][j] == Cell.O && board[2][j] == Cell.O && board[1][j] == Cell.EMPTY) {
                makeMove(1, j, Cell.O);
                return true;
            }
            if (board[1][j] == Cell.O && board[2][j] == Cell.O && board[0][j] == Cell.EMPTY) {
                makeMove(0, j, Cell.O);
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == Cell.O && board[1][1] == Cell.O && board[2][2] == Cell.EMPTY) {
            makeMove(2, 2, Cell.O);
            return true;
        }
        if (board[0][0] == Cell.O && board[2][2] == Cell.O && board[1][1] == Cell.EMPTY) {
            makeMove(1, 1, Cell.O);
            return true;
        }
        if (board[1][1] == Cell.O && board[2][2] == Cell.O && board[0][0] == Cell.EMPTY) {
            makeMove(0, 0, Cell.O);
            return true;
        }
        if (board[0][2] == Cell.O && board[1][1] == Cell.O && board[2][0] == Cell.EMPTY) {
            makeMove(2, 0, Cell.O);
            return true;
        }
        if (board[0][2] == Cell.O && board[2][0] == Cell.O && board[1][1] == Cell.EMPTY) {
            makeMove(1, 1, Cell.O);
            return true;
        }
        if (board[1][1] == Cell.O && board[2][0] == Cell.O && board[0][2] == Cell.EMPTY) {
            makeMove(0, 2, Cell.O);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        TicTacToe3 game = new TicTacToe3(true); // Computer plays first
        game.playGame();
    }
}




