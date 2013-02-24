package game;

import game.entity.Entity;
import game.type.Type;

public class ActionFilter {
	private ActionFilter a,b;
	private byte operation = -1;
	
	private ActionFilter(ActionFilter a, ActionFilter b, byte operation)
	{
		this.a = a;
		this.b = b;
		this.operation = operation;
	}
	
	public ActionFilter and(ActionFilter other)
	{
		return new ActionFilter(this,other, (byte) 0);
	}
	
	public ActionFilter or(ActionFilter other)
	{
		return new ActionFilter(this, other, (byte) 1);
	}
	
	public ActionFilter then(ActionFilter other)
	{
		return new ActionFilter(this, other, (byte) 2);
	}
	
	public boolean check(Action action)
	{
		return true;
	}
	
	public boolean checkAll(Action action)
	{
		boolean resultA = false,resultB = false;
		if(a != null) resultA = a.checkAll(action);
		if(b != null) resultB = b.checkAll(action);
		
		switch(operation)
		{
		case 0:
			return resultA && resultB;
		case 1:
			return resultA || resultB;
		case 2:
			return (resultA ? resultB : true);
		default:
			return check(action);
		}
	}
	
	public ActionFilter(){};
	
	public static class ActionType extends ActionFilter
	{
		Type type;
		
		@Override
		public boolean check(Action action)
		{
			return action.type.is(type);
		}
		
		public ActionType(Type type)
		{
			this.type = type;
		}
	}
	
	public static class CardType extends ActionFilter
	{
		Type type;
		
		@Override
		public boolean check(Action action)
		{
			if(action.card(0) != null)
				return action.card(0).is(type);
			
			return true;
		}
		
		public CardType(Type type)
		{
			this.type = type;
		}
	}
	
	public static class Count extends ActionFilter
	{
		Class<? extends Entity> className;
		int min,max;
		
		public boolean check(Action action)
		{
			int len = action.list(className).length;
			
			return len >= min && len <= max;
		}
		
		public Count(Class<? extends Entity> className, int max, int min)
		{
			this.className = className;
			this.max = max;
			this.min = min;
		}
		
		public Count(Class<? extends Entity> className, int max)
		{
			this(className,max,1);
		}
		
		public Count(Class<? extends Entity> className)
		{
			this(className,1,1);
		}
	}
}
