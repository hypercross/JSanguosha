package test.event.wrapped;

import application.network.NetworkManager;
import game.api.EventContext;
import game.api.EventResolve;
import game.api.Type;
import game.entity.CardEntity;
import game.entity.CardSlotEntity;

@Type("Type.Event.CardMove")
public class CardMove{
	
	@EventContext
	public CardEntity subject;
	
	@EventContext
	public CardSlotEntity target;
	
	@EventResolve
	public boolean resolve()
	{
		subject.moveTo(target);
		NetworkManager.instance.broadcastUpdate(subject);
		
		return false;
	}
}
