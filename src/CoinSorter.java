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
	}

	/**
	 * Initialise class attributes
	 */
	public CoinSorter(String currency, int minCoinIn, int maxCoinIn, List<Integer> coinList) {
		this.currency = currency;
		this.minCoinIn = minCoinIn;
		this.maxCoinIn = maxCoinIn;
		this.coinList = coinList;

		System.out.println(coinList);
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
		// TODO 

	}

}
