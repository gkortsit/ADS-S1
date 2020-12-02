import java.util.*;

/**
 * Textual menu and tester for the CoinSorter class
 * 
 * @version 29/11/2020
 */
public class TestCoinSorter {
	private static Scanner keyboard;
	private static CoinSorter coinSorter;
	private static ArrayList<Integer> denominations;

	public static void main(String[] args) {
		// this will hold the users input in relation to the menu options
		int choice;

		// Create a list of valid denominations
		denominations = new ArrayList<>(Arrays.asList(200, 100, 50, 20, 10));

		// Initialise keyboard input
		keyboard = new Scanner(System.in);

		// Initialise the CoinSorter object with default parameters
		coinSorter = new CoinSorter(Currency.getInstance("GBP").getCurrencyCode(), 0, 10000, denominations);

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

			// get user input
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
					System.out.print("Choose between [1-6] only: ");
			}
		} while (choice != 6);
	}

	/**
	 * Runs when the user selects the first option from the menu. Given an amount,
	 * this function shows the maximum amount of coins the user will get from a coin
	 * denomination of their choice.
	 */
	static void selection1() {
		int amountIn;
		int denominationIn;
		int minCoinIn = coinSorter.getMinCoinIn();
		int maxCoinIn = coinSorter.getMaxCoinIn();

		System.out.println(
				"Given an amount in pennies, this calculator shows the maximum amount of coins you will get from a coin denomination of your choice.");

		// get and validate the user's amount input
		do {
			System.out.print(
					"Enter the amount in pennies you would like to exchange. The amount to exchange must be between "
							+ minCoinIn + " and " + maxCoinIn + ": ");
			amountIn = getIntegerInput();
		} while (amountIn < minCoinIn || amountIn > maxCoinIn);

		// get and validate the user's denomination input
		do {
			System.out.print("\nEnter the denomination of coins you want to exchange. ");
			System.out.print(coinSorter.printCoinList() + ": ");
			denominationIn = getIntegerInput();
		} while (!denominations.contains(denominationIn));

		// pass the inputs into the coinCalcutor function and print the output
		System.out.println("\n" + coinSorter.coinCalculator(amountIn, denominationIn));
	}

	/**
	 * Runs when the user selects the second option from the menu. Given an amount,
	 * this function shows the maximum amount of coins the user will get from each
	 * coin denomination, excluding a denomination of their choice.
	 */
	static void selection2() {
		int amountIn;
		int denominationIn;
		int minCoinIn = coinSorter.getMinCoinIn();
		int maxCoinIn = coinSorter.getMaxCoinIn();

		System.out.println(
				"Given an amount in pennies, this calculator shows the maximum amount of coins you will get from each coin denomination, excluding a denomination of your choice.");

		// get and validate the user's amount input
		do {
			System.out.print(
					"Enter the amount in pennies you would like to exchange. The amount to exchange must be between "
							+ minCoinIn + " and " + maxCoinIn + ": ");
			amountIn = getIntegerInput();
		} while (amountIn < minCoinIn || amountIn > maxCoinIn);

		// get and validate the user's denomination input
		do {
			System.out.print("\nEnter the denomination of coins you want to exclude from the exchange. ");
			System.out.print(coinSorter.printCoinList() + ": ");
			denominationIn = getIntegerInput();
		} while (!denominations.contains(denominationIn));

		// pass the inputs into the multiCoinCalculator function print the output
		System.out.println("\n" + coinSorter.multiCoinCalculator(amountIn, denominationIn));
	}

	/**
	 * Runs when the user selects the third option from the menu. This function
	 * prints the current valid denominations of coins the user can use in the
	 * programme.
	 */
	static void selection3() {
		System.out.println(coinSorter.printCoinList());
	}

	/**
	 * Runs when the user selects the fourth option from the menu. This function
	 * prints a second menu where the user can set the programme's currency, along
	 * with minimum and maximum coin inputs to exchange
	 */
	static void selection4() {
		int choice;

		// sub-menu logic
		do {
			// display sub-menu options
			System.out.println("\n***Set Details Sub-Menu***");
			System.out.println("1 - Set currency");
			System.out.println("2 - Set minimum coin input value");
			System.out.println("3 - Set maximum coin input value");
			System.out.println("4 - Return to main menu\n");
			System.out.print("Enter a choice [1-4]: ");

			// get user input
			choice = getIntegerInput();

			// display relevant output
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
					System.out.print("Choose between [1-4] only: ");
			}
		} while (choice != 4);
	}

	/**
	 * Runs when the user selects the fifth option from the menu. This function
	 * prints the current programme's configuration which consists of the currency,
	 * along with minimum and maximum inputs to exchange.
	 */
	static void selection5() {
		System.out.println(coinSorter.displayProgramConfigs());
	}

	/**
	 * Runs when the user selects the first option from the sub-menu. The user will
	 * then be able to set the programme's currency.
	 */
	static void subSelection1() {
		String input;
		String currencyCode;

		System.out.print("Please enter the currency you'd like the programme to use. For example, GBP, USD, CNY: ");

		// check that the user input is not a number
		while (keyboard.hasNextInt()) {
			System.out.print("\nPlease enter a valid currency: ");
			keyboard.next();
		}

		// check that user enters a valid currency code
		while (true) {
			try {
				// get user input
				input = keyboard.next();
				currencyCode = Currency.getInstance(input).getCurrencyCode();
				break;
			} catch (Exception e) {
				System.out.print("Please enter a currency code similar to USD, GBP, EUR: ");
			}
		}

		// set the programmes currency and show a success message.
		coinSorter.setCurrency(currencyCode);
		System.out.println("\nCurrency has been set to: " + currencyCode);
	}

	/**
	 * Runs when the user selects the second option from the sub-menu. The user will
	 * then be able to set the programme's minimum input coin to exchange.
	 */
	static void subSelection2() {
		int minCoinIn;

		// check that the user enters a valid integer that is positive and less than the
		// current maximum input coin
		do {
			System.out.print(
					"Please enter a positive number that is less than or equal to " + coinSorter.getMaxCoinIn() + ": ");
			minCoinIn = getIntegerInput();
		} while (minCoinIn < 0 || minCoinIn > coinSorter.getMaxCoinIn());

		// set the programme's minimum coin input and show a success message
		coinSorter.setMinCoinIn(minCoinIn);
		System.out.println("\nMinimum input allowed has been set to " + minCoinIn);
	}

	static void subSelection3() {
		int maxCoinIn;

		// check that the user enters a valid integer that is positive and greater than
		// the current minimum input coin
		do {
			System.out.print("Please enter a positive number that is greater than or equal to "
					+ coinSorter.getMinCoinIn() + ": ");
			maxCoinIn = getIntegerInput();

		} while (maxCoinIn < 0 || maxCoinIn < coinSorter.getMinCoinIn());

		// set the programme's maximum coin input and show a success message
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
			System.out.print("Please enter a valid number: ");
			keyboard.next();
		}
		return keyboard.nextInt();
	}

}
