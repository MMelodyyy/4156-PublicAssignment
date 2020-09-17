package models;

public class Player {
	public Player(char type, int id) {
		this.type = type;
		this.id = id;			
	}

  private char type;

  private int id;
  
  public char getType() {
	    return type;
	  }

}
