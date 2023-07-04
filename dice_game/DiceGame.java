
package hw1_sample_solution;

import java.util.Scanner;


public class DiceGame {
    private static int playerGrandTotal = 0; // Total score accumulated by player so far
	private static int playerTurnTotal = 0; // Total score accumulated by player in the current turn

	private static int computerGrandTotal = 0; // Total score accumulated by computer so far
	private static int computerTurnTotal = 0; // Total score accumulated by computer in the current turn

	private static PairOfDice playerDice = new PairOfDice(); // Player's dice
	private static PairOfDice computerDice = new PairOfDice(); // Computer's dice
        
        private static Scanner sc = new Scanner(System.in); // Used to get input from the user
        
        private static char whoIsPlaying;

	/**
	 * main method
	 * 
	 * @param args A string array containing the command line arguments
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to the DiceGame. It's you against the computer.\n" +
				"You play by rolling the dice. The first player to get 100\n" +
				"points wins. However, if you roll one 1 you lose all the \n" +
				"points you've accumulated in your turn. If you roll two 1's,\n" +
				"you lose all your points. You can turn over the dice over at\n" +
				"any time. However, if you roll one or two 1's, you lose your\n" +
				"turn. I (the computer) play by the same rules, except I'll\n" +
				"always turn over the dice when I've rolled20 or more points\n" +
				"in a single turn.");

		char choice = getChoiceFromPlayer("Ready to begin? (Type 'y' when you're ready)");

		if (choice != 'y') {
			// Player wasn't ready to begin
			return;
		}

		while (true) { // Main game loop, until either player or computer wins
			boolean playerWins = emulatePlayerTurn();
			if (playerWins) {
				printScores();
				System.out.println("\nYOU WON!\n");
				break;
			}

			boolean computerWins = emulateComputerTurn();
			if (computerWins) {
				printScores();
				System.out.println("\nBetter luck next time!\n");
				break;
			}
		}

	}

	/**
	 * Emulates the player's turn by rolling the dice, checking validation, checking
	 * if the player has won, and asking if the player wants to continue rolling or
	 * gives up the turn
	 * 
	 * @return boolean true if the player has won, false otherwise
	 */
	public static boolean emulatePlayerTurn() {
		boolean running = true; // varialbe that indicates the status of the player's turn
                whoIsPlaying = 'p';
		while (running) {
			System.out.println("You're rolling the dice...");
			playerDice.roll();
			System.out.println("You rolled " + playerDice.toString());

			int validation = validateRoll(playerDice);
			if (validation > 0) {
				// Did not get any 1's, so the player continues if the player hasn't won yet
				playerTurnTotal += playerDice.getDiceSum();

				if (checkPlayerWin()) {
					playerGrandTotal += playerTurnTotal;
					return true; // The player wins
				}

				
				printScore();// Prints the player's scores if it hasn't won yet
                                char choice = getChoiceFromPlayer("Do you want to continue rolling? (Type 'y' or 'n')");
				running = (choice == 'y'); // turn over dice if the player doesn't want to continue rolling

			} else if (validation == 0) {
				// Got one 1 in dice roll, player loses the turn score and turns over the dice
				System.out.println("You got a 1!");
				playerTurnTotal = 0;
			} else {
				// Got two 1's, player loses the total score and turns over the dice
				System.out.println("You got two 1's!");
				playerTurnTotal = 0;
				playerGrandTotal = 0;
			}

			if (validation <= 0) {
				// If the player got at least one 1, the player turns over the dice
				getChoiceFromPlayer("Continue? (Type 'y' when you're ready to turn the dice over to me)");
				running = false;
			}
		}

		playerGrandTotal += playerTurnTotal; // Increment the player's grand total
		playerTurnTotal = 0; // reset the player's turn total

		printScores();

		return false;
	}

