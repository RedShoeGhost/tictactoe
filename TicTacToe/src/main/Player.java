package main;

public class Player {
	private String name;
	private String symbol;
	private boolean cpu;
	private String difficulty;

	Player(String name, String symbol, String difficulty) {
		this.name = name;
		this.symbol = symbol;

		switch (difficulty) {
		case "Easy":
			this.cpu = true;
			this.difficulty = difficulty;
			break;
		case "Hard":
			this.difficulty = difficulty;
			break;
		default:
			this.cpu = false;
			break;
		}
	}

	public String getName() {
		return this.name;
	}

	public String getSymbol() {
		return this.symbol;
	}
	
	public boolean isCPU(){
		return cpu;
	}
}
