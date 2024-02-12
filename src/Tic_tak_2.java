import java.util.Scanner;

public class Tic_tak_2 {


    public enum Cell{
        X, O, E
    }
    public static void main(String[] args){
        //  create a empty board 3x3
        Cell[][] board = new Cell[3][3];
        // print the board using for loop
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
        // and here is the things how we gonna input elements:
        while(!gameOver){
            printBoard(board);
            System.out.print("Player " + player + " enter here: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if(board[row][col] == Cell.E){
                board[row][col] = player;
                gameOver = haveWon(board, player);
                if(gameOver){
                    System.out.println("Congratulations !!!");
                    System.out.println("\nPlayer " + player + " has Won!");
                }
                else{
//                    if(player == Cell.X){
//                        player =Cell.O;
//                    }
//                    else {
//                        player = Cell.X;
//                    }
                    player = (player == Cell.X) ? Cell.O : Cell.X;
                }

            }
            else {
                System.out.println("Invalid move, try again...");
            }

        }
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


    public static void printBoard(Cell[][] board){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }
}

