package gameEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RuleRegistry
{	
	private ArrayList<Rule> rules = new ArrayList<Rule>();
	public Comparator<Rule> sorter = new compare_by_entity_hierarchy();
	
	public boolean trigger(GameEvent ge)
	{
		boolean triggered = false;
		if(ge.children.isEmpty())return false;
		
		for(Rule rule : rules)
		{
			triggered |= rule.trigger(ge);
		}
		
		return triggered;
	}
	
	public void register(Rule rule)
	{
		rules.add(rule);
		Collections.sort(rules, sorter);
	}
	
	private class compare_by_entity_hierarchy implements Comparator<Rule>
	{
		// incorrect
		@Override
		public int compare(Rule arg0, Rule arg1) {
			if(arg0.owner().parentOf(arg1.owner()))return -1;
			if(arg1.owner().parentOf(arg0.owner()))return 1;
			return 0;
		}
		
	}
}