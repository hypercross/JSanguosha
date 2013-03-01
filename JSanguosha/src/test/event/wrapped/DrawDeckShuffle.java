package test.event.wrapped;

import game.api.EventResolve;
import game.api.Type;
import game.entity.CardEntity;
import game.entity.Entity;
import gameEvent.GameEventEngine;

@Type("Type.Event.DrawDeckShuffle")
public class DrawDeckShuffle {
	
	@EventResolve
	public boolean resolve()
	{
		for(Object obj : GameEventEngine.the_game.child("discardDeck").toArray())
		{
			CardEntity ce = (CardEntity)obj;
			ce.moveTo((Entity) GameEventEngine.the_game.child("drawDeck"));
		}
		
		return false;
	}
}
