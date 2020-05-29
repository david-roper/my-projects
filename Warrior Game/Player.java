//-----------------------------------------------------------
// Assignment 4
// Written by David Roper 40131739
// For COMP 248 Section P - Fall 2019
// December  2nd, 2019
//-----------------------------------------------------------

//Welcome to player class!

/*This class has string variable for name, int variables for player level, xpos, ypos and energy. has 3 default constructors
 * which take, no input, only name or position (level, xpos, ypos). has get and set method for each variable, has a copy method. 
 * has a moveTo method to move a player to another player level, x and y. has a won method to see if player wins after reach the end 
 * of the board. And equal method to check if two players are on the same board tile/position. Has a toString method to print out
 * players name and current level and position*/
public class Player {
	private String name;
	private int Level,x,y,energy;
	
	//default player
	public Player() {
		name = "";
		Level = 0;
		x = 0;
		y = 0;
		energy = 10;
	}
	//creator that sets players name
	public Player(String n) {
		name = n;
		this.Level = 0;
		this.x = 0;
		this.y = 0;
		this.energy = 10;
	}
	//creator that sets player position
	public Player(int l,int x,int y) {
		name = "";
		Level = l;
		this.x = x;
		this.y = y;
		energy = 10;
	}
	//get and mutator methods for each variable
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	//copy method
	public Player(Player p) {
		this.name = p.name;
		this.Level = p.Level;
		this.x = p.x;
		this.y = p.y;
		this.energy = p.energy;
		
	}
	//moveTo method move player to inputed player
	public void moveTo(Player p) {
		this.x = p.x;
		this.y = p.y;
		this.Level = p.Level;
		
	}
	//check if player is on last level and last tile of board
	public boolean won(Board b) {
		if (this.x == (b.getSize()-1) && this.y == (b.getSize()-1) && this.Level == (b.getLevel()-1)) {
			return true;
		}
		return false;
	}
	
	//equals method compares player's positions
	public boolean equals(Player p) {
		return(this.x == p.x && this.y == p.y && this.Level == p.Level);
	}
	//compare all variable in player class to see if equal
	public boolean tEquals(Player p) {
		return(this.x == p.x && this.y == p.y && this.Level == p.Level && this.name.equalsIgnoreCase(p.name));
	}
	
	//to string method
	public String toString() {
		return(name + " is on level "+ Level+ " at location ("+x+","+y+") and has "+ energy + " units of energy.");
	}
}
