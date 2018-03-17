package code;

import java.util.Scanner;

public class HumanPlayer extends Player{

	public HumanPlayer(char s){
		this.symbol = s;
	}

	@Override
	public int play(char[] gameboard) {
		int L = (int)Math.sqrt(gameboard.length);
		Scanner sc = new Scanner(System.in);
		String entry = "";
		
		while(entry.isEmpty()){
			System.out.println("Enter your position like this x,y : ");
			entry = sc.nextLine().trim();
			
			if(!entry.matches("[0-9],[0-9]")){
				System.out.println("Ooh oh \u2639, entry doesn't seem OK, Try again !");
				entry="";
				continue;
			}
		
			String[] indeces = entry.split(",");
			int i = Integer.parseInt(indeces[0]);
			int j = Integer.parseInt(indeces[1]);
			
			if((i>=0 && i<L)&&(j>=0 && j<L)){
				if(gameboard[i*L+j]!='-')
					System.out.println("Ooh oh \u2639, this move is already occupied, Try again !");
				return i*L+j;
			}else{
				System.out.println("Ooh oh \u2639, this position is out of boundry, Try again !");
			}
		}
		return -1;
	}

	
}
