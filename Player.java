
public class Player
{
	int leftHand; 
	int rightHand;
	//Hand values range from 0 to 4 representing extended fingers on hand.
	//5 fingers on the hand wraps back to 0.
	
	boolean inGame;
	//A player is eliminated if both of their hands have 0 extended fingers
	
	//All players start with 1 extended finger on each hand
	public Player()
	{
		leftHand = 1;
		rightHand = 1;
		inGame = true;
	}
	
	/**
	 * 
	 * @param targetPlayer
	 * @param playerHand
	 * @param targetHand
	 * @return
	 */
	public boolean attack(Player targetPlayer, String playerHand, String targetHand)
	{
		if(!targetPlayer.getStatus())
		{
			System.out.println("That Player is already eliminated!\n");
			return false;
		}
		if((playerHand.equals("l") || playerHand.equals("left")) && (targetHand.equals("l") || targetHand.equals("left")))
		{
			targetPlayer.setLeft((targetPlayer.getLeft() + this.getLeft()) % 5);
		}
		else if((playerHand.equals("l") || playerHand.equals("left")) && (targetHand.equals("r") || targetHand.equals("right")))
		{
			targetPlayer.setRight((targetPlayer.getRight() + this.getLeft()) % 5);
		}
		else if((playerHand.equals("r") || playerHand.equals("right")) && (targetHand.equals("l") || targetHand.equals("left")))
		{
			targetPlayer.setLeft((targetPlayer.getLeft() + this.getRight()) % 5);
		}
		else if((playerHand.equals("r") || playerHand.equals("right")) && (targetHand.equals("r") || targetHand.equals("right")))
		{
			targetPlayer.setRight((targetPlayer.getRight() + this.getRight()) % 5);
		} 
		if(targetPlayer.getRight() == 0 && targetPlayer.getLeft() == 0)
		{
			targetPlayer.setStatus(false);
		}
		return true;
	}
	
	public void setStatus(boolean status)
	{
		inGame = status;
	}
	
	public boolean getStatus()
	{
		return inGame;
	}
	
	public void setLeft(int left)
	{
		leftHand = left;
	}
	
	public int getLeft()
	{
		return leftHand;
	}
	
	public void setRight(int right)
	{
		rightHand = right;
	}
	public int getRight()
	{
		return rightHand;
	}
	
}
