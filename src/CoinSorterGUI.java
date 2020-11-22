import java.util.ArrayList;
import java.util.Arrays;

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
import javafx.stage.Stage;

public class CoinSorterGUI extends Application {
	private static final String COIN_CALCULATOR = "COIN_CALCULATOR";
	private static final String MULTI_COIN_CALCULATOR = "MULTI_COIN_CALCULATOR";
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	BorderPane mainContainer;
	VBox mainScreen;
	HBox header;
	VBox menu;
	Text mainScreenDescription;
	TextArea output;
	ArrayList<Integer> denominations;
	CoinSorter coinSorter;

	public void start(Stage stage) {
		// Initialise the coinSorter object with default parameters
		denominations = new ArrayList<>(Arrays.asList(200, 100, 50, 20, 10));
		coinSorter = new CoinSorter("GBP", 0, 10000, denominations);

		// initialise containers
		header = new HBox();
		menu = new VBox(10);
		mainContainer = new BorderPane();
		mainScreen = new VBox();

		// create application title
		Text title = new Text("Coin Sorter");

		// create box to show the outputs
		output = new TextArea();
		output.setEditable(false);

		// create menu
		Text menuTitle = new Text("Menu");
		Button option1 = new Button("Coin Calculator");
		Button option2 = new Button("Multiple Coin Calculator");
		Button option3 = new Button("Print Coin List");
		Button option4 = new Button("Set Details");
		Button option5 = new Button("Display Program Configuration");
		Button option6 = new Button("Quit the program");

		// set event handlers
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
			renderMessageToScreen(coinSorter.printCoinList());
		});
		option4.setOnAction(e -> {
			clearScreen();
			renderSubMenu();
		});
		option5.setOnAction(e -> {
			clearScreen();
			renderMessageToScreen(coinSorter.displayProgramConfigs());
		});
		option6.setOnAction(e -> stage.close());

		// set alignment of boxes
		menu.setAlignment(Pos.CENTER);
		header.setAlignment(Pos.CENTER);
		mainScreen.setAlignment(Pos.CENTER);

		// TODO set CSS classes
		header.getStyleClass().add("header");
		menu.getStyleClass().add("menu");
		output.getStyleClass().add("output");

		output.setMaxSize(Double.MAX_VALUE, 100);

		// add elements to boxes
		header.getChildren().add(title);
		menu.getChildren().addAll(menuTitle, option1, option2, option3, option4, option5, option6);
		mainContainer.setCenter(mainScreen);
		mainContainer.setBottom(output);

		// Show welcome screen
		renderMessageToScreen("Welcome");

		// setup root element
		BorderPane root = new BorderPane();
		root.setTop(header);
		root.setLeft(menu);
		root.setCenter(mainContainer);
		root.setPrefSize(WIDTH, HEIGHT);

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
	 */
	private void renderMessageToScreen(String message) {
		clearScreen();
		mainScreenDescription = new Text(message);
		mainScreen.getChildren().addAll(mainScreenDescription);
	}

	/**
	 * Function to render the input fields and description of each calculator
	 * 
	 * @param view The calculator view to render
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

		// TODO set CSS Classes
		amountLabel.setFont(Font.font(20));
		amountLabel.setMinWidth(200);
		amountLabel.setAlignment(Pos.CENTER_RIGHT);
		amountInput.setMaxWidth(200);
		denominationLabel.setFont(Font.font(20));
		denominationLabel.setAlignment(Pos.CENTER_RIGHT);
		denominationLabel.setMinWidth(200);
		denominationInput.setMaxWidth(200);

		// add labels and input fields to respective containers
		amountInputContainer.getChildren().addAll(amountLabel, amountInput);
		denominationInputContainer.getChildren().addAll(denominationLabel, denominationInput);

		// logic to run when the user clicks the calculate button
		calculateButton.setOnAction(e -> {
			// check that fields are not empty
			if (amountInput.getText().isEmpty() || denominationInput.getText().isEmpty()) {
				output.setText("Currency and denomination inputs must be filled.");
				return;
			}

			Integer amountIn;
			Integer denominationIn;
			int minCoin = coinSorter.getMinCoinIn();
			int maxCoin = coinSorter.getMaxCoinIn();

			// check the user enters numbers only
			try {
				amountIn = Integer.parseInt(amountInput.getText());
				denominationIn = Integer.parseInt(denominationInput.getText());
			} catch (Exception e2) {
				output.setText("Please enter valid numbers");
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
			mainScreenDescription = new Text("COIN CALCULATOR");
		} else {
			mainScreenDescription = new Text("MULTPLE COIN CALCULATOR");
		}

		// add input fields, labels and the calculate button to the main screen
		container.getChildren().addAll(mainScreenDescription, amountInputContainer, denominationInputContainer,
				calculateButton);
		mainScreen.getChildren().add(container);
	}

	/**
	 * Renders the submenu to setup currency, min, and max input limits
	 */
	private void renderSubMenu() {
		mainScreenDescription = new Text("SUB MENU");

		// containers that will hold the labels and input fields
		HBox currencyInputContainer = new HBox(20);
		HBox minCoinInputContainer = new HBox(20);
		HBox maxCoinInputContainer = new HBox(20);
		HBox buttonsContainer = new HBox(20);

		// create labels
		Label currencyLabel = new Label("Currency");
		Label minCoinLabel = new Label("Minimum Coin Input");
		Label maxCoinLabel = new Label("Maximum Coin Input");

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
			// clear output box
			output.setText("");

			// check that fields are not empty
			if (currencyInput.getText().isEmpty() || minCoinInput.getText().isEmpty()
					|| maxCoinInput.getText().isEmpty()) {
				output.setText("Currency and denomination inputs must be filled.");
				return;
			}

			Integer minCoinIn;
			Integer maxCoinIn;
			String currencyIn = currencyInput.getText();
			int minCoin = coinSorter.getMinCoinIn();
			int maxCoin = coinSorter.getMaxCoinIn();

			// check the user enters numbers only for minCoin and maxCoin
			try {
				minCoinIn = Integer.parseInt(minCoinInput.getText());
				maxCoinIn = Integer.parseInt(maxCoinInput.getText());
			} catch (Exception e2) {
				output.setText("Please enter valid numbers for the minimum and maximum coin limits");
				return;
			}

			// check that the min and max limits are valid
			if (minCoinIn > maxCoin) {
				output.setText("Minimum coin input must be less than " + maxCoin);
			} else if (maxCoinIn < minCoin) {
				output.setText("Maximum coin input must be greater than " + minCoin);
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
		discardSettings.setOnAction(e -> renderMessageToScreen("Welcome"));

		// TODO set styles
		currencyLabel.setFont(Font.font(20));
		currencyInput.setMaxWidth(200);
		minCoinLabel.setFont(Font.font(20));
		minCoinInput.setMaxWidth(200);
		maxCoinLabel.setFont(Font.font(20));
		maxCoinInput.setMaxWidth(200);

		// add labels and input fields to respective containers
		currencyInputContainer.getChildren().addAll(currencyLabel, currencyInput);
		minCoinInputContainer.getChildren().addAll(minCoinLabel, minCoinInput);
		maxCoinInputContainer.getChildren().addAll(maxCoinLabel, maxCoinInput);
		buttonsContainer.getChildren().addAll(saveSettings, discardSettings);

		mainScreen.getChildren().addAll(mainScreenDescription, currencyInputContainer, minCoinInputContainer,
				maxCoinInputContainer, buttonsContainer);
	}

}
