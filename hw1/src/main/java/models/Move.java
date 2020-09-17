package models;

public class Move {
  public Move(Player player, int moveX, int moveY) {
	  this.player = player;
	  this.moveX = moveX;
	  this.moveY = moveY;
  }

  private Player player;

  private int moveX;

  private int moveY;
  
  public Player getPlayer() {
	  return player;
  }
  
  public int getPlayerId() {
	  return getPlayer().getPlayerId();
  }
  
  public int getMoveX() {
	    return moveX;
  }
  
  public int getMoveY() {
	    return moveY;
  }
}
