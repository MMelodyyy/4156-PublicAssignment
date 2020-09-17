package models;

public class GameBoard {
	public GameBoard(char type) {
		this.p1 = new Player(type, 1); // not null but Player
	    this.p2 = null; // set to be null 
		this.gameStarted = false;
		this.turn = type == 'X' ? 1 : 2;
		this.boardState = new char[][]{{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
		this.winner = 0;
		this.isDraw = false;
		
	}

  private Player p1;

  private Player p2;

  private boolean gameStarted;

  private int turn;

  private char[][] boardState;

  private int winner;

  private boolean isDraw;
  
  public void p2Join() {
	    char type = '0';
	    if (this.p1.getType() == 'X') {
	      type = 'O';
	    } else {
	      type = 'X';
	    }
	    p2 = new Player(type, 2);
	    gameStarted = true;
	  }
  

  public Message Moveprocess(Move move) {
	  int x = move.getMoveX();
	  int y = move.getMoveY();
	  int playerId = move.getPlayerId();
	  
	  if (!gameStarted) {
		  return new Message(false,200,"No game start!");
	  }
	  if (winner != 0) {
		  return new Message(false,200,"Game is already over!");
	  }
	  if (playerId != turn) {
		  return new Message(false,200,"Not your turn!");
	  }
	  if (x > 2 || x < 0 || y > 2 || y < 0) {
		  return new Message(false,200,"Go out of the borad!");
	  }
	  if (this.boardState[x][y] != 0) {
		  return new Message(false,200,"Can not move here!");
	  }
	  
	  char type = '\0';
	  if (playerId == 1) {
		  type = p1.getType();
		  turn = 2;
	  }else {
		  type = p2.getType();
		  turn = 1;
	  }
	  boardState[x][y] = type;
	  checkstate();  
	  
	  return new Message(true,100,"Move success");
  }
  
  // check if there is a winner
  public void checkstate() {
	  char wintype = '\0';
	  int dia1 = 0, dia2 = 0;
	  int[] row = new int[3], col = new int[3];	  
	  boolean pending = false;
	  for (int i = 0; i < 3; i++) {
		  for (int j = 0; j < 3; j++) {
			  int offset = 0;
			  if (boardState[i][j] == 'O') offset = 1;
			  else if (boardState[i][j] == 'X') offset = -1;
			  else pending = true;
			  row[i] += offset;
			  col[j] += offset;
			  if (i == j) dia1 += offset;
			  if (i + j == 2) dia2 += offset;
		  
		  }
	  }
	  if (dia1 == 3 || dia2 == 3) wintype = 'O';
	  if (dia1 == -3 || dia2 == -3) wintype = 'X';
	  
	  for (int k = 0; k < 3; k++) {
		  if (row[k] == 3 || col[k] == 3) wintype = 'O';
		  else if (row[k] == -3 || col[k] == -3) wintype = 'X';
	  }
	  
	  if (!pending) isDraw = false;
	  if (p1.getType() == wintype) winner = 1;
	  else winner = 2;
	  return;
  }
}