	/**
	 * Emulates the computer's turn by rolling the dice, checking validation,
	 * checking if the computer has won, and turning over the dice if the computer's
	 * turn score is greater than 20
	 * 
	 * @return boolean true if the computer has won, false otherwise
	 */
	public static boolean emulateComputerTurn() {
		boolean running = true; // variable that indicates the status of the computer's turn
                whoIsPlaying = 'c';
		while (running) {
			System.out.println("I'm rolling the dice...");
			computerDice.roll();
			System.out.println("I rolled " + computerDice);

			int validation = validateRoll(computerDice);
			if (validation > 0) {
				// Did not get any 1's, so the computer continues if it hasn't won yet
				computerTurnTotal += computerDice.getDiceSum();

				if (checkComputerWin()) {
					computerGrandTotal += computerTurnTotal;
					return true; // The computer has won
				}

				 
                                printScore();// print the computer's scores if it didn't win yet
			} else if (validation == 0) {
				// Got one 1 in dice roll, computer loses its turn score and turns over the dice
				System.out.println("I got a 1!");
				computerTurnTotal = 0;
			} else {
				// Got two 1's, computer loses its total score and turns over the dice
				System.out.println("I got two 1's");
				computerTurnTotal = 0;
				computerGrandTotal = 0;
			}

			if (computerTurnTotal >= 20) {
				// Computer accumulated more than 20 points in the current turn
				System.out.println("I got more than 20 in this turn!");
			}

			if (validation <= 0 || computerTurnTotal >= 20) {
				// If the computer got at least one 1, or if the computer got more than 20
				// points in the current turn, then turn over the dice
				getChoiceFromPlayer("Continue? (Type 'y' when you're ready)");
				running = false;
			}
		}

		computerGrandTotal += computerTurnTotal; // Increment the computer's total score
		computerTurnTotal = 0; // reset the computer's turn score

		printScores();

		return false; // the computer hasn't won yet
	}

	/**
	 * Checks whether the player has won
	 * 
	 * @return true if the player has won, false otherwise
	 */
	public static boolean checkPlayerWin() {
		if ((playerGrandTotal + playerTurnTotal) >= 100) {
			return true;
		}
		return false;
	}

	/**
	 * Checks whether the computer has won
	 * 
	 * @return true if the computer has won, false otherwise
	 */
	public static boolean checkComputerWin() {
		if ((computerGrandTotal + computerTurnTotal) >= 100) {
			return true;
		}
		return false;
	}
        public static void printScore()
        {
            if(whoIsPlaying == 'p')
            {
                System.out.println("This gives you a turn total of\n" +
				"\t" + playerTurnTotal);
		System.out.println("and a grand total of\n" +
				"\t" + (playerGrandTotal + playerTurnTotal));
		System.out.println("The computer has a total of\n" +
				"\t" + computerGrandTotal);
		System.out.println();
            }
            else if(whoIsPlaying == 'c')
            {
                System.out.println("This gives me a turn total of\n" +
				"\t" + computerTurnTotal);
		System.out.println("and a grand total of\n" +
				"\t" + (computerGrandTotal + computerTurnTotal));
		System.out.println("You have a total of\n" +
				"\t" + playerGrandTotal);
		System.out.println();
            }
        }

	
	/**
	 * Prints both the player's and the computer's total score in a nice format
	 */
	public static void printScores() {
		System.out.println("The score is: ");
		System.out.println("\tYou: " + playerGrandTotal);
		System.out.println("\tComputer: " + computerGrandTotal);
		System.out.println();
	}

	/**
	 * Validates roll of dice `dice`
	 * 
	 * @param dice object of type PairOfDice
	 * @return Integer. Negative if two 1's, 0 if one 1 and positive if zero 1's and
	 *         the roll is valid
	 */
	public static int validateRoll(PairOfDice dice) {
		if (dice.getDie1().getFaceValue() == 1 && dice.getDie2().getFaceValue() == 1) {
			return -1;
		} else if (dice.getDie1().getFaceValue() == 1 || dice.getDie2().getFaceValue() == 1) {
			return 0;
		} else {
			return 1;
		}
	}

	

	/**
	 * Get single character choice input from the player
	 * 
	 * @param prompt
	 * @return char first character of input after converting to lower case
	 */
	public static char getChoiceFromPlayer(String prompt) {
		System.out.println(prompt);
		char choice = sc.nextLine().toLowerCase().charAt(0);
		System.out.println();
		return choice;
	}
  
}
       
            
        
         
        
            
                    
        

