package application.view;

import game.entity.Entity;
import hx.Log;
import hx.Traced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

@Traced
public class EntityViewManager {
	public	static EntityViewManager instance = new EntityViewManager();
	
	HashMap <IEntityView, Entity> actor_to_entity = new HashMap <IEntityView, Entity>();
	
	public synchronized void addActor(Stage stage, Actor actor, Entity entity)
	{
		stage.addActor(actor);
		if(actor instanceof IEntityView)
			actor_to_entity.put((IEntityView)actor,entity);
	}
	
	public synchronized void addActor(Group group, Actor child, Entity entity)
	{
		group.addActor(child);
		if(child instanceof IEntityView)
			actor_to_entity.put((IEntityView)child, entity);
	}
	
	public synchronized void removeActor(Actor actor)
	{
		actor.remove();
		actor_to_entity.remove(actor);
	}
	
	public synchronized void removeRelated(Entity entity)
	{
		for(Entry<IEntityView, Entity> entry : actor_to_entity.entrySet())
		{
			if(entry.getValue().equals(entity))((Actor)entry.getKey()).remove();
		}
	}
	
	public synchronized void updateEntityView(Entity entity)
	{
		ArrayList<IEntityView> iev = new ArrayList<IEntityView>();
		
		for(Entry<IEntityView, Entity> entry : actor_to_entity.entrySet())
		{
			if(entry.getValue().equals(entity))iev.add(entry.getKey());
		}
		
		for(IEntityView aiev : iev)aiev.updateProperty();
		
		Log.o(entity.root().toString());
	}
	
	public synchronized void removeAll()
	{
		for(IEntityView actor : actor_to_entity.keySet())
			((Actor)actor).remove();
		
		actor_to_entity.clear();
	}
}
