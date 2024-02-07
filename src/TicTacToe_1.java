import java.util.Scanner;

public class TicTacToe_1 {
    public enum Cell { X, O, EMPTY }

    public static void main(String[] args) {
        System.out.println("TIC TAC TOE GAME ");

        // Create 3x3 2d dimensional array, so that we are initializing the empty Board
        Cell[][] board = new Cell[3][3];
        for(int row=0; row<board.length; row++){
            for(int col=0; col< board[row].length; col++ ){
                board[row][col] = Cell.EMPTY;
            }
        }

        // Initialize the Player
        Cell player  = Cell.X;
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        // here is the things how we could put the elements in the Board
        while(!gameOver){
            printBoard(board);
            System.out.print("Player " + player + " enter: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if(board[row][col] == Cell.EMPTY){
                board[row][col] = player; // place the element
                gameOver = haveWon(board, player);
                if(gameOver){
                    System.out.println("\nPlayer " + player + " has Won: ");
                    System.out.println("Congratulations you Won.....!");
                }
                else{
                    // otherwise the gamer is switched
                    player = (player == Cell.X) ? Cell.O : Cell.X;
                }
            }else{
                System.out.println("Invalid move, please try again!");
            }

        }
        printBoard(board);

    }

    public static boolean haveWon(Cell[][] board, Cell player){
        // check the rows;
        for(int row = 0; row < board.length; row++){
            if(board[row][0] == player && board[row][1] == player && board[row][2] == player){
                return true;
            }
        }

        // check the columns;
        for(int col = 0; col < board.length; col++){
            if(board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // check the diagonals
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }
        if(board[0][2] == player && board[1][1] == player && board[2][0] == player){
            return true;
        }

        return false;
    }

    public static void printBoard(Cell[][] board ){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }

    }
}
