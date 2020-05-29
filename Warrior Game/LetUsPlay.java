//-----------------------------------------------------------
// Assignment 4
// Written by David Roper 40131739
// For COMP 248 Section P - Fall 2019
// December  2nd, 2019
//-----------------------------------------------------------

//Welcome to driver class LetUsPlay of Warrior Game!

/*This Driver class initiates the warrior game and allows the user the play the game. The first set is to determine the board
 * size through user input, using the java scanner the user can either choose the default board with 3 levels and a
 * 4x4 size or can choose to make their own board size, which can have between 3 to 10 levels and 4x4 to 10x10 size
 * if the user input are not between these parameters, the program will print an error and ask for the input again, it does this using a 
 * while loop, with continues and breaks once the inputs are correct. after the user input the driver uses the inputed 
 * parameters to create the board using the board class using said parameters for level and size. after the board is
 * created the drive prints it out to show to the user. The driver then asks the user to input the names for the two
 * players. the driver than initializes to player classes p0 and p1 with the two names. the drive class also create a
 * array of player type with a size of 2 two put the two player classes in. then using java random the driver puts
 * the players into the array in random order, whichever player is first in this array goes first in the game.
 * The drive then uses a for loop nest in a constant while loop to play the game. The for loop goes through the player
 * array players move once at a time. within the for loop the players energy is checked, if they have less than zero
 * energy, the dice is rolled 3 times using rolldice() and if the player gets a double their energy is increased by two
 * to max energy gain of 6. if they still don't have enough energy, the player cannot move and their turn is skipped. this
 * is done using if statements. two more player classes are also created, pm (predicted move) and place. pm is
 * used to calculate the players future move to see if its off the board or causes a challenge. place is equal the
 * the player who went before, its used for the challenge part of the game. if the player has enough energy then they
 * roll the dice, using the rolldice() method. Their movement across the x-y dimension of the board is than
 * calculated as their predicted move, if the x or y value of the predicted move is greater than size, it is recalculated
 * and the player goes up a level. if the predicted move is above the board levels, than the player stays where they were and
 * loses 2 energy (this is done with setEnergy() and getEnergy()). If the player is about to land on the other players 
 * position, a challenge is happens, which the player can accept or forfeit. If the latter they will move to that pos
 * but a level lower, if one level 0 they will go back to (0,0). if they accept, the program chooses a random int,
 * from 1 to 10, if its more than 6 player loses the challenge and gives half of their energy to the other player,
 * using getEnergy() and setEnergy(). if its less than 6 than the player wins the challenge, move to their predicted move
 * and the other player moves to their old position, thus swapping places using the moveTo() method. after the player
 * reached moves to their predicted move their energy is adjusted using the getEnergyAdj() of the board if the
 * player did not move the adjustment is not taken. the driver then checks the player won the game using the Won() method
 * which outputs true if the player is at the last tile of the board (size,size) and last level. Their position is also
 * save in the place class so their position can be compared to for the challenge during the next players turn. After the for loop is exited
 * a the summation of what happened during the round is printed and if a player won, the loop is broken and the winning player 
 * is congratulated. The program waits for user input before proceeding to the next round.*/

//import randomizer and scanner
import java.util.Random;
import java.util.Scanner;

//drive class for warrior game, called LetUsPlay
public class LetUsPlay {

