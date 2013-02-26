package application.network;

import game.type.Type;

public class SelectionUpdate {
	int[] selected;
	String[] replies;
	
	public SelectionUpdate(){}
	
	public boolean isEnabled(int id)
	{
		return true;
	}
	
	public boolean isSelected(int id)
	{
		for(int i : selected)
			if(i == id)return true;
		
		return false;
	}
	
	public void select(int... ids)
	{
		selected = ids;
	}
	
	public void repliable(Object... atype)
	{
		replies = new String[atype.length];
		for(int i =0;i<atype.length;i++)
		{
			replies[i] = ((Type)atype[i]).fullName();
		}
	}
	
	public int[] toToggle()
	{
		return selected;
	}
	
	public Type[] types()
	{
		Type[] types = new Type[replies.length];
		for(int i =0;i<types.length;i++)
		{
			types[i] = Type.fromString(replies[i]);
		}
		
		return types;
	}
	
	public static SelectionUpdate getIdle()
	{
		SelectionUpdate su = new SelectionUpdate();
		su.select();
		su.repliable();
		
		return su;
	}
}
