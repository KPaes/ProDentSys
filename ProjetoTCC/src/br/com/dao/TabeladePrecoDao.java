package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.exception.DaoException;
import br.com.util.DbUtil;
import br.com.bean.TabeladePreco;

public class TabeladePrecoDao {
	private static final String EXCLUIR_TABELADEPRECO =
			"delete from tbtabeladeprecos where numProtese = ?";
	
	private static final String INSERIR_TABELADEPRECO =
			"insert into tbtabeladeprecos(tipoProtese, nomeProtese, precoProtese) "+
			"values (?,?,?)";
	
	private static final String ATUALIZAR_TABELADEPRECO =
			"update tbtabeladeprecos set " +
			"tipoProtese = ?, " +
			"nomeProtese = ?, " +			
			"precoProtese = ? " +			
			"where numProtese = ? ";
	
	private static final  String CONSULTA_TABELADEPRECO =
			"select * from tbtabeladeprecos order by tipoProtese ASC, nomeProtese ASC";
	
	private static final  String CONSULTA_TABELADEPRECO_ID = 
			"select * from tbtabeladeprecos where numProtese = ?";
	
	private static final  String CONSULTA_TABELADEPRECO_NOME =
			"select * from tbtabeladeprecos where nomeProtese like ? order by nomeProtese";
	
	
	public List<TabeladePreco> consultarProteses(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<TabeladePreco> listaTab = new ArrayList<TabeladePreco>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_TABELADEPRECO);
			}else{
				statement = conn.prepareStatement(CONSULTA_TABELADEPRECO_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				TabeladePreco objTab = new TabeladePreco();
				objTab.setNumProtese(result.getInt(4));
				objTab.setNomeProtese(result.getString(2));
				objTab.setTipoProtese(result.getString(1));
				objTab.setPrecoProtese(result.getDouble(3));
				listaTab.add(objTab);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaTab;		
	}	
	
	public List<TabeladePreco> consultarProteses() throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<TabeladePreco> listaTab = new ArrayList<TabeladePreco>();
		try {
			statement = conn.prepareStatement(CONSULTA_TABELADEPRECO);
			result = statement.executeQuery();
			while (result.next()) {
				TabeladePreco objTab = new TabeladePreco();
				objTab.setNumProtese(result.getInt(4));
				objTab.setNomeProtese(result.getString(2));
				objTab.setTipoProtese(result.getString(1));
				objTab.setPrecoProtese(result.getDouble(3));
				listaTab.add(objTab);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaTab;		
	}

	public TabeladePreco consultarTabeladePrecoID(int idFunc) throws DaoException{		
		TabeladePreco objTab = new TabeladePreco();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_TABELADEPRECO_ID);
			statement.setInt(1, idFunc);
			result = statement.executeQuery();
			while (result.next()) {
				objTab.setNumProtese(result.getInt(4));
				objTab.setNomeProtese(result.getString(2));
				objTab.setTipoProtese(result.getString(1));
				objTab.setPrecoProtese(result.getDouble(3));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objTab;		
	}
	
	public boolean atualizarTabeladePreco(TabeladePreco objTab) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_TABELADEPRECO);
			statement.setString(2, objTab.getNomeProtese());
			statement.setString(1, objTab.getTipoProtese());
			statement.setDouble(3, objTab.getPrecoProtese());	
			statement.setInt(4, objTab.getNumProtese());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean inserirTabeladePreco(TabeladePreco obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_TABELADEPRECO);
			statement.setString(2, obj.getNomeProtese());
			statement.setString(1, obj.getTipoProtese());
			statement.setDouble(3, obj.getPrecoProtese());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	
	public boolean excluirTabeladePreco(int idProtese) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_TABELADEPRECO);
			statement.setInt(1, idProtese);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;

	}
}
