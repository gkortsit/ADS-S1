import java.util.*;

/**
 * Assignment 1
 */

/**
 * @author Theodoros Gkortsilas
 * @version 0.0.1
 */
public class CoinSorter {
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private List<Integer> coinList;

	public static void main(String[] args) {
		List<Integer> denominations = new ArrayList<>();
		denominations.add(200);
		denominations.add(100);
		denominations.add(50);
		denominations.add(30);
		denominations.add(10);

		CoinSorter TestCoinSorter = new CoinSorter("£", 5, 10, denominations);
		TestCoinSorter.printCoinList();

		System.out.println(TestCoinSorter.coinCalculator(562, 50));
		System.out.println(TestCoinSorter.coinCalculator(352, 100));
//		TestCoinSorter.coinCalculator(600, 2);
//		TestCoinSorter.coinCalculator(941, 2);

	}

	/**
	 * Initialise class attributes
	 */
	public CoinSorter(String currency, int minCoinIn, int maxCoinIn, List<Integer> coinList) {
		this.currency = currency;
		this.minCoinIn = minCoinIn;
		this.maxCoinIn = maxCoinIn;
		this.coinList = coinList;
	}

	/**
	 * Setter methods
	 */

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setMinCoinIn(int minCoinIn) {
		this.minCoinIn = minCoinIn;
	}

	public void setMaxCoinIn(int maxCoinIn) {
		this.maxCoinIn = maxCoinIn;
	}

	/**
	 * Getter methods
	 */

	public String getCurrency() {
		return currency;
	}

	public int getMinCoinIn() {
		return minCoinIn;
	}

	public int getMaxCoinIn() {
		return maxCoinIn;
	}

	/**
	 * Print the contents of the coin list, indicating what denominations are
	 * currently in circulation.
	 */
	public void printCoinList() {
		String output = "The current coin denominations are in circulation: ";
		for (int i = 0; i < coinList.size(); i++) {
			output += coinList.get(i);
			if (i + 1 != coinList.size()) {
				output += ", ";
			}
		}
		System.out.println(output);
	}

	/**
	 * coinCalculator(int, int) : String • This method should take two values; the
	 * total value to exchange and a coin type, in order to calculate and return the
	 * maximum number of coins of the input coin type that can be exchanged, in
	 * addition to the remainder as a string. For example, coinCalculator(562, 50)
	 * may return “A total of 11 x 50p coins can be exchanged, with a remainder of
	 * 12p”. For example, given the input value of 352 pennies and the input
	 * denomination of £1 coins, the output should be three £1 coins and a remainder
	 * of 52p.
	 * 
	 */
	public String coinCalculator(int total, int coinType) {
		int totalCoins = total / coinType;
		int remainder = total % coinType;
		String currency;

		if (coinType == 200 || coinType == 100) {
			currency = "£" + coinType / 100;
		} else {
			currency = coinType + "p";
		}

		String output = "A total of " + totalCoins + " x " + currency + " coins can be exchanged";

		if (remainder > 0) {
			output += ", with a remainder of " + remainder + "p";
		}

		return output;
	}
}
