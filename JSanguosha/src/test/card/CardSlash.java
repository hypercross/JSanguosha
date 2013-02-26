package test.card;

import game.ActionFilter;
import game.ActionSet;
import game.ICard;
import game.entity.CardEntity;
import game.entity.GameEntity;
import game.entity.PlayerEntity;
import game.type.LinkedType;
import game.type.Type;
import gameEvent.GameEvent;
import gameEvent.StagedGameEvent;
import test.event.GameEventDecision;
import test.event.GameEventPlay;
import test.rule.RuleOnPlay;

public class CardSlash implements ICard{
	public static Type typeCardSlash = new LinkedType("Slash",Type.ENTITY_CARD);
	public static Type typeEventSlash = new LinkedType("Slash",Type.EVENT);

	@Override
	public Type cardType() {
		return typeCardSlash;
	}
	
	public static class RuleOnPlaySlash extends RuleOnPlay
	{
		public RuleOnPlaySlash(CardEntity ce) {
			super(ce);
		}

		@Override
		public boolean onPlay(GameEventPlay ge) {
			
			for(PlayerEntity target : (PlayerEntity[])ge.playAction.list(PlayerEntity.class))
			 ge.attachToTop(new GameEventPlaySlash(ge.theGame, 
					ge.playAction.performer,
					target));
			
			return true;
		}
	}
	
	public static class GameEventPlaySlash extends StagedGameEvent
	{
		PlayerEntity from, to;
		GameEventDecision ged;

		public GameEventPlaySlash(GameEntity theGame, PlayerEntity from, PlayerEntity to) {
			super(typeEventSlash, theGame, new int[]{0,1});
			this.from = from;
			this.to   = to;
		}	

		@Override
		public boolean onResolve(int stage, GameEvent subEvent) {
			switch(stage)
			{
			case 0:
				ActionSet set = new ActionSet();
				
				set.filter()	.and(new ActionFilter.ActionType(Type.ACTION_CAST))
								.and(new ActionFilter.CardType(CardDodge.typeDodge))
								.and(new ActionFilter.Count(CardEntity.class))
								.and(new ActionFilter.Count(PlayerEntity.class,0));

				subEvent.attachToTop(ged = new GameEventDecision( set , to));
				break;
			case 1:
				if(ged.getResponse().typeDesc().is(Type.ACTION_IDLE))
					;//attach damage event
				break;
			}
			return false;
		}
		
	}

	@Override
	public void registerRules(CardEntity ce) {
		ce.root().rules.register(new RuleOnPlaySlash(ce));
	}

}
