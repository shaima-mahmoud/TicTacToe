package code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	public static Player[] players = new Player[3];
	public static char[] gameBoard;
	
	public static void main(String[] args) {
		
		gameBoard = readGameConfig();
		printBoard();
		Character winner = null;
		int role = randomStart();
		int emptyPositions=gameBoard.length;
		
		while (winner == null && emptyPositions>0) {
			System.out.println("Hey " + players[role%3].getSymbol()+", Are you ready, take a move!");
			int move = players[role%3].play(gameBoard);
			boolean b = validateNextMove(move);
			
			if(b==true){
				gameBoard[move] = players[role%3].getSymbol();
				printBoard();
				winner = findAWinner();
				role++;
				emptyPositions--;
			}
		}
		if(emptyPositions==0 && winner==null){
			System.out.println("Game closed, No winner !");
			return;
		}
		System.out.println("\nCongrats " + winner + ", you win!");
	}


	private static int randomStart() {
		return (int)(Math.random()*3);
	}
	

	private static void printBoard() {
		int L = (int) Math.sqrt(gameBoard.length);
		for (int i = 0; i < gameBoard.length; i++) {

			System.out.print(gameBoard[i] + "\t");
			if ((i + 1) % L == 0)
				System.out.println();
		}

	}

	/**
	 * Validates the chosen move by the player 
	 * The player choose an <i>(i,j)</i> position as if the gameboard it is a
	 * 2-D array, but since it is actually a 1-D array.<br/>
	 * The new position in the 1-D array is calculated as follows:<br/>
	 * <b><i>i*L+j</i></b>
	 * @param gameBoard 
	 */

	public static boolean validateNextMove(int i) {
		int L = (int) Math.sqrt(gameBoard.length);
		
		if(gameBoard[i]=='-')		
			return true;
		return false;
	}
	
	/**
	 * Reads the configuration of the game, players characters and board side length.
	 * And initializes the game board if all the entries are valid. 
	 * @return char[] representing the game board
	 */

	public static char[] readGameConfig() {
		
		Properties prop = new Properties();
		InputStream input = null;
		
		int size=0;
		String prop1="";
		String prop2="";
		String prop3="";
		
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			
			prop1 = prop.getProperty("player1").trim();
			prop2 = prop.getProperty("player2").trim();
			prop3 = prop.getProperty("computer_player").trim();
			size = Integer.parseInt(prop.getProperty("board_size").trim());
			if(prop1.equals("")|| prop2.equals("")|| prop3.equals("")){
				System.err.println("Please make sure you configured all the players character \u263A . Check the config file and try again");
				return null ;
			}
			if(!(size>=3 && size<=10)){
				System.err.println("This is board size is not allowed, a size between 3 and 10 is OK \u263A . Check the config file and try again");
			return null;
			}
			players[0] = new HumanPlayer(prop1.charAt(0));
			players[1] = new HumanPlayer(prop2.charAt(0));
			players[2] = new ComputerPlayer(prop3.charAt(0));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		char[] board = new char[size * size];
		for (int i = 0; i < board.length; i++)
			board[i] = '-';
		return board;
	}

	/**
	 * <b>Winning Cases:</b> <br/>
	 * <b><i>i</i></b> is a starting position, <b><i>L</i></b> is the side
	 * length of the board <br/>
	 * <br/>
	 * - Right Diagonal \: i , i+(L+1) , i+2(L+1) <br/>
	 * - Left Diagonal /: i , i+(L-1) , i+2(L-1) <br/>
	 * - Vertical : i , i+L , i+2L <br/>
	 * - Horizontal : i , i+1 , i+2 <br/>
	 */
	public static Character findAWinner() {
		int L = (int) Math.sqrt(gameBoard.length);
		for (int i = 0; i < gameBoard.length; i++) {
			if (gameBoard[i] == '-')
				continue;
			try {
				if ((gameBoard[i] == gameBoard[i + L + 1]) && (gameBoard[i] == gameBoard[i + 2 * (L + 1)]))
					return gameBoard[i];

				else if ((gameBoard[i] == gameBoard[i + L - 1]) && (gameBoard[i] == gameBoard[i + 2 * (L - 1)]))
					return gameBoard[i];

				else if ((gameBoard[i] == gameBoard[i + L]) && (gameBoard[i] == gameBoard[i + 2*L]))
					return gameBoard[i];

				else if ((gameBoard[i] == gameBoard[i + 1]) && (gameBoard[i] == gameBoard[i + 2]))
					return gameBoard[i];

			} catch (ArrayIndexOutOfBoundsException ex) {
				continue;
			}
		}
		return null;
	}

}
