package test.event.wrapped;

import game.api.EventContext;
import game.api.EventResolve;
import game.api.Type;
import game.entity.CardEntity;
import game.entity.Entity;
import gameEvent.GameEventEngine;

@Type("Type.Event.CardMove.CardDiscard")
public class CardDiscard {

	@EventContext
	CardEntity card;
	
	@EventResolve
	public boolean discard()
	{
		card.moveTo((Entity) GameEventEngine.the_game.child("discardDeck"));
		return false;
	}
	
}
