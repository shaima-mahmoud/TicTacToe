package code;

public class ComputerPlayer extends Player{


	public final int MAKE_ME_WIN = 4;
	public final int BLOCK_PLAYER_WINNING = 3;
	public final int TAKE_NEXT_STEP = 2;
	public final int BLOCK_PLAYER_NEXT_STEP = 1;
	public final int NO_DIFFERECE = 0;
	
	
	public ComputerPlayer(){
	}
	
	public ComputerPlayer(char s){
		this.symbol = s;
	}

	/**
	 * The computer player will evaluates all empty positions before choosing a position and returning it:
	 * <br/>
	 * 
	 * - The position that makes me win will have value of <b>4</b> <br/>
	 * - The position that will make me prevent another player winning will have
	 * a value of <b>3</b>. <br/>
	 * - The position that will be my next step to win will have a
	 * value of <b>2</b>. <br/>
	 * - The position that will make me prevent another player from taking next step will have
	 * a value of <b>1</b>. <br/>
	 * - The position that makes no different in the next step will have a value
	 * of <b>0</b>. <br/>
	 * - The filled positions will have the value of <b>-1</b>. <br/>
	 * 
	 * Note: If one position my have 2 values, the maximum value will be the
	 * one. <br/>
	 */

	@Override
	public int play(char[] gameBoard) {
		System.out.println("computer thunking :)");
		int bestPos = -1;
		int bestPosValue = -1;
		int positionValue = -1;
		for (int i = 0; i < gameBoard.length; i++) {
			if (gameBoard[i] == '-') {
				positionValue = evaluatePosition(i, gameBoard);
				if (positionValue > bestPosValue){
					bestPos = i;
					bestPosValue = positionValue;
				}
				positionValue = -1;
				if(bestPosValue==MAKE_ME_WIN)
					gameBoard[bestPos] = symbol;
			}
		}

		return bestPos;
	}
	
	
	public int evaluatePosition(int p, char[] gameBoard) {
		
		int L = (int) Math.sqrt(gameBoard.length);
		int val;
		int rightDiagonalHop = L + 1;
		int leftDiagonalHop = L - 1;
		int verticalHop = L;
		int horizontalHop = 1;
		
		
		int v1 = evaluateAtAnyDirection(p, gameBoard, rightDiagonalHop);
		if(v1 == MAKE_ME_WIN)
			return v1;
		
		
		int v2 = evaluateAtAnyDirection(p, gameBoard, leftDiagonalHop);
		if(v2 == MAKE_ME_WIN)
			return v2;
		
		
		int v3 = evaluateAtAnyDirection(p, gameBoard, verticalHop);
		if(v3 == MAKE_ME_WIN)
			return v3;
		
		
		int v4 = evaluateAtAnyDirection(p, gameBoard, horizontalHop);
		if(v4 == MAKE_ME_WIN)
			return v4;

		val = Math.max(v1, v2);
		val = Math.max(val, v3);
		val = Math.max(val, v4);

		return val;
	}

		
	private int evaluateAtAnyDirection(int p, char[] gameBoard, int hop) {
		int lastIndex = gameBoard.length-1;
		
		if (p + hop <= lastIndex && p + 2 * hop <= lastIndex) {
			if (gameBoard[p + hop] == gameBoard[p + 2 * hop] && gameBoard[p + hop] == symbol)
				return MAKE_ME_WIN;
		}
		if (p - hop >= 0 && p + hop <= lastIndex) {
			if (gameBoard[p - hop] == gameBoard[p + hop] && gameBoard[p - hop] == symbol)
				return MAKE_ME_WIN;
		}

		if (p - hop >= 0 && p - 2 * hop >= 0) {
			if (gameBoard[p - hop] == gameBoard[p - 2 * hop] && gameBoard[p - hop] == symbol)
				return MAKE_ME_WIN;
		}

		////////
		
		if (p + hop <= lastIndex && p + 2 * hop <= lastIndex) {
			if (gameBoard[p + hop] == gameBoard[p + 2 * hop] && gameBoard[p + hop] != symbol && gameBoard[p + hop] != '-')
				return BLOCK_PLAYER_WINNING;
		}

		if (p - hop >= 0 && p + hop <= lastIndex) {
			if (gameBoard[p - hop] == gameBoard[p + hop] && gameBoard[p - hop] != symbol && gameBoard[p - hop] != '-')
				return BLOCK_PLAYER_WINNING;
		}
		if (p - hop >= 0 && p - 2 * hop >= 0) {
			if (gameBoard[p - hop] == gameBoard[p - 2 * hop] && gameBoard[p - hop] != symbol && gameBoard[p - hop] != '-')
				return BLOCK_PLAYER_WINNING;
		}

//////////
		//mid
		if(p-hop>=0 && p+hop<=lastIndex){
			if( gameBoard[p-hop]==symbol && gameBoard[p+hop]=='-')
				return TAKE_NEXT_STEP;
		}
		
		if(p-hop>=0 && p+hop<=lastIndex){
			if(gameBoard[p-hop]=='-' && gameBoard[p+hop]==symbol)
				return TAKE_NEXT_STEP;
		}
		// top
		if(p+hop<=lastIndex && p+2*hop<=lastIndex){
			if(gameBoard[p+hop]=='-' && gameBoard[p+2*hop]==symbol)
				return TAKE_NEXT_STEP;
		}
		
		if(p+hop<=lastIndex && p+2*hop<=lastIndex){
			if(gameBoard[p+hop]==symbol && gameBoard[p+2*hop]=='-')
				return TAKE_NEXT_STEP;
		}
		
		// tail
		if(p-hop>=0 && p-2*hop>=0){
			if(gameBoard[p-hop]=='-' && gameBoard[p-2*hop]==symbol)
				return TAKE_NEXT_STEP;
		}
		
		if(p-hop>=0 && p-2*hop>=0){
			if(gameBoard[p-hop]==symbol && gameBoard[p-2*hop]=='-')
				return TAKE_NEXT_STEP;
		}
		
		
		
		
		
		//////////
		//mid
		if(p-hop>=0 && p+hop<=lastIndex){
			if(gameBoard[p+hop]=='-' && gameBoard[p-hop]!=symbol && gameBoard[p-hop]!='-')
				return BLOCK_PLAYER_NEXT_STEP;
		}
		
		if(p-hop>=0 && p+hop<=lastIndex){
			if(gameBoard[p-hop]=='-' && gameBoard[p+hop]!=symbol && gameBoard[p+hop]!='-')
				return BLOCK_PLAYER_NEXT_STEP;
		}
		///// top
		if(p+hop<=lastIndex && p+2*hop<=lastIndex){
			if(gameBoard[p+hop]=='-' && gameBoard[p+2*hop]!=symbol && gameBoard[p+2*hop]!='-')
				return BLOCK_PLAYER_NEXT_STEP;
		}
		
		if(p+hop<=lastIndex && p+2*hop<=lastIndex){
			if(gameBoard[p+2*hop]=='-' && gameBoard[p+hop]!=symbol && gameBoard[p+hop]!='-')
				return BLOCK_PLAYER_NEXT_STEP;
		}
		
		///// tail
		if(p-hop>=0 && p-2*hop>=0){
			if(gameBoard[p-hop]=='-' && gameBoard[p-2*hop]!=symbol && gameBoard[p-2*hop]!='-')
				return BLOCK_PLAYER_NEXT_STEP;
		}
		
		if(p-hop>=0 && p-2*hop>=0){
			if(gameBoard[p-2*hop]=='-' && gameBoard[p-hop]!=symbol && gameBoard[p-hop]!='-')
				return BLOCK_PLAYER_NEXT_STEP;
		}
		/////
		
		return NO_DIFFERECE;
	
	}
	
}
