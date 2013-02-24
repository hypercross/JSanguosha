package application.view;

import game.ActionSet;
import game.entity.Entity;

import java.util.HashMap;

public class ClientControlManager {
	HashMap<Selectable,Entity> view_to_entity = new HashMap<Selectable,Entity>();
	
	ActionSet actionSet;
	
	public void register(Selectable actor, Entity entity)
	{
		view_to_entity.put(actor,entity);
	}
	
	public void deployRequest(ActionSet set)
	{
		actionSet = set;
	}
	
	public void updateSelectables()
	{
		if(actionSet == null)
			for(Selectable select : view_to_entity.keySet())
				select.isEnabled = false;

//		else for(Selectable select : view_to_entity.keySet())
//		{
//			
//		}
	}
}
