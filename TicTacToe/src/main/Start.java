package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	//Fields
	private Game game = new Game(this);
	
	//Properties
	private Button[][] buttons = new Button[3][3];

	//Lifecycle Events
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tic-Tac-Toe");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final int x = i;
				final int y = j;
				buttons[x][y] = new Button("");
				buttons[x][y].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						game.click(x, y);
					}
				});

				grid.add(buttons[x][y], x, y);
			}
		}

		primaryStage.show();
	}
	
	//Methods
	public void setButtonText(String text,int x, int y){
		buttons[x][y].setText(text);
	}

}