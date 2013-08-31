package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.exception.DaoException;
 
public class DbUtil {
//55826
    private static final String URL_DB = "jdbc:sqlserver://localhost:1433;databaseName=PROJETO_TCC_PRODENTSYS;";
    private static final String DRIVER_JDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String USER = "sa";
    private static final String PASS = "1234";

    static {
		try {
			Class.forName(DRIVER_JDBC);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    public static Connection getConnection() throws DaoException {
    	try {
			Connection connection = DriverManager.getConnection(URL_DB, USER, PASS);
			connection.setAutoCommit(true);
			return connection;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	public static void close(Connection conn, Statement statement, ResultSet result) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public class ConnectionFactory {
	    public Connection getConnection() {
	        try {
	            return DriverManager.getConnection(
	            		URL_DB, USER, PASS);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}
}