//Written by Austin Lee and Christina Tu
public class Queen {
  private int row;
  private int col;
  private boolean isBlack;
  public Queen(int row, int col, boolean isBlack){
    this.row = row;
    this.col = col;
    this.isBlack= isBlack;
  }
  public boolean isMoveLegal(Board board, int endRow, int endCol) {
    //queen can go forward backwards and diagonal
    return (board.verifyVertical(this.row,this.col,endRow,endCol)) || (board.verifyHorizontal(this.row,this.col,endRow,endCol) || board.verifyDiagonal(this.row,this.col,endRow,endCol));
  }
}
