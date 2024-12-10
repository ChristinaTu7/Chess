//Written by Christina Tu and Austin Lee
public class Board {

    // Instance variables
    private final Piece[][] board;

    public Board() {
        board = new Piece[8][8]; //8x8 chess board
    }

    // Accessor Methods

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
        board[row][col].setPosition(row, col);

    }

    public boolean pieceExist(int row, int col) {
        return board[row][col] != null;
    }


    // Game functionality methods

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {

        //Check move legality before we move
        if (getPiece(startRow, startCol).isMoveLegal(this, endRow, endCol)) {

            //setPiece in new position and set old position to null; since the piece is not there anymore.
            setPiece(endRow, endCol, getPiece(startRow, startCol));
            board[startRow][startCol] = null;
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        /*
        -Iterate through the whole board
        -Check each index to see if it's a king
        -If it finds a king, {color}Captured = false (since it found it)
         */

        boolean blackCaptured = true;
        boolean whiteCaptured = true;
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (board[row][col] != null) {
                    if (board[row][col].getCharacter() == ('\u265a')) { //black king
                        blackCaptured = false;
                    }
                    if (board[row][col].getCharacter() == ('\u2654')) { //white king
                        whiteCaptured = false;
                    }
                }
            }
        }
        return blackCaptured || whiteCaptured;
    }

    public String toString() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append("    ");
        for (int i = 0; i < 8; i++) {
            chessBoard.append(i).append("   ");
        }
        chessBoard.append("\n");
        for (int row = 0; row < 8; row++) {
            chessBoard.append(row).append(" | ");
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null) {
                    chessBoard.append(" ").append(" | ");
                } else {
                    chessBoard.append(board[row][col]).append(" | ");
                }

            }
            chessBoard.append("\n");
        }

        return chessBoard.toString();
    }

    public void clear() {
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                board[row][col] = null;
            }
        }
    }

    // Movement helper functions

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {

        //check to see if both row and col (start and end)  are in bounds.
        if (startRow > board.length || startRow < 0 || startCol > board[0].length || startCol < 0) {
            return false;
        }
        if (endRow > board.length || endRow < 0 || endCol > board[0].length || endCol < 0) {
            return false;
        }

        //check to see if starting position is not empty, and it has matching colors.
        if (board[startRow][startCol] != null && board[startRow][startCol].getIsBlack() == isBlack) {
            //now check if the destination is empty or contains a piece of the opposite color.
            return board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack;
        }

        return false;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int distanceBetweenRows = endRow - startRow;
        int distanceBetweenCols = endCol - startCol;

        //As long as the distance between rows or cols is 1; the move is legal.
        if ((Math.abs(distanceBetweenRows) > 1 || Math.abs(distanceBetweenCols) > 1)) {
            return false;
        }

        return true;
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        int currCol = startCol;
        boolean sameRow = startRow == endRow;
        boolean isStartColLessThanEnd = startCol < endCol;

        //base case
        if (!sameRow) {
            return false;
        }

        //iterate until currCol is equal to endCol
        while (currCol != endCol) {
            if (isStartColLessThanEnd) { //moves currCol to the right of the board
                currCol++;
            } else { //moves currColl to the left of the board
                currCol--;
            }

            if (board[startRow][currCol] != null && currCol != endCol) { //check for piece
                return false;
            }
        }

        return true;
    }

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        int currRow = startRow;
        boolean sameCol = startCol == endCol;
        boolean isStartRowLessThanEnd = startRow < endRow;

        //base case
        if (!sameCol) {
            return false;
        }

        //iterate until currRow is equal to endRow
        while (currRow != endRow) {
            if (isStartRowLessThanEnd) { //moves currRow down the board
                currRow++;
            } else { //moves currRow up the board
                currRow--;
            }

            if (board[currRow][startCol] != null && currRow != endRow) {
                return false;
            }
        }

        return true;
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        //using these aa markers to check each square
        int currRow = startRow;
        int currCol = startCol;
        int diffRow = endRow - startRow;
        int diffCol = endCol - startCol;
        boolean isStartRowLessThanEnd = startRow < endRow;
        boolean isStartColLessThanEnd = startCol < endCol;

        //Iterate until currRow and currCol are equal to endRow and endCol
        if (Math.abs(diffRow) != Math.abs(diffCol)) {
            return false;

        }
        while (currRow != endRow && currCol != endCol) {

            /*
            If our startRow < than endRow, we want to increment start row towards the endRow and de-increment if its >.
            When we do this, we can check each square on the diagonal on the way to the endRow.
            This concept applies for the col as well.
            */
            if (isStartRowLessThanEnd) {
                currRow++;
            } else {
                currRow--;
            }

            if (isStartColLessThanEnd) {
                currCol++;
            } else {
                currCol--;
            }

            //now lets check the square to see if there is a piece with our new positions
            if (board[currRow][currCol] != null && currRow != endRow && currCol != endCol) {
                return false;
            }
        }
        return true;
    }
}
