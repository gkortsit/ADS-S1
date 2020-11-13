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
		String output;
		String coinCurrency;
		int totalCoins = total / coinType;
		int remainder = total % coinType;

		if (coinType == 200 || coinType == 100) {
			coinCurrency = "£" + coinType / 100;
		} else {
			coinCurrency = coinType + "p";
		}

		output = "A total of " + totalCoins + " x " + coinCurrency + " coins can be exchanged";

		if (remainder > 0) {
			output += ", with a remainder of " + remainder + "p";
		}

		return output;
	}

	/**
	 * multiCoinCalculator(int, int) : String • This method should take two values;
	 * the total value to exchange and a coin type to exclude, in order to calculate
	 * and return the maximum number of coins of the input coin type that can be
	 * exchanged while excluding the input coin type, in addition to the remainder
	 * as a string. For example, multiCoinCalculator(562, 50) may return “The coins
	 * exchanged are: 2 x 200p, 1 x 100p, 0 x 50p, 3 x 20p, 0 x 10p, with a
	 * remainder of 2p”.
	 * 
	 */

	public String multiCoinCalculator(int total, int denominationToExclude) {
		int adjustingTotal = total;
		String output = "The coins exchanged are: ";

		for (int i = 0; i < coinList.size(); i++) {
			int denomination = coinList.get(i);
			int coinNum = 0;

			if (adjustingTotal > denomination && denomination != denominationToExclude) {
				coinNum = adjustingTotal / denomination;
				adjustingTotal = adjustingTotal % denomination;
			}

			output += coinNum + " x " + denomination + "p, ";
		}

		output += "with a remainder of " + adjustingTotal + "p";

		return output;

	}

	/**
	 * displayProgramConfigs() : String • This method should return the following as
	 * a string: the current currency and the current minimum and maximum value
	 * accepted as input.
	 */
	public String displayProgramConfigs() {
		return "Currency is set to " + currency + ", current minimum is " + minCoinIn
				+ ", and current maximum is set to " + maxCoinIn;
	}
}
