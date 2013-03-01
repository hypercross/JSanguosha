package test.event.wrapped;

import game.Action;
import game.api.*;
import game.entity.CardEntity;
import gameEvent.GameEventEngine;

@EventAction("Type.Action.Discard")
@Type("Type.Event.Action.Discard")
public class DiscardAction {

	@EventContext
	Action decision;
	
	@EventResolve()
	public boolean resolve()
	{
		for(CardEntity ce : decision.<CardEntity>list(CardEntity.class))
		{
			Object evnt = GameEventEngine.instance(CardDiscard.class, ce);
			GameEventEngine.plan(evnt);
		}
		return false;
	}
}
