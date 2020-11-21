import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

public class CoinSorterGUI extends Application {

	public void start(Stage stage) {
		// START HEADER
		Text title = new Text("Coin Sorter");
		title.setFont(new Font(32));

		HBox header = new HBox();
		header.setAlignment(Pos.CENTER);
		header.getChildren().add(title);

		// END HEADER

		// START LINKS
		Button link1 = new Button("Coin Calculator");
		Button link2 = new Button("Multiple Coin Calculator");
		Button link3 = new Button("Print Coin List");
		Button link4 = new Button("Set Details");
		Button link5 = new Button("Display Program Configuration");
		Button link6 = new Button("Quit the program");

		VBox links = new VBox();
		links.setAlignment(Pos.CENTER);
		links.getChildren().addAll(link1, link2, link3, link4, link5, link6);
		// END LINKS

		// START CONTENT
		Text henlo = new Text("Henlo");

		// END CONTENT

		BorderPane root = new BorderPane();
		root.setTop(header);
		root.setLeft(links);
		root.setCenter(henlo);
		root.setPrefSize(800, 800);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Coin Sorter");
		stage.show();
	}

}
