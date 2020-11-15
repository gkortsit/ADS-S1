import java.util.*;

public class CoinSorter {
	// the currency to be shown in the outputs
	private String currency;
	// the minimum amount of coin that can be used as an input
	private int minCoinIn;
	// the maximum amount of coin that can be used as an input
	private int maxCoinIn;
	// a list of all the coin denominations that input can be exchanged into
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
	 * Set the program's currency
	 * 
	 * @param currency The currency to be used
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Set the minimum amount of coin that can be used as an input
	 * 
	 * @param minCoinIn Integer indicating the minimum amount
	 */
	public void setMinCoinIn(int minCoinIn) {
		this.minCoinIn = minCoinIn;
	}

	/**
	 * Set the maximum amount of coin that can be used as an input
	 * 
	 * @param maxCoinIn Integer indicating the maximum amount
	 */
	public void setMaxCoinIn(int maxCoinIn) {
		this.maxCoinIn = maxCoinIn;
	}

	/**
	 * Get the program's currency
	 * 
	 * @return String indicating the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Get the minimum amount of coin that can be used as an input
	 * 
	 * @return Integer indicating the minimum amount
	 */
	public int getMinCoinIn() {
		return minCoinIn;
	}

	/**
	 * Get the maximum amount of coin that can be used as an input
	 * 
	 * @return Integer indicating the maximum amount
	 */
	public int getMaxCoinIn() {
		return maxCoinIn;
	}

	/**
	 * Print the denominations that are currently in circulation.
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
	 * Calculates and returns a string with the maximum number of coins you can get
	 * from the given denomination, along with any remainder.
	 * 
	 * @param total        Integer indicating the amount for coins to be calculated
	 * @param denomination Integer indicating the coin denomination to calculate
	 *                     coins
	 * 
	 * @return String output with the number of coins and remainder for the given
	 *         amount
	 */
	public String coinCalculator(int total, int denomination) {
		String output;
		String coinsWithCurrency;
		int totalCoins = total / denomination;
		int remainder = total % denomination;

		if (denomination == 200 || denomination == 100) {
			coinsWithCurrency = currency + denomination / 100;
		} else {
			coinsWithCurrency = denomination + "p";
		}

		output = "A total of " + totalCoins + " x " + coinsWithCurrency + " coins can be exchanged";

		if (remainder > 0) {
			output += ", with a remainder of " + remainder + "p";
		}

		return output;
	}

	/**
	 * Calculates and returns a string with the maximum number of coins you can get
	 * excluding the given denomination, along with any remainder.
	 * 
	 * @param total                 Integer indicating the amount for coins to be
	 *                              calculated
	 * @param denominationToExclude Integer indicating the coin denomination to
	 *                              exclude
	 * 
	 * @return String output with the number of coins and remainder for the given
	 *         amount
	 */
	public String multiCoinCalculator(int total, int denominationToExclude) {
		int adjustingTotal = total;
		String output = "The coins exchanged are: ";

		for (int i = 0; i < coinList.size(); i++) {
			int denomination = coinList.get(i);
			int coinNum = 0;

			if (adjustingTotal >= denomination && denomination != denominationToExclude) {
				coinNum = adjustingTotal / denomination;
				adjustingTotal = adjustingTotal % denomination;
			}

			output += coinNum + " x " + denomination + "p, ";
		}

		output += "with a remainder of " + adjustingTotal + "p";

		return output;

	}

	/**
	 * Print the current program configuration
	 * 
	 * @return String indicating current currency, min, and max input coin allowed
	 */
	public String displayProgramConfigs() {
		return "Currency is set to " + currency + ", the current minimum input allowed is " + minCoinIn
				+ ", and the current maximum input allowed is " + maxCoinIn;
	}
}
