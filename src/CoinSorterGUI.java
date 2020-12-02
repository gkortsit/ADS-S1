import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * GUI for the CoinSorter programme
 * 
 * @version 29/11/2020
 */
public class CoinSorterGUI extends Application {
	private static final String COIN_CALCULATOR = "COIN_CALCULATOR";
	private static final String MULTI_COIN_CALCULATOR = "MULTI_COIN_CALCULATOR";
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	BorderPane mainContainer;
	VBox mainScreen;
	HBox header;
	VBox menu;
	VBox outputContainer;
	HBox outputTitleContainer;
	Text mainScreenDescription;
	TextArea output;
	ArrayList<Integer> denominations;
	CoinSorter coinSorter;

	public void start(Stage stage) {
		// Initialise the coinSorter object with default parameters
		denominations = new ArrayList<>(Arrays.asList(200, 100, 50, 20, 10));
		coinSorter = new CoinSorter(Currency.getInstance("GBP").getCurrencyCode(), 0, 10000, denominations);

		// initialise containers
		header = new HBox();
		menu = new VBox(10);
		mainContainer = new BorderPane();
		mainScreen = new VBox();
		outputContainer = new VBox();
		outputTitleContainer = new HBox();

		// create application title
		Text appTitle = new Text("Coin Sorter");

		// create output section title
		Text outputTitle = new Text("Output");

		// create box to show the outputs
		output = new TextArea();
		output.setEditable(false);
		output.setMaxSize(Double.MAX_VALUE, 150);
		output.setWrapText(true);

		// create menu buttons
		Text menuTitle = new Text("Menu");
		Button option1 = new Button("Coin Calculator");
		Button option2 = new Button("Multiple Coin Calculator");
		Button option3 = new Button("Print Coin List");
		Button option4 = new Button("Set Details");
		Button option5 = new Button("Display Program Configuration");
		Button option6 = new Button("Quit the program");

		// set event handlers for the menu buttons
		option1.setOnAction(e -> {
			clearScreen();
			renderCalculator(COIN_CALCULATOR);
		});
		option2.setOnAction(e -> {
			clearScreen();
			renderCalculator(MULTI_COIN_CALCULATOR);
		});
		option3.setOnAction(e -> {
			clearScreen();
			renderMessageToScreen(coinSorter.printCoinList(), 16);
		});
		option4.setOnAction(e -> {
			clearScreen();
			renderSubMenu();
		});
		option5.setOnAction(e -> {
			clearScreen();
			renderMessageToScreen(coinSorter.displayProgramConfigs(), 16);
		});
		option6.setOnAction(e -> stage.close());

		// set alignment of boxes
		menu.setAlignment(Pos.CENTER);
		header.setAlignment(Pos.CENTER);
		mainScreen.setAlignment(Pos.CENTER);

		// set CSS classes
		header.getStyleClass().add("header");
		menu.getStyleClass().add("menu");
		outputContainer.getStyleClass().add("output");
		outputTitleContainer.getStyleClass().add("output-title-container");

		// add elements to boxes
		header.getChildren().add(appTitle);
		menu.getChildren().addAll(menuTitle, option1, option2, option3, option4, option5, option6);
		outputTitleContainer.getChildren().addAll(outputTitle);
		outputContainer.getChildren().addAll(outputTitleContainer, output);
		mainContainer.setCenter(mainScreen);
		mainContainer.setBottom(outputContainer);

		// Show welcome screen
		renderMessageToScreen("Welcome", 30);

		// setup root element
		BorderPane root = new BorderPane();
		root.setTop(header);
		root.setLeft(menu);
		root.setCenter(mainContainer);
		root.setPrefSize(WIDTH, HEIGHT);

		// setup scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(CoinSorterGUI.class.getResource("styles.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Coin Sorter");
		stage.show();
	}

	/**
	 * Clear main screen and output box
	 */
	private void clearScreen() {
		mainScreen.getChildren().clear();
		output.setText("");
	}

