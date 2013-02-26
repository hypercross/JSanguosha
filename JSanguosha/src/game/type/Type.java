package game.type;

public class Type {
	public Type()
	{
	}

	public static Type BASE_TYPE = new Type();
	
	public static Type EVENT = new LinkedType("Event",BASE_TYPE);
	
	public static Type ENTITY = new LinkedType("Entity",BASE_TYPE);
	
	public static Type ENTITY_GAME = new LinkedType("Game",ENTITY);
	public static Type ENTITY_CARD = new LinkedType("Card",ENTITY);
	public static Type ENTITY_PLAYER = new LinkedType("Player",ENTITY);
	public static Type ENTITY_SLOT = new LinkedType("Slot",ENTITY);
	
	public static Type ACTION = new LinkedType("Action", BASE_TYPE);
	
	public static Type ACTION_PLAY = new LinkedType("Play", ACTION);
	public static Type ACTION_CAST = new LinkedType("Cast", ACTION);
	public static Type ACTION_DISCARD = new LinkedType("Discard", ACTION);
	public static Type ACTION_SKILL = new LinkedType("Skill", ACTION);
	public static Type ACTION_IDLE = new LinkedType("Idle", ACTION);
	
	public String toString()
	{
		return "Type";
	}
	
	public String fullName()
	{
		return toString();
	}
	
	public boolean parentOf(Type td)
	{
		return fullName().startsWith(td.fullName());
	}
	
	public boolean is(Type td)
	{
		return td.parentOf(this);
	}
	
	public String description()
	{
		return "";
	}
	
	public TypeMeta meta()
	{
		return TypeMeta.getMeta(this);
	}
	
	public static Type fromString(String blah)
	{
		return new StringType(blah);
	}
	
	public static Type fromParent(String name, Type parent)
	{
		return new LinkedType(name,parent);
	}
}
