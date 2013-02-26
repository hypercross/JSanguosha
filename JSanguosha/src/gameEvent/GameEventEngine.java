package gameEvent;

import java.lang.reflect.Field;

import game.IEvent;
import game.entity.Entity;

public class GameEventEngine {
	public static GameEvent eventOf(Class<? extends IEvent> event, Entity... entities) throws Exception
	{
		IEvent theEvent = event.newInstance();
		
		for(Entity entity : entities)		
		for(Field f : event.getFields())
		{
			if(f.isAnnotationPresent(EventContext.class) 
					&& f.getType().equals(entity.getClass())
					&& f.get(theEvent) == null)
			f.set(theEvent, entity);
		}
		
		return new WrappedGameEvent(theEvent);
	}
}
