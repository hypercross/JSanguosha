package gameEvent;

import java.lang.reflect.Field;
import java.util.Set;

import org.reflections.*;

import com.badlogic.gdx.utils.Array;

import game.Action;
import game.api.EventAction;
import game.api.EventContext;
import game.entity.GameEntity;
import hx.Log;

public class GameEventEngine {

	public static <T> T instance(Class<? extends T> event, Object... context)
	{
		try
		{
			T theEvent = event.newInstance();

			for(Object entity : context)		
				for(Field f : event.getFields())
				{
					if(f.isAnnotationPresent(EventContext.class) 
							&& f.getType().equals(entity.getClass())
							&& f.get(theEvent) == null)
						f.set(theEvent, entity);
				}

			return theEvent;
		}catch(Exception e)
		{
			Log.severe("Something's wrong. Event creation fail.");
			return null;
		}
	}

	public static Class<?> findEventClass(Action action)
	{
		Reflections reflection = new Reflections();

		Set<Class<?>> annotated = 
				reflection.getTypesAnnotatedWith(EventAction.class);

		for(Class<?> aclass : annotated)
		{
			if(aclass.getAnnotation(EventAction.class).value().equals(action.typeDesc().fullName()))
				return aclass;
		}
		Log.severe("no event type for the action " + action.toString());

		return null;
	}
	
	
	//event controls
	public static boolean FLAG_RECURSE = false;
	public static boolean FLAG_RETRIGGER = false;
	public static GameEntity the_game; 
	
	static Array<Object> planned = new Array<Object>();
	
	public static void recurse()
	{
		
	}
	
	public static void retrigger()
	{
		
	}
	
	//for actions
	
	public static void trigger(Action action)
	{
		trigger(instance(findEventClass(action),action));
	}
	
	public static void plan(Action action)
	{
		plan(instance(findEventClass(action),action));
	}
	
	//for events
	public static void trigger(Object evnt)
	{
		the_game.events.peek().attach(new WrappedGameEvent(evnt,the_game));
	}
	
	public static void plan(Object evnt)
	{
		planned.add(evnt);
	}
	
	public static Object[] retrievePlanned()
	{
		Object[] ges = planned.toArray();
		planned.clear();
		return ges;
	}
}
