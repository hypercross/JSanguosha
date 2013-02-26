package test;

import test.event.wrapped.CardMove;
import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import game.type.Type;
import gameEvent.GameEvent;
import gameEvent.GameEventEngine;

public class TestPackage {
	Type CARD_INPLAY = Type.fromParent("Inplay", Type.ENTITY_CARD);
	
	Type CARD_BASIC = Type.fromParent("Basic", CARD_INPLAY);
	Type CARD_SHA = Type.fromParent("Sha", CARD_BASIC);
	Type CARD_SHAN = Type.fromParent("Shan", CARD_BASIC);
	
	public static GameEvent discard(CardEntity ce)
	{
		try{
			return GameEventEngine.eventOf(CardMove.class, ce, (CardSlotEntity) ce.root().child("discardDeck"));
		}catch(Exception e)
		{
			//hmmm
		}
		
		return null;
	}
}
