package gameEvent;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

import game.api.EventResolve;
import game.entity.GameEntity;
import game.type.Type;

public class WrappedGameEvent extends StagedGameEvent{

	static int level(Method a)
	{
		if(!a.isAnnotationPresent(EventResolve.class))return Integer.MAX_VALUE;

		return a.getAnnotation(EventResolve.class).value();
	}

	static class ResolveMethodComp implements Comparator<Method>
	{
		@Override
		public int compare(Method arg0, Method arg1) {
			return level(arg0) - level(arg1);
		}
	}

	private static int[] getStages(Object ie)
	{
		Method[] methods = ie.getClass().getMethods();
		Arrays.sort(methods,new ResolveMethodComp());
		
		int s = 0;		
		for(Method m : methods)
			if(m.isAnnotationPresent(EventResolve.class))s++;
		
		int[] stages = new int[s];
		s = 0;

		for(Method m : methods)
			if(m.isAnnotationPresent(EventResolve.class))stages[s++] = level(m);
		
		return stages;
	}

	Object event;
	public WrappedGameEvent(Object event, GameEntity game) {
		super(Type.fromAnnotation(event), game, getStages(event) );
		this.event = event;
	}
	
	 

	@Override
	public boolean onResolve(int stage, GameEvent sub) {
		for(Method m : event.getClass().getMethods())
			if(level(m) == stage)
				try {
					return (Boolean) m.invoke(event);
				} catch (Exception e)
				{
					//hmmm
				}
		
		return false;
	}

}
