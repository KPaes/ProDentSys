package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.exception.DaoException;
import br.com.util.DbUtil;

public class RelatorioDao {
	private static final String RELATORIO =
			"SELECT *  FROM tbPedido ORDER BY nomeCliente";
	

	private static final String RELATORIO_MES =
			"SELECT * FROM tbPedido where dataEntrega like ? ORDER BY nomeCliente";
	
	
	 public ResultSet pedidosResultSet() throws DaoException {		 
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;			
	        ResultSet rs = null;		
			
	        try {	          
	        	statement = conn.prepareStatement(RELATORIO);
	        	rs = statement.executeQuery();
	        } catch (SQLException e) {
	        	throw new DaoException(e);			
			}
	        
		
	        return rs;
	    }
	 
	 public ResultSet pedidosResultSet(String mes) throws DaoException {		 
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;			
	        ResultSet rs = null;		
			
	        try {	          
	        	statement = conn.prepareStatement(RELATORIO_MES);
	        	statement.setString(1, "%/"+mes+"/%");
	        	rs = statement.executeQuery();
	        } catch (SQLException e) {
	        	throw new DaoException(e);			
			}
	        
		
	        return rs;
	    }
	
}
