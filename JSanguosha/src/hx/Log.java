package hx;

public class Log {

	private static boolean notTraced()
	{
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		try {
			return Class.forName(stack[2].getClassName()).isAnnotationPresent(Traced.class);
		} catch (ClassNotFoundException e) {
			//hmmm
			return true;
		}
	}
	
	
	public static void o(String n)
	{
		if(notTraced())return;
		System.out.println(n);
	}
	
	public static void e(String n)
	{
		if(notTraced())return;
		System.err.println(n);
	}
	
	public static void o(Object[] n)
	{
		if(notTraced())return;
		
		String str = "[";
		for(Object obj:n)str+= obj.toString() + ", ";
		str+= "]";
		
		o(str);
	}
}
