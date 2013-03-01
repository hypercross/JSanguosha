package test.card;

import game.Action;
import game.ActionSet;
import game.api.IPlayable;
import game.api.Type;
import game.entity.Entity;
import gameEvent.GameEvent;

@Type("Type.Entity.Card.Inplay.Jinnang.Tiesuolianhuan")
public class CardTiesuolianhuan implements IPlayable{

	@Override
	public ActionSet usage() {
		return null;
	}

	@Override
	public GameEvent onUse(Action use) {
		return null;
	}

	@Override
	public GameEvent onEffect(Action use, Entity target) {
		return null;
	}

}
