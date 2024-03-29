package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KI {
	private int difficulty;
	private String symbol;
	private double[] difficultyLevel = new double[2];
	private String[][] field;

	KI(int difficulty, String symbol) {
		difficultyLevel[0] = 0.8f;
		difficultyLevel[1] = 1.0f;
		this.symbol = symbol;
		this.difficulty = difficulty;
	}

	public int[] getTurn(String[][] field) {
		this.field = field;
		double successRate = difficultyLevel[this.difficulty];

		if (isSuccess(successRate)) {
			int[] winTurn = getWinTurn(true);
			if (winTurn != null) {
				System.out.println("win possible");
				return winTurn;
			}
		}
		if (isSuccess(successRate)) {
			int[] winTurn = getWinTurn(false);
			if (winTurn != null) {
				System.out.println("lose possible");
				return winTurn;
			}
		}
		return getRandomTurn();
	}

	private boolean isSuccess(double successRate) {
		double random = Math.random();
		return random <= successRate;
	}

	private int[] getWinTurn(boolean me) {
		String symboltemp;
		if (me){
			symboltemp = (this.symbol == "X") ? "X" : "O";
		} else {
			symboltemp = (this.symbol == "X") ? "O" : "X";
		}
		List<String> winFields = new ArrayList<String>();
		winFields.add("0,0,0,1,0,2");
		winFields.add("1,0,1,1,1,2");
		winFields.add("2,0,2,1,2,2");
		winFields.add("0,0,1,0,2,0");
		winFields.add("0,1,1,1,2,1");
		winFields.add("0,2,1,2,2,2");
		winFields.add("0,0,1,1,2,2");
		winFields.add("2,0,1,1,0,2");

		for (int i = 0; i < winFields.size(); i++) {
			String[] set = winFields.get(i).split(",");
			int count = 0;
			int[] theField = new int[2];//Das feld, welches vielleicht zur�ckgegeben wird
			for (int j = 0; j < 6; j += 2) {
				int x = Integer.parseInt(set[j]);
				int y = Integer.parseInt(set[j + 1]);
				String fieldCharacter = this.field[x][y];//immer  nulljop aber was ist der fehler hm siehst den fehler?  symboltemp = O
				if(fieldCharacter == null){
					theField[0] = x;
					theField[1] = y;
				} else if(fieldCharacter == symboltemp){
					count++;
				} else {
					count--;
					break;
				}
			}
			if(count == 2){
				return theField;
			}
		}
		return null;
	}

	private int[] getRandomTurn() {
		List<int[]> emptyFields = new ArrayList<int[]>();
		int length = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (this.field[x][y] == null) {
					int[] values = { x, y };
					emptyFields.add(values);
					length++;
				}
			}
		}
		Random r = new Random();
		int random = r.nextInt(length);

		return emptyFields.get(random);
	}
}
