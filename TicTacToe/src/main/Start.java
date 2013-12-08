package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	// Fields
	private Game game;

	// Properties
	private Button[][] buttons = new Button[3][3];

	private Label label;

	// Lifecycle Events
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tic-Tac-Toe");

		MenuBar menuBar = setMenu();
		GridPane grid = setGridpane();
		label = new Label("");

		VBox vBox = new VBox(10);
		vBox.getChildren().add(menuBar);
		vBox.getChildren().add(grid);
		vBox.getChildren().add(label);

		Scene scene = new Scene(vBox, 300, 275);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		game = new Game(this);
	}

	// Methods
	public void reset() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				buttons[x][y].setText("");
			}
		}
	}

	public void setButtonText(String text, int x, int y) {
		buttons[x][y].setText(text);
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();

		// --- Menu File
		Menu menuGame = new Menu("Game");
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				game.init();
			}
		});
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});
		menuGame.getItems().addAll(newGame, exit);
		menuBar.getMenus().add(menuGame);

		return menuBar;

	}

	private GridPane setGridpane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(1);
		grid.setVgap(1);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final int x = i;
				final int y = j;
				buttons[x][y] = new Button("");
				buttons[x][y].setPrefSize(60, 60);
				buttons[x][y].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						game.click(x, y);
					}
				});

				grid.add(buttons[x][y], x, y);
			}
		}

		return grid;

	}

}