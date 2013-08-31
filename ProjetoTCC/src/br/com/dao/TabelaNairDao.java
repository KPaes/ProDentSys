package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.exception.DaoException;
import br.com.util.DbUtil;
import br.com.bean.TabeladePrecoNair;

public class TabelaNairDao {
	private static final String EXCLUIR_TABELADEPRECO =
			"delete from tbtabeladeprecosseg where numProteseSeg = ?";
	
	private static final String INSERIR_TABELADEPRECO =
			"insert into tbtabeladeprecosseg(nomeProteseSeg, tipoProteseSeg, precoProteseSeg) "+
			"values (?,?,?)";
	
	private static final String ATUALIZAR_TABELADEPRECO =
			"update tbtabeladeprecosseg set " +
			"nomeProteseSeg = ?, " +
			"tipoProteseSeg = ?, " +
			"precoProteseSeg = ? " +			
			"where numProteseSeg = ? ";
	
	private static final  String CONSULTA_TABELADEPRECO =
			"select * from tbtabeladeprecosseg order by nomeProteseSeg";
	
	private static final  String CONSULTA_TABELADEPRECO_ID = 
			"select * from tbtabeladeprecosseg where numProteseSeg = ?";
	
	public List<TabeladePrecoNair> consultarProteses() throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<TabeladePrecoNair> listaTab = new ArrayList<TabeladePrecoNair>();
		try {
			statement = conn.prepareStatement(CONSULTA_TABELADEPRECO);
			result = statement.executeQuery();
			while (result.next()) {
				TabeladePrecoNair objTab = new TabeladePrecoNair();
				objTab.setNumProteseSeg(result.getInt(1));
				objTab.setNomeProteseSeg(result.getString(2));
				objTab.setTipoProteseSeg(result.getString(3));
				objTab.setPrecoProteseSeg(result.getDouble(4));
				listaTab.add(objTab);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaTab;		
	}

	public TabeladePrecoNair consultarTabeladePrecoID(int idFunc) throws DaoException{		
		TabeladePrecoNair objTab = new TabeladePrecoNair();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_TABELADEPRECO_ID);
			statement.setInt(1, idFunc);
			result = statement.executeQuery();
			while (result.next()) {
				objTab.setNumProteseSeg(result.getInt(1));
				objTab.setNomeProteseSeg(result.getString(2));
				objTab.setTipoProteseSeg(result.getString(3));
				objTab.setPrecoProteseSeg(result.getDouble(4));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objTab;		
	}
	
	public boolean atualizarTabeladePreco(TabeladePrecoNair objTab) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_TABELADEPRECO);
			statement.setString(1, objTab.getNomeProteseSeg());
			statement.setString(2, objTab.getTipoProteseSeg());
			statement.setDouble(3, objTab.getPrecoProteseSeg());	
			statement.setInt(4,objTab.getNumProteseSeg());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean inserirTabeladePreco(TabeladePrecoNair obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_TABELADEPRECO);
			statement.setString(2, obj.getNomeProteseSeg());
			statement.setString(3, obj.getTipoProteseSeg());
			statement.setDouble(4, obj.getPrecoProteseSeg());
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