package test.event;

import application.network.NetworkManager;
import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import game.entity.GameEntity;
import gameEvent.GameEvent;

public class GameEventCardMoveMulti extends GameEvent
{

	public CardEntity[] subjects;
	public CardSlotEntity target;

	public GameEventCardMoveMulti(GameEntity theGame, CardSlotEntity target, CardEntity... subjects) {
		super(GameEventCardMove.EVENT_MOVE, theGame);
		this.subjects = subjects;
		this.target = target;
	}

	@Override
	public boolean resolve() {

		for(CardEntity subject : subjects)
		{
			if(subject.container != null)
				subject.container.remove(subject);

			target.add(subject);
			subject.container = target;
			NetworkManager.instance.broadcastUpdate(subject);
		}

		return false;
	}
}