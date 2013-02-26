import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

	public void testSQL() throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://instance38419.db.xeround.com:6417/servers", 
				"JSanguosha", "JSanguosha");

		Statement stat = con.createStatement();

		ResultSet result = stat.executeQuery("SELECT * FROM server");

		System.out.println(result.getMetaData());
	}

	public static void main(String[] args)
	{
	}
}
