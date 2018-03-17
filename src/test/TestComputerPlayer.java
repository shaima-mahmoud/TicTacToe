package test;

import org.junit.Assert;
import org.junit.Test;

import code.ComputerPlayer;
import code.HumanPlayer;

public class TestComputerPlayer {

	HumanPlayer a = new HumanPlayer('a');
	HumanPlayer b = new HumanPlayer('b');
	ComputerPlayer c = new ComputerPlayer('C');
	
	
	
	@Test
	public void testEvaluatePosition(){
		char[] gameboard = {'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		int val = c.evaluatePosition(0, gameboard);
		Assert.assertEquals(c.NO_DIFFERECE, val);;
	}
	
	
	@Test
	public void testEvaluatePosition2(){
		char[] gameboard = {'-' , 'A', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		int val = c.evaluatePosition(0, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);;
	}
	
	@Test
	public void testEvaluatePosition3(){
		char[] gameboard = {'A' , 'A', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		int val = c.evaluatePosition(2, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_WINNING, val);;
	}
	
	
	@Test
	public void testEvaluatePosition4(){
		char[] gameboard = {'A' , '-', '-', '-' , '-',
							'-' , 'B', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		int val = c.evaluatePosition(1, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
		
		val = c.evaluatePosition(2, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
		
		val = c.evaluatePosition(5, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
		
		val = c.evaluatePosition(7, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
		
		val = c.evaluatePosition(10, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
		
		val = c.evaluatePosition(11, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
		
		val = c.evaluatePosition(12, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_NEXT_STEP, val);
	}
	
	
	
	@Test
	public void testEvaluatePosition5(){
		char[] gameboard = {'A' , '-', '-', '-' , '-',
							'-' , 'B', '-', '-' , '-',
							'-' , '-', 'B', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		int val = c.evaluatePosition(18, gameboard);
		Assert.assertEquals(c.BLOCK_PLAYER_WINNING, val);
	}
}
