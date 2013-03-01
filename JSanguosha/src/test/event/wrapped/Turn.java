package test.event.wrapped;

import game.ActionFilter;
import game.ActionSet;
import game.api.EventContext;
import game.api.EventResolve;
import game.entity.CardEntity;
import game.entity.PlayerEntity;
import game.type.Type;
import gameEvent.GameEventEngine;

public class Turn {

	@EventContext
	public PlayerEntity player;
	
	@EventResolve(0)
	public boolean startPhrase()
	{
		return false;
	}
	
	@EventResolve(1)
	public boolean drawPhrase()
	{
		return true;
	}
	
	@EventResolve(2)
	public boolean playPhrase()
	{
		//TODO - make this recursive
		Object evnt = GameEventEngine.instance(AskForAction.class, player, new ActionSet(Type.ACTION_PLAY));
		GameEventEngine.trigger(evnt);
			
		return true;
	}
	
	@EventResolve(3)
	public boolean discardPhrase()
	{
		if(player.child("hand").size() > player.hp)
		{
			ActionSet as = new ActionSet(Type.ACTION_DISCARD);
			int amount = player.child("hand").size() - player.hp;
			
			as.setFilter(new ActionFilter.Count(CardEntity.class, amount, amount));
			
			Object evnt = GameEventEngine.instance(AskForAction.class, player, as);
			GameEventEngine.trigger(evnt);
			return true;
		}
		return false;
	}
	
	@EventResolve(4)
	public boolean endPhrase()
	{
		return false;
	}
}
