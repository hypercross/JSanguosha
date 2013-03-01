package test.card;

import game.Action;
import game.ActionSet;
import game.api.IPlayable;
import game.api.Type;
import game.entity.Entity;
import gameEvent.GameEvent;

@Type("Type.Entity.Card.Inplay.Basic.Sha")
public class CardSha implements IPlayable
{

	@Override
	public ActionSet usage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameEvent onUse(Action use) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameEvent onEffect(Action use, Entity target) {
		// TODO Auto-generated method stub
		return null;
	}
	
}