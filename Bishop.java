//Written by Austin Lee and Christina Tu

public class Bishop {
  private int row;
  private int col;
  private boolean isBlack;
  public Bishop(int row, int col, boolean isBlack){
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }
  public boolean isMoveLegal(Board board, int endRow, int endCol) {
    //Bishop moves only in diagonals
    //no distance cap except when hitting fellow pieces
    return board.verifyDiagonal(this.row,this.col,endRow,endCol);
  }
}
