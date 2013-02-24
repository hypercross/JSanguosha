package application.network;

import game.type.Type;

public class SelectionUpdate {
	int[] selected;
	Type[] replies;
	
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
	
	public void repliable(Type... atype)
	{
		replies = atype;
	}
}
