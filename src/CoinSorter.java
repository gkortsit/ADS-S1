import java.text.NumberFormat;
import java.util.*;

/**
 * Coin sorting program
 * 
 * @version 29/11/2020
 */
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
	 * Return denominations that are currently in circulation.
	 */
	public String printCoinList() {
		String output = "The current coin denominations are in circulation: ";
		// loop through the coinList and concatenate a string with each denomination
		// into the output string
		for (int i = 0; i < coinList.size(); i++) {
			output += coinList.get(i);
			// keep adding commas for each denomination until we reach the end of the loop
			if (i + 1 != coinList.size()) {
				output += ", ";
			}
		}
		return output;
	}

	/**
	 * Calculates and returns a string with the number of coins you can get from the
	 * given denomination, along with any remainder.
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
		// the money formatter object to convert inputs to money
		NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
		moneyFormatter.setCurrency(Currency.getInstance(currency));

		// calculate the amount of coins and the remainder
		result = amountIn / denominationIn;
		remainder = amountIn % denominationIn;

		// build the string output
		output = "A total of " + result + " x " + moneyFormatter.format(denominationIn / 100.0)
				+ " coins can be exchanged";

		// if there is any remainder, concatenate the below string to the output
		if (remainder > 0) {
			output += ", with a remainder of " + moneyFormatter.format(remainder / 100.0);
		}

		return output;
	}

	/**
	 * Calculates and returns a string with the number of coins you can get
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
		String output;
		// the money formatter object to convert inputs to money
		NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
		moneyFormatter.setCurrency(Currency.getInstance(currency));

		adjustingAmount = amountIn; // a dynamic number used to calculate coins
		output = "The coins exchanged are: ";

		for (int i = 0; i < coinList.size(); i++) {
			int denomination = coinList.get(i);
			int coinNum = 0;

			// check if the dynamic number is greater than the current denomination and that
			// the denomination is not excluded
			if (adjustingAmount >= denomination && denomination != denominationToExclude) {
				// calculate the coins that can be exchanged with the current denomincation
				coinNum = adjustingAmount / denomination;
				// adjust the dynamic number with the remainder
				adjustingAmount = adjustingAmount % denomination;
			}

			output += coinNum + " x " + moneyFormatter.format(denomination / 100.0) + ", ";
		}

		// if there is any remainder, concatenate the below string to the output
		if (adjustingAmount > 0) {
			output += "with a remainder of " + moneyFormatter.format(adjustingAmount / 100.0);
		}

		return output;

	}

	/**
	 * Print the current program configuration
	 * 
	 * @return String indicating current currency, min, and max input coin allowed
	 */
	public String displayProgramConfigs() {
		return "Currency is set to " + Currency.getInstance(currency).getCurrencyCode()
				+ ", the current minimum input allowed is " + minCoinIn + ", and the current maximum input allowed is "
				+ maxCoinIn;
	}
}
