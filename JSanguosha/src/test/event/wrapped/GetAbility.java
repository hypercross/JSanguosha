package test.event.wrapped;

import game.ActionSet;
import game.api.EventContext;
import game.api.EventResolve;
import game.api.IPlayable;
import game.api.Type;
import game.entity.CardEntity;
import game.entity.PlayerEntity;

@Type("Type.Event.GetAbility")
public class GetAbility {

	@EventContext
	public PlayerEntity player;
	
	@EventContext
	public ActionSet ability = new ActionSet();
	
	@EventResolve
	public boolean getAbility()
	{
		
		for(Object obj : player.child("hand").toArray())
		{
			CardEntity ce = (CardEntity)obj;			
			
			if(ce.prototype instanceof IPlayable)
			{
				IPlayable proto = (IPlayable)ce.prototype;
				ability.union(proto.usage());
			}
		}
		
		return false;
	}
}
