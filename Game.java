//Written by Christina Tu and Austin Lee
import java.util.Scanner;

public class Game {

  public static void main(String[] args) {
    Board board = new Board();
    Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
    System.out.println(board);
    System.out.println("Welcome player!");


    boolean gameInProgress = true;
    boolean whiteTurn = true;
    Scanner scan = new Scanner(System.in);

    while (gameInProgress) {

      //check for game over state
      if (board.isGameOver()) {
        System.out.println("Game Over!");
        gameInProgress = false;
        break;
      }

      if (whiteTurn) { //white's turn
        //gather user inputs
        System.out.println("It is currently white's turn");
        System.out.println("What is your move? (Format: [startRow] [startCol] [endRow] [endCol])\n");
        String user = scan.nextLine();

        String[] numbers = user.split(" ");

        //store input into variables
        int startRow = Integer.parseInt(numbers[0]);
        int startCol = Integer.parseInt(numbers[1]);
        int endRow = Integer.parseInt(numbers[2]);
        int endCol = Integer.parseInt(numbers[3]);


        //check color
        Piece piece = board.getPiece(startRow, startCol);
        if (piece.getIsBlack()) {
          System.out.println("Wrong color! It's white's turn");
        }

        // check move legality and move
        else if (board.movePiece(startRow, startCol, endRow, endCol)) {

          //let's check for pawn promotion
          //check to see if it's a pawn
          piece = board.getPiece(endRow, endCol);
          if (piece.canPromote()) {

            //get user input
            System.out.println("Pawn Promotion for white! Choose a piece: ");
            System.out.println("Type one: 'queen' 'knight' 'rook' 'bishop'");
            user = scan.nextLine();

            //promote!
            piece.promotePawn(user, board, endRow, endCol, false);
          }

          //change turn
          whiteTurn = false;

        } else {
          System.out.println("Sorry! That's an illegal move");
        }

      } else { // Black's turn
        //gather user input
        System.out.println("It is currently black's turn");
        System.out.println("What is your move? (Format: [startRow] [startCol] [endRow] [endCol])\n");
        String user = scan.nextLine();
        String[] numbers = user.split(" ");


        //store input into variables
        int startRow = Integer.parseInt(numbers[0]);
        int startCol = Integer.parseInt(numbers[1]);
        int endRow = Integer.parseInt(numbers[2]);
        int endCol = Integer.parseInt(numbers[3]);

        //variable for simplicity
        Piece piece = board.getPiece(startRow, startCol);

        //check color
        if (!piece.getIsBlack()) {
          System.out.println("Wrong color! It is black's turn");
        }

        // check move legality and move
        else if (board.movePiece(startRow, startCol, endRow, endCol)) {

          //let's check for pawn promotion
          //check to see if it's a pawn
          piece = board.getPiece(endRow, endCol);
          if (piece.canPromote()) {

            //get user input
            System.out.println("Pawn Promotion for black! Choose a piece: ");
            System.out.println("Type one: 'queen' 'knight' 'rook' 'bishop'");
            user = scan.nextLine();

            //promote!
            piece.promotePawn(user, board, endRow, endCol, true);
          }

          //change turn
          whiteTurn = true;


        } else {
          System.out.println("Sorry! That's an illegal move\n");
        }

      }

      //update board
      System.out.println(board);
    }
  }
}
