package game;

import com.badlogic.gdx.utils.Array;

import game.type.Type;
import hx.Log;

public class ActionSet {
	
	ActionFilter filter;
	public Array<Type> types;
	
	public ActionSet()
	{
		this.types = new Array<Type>();
		this.types.add(Type.ACTION_IDLE);
		this.filter = defaultFilter();
	}
	
	public ActionSet(String... types)
	{
		this.types = new Array<Type>(types.length);
		for(int i =0;i<types.length;i++)this.types.set(i,Type.fromString(types[i]));
		this.types.add(Type.ACTION_IDLE);
		this.filter = defaultFilter();
	}
	
	public ActionSet(Type... type)
	{
		this.types = new Array<Type>(type);
		this.types.add(Type.ACTION_IDLE);
		this.filter = defaultFilter();
	}

	public boolean contains(Action action)
	{
		boolean result = filter.checkAll(action) || action.typeDesc().is(Type.ACTION_IDLE);
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
		return new ActionFilter();
	}

	public ActionSet union(ActionSet as)
	{
		ActionFilter af1 = new ActionFilter.Empty();
		for(Type type : this.types)
			af1 = af1.or(new ActionFilter.ActionType(type));
		
		ActionFilter af2 = new ActionFilter.Empty();
		for(Type type : as.types)
			af2 = af2.or(new ActionFilter.ActionType(type));
		
		this.setFilter( (this.filter().and(af1)) .or( as.filter().and(af2) ));
		for(Type at : as.types)
		{
			if(types.contains(at,false))continue;
			types.add(at);
		}
		
		return this;
	}
	
	public ActionSet restrictBy(ActionSet as)
	{
		this.setFilter(this.filter().and(as.filter()));
		for(int i = 0; i < this.types.size; i ++)
		{
			Type at = types.get(i);
			boolean valid = false;
			for(Type atype : as.types)
				if(at.is(atype)){
					valid = true;
					break;
				}
			if(!valid)types.removeIndex(i);
		}
		
		return this;
	}
}
