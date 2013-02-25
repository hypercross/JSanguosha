package test.event.decision;

import test.event.GameEventCardMove;
import game.Action;
import game.ActionSet;
import game.entity.PlayerEntity;
import game.type.Type;
import gameEvent.GameEvent;
import gameEvent.StagedGameEvent;

public class AskForDiscard extends StagedGameEvent{
	static Type EVENT_DECISION_AKD = Type.fromParent("Discard", Type.EVENT_DECISION);

	PlayerEntity player;
	Action decision;
	ActionSet as;
	
	public AskForDiscard(PlayerEntity player, ActionSet as) {
		super(EVENT_DECISION_AKD, player.root(),0,1);
		this.player = player;
		this.as = as;
	}

	@Override
	public boolean onResolve(int stage, GameEvent sub) {
		switch(stage)
		{
		case 0:
			decision = this.theGame.players.askForDecision(player, as);
			break;
		case 1:
			sub.attachToTop(GameEventCardMove.discard(decision.card(0)));
		}
		return false;
	}

}