	/**
	 * Shows a message in the main container
	 * 
	 * @param message the message to show in the main container
	 */
	private void renderMessageToScreen(String message, int fontSize) {
		// create a box to hold the text
		VBox container = new VBox();

		// clear whatever was in the main screen
		clearScreen();

		// create the text from the string input
		mainScreenDescription = new Text(message);
		mainScreenDescription.setFont(new Font(fontSize));
		mainScreenDescription.setTextAlignment(TextAlignment.CENTER);
		mainScreenDescription.setWrappingWidth(500);

		// set some styles to the text container
		container.maxWidth(300);
		container.getStyleClass().add("section-message");
		container.setAlignment(Pos.CENTER);

		// append the text to the containers
		container.getChildren().addAll(mainScreenDescription);
		mainScreen.getChildren().addAll(container);
	}

	/**
	 * Function to render the input fields and description of each calculator
	 * 
	 * @param view String indicating which calculator is in view
	 */
	private void renderCalculator(String view) {
		// containers that will hold the labels and input fields
		HBox amountInputContainer = new HBox(20);
		HBox denominationInputContainer = new HBox(20);
		VBox container = new VBox(20);
		container.setAlignment(Pos.CENTER);
		container.setMaxWidth(500);

		// button to start the calculations
		Button calculateButton = new Button("Calculate");

		// create labels
		Label amountLabel = new Label("Amount");
		Label denominationLabel = new Label("Denomination");

		// create input fields
		TextField amountInput = new TextField();
		TextField denominationInput = new TextField();

		// align labels
		amountLabel.setAlignment(Pos.CENTER_RIGHT);
		denominationLabel.setAlignment(Pos.CENTER_RIGHT);

		// add labels and input fields to respective containers
		amountInputContainer.getChildren().addAll(amountLabel, amountInput);
		denominationInputContainer.getChildren().addAll(denominationLabel, denominationInput);

		// logic to run when the user clicks the calculate button
		calculateButton.setOnAction(e -> {
			Integer amountIn;
			Integer denominationIn;
			int minCoin = coinSorter.getMinCoinIn();
			int maxCoin = coinSorter.getMaxCoinIn();

			// check that fields are not empty
			if (amountInput.getText().isEmpty() || denominationInput.getText().isEmpty()) {
				output.setText("Amount and denomination inputs must be filled.");
				return;
			}

			// check the user enters numbers only
			try {
				amountIn = Integer.parseInt(amountInput.getText());
				denominationIn = Integer.parseInt(denominationInput.getText());
			} catch (Exception e2) {
				output.setText("Please enter valid numbers");
				return;
			}

			// check that the user enters a valid denomination
			if (!denominations.contains(denominationIn)) {
				output.setText("Please enter a valid denomination. " + coinSorter.printCoinList());
				return;
			}

			// check the amount entered is within the minimum and maximum input settings
			if (amountIn < minCoin || amountIn > maxCoin) {
				output.setText("The amount to exchange must be between " + minCoin + " and " + maxCoin);
				return;
			}

			// perform calculation depending on which view is currently selected
			if (view.equals(COIN_CALCULATOR)) {
				output.setText(coinSorter.coinCalculator(amountIn, denominationIn));
			} else {
				output.setText(coinSorter.multiCoinCalculator(amountIn, denominationIn));
			}

		});

		// show main screen description depending on which view is currently selected
		if (view.equals(COIN_CALCULATOR)) {
			renderMessageToScreen(
					"Enter an amount in pennies/cents and a denomination. The program will calculate how many coins you will receive from that denomination",
					16);
		} else {
			renderMessageToScreen(
					"Enter an amount in pennies/cents and a coin denomination. The program will calculate the minimum coins you will need of all denominations set in the programme settings, excluding the one you entered.",
					16);
		}

		// add input fields, labels and the calculate button to the main screen
		container.getChildren().addAll(amountInputContainer, denominationInputContainer, calculateButton);
		mainScreen.getChildren().add(container);
	}

