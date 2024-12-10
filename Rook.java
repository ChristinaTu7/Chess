//Written by Austin Lee and Christina Tu
public class Rook {

  private int row;
  private int col;
  private boolean isBlack;

  public Rook(int row, int col, boolean isBlack) {
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }

  public boolean isMoveLegal(Board board, int endRow, int endCol) {
    //Rook moves horizontally or vertically
    //no distance cap except when hitting fellow pieces
    return (board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(
        this.row, this.col, endRow, endCol));
  }
}