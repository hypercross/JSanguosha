package test.event;

import application.network.NetworkManager;
import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import game.entity.GameEntity;
import game.entity.PlayerEntity;
import game.type.LinkedType;
import game.type.Type;
import gameEvent.GameEvent;

@Deprecated
public class GameEventCardMove extends GameEvent{
	public static Type EVENT_MOVE = new LinkedType("CardMove", Type.EVENT);
	
	public CardEntity subject;
	public CardSlotEntity target;

	public GameEventCardMove(CardEntity subject, CardSlotEntity target) {
		super(EVENT_MOVE);
		this.subject = subject;
		this.target  = target;
	}
	
	public static GameEventCardMove draw(PlayerEntity pe)
	{
		GameEntity ge = pe.root();
		
		if(ge.child("drawDeck").size() == 0)return null;
		
		return new GameEventCardMove(
				(CardEntity) ge.child("drawDeck").get(0), 
				(CardSlotEntity) pe.child("hand"));
	}
	
	public static GameEventCardMoveMulti draw(PlayerEntity pe, int i)
	{ 
		CardEntity[] ces = new CardEntity[i];
		for(int j = 0;j<i;j++)ces[j] = (CardEntity) pe.root().child("drawDeck").get(j);
		return new GameEventCardMoveMulti(pe.root(), (CardSlotEntity) pe.child("hand"), ces);
	}
	
	public static GameEventCardMove discard(CardEntity ce)
	{
		if(ce.container == null)return null;
		
		GameEntity ge = ce.container.root();
		
		return new GameEventCardMove(
				ce,
				(CardSlotEntity) ge.child("discardDeck"));
	}

	@Override
	public boolean resolve() {
		
		
		if(subject.container != null)
			subject.container.remove(subject);
		
		target.add(subject);
		subject.container = target;
		

		NetworkManager.instance.broadcastUpdate(subject);
		
		return false;
	}

}
