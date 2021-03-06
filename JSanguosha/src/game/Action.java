package game;

import java.util.ArrayList;

import game.entity.CardEntity;
import game.entity.Entity;
import game.entity.PlayerEntity;
import game.type.Type;
import hx.Log;

public class Action {
	
	public PlayerEntity performer;
	Entity[] entities;
	Type type;
	
	public Action(Type type ,PlayerEntity performer, Entity... entites)
	{
		this.performer = performer;
		this.type = type;
		this.entities = entites;
	}

	public Type typeDesc()
	{
		return type;
	}
	
	public CardEntity card(int i)
	{
		return (CardEntity) get(i,CardEntity.class);
	}
	
	public PlayerEntity player(int i)
	{
		return (PlayerEntity) get(i,PlayerEntity.class);
	}
	
	public Entity entity(int i)
	{
		return get(i,Entity.class);
	}
	
	public Object[] list(Class<? extends Entity> entityType)
	{
		ArrayList<Entity> entityList = new ArrayList<Entity>();
		
		for(Entity e : entities)
		{
			if(entityType.isInstance(e))
				entityList.add(e);
		}
		
		return  entityList.toArray();
	}
	
	private Entity get(int i, Class<? extends Entity> returnType)
	{
		for(Entity e : entities)
		{
			if(returnType.isInstance(e))
			{
				if(i==0)return e;
				else i--;
			}
		}
		
		return null;
	}
	
	public String toString()
	{
		return performer.name() +" "+ type + Log.list(entities); 
	}
}
