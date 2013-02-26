package game;

import com.badlogic.gdx.utils.Array;

import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import game.entity.PlayerEntity;
import game.type.Type;
import hx.Log;

public class ActionSet {
	
	ActionFilter filter;
	public Array<Type> types;
	
	public ActionSet(Type... type)
	{
		this.types = new Array<Type>(type);
		this.types.add(Type.EVENT_DECISION_IDLE);
		this.filter = defaultFilter();
	}

	public boolean contains(Action action)
	{
		boolean result = filter.checkAll(action) || action.typeDesc().is(Type.EVENT_DECISION_IDLE);
		Log.fine("ActionSet checked " + action + " ..." + (result ? "true" : "false"));
		Log.r();
		return result;
	}
	
	public ActionFilter filter()
	{
		return filter;
	}
	
	public ActionFilter setFilter(ActionFilter f)
	{
		return filter = f;
	}
	
	public static ActionFilter defaultFilter()
	{
		return new ActionFilter.Count(CardEntity.class)
		.and(new ActionFilter.Count(PlayerEntity.class)
		.and(new ActionFilter.Count(CardSlotEntity.class,0)));
	}

}
