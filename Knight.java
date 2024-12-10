//Written by Austin Lee and Christina Tu
import java.lang.Math;
public class Knight {
  private int row;
  private int col;
  private boolean isBlack;

  public Knight(int row, int col, boolean isBlack) {
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }

  public boolean isMoveLegal(Board board, int endRow, int endCol)  {
    //knight only moves in weird L shapes
    int moveRow = this.row - endRow;
    int moveCol = this.col-endCol;
    return(Math.abs(moveRow) ==2 & Math.abs(moveCol) ==1) || (Math.abs(moveCol) == 2 && Math.abs(moveRow) ==1);
  }

}

