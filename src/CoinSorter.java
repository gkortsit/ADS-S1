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
	 * @param amountIn       Integer indicating the amount for coins to be
	 *                       calculated
	 * @param denominationIn Integer indicating the coin denomination to calculate
	 *                       coins
	 * 
	 * @return String output with the number of coins and remainder for the given
	 *         amount
	 */
	public String coinCalculator(int amountIn, int denominationIn) {
		int result;
		int remainder;
		String output;

		if (!coinList.contains(denominationIn)) {
			return "Invalid denomination.\n Please choose from the following list: " + coinList;
		}

		result = amountIn / denominationIn;
		remainder = amountIn % denominationIn;

		output = "A total of " + result + " x " + denominationIn + currency + " coins can be exchanged";

		if (remainder > 0) {
			output += ", with a remainder of " + remainder + currency + " coin";
		}

		return output;
	}

	/**
	 * Calculates and returns a string with the maximum number of coins you can get
	 * excluding the given denomination, along with any remainder.
	 * 
	 * @param amountIn              Integer indicating the amount for coins to be
	 *                              calculated
	 * @param denominationToExclude Integer indicating the coin denomination to
	 *                              exclude
	 * 
	 * @return String output with the number of coins and remainder for the given
	 *         amount
	 */
	public String multiCoinCalculator(int amountIn, int denominationToExclude) {
		int adjustingAmount;
		String validOutput;

		if (!coinList.contains(denominationToExclude)) {
			return "Invalid denomination.\n Please choose from the following list: " + coinList;
		}

		adjustingAmount = amountIn;
		validOutput = "The coins exchanged are: ";

		for (int i = 0; i < coinList.size(); i++) {
			int denomination = coinList.get(i);
			int coinNum = 0;

			if (adjustingAmount >= denomination && denomination != denominationToExclude) {
				coinNum = adjustingAmount / denomination;
				adjustingAmount = adjustingAmount % denomination;
			}

			validOutput += coinNum + " x " + denomination + currency + ", ";
		}

		validOutput += "with a remainder of " + adjustingAmount + currency;

		return validOutput;

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
