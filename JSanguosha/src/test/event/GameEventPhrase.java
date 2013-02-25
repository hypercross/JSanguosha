package test.event;

import test.CardDodge;
import test.event.decision.AskForDiscard;
import game.ActionFilter;
import game.ActionSet;
import game.entity.CardEntity;
import game.entity.GameEntity;
import game.entity.PlayerEntity;
import game.type.LinkedType;
import game.type.Type;
import gameEvent.GameEvent;
import gameEvent.StagedGameEvent;

public class GameEventPhrase extends StagedGameEvent{
	public static Type EVENT_PHRASE = new LinkedType("Phrase",Type.EVENT_PHRASE);
	public PlayerEntity pe;

	public GameEventPhrase(GameEntity theGame, PlayerEntity pe) {
		super(EVENT_PHRASE, theGame, new int[]{0,1,2,3,4});
		this.pe = pe;
	}

	/**
	 * @return true if events are attached
	 */
	@Override
	public boolean onResolve(int stage, GameEvent sub) {

		switch(stage)
		{
		case 0:
			//start phrase
			break;
		case 1:
			//judge
			break;
		case 2:
			//draw cards
			sub.attach(GameEventCardMove.draw(pe, 3));
			return true;
		case 3:
			//play
			
			break;
		case 4:
			ActionSet as = new ActionSet();
			as.types.add(Type.ACTION_CAST);
			as.filter()	.and(new ActionFilter.ActionType(Type.ACTION_CAST))
						.and(new ActionFilter.Count(CardEntity.class))
						.and(new ActionFilter.Count(PlayerEntity.class,0));
			
			sub.attach(new AskForDiscard(pe, as));
			return true;
		case 5:
			//end phrase
			break;
		}
		
		return false;
	}

}
