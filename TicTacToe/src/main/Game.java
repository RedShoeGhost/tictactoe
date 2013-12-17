package main;

import java.util.Random;

public class Game {

	private Start view;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private String[][] field;
	private boolean running = false;

	Game(Start _view) {
		this.view = _view;
		init();
	}

	public void init() {
		this.view.reset();
		this.field = new String[3][3];
		initPlayer();
		setRandomCurrentPlayer();
		this.running = true;
	}

	public void click(int x, int y) {
		if (this.running && !currentPlayer.isCPU() && isFieldFree(x, y)) {
			setField(currentPlayer.getSymbol(), x, y);
			if (isPlayerWon()) {
				this.running = false;
				view.setLabelText(currentPlayer.getName() + " hat gewonnen.");
			} else {
				switchPlayer();
			}
		}
	}
	
	private void setRandomCurrentPlayer(){
		Random r = new Random();
		this.currentPlayer = (r.nextBoolean()) ? player1 : player2;
		switchPlayer(); //Stoﬂt die KI an.
	}
	
	private void initPlayer(){
		this.player1 = new Player(this.view.GetPlayerName(1), "X", this.view.GetPlayerKI(1));
		this.player2 = new Player(this.view.GetPlayerName(2), "O", this.view.GetPlayerKI(2));
	}

	private boolean isFieldFree(int x, int y) {
		return field[x][y] == null;
	}

	private void setField(String symbol, int x, int y) {
		field[x][y] = symbol;
		view.setButtonText(symbol, x, y);
	}

	private boolean isPlayerWon() {
		String symbol = currentPlayer.getSymbol();
		return (field[0][0] == symbol && field[0][1] == symbol && field[0][2] == symbol)
				|| (field[1][0] == symbol && field[1][1] == symbol && field[1][2] == symbol)
				|| (field[2][0] == symbol && field[2][1] == symbol && field[2][2] == symbol)
				|| (field[0][0] == symbol && field[1][0] == symbol && field[2][0] == symbol)
				|| (field[0][1] == symbol && field[1][1] == symbol && field[2][1] == symbol)
				|| (field[0][2] == symbol && field[1][2] == symbol && field[2][2] == symbol)
				|| (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol)
				|| (field[2][0] == symbol && field[1][1] == symbol && field[0][2] == symbol);
	}

	private void switchPlayer() {
		if (!isFieldFull()) {
			currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
			view.setLabelText(currentPlayer.getName() + " ist dran.");

			if (currentPlayer.isCPU()) {
				int[] choice = currentPlayer.getTurn(field);
				setField(currentPlayer.getSymbol(), choice[0], choice[1]);
				if (isPlayerWon()) {
					this.running = false;
					view.setLabelText(currentPlayer.getName()
							+ " hat gewonnen.");
				} else {
					switchPlayer();
				}
			}
		} else {
			view.setLabelText("Unentschieden");
		}
	}

	private boolean isFieldFull() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if(this.field[x][y] == null) return false;
			}
		}
		return true;
	}
}
