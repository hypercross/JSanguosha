package game.type;

public class StringType extends Type{
	String name;
	String parent;
	
	public StringType(String string)
	{
		int point = string.lastIndexOf(".");
		name = string.substring(point+1);
		parent = string.substring(0, point);
		
	}

	public String toString()
	{
		return name;
	}
	
	public String fullName()
	{
		return parent + "." + name;
	}
}
