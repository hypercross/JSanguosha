package test.event.wrapped;

import game.Action;
import game.ActionSet;
import game.api.EventContext;
import game.api.EventResolve;
import game.api.Type;
import game.entity.PlayerEntity;
import gameEvent.GameEventEngine;

@Type("Type.Action.AskForAction")
public class AskForAction {

	@EventContext
	public PlayerEntity pe;
	
	@EventContext
	public Action decision;
	
	@EventContext
	public ActionSet request;
	
	public GetAbility ability;
	
	@EventResolve(0)
	public boolean getAbility()
	{
		ability = GameEventEngine.<GetAbility>instance(GetAbility.class, pe);
		GameEventEngine.trigger(ability);
		return true;
	}
	
	@EventResolve(1)
	public boolean askForDecision()
	{
		decision = pe.root().players.askForDecision(pe, ability.ability.restrictBy(request));
		return false;
	}
	
	@EventResolve(2)
	public boolean actionEvent()
	{
		GameEventEngine.trigger(decision);
		return false;
	}
}
