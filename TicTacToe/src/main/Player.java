package main;

public class Player {
	private String name;
	private String symbol;
	private boolean cpu;
	private KI ki;

	Player(String name, String symbol, String difficulty) {
		this.name = name;
		this.symbol = symbol;

		switch (difficulty) {
		case "Easy":
			this.cpu = true;
			this.ki = new KI(0, symbol);
			break;
		case "Hard":
			this.cpu = true;
			this.ki = new KI(1, symbol);
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
	
	public int[] getTurn(String[][] field){
		return this.ki.getTurn(field);
		}
}
