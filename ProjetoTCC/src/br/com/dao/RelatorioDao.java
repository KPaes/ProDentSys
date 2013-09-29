package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.com.exception.DaoException;
import br.com.util.DbUtil;

public class RelatorioDao {
	private static final String RELATORIO =
			"SELECT *  FROM tbPedido ORDER BY nomeCliente";
	

	private static final String RELATORIO_MES =
			"SELECT * FROM tbPedido where dataEntrega = ? ORDER BY nomeCliente";
	
	
	private static final String FOLHA_DE_PAGAMENTO =
			"SELECT a.nomeCliente, a.nomePaciente, a.observacoesPed, a.totalPedido," +
			" b.nomeFunc, b.salarioFunc, b.comissaoFuncTotal, b.bonusFunc," +
			" b.totalFunc FROM tbPedido a " +
			" inner join tbFolhadePagamento b " +
			" on a.numFunc = b.numFunc " +
			" WHERE b.numFunc = ?";
	
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
	        	statement.setString(1, mes);
	        	rs = statement.executeQuery();
	        } catch (SQLException e) {
	        	throw new DaoException(e);			
			}
	        
		
	        return rs;
	    }
	 
	 /** Relatório do pagamento do funcionário */	
	 public ResultSet folhaResultSet(Integer func) throws DaoException {		 
			Connection conn = DbUtil.getConnection();
			PreparedStatement statement = null;			
	        ResultSet rs = null;		
			
	        try {	          
	        	statement = conn.prepareStatement(FOLHA_DE_PAGAMENTO);
	        	statement.setInt(1, func);
	        	rs = statement.executeQuery();
	        } catch (SQLException e) {
	        	throw new DaoException(e);			
			}
	        
		
	        return rs;
	    }
}
