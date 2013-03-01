package gameEvent;

import game.entity.Entity;

public abstract class Rule {
	
	Entity owner;
	public Rule(Entity owner)
	{
		this.owner = owner;
	}
	
	public abstract boolean trigger(GameEvent ge);
	
	public Entity owner()
	{
		return owner;
	}
}
