package hx;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Log {
	
	enum Level{
		Severe,
		Alert,
		Fine,
		Trivil
	};
	
	public static PrintStream out = System.out;  
	
	static boolean alert = true;
	static boolean severe = true;
	static boolean fine = true;
	static boolean trivil = false;	
	
	static HashSet<String> tempTrace = new HashSet<String>();
	
	public static void temporary()
	{
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		tempTrace.add(stack[2].getClassName());
	}
	
	public static void t(String n)
	{
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		if(tempTrace.contains(stack[2].getClassName()))
			o("JSanguoshaLog[ temp ]: "+n);
	}
	
	public static void suppress(Level level)
	{
		if(level == Level.Severe)severe = false;
		if(level == Level.Alert)alert = false;
		if(level == Level.Fine)fine = false;
		if(level == Level.Trivil)trivil = false;
	}
	
	public static void promote(Level level)
	{
		if(level == Level.Severe)severe = true;
		if(level == Level.Alert)alert = true;
		if(level == Level.Fine)fine = true;
		if(level == Level.Trivil)trivil = true;
	}
	
	//==================================
	public static void severe(String n)
	{
		if(severe)
		o("JSanguoshaLog[SEVERE]: "+n);
	}
	
	public static void alert(String n)
	{
		if(alert)
		o("JSanguoshaLog[ alert]: "+n);
	}
	
	public static void r()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		o(dateFormat.format(date));
	}
	
	public static void fine(String n)
	{
		if(fine)
		o("JSanguoshaLog[ fine ]: "+n);
	}
	
	public static void trivial(String n)
	{
		if(trivil)
		o("JSanguoshaLog[trivil]: "+n);
	}
	
	//===================================
	public static void o(String n)
	{
		out.println(n);
	}
	
	public static void e(String n)
	{
		System.err.println(n);
	}
	
	public static void o(Object[] n)
	{
		o(list(n));
	}
	
	public static String list(Object[] n)
	{
		String str = "[";
		for(Object obj:n)
			{
				str+= (obj == null ? "null" : obj.toString()) + ", ";
			}
		str+= "]";
		
		return str;
	}


	public static void o(int i) {
		o(String.valueOf(i));
	}
}
