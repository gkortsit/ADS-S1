import java.util.ArrayList;
import java.util.Scanner;

public class TestCoinSorter {
	private static Scanner keyboard;
	private static CoinSorter coinSorter;
	private static ArrayList<Integer> denominations;

	public static void main(String[] args) {
		int choice;

		// Create a list of valid denominations
		denominations = new ArrayList<>();
		denominations.add(200);
		denominations.add(100);
		denominations.add(50);
		denominations.add(20);
		denominations.add(10);

		// initialize keyboard input
		keyboard = new Scanner(System.in);

		// initialize the CoinSorter object with default parameters
		coinSorter = new CoinSorter("GBP", 0, 10000, denominations);

		// menu logic
		do {
			// display menu options
			System.out.println("\n***Coin Sorter - Main Menu***");
			System.out.println("1 - Coin calculator");
			System.out.println("2 - Multiple coin calculator");
			System.out.println("3 - Print coin list");
			System.out.println("4 - Set details");
			System.out.println("5 - Display program configurations");
			System.out.println("6 - Quit the program\n");
			System.out.print("Enter a choice [1-6]: ");

			// get entered option
			choice = getIntegerInput();

			// display relevant output
			switch (choice) {
			case 1:
				selection1();
				break;
			case 2:
				selection2();
				break;
			case 3:
				selection3();
				break;
			case 4:
				selection4();
				break;
			case 5:
				selection5();
				break;
			default:
				if (choice != 6)
					System.out.print("Choose between 1-6 only");
			}
		} while (choice != 6);
	}

	static void selection1() {
		int amountIn;
		int denominationIn;
		int minCoinIn = coinSorter.getMinCoinIn();
		int maxCoinIn = coinSorter.getMaxCoinIn();

		System.out.println("Enter the amount, you would like to exchange.");

		do {
			System.out.println("The amount to exchange must be between " + minCoinIn + " and " + maxCoinIn);
			amountIn = getIntegerInput();
		} while (amountIn < minCoinIn || amountIn > maxCoinIn);

		System.out.println("\nEnter the denomination of coins you want to exchange.");
		coinSorter.printCoinList();
		denominationIn = getIntegerInput();

		// pass the inputs into the coinCalcutor function print the output
		System.out.println("\n" + coinSorter.coinCalculator(amountIn, denominationIn));
	}

	static void selection2() {
		int amountIn;
		int denominationIn;
		int minCoinIn = coinSorter.getMinCoinIn();
		int maxCoinIn = coinSorter.getMaxCoinIn();

		System.out.println("Enter the amount, you would like to exchange.");

		do {
			System.out.println("The amount to exchange must be between " + minCoinIn + " and " + maxCoinIn);
			amountIn = getIntegerInput();
		} while (amountIn < minCoinIn || amountIn > maxCoinIn);

		System.out.println("\nEnter the denomination of coins you want to exclude from the exchange.");
		coinSorter.printCoinList();
		denominationIn = getIntegerInput();

		// pass the inputs into the multiCoinCalculator function print the output
		System.out.println("\n" + coinSorter.multiCoinCalculator(amountIn, denominationIn));
	}

	static void selection3() {
		coinSorter.printCoinList();
	}

	static void selection4() {
		int choice;

		do {
			System.out.println("\n***Set Details Sub-Menu***");
			System.out.println("1 - Set currency");
			System.out.println("2 - Set minimum coin input value");
			System.out.println("3 - Set maximum coin input value");
			System.out.println("4 - Return to main menu\n");
			System.out.print("Enter a choice [1-4]: ");

			choice = getIntegerInput();

			switch (choice) {
			case 1:
				subSelection1();
				break;
			case 2:
				subSelection2();
				break;
			case 3:
				subSelection3();
				break;
			default:
				if (choice != 4)
					System.out.print("Choose between 1-4 only");
			}
		} while (choice != 4);
	}

	static void selection5() {
		System.out.println(coinSorter.displayProgramConfigs());
	}

	static void subSelection1() {
		String input;

		System.out.println("Please enter the currency you'd like the programme to use. For example, GBP, USD, CNY");

		while (keyboard.hasNextInt()) {
			System.out.println("\nPlease enter a valid currency\n");
			keyboard.next();
		}

		input = keyboard.next();

		coinSorter.setCurrency(input);
		System.out.println("\nCurrency has been set to: " + coinSorter.getCurrency());
	}

	static void subSelection2() {
		int minCoinIn;

		do {
			System.out.println(
					"Please enter a positive number that is less than or equal to " + coinSorter.getMaxCoinIn());
			minCoinIn = getIntegerInput();
		} while (minCoinIn < 0 || minCoinIn > coinSorter.getMaxCoinIn());

		coinSorter.setMinCoinIn(minCoinIn);
		System.out.println("\nMinimum input allowed has been set to " + minCoinIn);
	}

	static void subSelection3() {
		int maxCoinIn;

		do {
			System.out.println(
					"Please enter a positive number that is greater than or equal to " + coinSorter.getMinCoinIn());
			maxCoinIn = getIntegerInput();

		} while (maxCoinIn < 0 || maxCoinIn < coinSorter.getMinCoinIn());

		coinSorter.setMaxCoinIn(maxCoinIn);
		System.out.println("\nMaximum input allowed has been set to " + maxCoinIn);
	}

	/**
	 * Gets and validates user integer input
	 * 
	 * @return int integer
	 */
	static int getIntegerInput() {
		while (!keyboard.hasNextInt()) {
			System.out.println("Please enter a valid number.");
			keyboard.next();
		}
		return keyboard.nextInt();
	}

}
