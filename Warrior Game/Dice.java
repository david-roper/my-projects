//-----------------------------------------------------------
// Assignment 4
// Written by David Roper 40131739
// For COMP 248 Section P - Fall 2019
// December  2nd, 2019
//-----------------------------------------------------------

//welcome to dice class

/*Dice class has private int die1, die2 as game is played with two dice. default constructor set each die to have 6 sides
 * get method for each die value, has a rolldice method which rolls dice using java randomizer, each can give values 1-6.
 * returns the sum of the dice. has bollean isdouble method, returns truth value of if the dice have the same value.*/

//import random
import java.util.Random;

public class Dice {
	private int die1, die2;
	
	//constructor default # of sides is 6
	public Dice() {
		die1 = 6;
		die2 = 6;
	}
	//get methods to retrieve values of die1 or die2
	public int getDie1() {
		return die1;
	}
	public int getDie2() {
		return die2;
	}
	//roll dice method return int total of the 2 dice
	public int rollDice() {
		Random rand = new Random();
		
		this.die1 = rand.nextInt(6)+1;
		this.die2 = rand.nextInt(6)+1;
		
		return(this.die1+this.die2);
		
		
	}
	//check if the two dice values are the same
	public boolean isDouble() {
		return (this.die1==this.die2);
		
		
	}
	@Override
	//prints dice values
	public String toString() {
		return "Die1: " + die1 + " Die2: " + die2;
	}
	
	
}
