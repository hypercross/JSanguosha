package game.entity;


import game.ICard;
import game.IEntityContainer;
import game.type.Type;

@SuppressWarnings("serial")
public class CardEntity extends Entity{

	public int cardID;	// id in the environment
	public int number;	// poker number
	public char suit;	// poker suit
	public CardSlotEntity container;
	public ICard prototype;
	
	public CardEntity(int cardID, int number, char suit, ICard prototype)
	{
		this.cardID = cardID;
		this.number = number;
		this.suit = suit;
		
		this.prototype = prototype;
		this.type = prototype.cardType();
		this.name = type.toString();
	}
	
	public CardEntity(int num, char suit, Type type)
	{
		this.number = num;
		this.suit = suit;
		this.type = type;
		this.name = type.toString();
	}
	
	public IEntityContainer parent()
	{
		return container;
	}
	
	public String toString()
	{
		return this.type.toString() + " " + number + ":" + suit + " " + cardID;
	}
}
