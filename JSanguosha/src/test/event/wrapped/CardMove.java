package test.event.wrapped;

import application.network.NetworkManager;
import game.IEvent;
import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import gameEvent.EventContext;
import gameEvent.EventResolve;

public class CardMove implements IEvent {
	
	@EventContext
	public CardEntity subject;
	
	@EventContext
	public CardSlotEntity target;
	
	@EventResolve
	public boolean resolve()
	{
		if(subject.container != null)
			subject.container.remove(subject);
		
		target.add(subject);
		subject.container = target;		

		NetworkManager.instance.broadcastUpdate(subject);
		
		return false;
	}

	@Override
	public String type() {
		return "Type.Event.CardMove";
	}
}
