package game.entity;


import game.IEntityContainer;
import game.type.Type;

@SuppressWarnings("serial")
public class CardEntity extends Entity{

	public int cardID;	// id in the environment
	public int number;	// poker number
	public char suit;	// poker suit
	public Entity container;
	public Object prototype;
	
	public CardEntity(int cardID, int number, char suit, Object prototype)
	{
		this.cardID = cardID;
		this.number = number;
		this.suit = suit;
		
		this.prototype = prototype;
		this.type = Type.fromAnnotation(prototype);
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
	
	public boolean moveTo(Entity cse){
		
		if(cse != null)
			cse.add(this);
		else return false;
		
		if(this.container != null)
			this.container.remove(this);
		
		this.container = cse;
		return true;
	}
}
