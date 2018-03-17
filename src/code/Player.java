package code;

public abstract class Player {

	char symbol;
	
	public abstract int play(char[] gameboard);

	public char getSymbol() {
		return symbol;
	}
}
