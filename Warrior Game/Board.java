
//-----------------------------------------------------------
// Assignment 4
// Written by David Roper 40131739
// For COMP 248 Section P - Fall 2019
// December  2nd, 2019
//-----------------------------------------------------------

//Welcome to the Board Class!

/*This is class is used to create the board for the warrior game, it has the variables for number of levels, size of board,
 * and public integers to create a board using user input. Uses create board function to make board after a new board class is
 * created. The function creates a 3D matrix using the dimensions of level, size for x and then size for y. Creates the
 * board using nested for loops and chooses which tiles to put energy gain/lose values, it takes the sum of the dimensions of
 * the tiles specific position and does modulus of it to determine its energy gain/lose value. The class has get methods for
 * level and size also a getEnergyAdj which takes values for specific level, x-pos and y-pos and return the energy
 * value of that tile. The toString method returns the board as a printed matrix with level # and x-y matrix  */

public class Board {
	private int board[][][];
	private static int MIN_LEVEL;
	private static int MIN_SIZE;
	private int level;
	private int size;
	public int L;
	public int x;

	//default board level 3, size 4
	public Board() {
		level = 3;
		size = 4;
		createBoard(level,size);
	}
	
	//create board with two integer variables
	public Board(int L,int x) {
		level = L;
		size = x;
		createBoard(level, size);
	}
	//create board method create board with matrix by level*size*size dimensions
	private void createBoard(int level, int size) {
		//create new board variable 
		board = new int[level][size][size];
		
		//nested for loops to set board tile values
		for (int L = 0; L < level; L++) {
			
			for(int x = 0;x < size; x++) {
				
				for(int y = 0; y < size;y++) {
					//set tiles energy values, algorithm based off the sum of each dimension 
					//and if its divisible by 3,5 or 7.
					if(L ==0 && x == 0 && y ==0) {
						board[L][x][y]=0;
						continue;
					}
					if ((L+x+y)%3 ==0) {
						board[L][x][y]= -3;
						
					}
					else if((L+x+y)%5 == 0) {
						board[L][x][y]= -2;
					}
					else if ((L+x+y)%7 ==0) {
						board[L][x][y]= 2;
					}
					else {
						board[L][x][y]= 0;
					}
				}
			}
		}
		
		
	}
	//get methods
	public int getLevel() {
		return level;
	}

	public int getSize() {
		return size;
	}
	
	//get method that returns the energy value of that tile.
	public int getEnergyAdj(int L, int x, int y) {
		return board[L][x][y];
		
	}
	//to string method returns each part of the boards dimensions as a matrix
	public String toString() {
		String ts = "";
		
		for (int L = 0; L < level; L++) {
			
			ts += ("\nLevel "+ L + "\n");
			
			for(int x = 0;x < size; x++) {
				
				ts += "\n";
				
				for(int y = 0; y < size;y++) {
					
					ts += (board[L][x][y] + "\t");
				}
				}
			ts += "\n";
			}
		return ts;
	}

	
	
}


