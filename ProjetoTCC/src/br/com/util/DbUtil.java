package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.exception.DaoException;
 
public class DbUtil {

    private static final String URL_DB = "jdbc:sqlserver://localhost:1433;databaseName=PROJETO_TCC_PRODENTSYS;";
    
    private static final String URL_DB2 = "jdbc:sqlserver://localhost:1433;databaseName=PROJETO_TCC_PRODENTSYS_NOVO;";
    private static final String DRIVER_JDBC = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String USER = "sa";
    private static final String PASS = "12345678";

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
			JOptionPane.showMessageDialog(null, "Aguarde! \nBanco de dados voltará a  funcionar em breve!", "Aguarde", JOptionPane.WARNING_MESSAGE);
			throw new DaoException(e);
		}
	}
	
    public static Connection getConnection2() throws DaoException {
    	try {
			Connection connection = DriverManager.getConnection(URL_DB2, USER, PASS);
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
	
	public static java.sql.Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Date(date.getTime());
		}
		return sqlDate;
	}
	
	public static void close(Connection conn, Statement statement) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
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