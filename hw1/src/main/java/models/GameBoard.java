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
}


