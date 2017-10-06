import java.util.Scanner;

public class Sticks {
	
	public static void main(String args[])
	{
		Scanner kb = new Scanner(System.in);
		int numPlayers = 0;
		
		System.out.println("The Game of Sticks (Rules):\n"
							+ "2 to 4 Players\n"
							+ "Each player has a right and left hand whose finger values range from 0 to 4.\n"
							+ "Both players start with one finger on each each hand.\n"
							+ "Players take turns using a hand to hit an opponent's hand.\n"
							+ "The player whose hand was hit adds the fingers on the opponent's hand to their hand.\n"
							+ "If the number of fingers would exceed or equal 5, subtract 5 fingers.\n"
							+ "A player is eliminated when both his hands have 0 fingers.\n");
		
		while(numPlayers < 2 || numPlayers > 4)
		{
			try 
			{
				System.out.println("Enter the number of players (2 to 4): ");
				numPlayers = kb.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Enter 2, 3, or 4 as the number of players!");
				numPlayers = 0;
				kb.next();
				continue;
			}
			if(numPlayers < 2 || numPlayers > 4)
			{
				System.out.println("Enter 2, 3, or 4 as the number of players!");
			}
		}
		
		Player[] players = new Player[numPlayers];
		
		for (int i = 0; i < players.length; i++)
		{
			players[i] = new Player();
		}
		printState(players);
		
		boolean winner = false;
		
		while(checkWinner(players))
		{
			for(int i = 0; i < players.length; i++)
			{
				int target = 0;
				String playerHand = "";
				String targetHand = "";
				if(!players[i].getStatus())
				{
					System.out.println("Player " + (i + 1) +" is eliminated\n");
					continue; 
				}
				while((target <= 0 || target > players.length) || target == i + 1)
				{
					try 
					{
						System.out.println("It is Player " + (i + 1) + "'s turn. Select a player you wish to attack: ");
						target = kb.nextInt();
					}
					catch(Exception e)
					{
						System.out.println("Enter a valid player number that is not yourself");
						target = 0;
						kb.next();
						continue;
					}
					if((target <= 0 || target > players.length) || target == i + 1)
					{
						System.out.println("Enter a valid player number that is not yourself");
						continue;
					}
					if(!players[target - 1].getStatus())
					{
						System.out.println("That player is already eliminated. Pick another player");
						target = 0;
					}
				}
				kb.nextLine();
				while(!playerHand.equals("r") && !playerHand.equals("right") && !playerHand.equals("l") && !playerHand.equals("left"))
				{
					System.out.println("It is Player " + (i + 1) + "'s turn. Select the hand you wish to use r or right or l or left: ");
					playerHand = kb.nextLine();
					if(!playerHand.equals("r") && !playerHand.equals("right") && !playerHand.equals("l") && !playerHand.equals("left"))
					{
						System.out.println("Valid inputs are: r or right and l or left");
					}
					if((playerHand.equals("r") || playerHand.equals("right")) && players[i].getRight() == 0)
					{
						System.out.println("Cannot attack with right hand because it has 0 fingers");
						playerHand = "";
					}
					if((playerHand.equals("l") || playerHand.equals("left")) && players[i].getLeft() == 0)
					{
						System.out.println("Cannot attack with left hand because it has 0 fingers");
						playerHand = "";
					}
				}
				while(!targetHand.equals("r") && !targetHand.equals("right") && !targetHand.equals("l") && !targetHand.equals("left"))
				{
					System.out.println("It is Player " + (i + 1) + "'s turn. Select the target's hand you wish to attack r or right or l or left: ");
					targetHand = kb.nextLine();
					if(!targetHand.equals("r") && !targetHand.equals("right") && !targetHand.equals("l") && !targetHand.equals("left"))
					{
						System.out.println("Valid inputs are: r or right and l or left");
					}
				}
				players[i].attack(players[target - 1], playerHand, targetHand); 
				if(!checkWinner(players))
				{
					winner = true;
					break;
				}
				printState(players);
			}
			if(winner == true)
			{
				break;
			}
		}
		kb.close();
	}
	
	public static void printState(Player[] players)
	{
		for(int i = 0; i < players.length; i++)
		{
			System.out.println("Player " + (i + 1) + "- Right: " + players[i].getRight() + "Left: " + players[i].getLeft());
		}
		System.out.println();
	}
	
	public static boolean checkWinner(Player[] players)
	{
		int stillIn = 0;
		int winner = 0;
		
		for(int i = 0; i < players.length; i++)
		{
			if(players[i].getStatus())
			{
				stillIn++;
				winner = i + 1;
			}
		}
		if(stillIn == 1)
		{
			System.out.println("The Winner is Player " + winner);
			return false;
		}
		return true;
	}

}
