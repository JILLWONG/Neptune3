package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Event数据访问
 * @author 王之威
 *
 */
public class EventDA {
	static Event aEvent;
	static Connection aConnection;
	static Statement aStatement;
	
	/** 事件ID*/
	static int ID;
	/** 事件描述*/
	static String description;
	/** 选项数组*/
	static Choice[] choices;
	
	/**
	 * 连接数据库
	 * @return 连接
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection initialize() throws ClassNotFoundException, SQLException {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=src\\Database.accdb";
			//aConnection = DriverManager.getConnection("jdbc:odbc:Database", "Database");
			Properties p = new Properties();
            p.put("charSet", "GBK");
            aConnection = DriverManager.getConnection(url, p);
			aStatement = aConnection.createStatement();
		return aConnection;		
	}
	
	/**
	 * 断开数据库连接
	 * @throws SQLException
	 */
	public static void terminate() throws SQLException {
		aStatement.close();
		aConnection.close();
	}
	
	/**
	 * 查找事件
	 * @param id 事件id
	 * @return 事件
	 * @throws SQLException
	 */
	public static Event find(int id) throws SQLException {
		aEvent=null;
		String sql="SELECT * FROM 事件 WHERE ID="+id;
		ResultSet rs=aStatement.executeQuery(sql);
		boolean gotIt=rs.next();
		if(gotIt) {
			ID=rs.getInt(1);
			description=rs.getString(2);
			choices=new Choice[4];
			choices[0]=new Choice(rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8), rs.getString(9),rs.getInt(10),rs.getInt(35),rs.getInt(39));
			choices[1]=new Choice(rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15),rs.getInt(16),rs.getString(17),rs.getInt(18),rs.getInt(36),rs.getInt(40));
			choices[2]=new Choice(rs.getString(19),rs.getInt(20),rs.getInt(21),rs.getInt(22),rs.getInt(23),rs.getInt(24), rs.getString(25),rs.getInt(26),rs.getInt(37),rs.getInt(41));
			choices[3]=new Choice(rs.getString(27),rs.getInt(28),rs.getInt(29),rs.getInt(30),rs.getInt(31),rs.getInt(32),rs.getString(33),rs.getInt(34),rs.getInt(38),rs.getInt(42));
			aEvent=new Event(ID,description,choices);
		}
		rs.close();
		return aEvent;	
	}
}