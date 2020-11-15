import java.util.*;

public class TestCoinSorter {
	private static CoinSorter coinSorter;
	private static Scanner keyboard;
	private static List<Integer> denominations;
	private static List<String> currencies;

	/**
	 * Initialise the scanner and coin calculator, and show the main menu
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		denominations = createDenominationsList();
		currencies = createCommonCurrenciesList();
		coinSorter = new CoinSorter("�", 0, 10000, denominations);
		showMenu();
	}

	/**
	 * Show main menu
	 */
	private static void showMenu() {
		int choice;
		int amountIn;
		int denominationIn;

		do {
			System.out.println("\n***Coin Sorter - Main Menu***");
			System.out.println("Select one of the following choices by typing the relevant number.\n");
			System.out.println("1 - Coin calculator");
			System.out.println("2 - Multiple coin calculator");
			System.out.println("3 - Print coin list");
			System.out.println("4 - Set details");
			System.out.println("5 - Display program configurations");
			System.out.println("6 - Quit the program\n");

			choice = getIntegerInput();

			switch (choice) {
			case 1:
				System.out.println("Enter the amount, you would like to exchange. The number should be in pennies.\n");
				amountIn = getAmountIn();

				System.out.println(
						"Enter the denomination of coins you want to exchange. The number should be in pennies.\n");
				denominationIn = getDenominationIn();

				System.out.println(coinSorter.coinCalculator(amountIn, denominationIn));
				break;
			case 2:
				System.out.println("Enter the number of coins you would like to exchange.\n");
				amountIn = getAmountIn();

				System.out.println("Exclude a coin denomination for the amount of coins you want to exchange.\n");
				denominationIn = getDenominationIn();

				System.out.println(coinSorter.multiCoinCalculator(amountIn, denominationIn));
				break;
			case 3:
				coinSorter.printCoinList();
				break;
			case 4:
				showSetDetailsMenu();
				break;
			case 5:
				System.out.println();
				System.out.println(coinSorter.displayProgramConfigs());
				break;
			default:
				if (choice != 6)
					System.out.println("Unknown option");
			}
		} while (choice != 6);

	}

	/**
	 * Shows submenu to configure currency and min/max coins
	 */
	private static void showSetDetailsMenu() {
		int choice;
		int minCoin = coinSorter.getMinCoinIn();
		int maxCoin = coinSorter.getMaxCoinIn();

		do {
			System.out.println("\n***Set Details Sub-Menu***");
			System.out.println("1 - Set currency");
			System.out.println("2 - Set minimum coin input value");
			System.out.println("3 - Set maximum coin input value");
			System.out.println("4 - Return to main menu\n");

			choice = getIntegerInput();

			switch (choice) {
			case 1:
				String input = getCurrencyIn();
				coinSorter.setCurrency(input);
				System.out.println("\nCurrency has been set to " + input);
				break;
			case 2:
				do {
					System.out.println("Please enter a positive number that is less than or equal to " + maxCoin);
					minCoin = getIntegerInput();
				} while (minCoin < 0 || minCoin > maxCoin);

				coinSorter.setMinCoinIn(minCoin);
				System.out.println("\nMinimum input allowed has been set to " + minCoin);
				break;
			case 3:
				do {
					System.out.println("Please enter a positive number that is greater than or equal to " + minCoin);
					maxCoin = getIntegerInput();
				} while (maxCoin < 0 && maxCoin < minCoin);

				coinSorter.setMaxCoinIn(maxCoin);
				System.out.println("\nMaximum input allowed has been set to " + maxCoin);
				break;
			default:
				if (choice != 4)
					System.out.println("Unknown option");
			}
		} while (choice != 4);
	}

	/**
	 * Gets and validates user integer input
	 * 
	 * @return int integer
	 */
	private static int getIntegerInput() {
		while (!keyboard.hasNextInt()) {
			System.out.println("Please enter a valid number.");
			keyboard.next();
		}
		return keyboard.nextInt();
	}

	/**
	 * Gets integer input and validates that it is within the min and max coin
	 * boundarie set
	 * 
	 * @return int amount
	 */
	private static int getAmountIn() {
		int amountIn = getIntegerInput();
		int minCoin = coinSorter.getMinCoinIn();
		int maxCoin = coinSorter.getMaxCoinIn();
		while (amountIn < minCoin || amountIn > maxCoin) // check that is between the min and max coin set
		{
			System.out.println("Please enter a number between " + minCoin + " and " + maxCoin);
			amountIn = getIntegerInput();
		}
		return amountIn;
	}

	/**
	 * Gets integer input and ensures it is a valid denomination
	 * 
	 * @return int denomination
	 */
	private static int getDenominationIn() {
		int denominationIn = getIntegerInput();
		while (!denominations.contains(denominationIn)) // check that is a valid denomination
		{
			System.out.println("Please choose from a provided denomination");
			coinSorter.printCoinList();
			denominationIn = getIntegerInput();
		}
		return denominationIn;
	}

	/**
	 * Gets string input and ensures it is a valid currency
	 * 
	 * @return string currency
	 */
	private static String getCurrencyIn() {
		String input;
		boolean validated = false;

		do {
			System.out.println("\nEnter a currency symbol from the following: " + currencies);
			input = keyboard.next();

			for (int i = 0; i < currencies.size(); i++) {
				if (currencies.get(i).equalsIgnoreCase(input)) {
					validated = true;
				}
			}
		} while (!validated);
		return input;
	}

	/**
	 * Creates a list of denominations
	 * 
	 * @return List<Integer> list of denominations
	 */
	private static List<Integer> createDenominationsList() {
		List<Integer> tempDenominations = new ArrayList<>();
		tempDenominations.add(200);
		tempDenominations.add(100);
		tempDenominations.add(50);
		tempDenominations.add(20);
		tempDenominations.add(10);
		return tempDenominations;
	}

	/**
	 * Creates a list of common currency symbols. Could be expanded.
	 * 
	 * @return List<String> list of currencies
	 */
	private static List<String> createCommonCurrenciesList() {
		List<String> currencies = new ArrayList<>();
		currencies.add("�");
		currencies.add("�");
		currencies.add("$");
		currencies.add("a$");
		currencies.add("c$");
		currencies.add("hk$");
		return currencies;
	}
}
