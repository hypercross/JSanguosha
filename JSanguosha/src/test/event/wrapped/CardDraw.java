package test.event.wrapped;

import game.api.EventContext;
import game.api.EventResolve;
import game.api.Type;
import game.entity.PlayerEntity;
import gameEvent.GameEventEngine;

@Type("Type.Event.CardMove.CardDraw")
public class CardDraw {

	@EventContext
	public int amount;

	@EventContext
	public PlayerEntity player;

	@EventResolve
	public boolean resolve()
	{
		if(amount <= 0)return false;

		if(GameEventEngine.the_game.child("drawDeck").isEmpty())
		{
			Object evnt = GameEventEngine.instance(DrawDeckShuffle.class);
			GameEventEngine.trigger(evnt);
		}else
		{
			Object evnt = GameEventEngine.instance(CardMove.class, 
					GameEventEngine.the_game.child("drawDeck").get(0),
					player.child("hand"));
			GameEventEngine.trigger(evnt);
			amount -- ;
		}
		
		GameEventEngine.recurse();		
		return true;
	}
}
