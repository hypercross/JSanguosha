package game;

import com.badlogic.gdx.utils.Array;

import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import game.entity.PlayerEntity;
import game.type.Type;

public class ActionSet {
	
	ActionFilter filter;
	public Array<Type> types;
	
	public ActionSet(Type... type)
	{
		this.types = new Array<Type>(type);
		this.filter = defaultFilter();
	}

	public boolean contains(Action action)
	{
		return filter.checkAll(action);
	}
	
	public ActionFilter filter()
	{
		return filter;
	}
	
	public static ActionFilter defaultFilter()
	{
		return new ActionFilter.Count(CardEntity.class)
		.and(new ActionFilter.Count(PlayerEntity.class)
		.and(new ActionFilter.Count(CardSlotEntity.class,0)));
	}

}
