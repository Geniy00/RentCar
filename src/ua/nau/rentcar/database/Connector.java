package ua.nau.rentcar.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import ua.nau.rentcar.resource.Resource;

/**
 * Data base connection pool to MySQL
 * @author Geniy
 *
 */
public class Connector {
	
	private static DataSource dataSource = null;
		
	private Connector() { }
	
	private static synchronized void init(){
		if(dataSource != null) return;
		
		try{
			InitialContext context = new InitialContext();
			dataSource = (DataSource) context.lookup(Resource.getStr("jdbc.context.path"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get new connection from pool
	 * @return
	 */
	public synchronized static Connection getConnection(){		
		if(dataSource == null) {
			init();
		}
			
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
}
