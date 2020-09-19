package models;

public class Move {
  public Move(int playerId, int moveX, int moveY) {
	  this.playerId = playerId;
	  this.moveX = moveX;
	  this.moveY = moveY;
  }

  private int playerId;

  private int moveX;

  private int moveY;
  

  
  public int getPlayerId() {
	  return playerId;
  }
  
  public int getMoveX() {
	    return moveX;
  }
  
  public int getMoveY() {
	    return moveY;
  }
}