	public static void main(String[] args) {
		//welcome message
		System.out.println("\t*************************************************\n"+
				"\t*\t\t\t\t\t\t*\n"+
				"\t*       Welcome to Nancy's Warrior Game!\t*\n"+
				"\t*\t\t\t\t\t\t*\n"+
				"\t*************************************************\n\n");



		//scanner and random variables
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		
		System.out.println("The default game board has 3 levels and each level has a 4x4 board");
		System.out.println("You can use this default board size or change the size"+
				"\n 0 to choose default size\n -1 to enter your own size\n ==> What do you want to do? ");
		//int variables for who goes first and user input
		int op = 0;
		int first = 0;
		
		boolean winner = false;


		//variables for board and dice
		Board b1 = new Board();
		Dice die = new Dice();
		


		//player variables 
		Player p0 = new Player();
		Player p1 = new Player();
		
		//pm (predicted move) where the player will move to, using moveTo method
		//place saves other players position, used to compare player position even if its not their turn
		Player pm = new Player();
		Player place = new Player(0,0,1);
		
		//set as winning player at end of game
		Player wp = new Player();

		//set array for player variables
		Player pArray[] = new Player[2];
		op = input.nextInt();
		
		//ask for user input for board
		while( true){
			if (op == 0 ) {
				System.out.println("Your 3D board has been set up and looks like this:");
				System.out.println(b1);
				
				break;
			}
			else if(op == -1) {
				//variables for user input for size and level
				int bsize = 0;
				int lev = 0;

				System.out.print("How many levels would you like? (minimum size 3, max 10)");
				
				//assign user input to level
				lev = input.nextInt();

				System.out.println();
				
				
				while(true) {
					//check if user input between 3 and 10
					if(lev < 3 || lev >10) {
						//ask user for level input again
						System.out.println("Sorry but "+ lev +" is not a legal choice. ");
						lev = input.nextInt();
						continue;
					}

					System.out.println("What size do you want n x n board to be (4x4 min 10x10 max");
					System.out.print("Enter value of size ==> ");
					
					//user input for size
					bsize = input.nextInt();
					System.out.println();
					
					//ask for user to choose level and size, check if valid with while loop
					while (true) {
						//board size has to be between 4 and 10
						if (bsize<4 || bsize>10) {
							//ask for size again
							System.out.println("Sorry but "+ bsize +" is not a legal choice. ");
							bsize = input.nextInt();
							continue;
						}
						//create board and print for user
						System.out.println("Your 3D board has been set up and looks like this:");
						b1 = new Board(lev,bsize);
						System.out.println(b1);
						break;
					}
					break;
				}
				break;
			}

			else {
				//error message
				System.out.println("Sorry but "+ op +" is not a legal choice. ");
				op = input.nextInt();
				continue;
			}
		}
		//now create players asking user for names uses setName() method
		System.out.print("What is player 0's name (one word only):");
		String Name = input.next();
		p0.setName(Name);

		System.out.print("What is player 1's name (one word only):");
		Name = input.next();
		p1.setName(Name);
		
		//determine order of player turns with random value
		first = random.nextInt((1)+1);
		
		//select turn order of players, depends on value of first value who goes first
		if (first == 0) {
			System.out.println("\nThe game has started "+ p0.getName()  +" goes first \n==========================\n");
			pArray[0] = p0;
			pArray[1] = p1;
		}
		else {
			System.out.println("\nThe game has started "+ p1.getName()  +" goes first \n==========================\n");
			pArray[0] = p1;
			pArray[1] = p0;
		}
		
		//game starts in a while loop, a for loop to for each player is nested within it
		while (true){
			//for loop to go through each player and their turn
			for(int i = 0; i<2; i++) {
				
				//int variables for move and the new x and new y position of the player
				int move = 0;
				int nX,nY =0;
				//print who's turn it is
				System.out.println("It is " + pArray[i].getName()+"'s turn");
				
				//check if the energy is at zero
				if(pArray[i].getEnergy()<=0) {

					//variable for energy gain
					int eGain = 0;

					//roll dice 3 times, doubles gain energy, 2 for each for a max of 6 energy gained
					for(int j = 0; j<3; j++) {
						die.rollDice();
						if(die.isDouble()) {
							eGain +=2;
						}
					}
					//if energy gajned is more than 6 set it to 6 max 
					if(eGain > 6) {
						eGain = 6;
					}
					//give player the energy
					pArray[i].setEnergy(eGain);
				}
				
				//check if player still has 0 energy, if so print player is too weak to move
				if(pArray[i].getEnergy()<=0) {
					System.out.println(pArray[i].getName()+" is too weak to move (not enough energy)!");
				}
				//else they have enough energy they can move
				else {
					
					//set variable move to the total/sum of dice
					move = die.rollDice();
					
					//print the values of the dice
					System.out.println("\t"+pArray[i].getName()+" you rolled Die1: "+die.getDie1()+" Die2: "+ die.getDie2());
					
					//add to player energy if they roll a double
					if (die.isDouble()) {
						pArray[i].setEnergy(pArray[i].getEnergy()+2);
						System.out.println("\tCongratulations you rolled a double "+ die.getDie1()+". Your energy went up 2 units");

					}
					
					//original set player move variable to player original position
					pm.moveTo(pArray[i]);
					
					//calculate new X (old pos + dicetotal/size) position and Y (old pos + dicetotal%size) position
					nX = pm.getX()+(move/b1.getSize());
					nY = pm.getY() + (move%b1.getSize());

					
					//if y-pos is off board recalculate, the position and player gains a level
					if(nY > (b1.getSize()-1)) {

						nX = nX +  nY/b1.getSize();
						nY = nY%b1.getSize();
						nX = nX%b1.getSize();
						
						//set predicted move and +1 level
						pm.setX(nX);
						pm.setY(nY);
						pm.setLevel(pm.getLevel()+1);



					}
					//if nX goes off board recalculate position and gain a level
					else if (nX > (b1.getSize()-1)) {
						
						//the recalculate new x (new x%size)
						nX = nX%(b1.getSize());
						
						//set predicted move and +1 level
						pm.setX(nX);
						pm.setY(nY);
						pm.setLevel(pm.getLevel()+1);

					}
					else {
						//set predicted move at this new position
						pm.setX(nX);
						pm.setY(nY);
					}
					//if the player is about to move off board, they stay at their original pos and lose 2 energy

					if (pm.getLevel() > (b1.getLevel()-1)) {
						pm.moveTo(pArray[i]);
						pArray[i].setEnergy(pArray[i].getEnergy()-2);
						System.out.println("!!! sorry you stay where you are, that put you off the grid and you lose 2 energy points.");
					}
					
					//challenge
					//see if the predicted move will land on the player who just went
					//the player cannot be compared to itself to start challenge. (example if other player couldn't move)
					if(pm.equals(place)&& !(pArray[i].tEquals(place))&&!(pArray[i].equals(place)) ) {
						//int for challenge option
						int cOp = 0;
						System.out.println("Player "+ p1.getName()+" is at your location\n"
								+ "What do you want to do?\n"
								+ "0 - challenge and risk loosing 50% of your energy if you loose \nor move to new location and get 50"
								+ "% of the other players energy untis\n"
								+ "1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy");
						cOp = input.nextInt();//user input
						
						//ask for user choice to challenge or forfeit
						while (true) {
							if (cOp == 0) {
								int chal = random.nextInt(10);
								//lose challenge, lose half of your energy
								if (chal > 6) {
									System.out.println("You lost the challenge!");
									pArray[i].setEnergy(pArray[i].getEnergy()/2);

								}
								//win challenge steal half of other players energy and swap places!
								else {
									System.out.println("Congrats! You won the challenge!");
									//if the player that challenged is p0 they take 1/2 of p1 energy
									if(pArray[i].equals(p0)) {
										pArray[i].setEnergy(pArray[i].getEnergy()+(p1.getEnergy()/2));
										p1.setEnergy(p1.getEnergy()/2);
										p1.moveTo(pArray[i]);
										pArray[i].moveTo(pm);
									}
									//p1 is the challenger and takes 1/2 of p0 energy.
									else {
										pArray[i].setEnergy(pArray[i].getEnergy()+(p0.getEnergy()/2));
										p0.setEnergy(p0.getEnergy()/2);
										p0.moveTo(pArray[i]);
										pArray[i].moveTo(pm);
									}
								}
								break;
							}
							//forfeit
							else if (cOp == 1) {
								//current player goes to (0,0) if on level 0 lose 2 energy
								if(pArray[i].getLevel() == 0) {
									//set player to (0,0)
									pArray[i].setX(0);
									pArray[i].setY(0);
									pArray[i].setEnergy(pArray[i].getEnergy()-2);
								}
								//move down a level
								else {
									//move player to predicted move
									pArray[i].moveTo(pm);
									pArray[i].setEnergy(pArray[i].getEnergy()-2);
									pArray[i].setLevel(pm.getLevel()-1);
								}
								break;
							}
							else {
								//0 or 1 not inputed
								System.out.println("input error");
								continue;
							}
						}	
					}
					else {
						//if user didn't stay in the same place
						if(!pArray[i].equals(pm)) {
							
							//move player and adjust their energy
							pArray[i].moveTo(pm);
							
							System.out.println("\tYour energy is adjusted by "+
									b1.getEnergyAdj(pArray[i].getLevel(), pArray[i].getX(), pArray[i].getY())+" for landing at ("
									+ pArray[i].getX()+","+pArray[i].getY()+") at level " + pArray[i].getLevel());

							pArray[i].setEnergy(pArray[i].getEnergy()+b1.getEnergyAdj(pArray[i].getLevel(), pArray[i].getX(), pArray[i].getY()));
						}
					}
					//check if player wins
					if(pArray[i].won(b1)) {
						wp = pArray[i];
						winner = true;
					}
					
					//move place player to the position of the player who's turn just ended
					//set place player name to the player who just played
					place.moveTo(pArray[i]);
					place.setName(pArray[i].getName());

				}
			}
			//end of round summary
			System.out.println("\nAt the end of this round:\n\t"
					+ pArray[0]+"\n\t"+pArray[1]+"\n");
			
			//break out of loop if there is winner, declare winner
			if(winner) {
				System.out.println("\n\nThe winner is "+ wp.getName()+". Well done! Thank you for playing!");
				break;
			}
			//ask for user input before next round, to make sure loop stops for an instance
			System.out.println("Press any key to continue to next round");
			String op2 = input.next();


		}
		input.close();



	}


}
