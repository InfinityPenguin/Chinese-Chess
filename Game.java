class Game {
  private static final int COLS = 9;
  private static final int ROWS = 10;
  private Player p1;
  private Player p2;
  private Piece board[COLS][ROWS];
  
  Game() {
    for (int i = 0; i < COLS; i++) {
      for (int j = 0; j < ROWS; j++) {
        board[i][j] = null;
      }
    }
    // create all Pieces and put them on the board in the right places
  }
  
  void play() {
  }
  
  void victory() {
  }
}
