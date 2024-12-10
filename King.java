//Written by Austin Lee and Christina Tu
public class King {
  private int row;
  private int col;
  private boolean isBlack;

  public King(int row, int col, boolean isBlack) {
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }

  public boolean isMoveLegal(Board board, int endRow, int endCol) {
    //king only moves one square in any direction around it
    return (board.verifyAdjacent(this.row, this.col, endRow, endCol));
  }
}

