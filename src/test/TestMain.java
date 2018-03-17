package test;

import java.util.Arrays;


import org.junit.Assert;
import org.junit.Test;

import code.Main;

public class TestMain {

	Main m = new Main();
	
	
	@Test
	public void testReagConfig(){
		m.readGameConfig();
		Assert.assertEquals('A', m.players[0].getSymbol());
		Assert.assertEquals('B', m.players[1].getSymbol());
		Assert.assertEquals('C', m.players[2].getSymbol());
		Assert.assertEquals('C', m.players[2].getSymbol());
	}


	@Test
	public void testValidateNextMove(){
		char[] gameboard = {'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		m.gameBoard = gameboard; 
		Assert.assertEquals(true, m.validateNextMove(0));
	}

	@Test
	public void testValidateNextMove2(){
		char[] gameboard = {'A' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		m.gameBoard = gameboard;
		Assert.assertEquals(false, m.validateNextMove(0));
	}
	
	@Test
	public void testfindAWinner(){
		char[] gameboard = {'A' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		m.gameBoard = gameboard;
		Assert.assertEquals(null, m.findAWinner());
	}
	
	@Test
	public void testfindAWinner2(){
		char[] gameboard = {'A' , '-', '-', '-' , '-',
							'-' , 'A', '-', '-' , '-',
							'-' , '-', 'A', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		m.gameBoard = gameboard;
		Assert.assertEquals('A', m.findAWinner().charValue());
	}
	
	public void testfindAWinner3(){
		char[] gameboard = {'A' , 'C', 'C', 'C' , '-',
							'-' , 'B', '-', '-' , '-',
							'-' , '-', 'A', '-' , '-',
							'-' , '-', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		m.gameBoard = gameboard;
		Assert.assertEquals('C', m.findAWinner().charValue());
	}
	
	
	@Test
	public void testfindAWinner4(){
		char[] gameboard = {'A' , 'C', '-', '-' , '-',
							'A' , 'B', '-', '-' , '-',
							'C' , 'B', 'A', '-' , '-',
							'-' , 'B', '-', '-' , '-',
							'-' , '-', '-', '-' , '-'};
		m.gameBoard = gameboard;
		Assert.assertEquals('B', m.findAWinner().charValue());
	}
}
