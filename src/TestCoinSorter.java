import java.util.*;

public class TestCoinSorter {
	private static CoinSorter coinSorter;
	private static Scanner keyboard;
	private static List<Integer> denominations;

	public static void main(String[] args) {
		keyboard = new Scanner(System.in);
		denominations = createDenominationsList();

//		TODO: what todo with min and max coins ?
		coinSorter = new CoinSorter("£", 10, 200, denominations);
		coinSorter.printCoinList();

		System.out.println(coinSorter.displayProgramConfigs());
		showMenu();

	}

	private static void showMenu() {
		int choice;
		int totalIn;
		int denominationIn;

		do {
			System.out.println();
			System.out.println("***Coin Sorter - Main Menu***");
			System.out.println("1 - Coin calculator");
			System.out.println("2 - Multiple coin calculator");
			System.out.println("3 - Print coin list");
			System.out.println("4 - Set details");
			System.out.println("5 - Display program configurations");
			System.out.println("6 - Quit the program");
			System.out.println();

			choice = keyboard.nextInt();

			switch (choice) {
			case 1:
				totalIn = getIntegerInput();
				denominationIn = getDenominationInput();
				System.out.println(coinSorter.coinCalculator(totalIn, denominationIn));
				break;
			case 2:
				totalIn = getIntegerInput();
				denominationIn = getDenominationInput();
				System.out.println(coinSorter.multiCoinCalculator(totalIn, denominationIn));
				break;
			case 3:
				coinSorter.printCoinList();
				break;
			case 4:
				showSetDetailsMenu();
				break;
			case 5:
				System.out.println(coinSorter.displayProgramConfigs());
				break;
			default:
				if (choice != 6)
					System.out.println("Unknown option");
			}
		} while (choice != 6);

	}

	private static void showSetDetailsMenu() {
		int choice;

		do {
			System.out.println();
			System.out.println("***Set Details Sub-Menu***");
			System.out.println("1 - Set currency");
			System.out.println("2 - Set minimum coin input value");
			System.out.println("3 - Set maximum coin input value");
			System.out.println("4 - Return to main menu");
			System.out.println();

			choice = keyboard.nextInt();

			switch (choice) {
			case 1:
				coinSorter.setCurrency(getCurrencyInput());
				break;
			case 2:
				coinSorter.setMinCoinIn(getDenominationInput());
				break;
			case 3:
				coinSorter.setMaxCoinIn(getDenominationInput());
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
		int input;

		do {
			// Integer Validation
			System.out.println("Enter a positive number to calculate coins for.");

			while (!keyboard.hasNextInt()) {
				System.out.println("Please enter a valid number.");
				keyboard.next();
			}

			input = keyboard.nextInt();
		} while (input <= 0);

		return input;
	}

	/**
	 * Gets and validates user denomination input
	 * 
	 * @return int denomination
	 */
	private static int getDenominationInput() {
		int input;

		do {
			System.out.println();
			System.out.println("Choose from the following denominations.");
			System.out.println("200 for £2, 100 for £1, 50 for 50p, 20 for 20p and 10 for 10p");

			while (!keyboard.hasNextInt()) {
				System.out.println("Please enter a valid number.");
				keyboard.next();
			}

			input = keyboard.nextInt();
		} while (!denominations.contains(input));

		return input;
	}

	/**
	 * Gets and validates user currency input
	 * 
	 * @return String currency
	 */
	private static String getCurrencyInput() {
		String input;
		boolean validated = false;
		List<String> currencies = createCommonCurrenciesList();

		do {
			System.out.println();
			System.out.println("Enter a currency symbol from the following: " + currencies);
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
	 * Creates a list of common currency symbols. Could be further expanded.
	 * 
	 * @return List<String> list of currencies
	 */
	private static List<String> createCommonCurrenciesList() {
		List<String> currencies = new ArrayList<>();
		currencies.add("£");
		currencies.add("€");
		currencies.add("a$");
		currencies.add("c$");
		currencies.add("hk$");
		return currencies;
	}
}