	/**
	 * Renders the submenu to setup currency, min, and max input limits
	 */
	private void renderSubMenu() {
		// create the title of the section
		renderMessageToScreen("Program Settings", 24);

		// create containers that will hold the labels and input fields
		HBox currencyInputContainer = new HBox(20);
		HBox minCoinInputContainer = new HBox(20);
		HBox maxCoinInputContainer = new HBox(20);
		HBox buttonsContainer = new HBox(20);
		buttonsContainer.setAlignment(Pos.CENTER);
		VBox container = new VBox(20);
		container.setAlignment(Pos.CENTER);
		container.setMaxWidth(500);

		// create labels
		Label currencyLabel = new Label("Currency");
		Label minCoinLabel = new Label("Minimum Coin Input");
		Label maxCoinLabel = new Label("Maximum Coin Input");

		// align labels
		currencyLabel.setAlignment(Pos.CENTER_RIGHT);
		minCoinLabel.setAlignment(Pos.CENTER_RIGHT);
		maxCoinLabel.setAlignment(Pos.CENTER_RIGHT);

		// create input fields
		TextField currencyInput = new TextField();
		TextField minCoinInput = new TextField();
		TextField maxCoinInput = new TextField();

		// show current settings in text fields
		currencyInput.setText(coinSorter.getCurrency());
		minCoinInput.setText(String.valueOf(coinSorter.getMinCoinIn()));
		maxCoinInput.setText(String.valueOf(coinSorter.getMaxCoinIn()));

		// create buttons to save or discard new settings
		Button saveSettings = new Button("Save");
		Button discardSettings = new Button("Discard");

		// set event handlers
		saveSettings.setOnAction(e -> {
			Integer minCoinIn;
			Integer maxCoinIn;
			String currencyIn;
			int minCoin = coinSorter.getMinCoinIn();
			int maxCoin = coinSorter.getMaxCoinIn();

			// clear output box
			output.setText("");

			// check that fields are not empty
			if (currencyInput.getText().isEmpty() || minCoinInput.getText().isEmpty()
					|| maxCoinInput.getText().isEmpty()) {
				output.setText("All input boxes must be filled.");
				return;
			}

			// check the user enters numbers only for minCoin and maxCoin
			try {
				minCoinIn = Integer.parseInt(minCoinInput.getText());
				maxCoinIn = Integer.parseInt(maxCoinInput.getText());
			} catch (Exception e2) {
				output.setText("Please enter valid numbers for the minimum and maximum coin limits");
				return;
			}

			// check that the user enters a valid code as currency
			try {
				currencyIn = Currency.getInstance(currencyInput.getText()).getCurrencyCode();
			} catch (Exception e3) {
				output.setText("Please enter a currency code similar to USD, GBP, EUR: ");
				return;
			}

			// check that min and max coin inputs are positive integers
			if (minCoinIn < 0 || maxCoinIn < 0) {
				output.setText("Please enter numbers that are greater than 0");
				return;
			}

			// check that the min coin input is less than the max coin input
			if (minCoinIn >= maxCoinIn) {
				output.setText("Minimum coin input must be less than maximum coin input.");
				return;
			}

			// Try to setup the coinSorter settings
			try {
				coinSorter.setCurrency(currencyIn);
				coinSorter.setMinCoinIn(minCoinIn);
				coinSorter.setMaxCoinIn(maxCoinIn);
				output.setText("Success!\n" + coinSorter.displayProgramConfigs());
			} catch (Exception e2) {
				System.out.println(e2.getCause());
			}

		});

		// return to welcome screen when pressing the discard button
		discardSettings.setOnAction(e -> renderMessageToScreen("Welcome", 30));

		// add labels and input fields to respective containers
		currencyInputContainer.getChildren().addAll(currencyLabel, currencyInput);
		minCoinInputContainer.getChildren().addAll(minCoinLabel, minCoinInput);
		maxCoinInputContainer.getChildren().addAll(maxCoinLabel, maxCoinInput);
		buttonsContainer.getChildren().addAll(saveSettings, discardSettings);

		// add elements to the containers
		container.getChildren().addAll(currencyInputContainer, minCoinInputContainer, maxCoinInputContainer,
				buttonsContainer);
		mainScreen.getChildren().addAll(container);
	}
}
