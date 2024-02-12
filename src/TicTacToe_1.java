import java.util.Scanner;

// Create TicTacToe_1 Class
public class TicTacToe_1 {

    // Create enum type:
    public enum Cell{ X, O, E }
    public static void main(String[] args){
        System.out.println("\nWelcome to Tic-Tac-Toe Game!");
        System.out.println("E = Empty\n");
        //  create a empty board 3x3
        Cell[][] board = new Cell[3][3];

        // print initial board:
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                board[row][col] = Cell.E;
            }
        }
        //  initialize players:
        Cell player = Cell.X;
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        // run the while loop until the Game Over:
        // and here is the things how we are going to input elements:
        while(!gameOver){
            // First - if game is not Over print the Board
            printBoard(board);
            System.out.print("\nPlayer " + player + " enter here: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            // if board is empty input the elements:
            if(board[row][col] == Cell.E){
                board[row][col] = player;
                // check if player wins
                gameOver = haveWon(board, player);
                if(gameOver){
                    System.out.println("\nCongratulations Congratulations Congratulations !!!");
                    System.out.println("\nPlayer " + player + " has Won!");
                }
                else{
//                    if(player == Cell.X){
//                        player =Cell.O;
//                    }
//                    else {
//                        player = Cell.X;
//                    }
                    // shorter if else
                    player = (player == Cell.X) ? Cell.O : Cell.X;
                }

            }
            else {
                System.out.println("Invalid move, try again...");
            }

        }
        // call printBoard method
        printBoard(board);

    }

    public static boolean haveWon(Cell[][] board, Cell player){
        // check the rows:
        for(int row = 0; row < board.length; row++){
            if(board[row][0] == player && board[row][1] == player && board[row][2] == player ){
                return true;
            }
        }

        // check the columns:
        for(int col =  0; col < board[0].length; col++){
            if(board[0][col] == player && board[1][col] == player && board[2][col] == player){
                return true;
            }
        }

        // check the diagonals
        if(board[0][0] == player &&  board[1][1] == player && board[2][2] == player){
            return true;
        }

        if(board[0][2] == player &&  board[1][1] == player && board[2][0] == player){
            return true;
        }

        return false;
    }


    // Create printBoard method that returns empty Board
    public static void printBoard(Cell[][] board){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }
}