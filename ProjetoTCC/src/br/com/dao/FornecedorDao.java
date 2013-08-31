package br.com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.exception.DaoException;
import br.com.util.DbUtil;
import br.com.bean.Fornecedor;


public class FornecedorDao {

	private static final String EXCLUIR_FORNECEDOR = 
			"delete from tbfornecedor where numFornec = ?";
	
	private static final String INSERIR_FORNECEDOR =
			"insert into tbfornecedor(nomeFornec, telFornec, "+
			"ruaFornec, numEndFornec, bairroFornec, cidadeFornec, cepFornec) " +
			"values (?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_FORNECEDOR =
			"update tbfornecedor set " +
			"nomeFornec = ?, " +
			"telFornec = ?, " +
			"ruaFornec = ?, " +
			"numEndFornec = ?, " +
			"bairroFornec = ?, " +
			"cidadeFornec = ?, " +
			"cepFornec = ? " +
			"where numFornec = ? ";
	
	
	private static final  String CONSULTA_FORNECEDORES =
			"select * from tbfornecedor order by numFornec";
	
	private static final  String CONSULTA_FORNECEDORES_ID = 
			"select * from tbfornecedor where numFornec = ?";
	
	private static final  String CONSULTA_FORNECEDORES_NOME =
			"select * from tbfornecedor where nomeFornec like ? order by nomeFornec";	
	
	public List<Fornecedor> consultarFornecedores(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Fornecedor> listaFornec = new ArrayList<Fornecedor>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_FORNECEDORES);
			}else{
				statement = conn.prepareStatement(CONSULTA_FORNECEDORES_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Fornecedor objFornec = new Fornecedor();
				objFornec.setNumFornec(result.getInt(1));
				objFornec.setNomeFornec(result.getString(2));
				objFornec.setTelFornec(result.getString(3));
				objFornec.setRuaFornec(result.getString(4));
				objFornec.setNumEndFornec(result.getString(5));
				objFornec.setBairroFornec(result.getString(6));
				objFornec.setCidadeFornec(result.getString(7));
				objFornec.setCepFornec(result.getString(8));
				listaFornec.add(objFornec);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFornec;		
	}	
	
	public List<Fornecedor> consultarFornecedores() throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Fornecedor> listaFornec = new ArrayList<Fornecedor>();
		try {
			statement = conn.prepareStatement(CONSULTA_FORNECEDORES);
			result = statement.executeQuery();
			while (result.next()) {
				Fornecedor objFornec = new Fornecedor();
				objFornec.setNumFornec(result.getInt(1));
				objFornec.setNomeFornec(result.getString(2));
				objFornec.setTelFornec(result.getString(3));
				objFornec.setRuaFornec(result.getString(4));
				objFornec.setNumEndFornec(result.getString(5));
				objFornec.setBairroFornec(result.getString(6));
				objFornec.setCidadeFornec(result.getString(7));
				objFornec.setCepFornec(result.getString(8));
				listaFornec.add(objFornec);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFornec;		
	}

	public Fornecedor consultarFornecedorID(int idFornec) throws DaoException{		
		Fornecedor objFornec = new Fornecedor();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_FORNECEDORES_ID);
			statement.setInt(1, idFornec);
			result = statement.executeQuery();
			while (result.next()) {
				objFornec.setNumFornec(result.getInt(1));
				objFornec.setNomeFornec(result.getString(2));
				objFornec.setTelFornec(result.getString(3));
				objFornec.setRuaFornec(result.getString(4));
				objFornec.setNumEndFornec(result.getString(5));
				objFornec.setBairroFornec(result.getString(6));
				objFornec.setCidadeFornec(result.getString(7));
				objFornec.setCepFornec(result.getString(8));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objFornec;		
	}

	public boolean inserirFornecedores(Fornecedor obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_FORNECEDOR);
			statement.setString(1, obj.getNomeFornec());
			statement.setString(2, obj.getTelFornec());
			statement.setString(3, obj.getRuaFornec());
			statement.setString(4, obj.getNumEndFornec());
			statement.setString(5, obj.getBairroFornec());
			statement.setString(6, obj.getCidadeFornec());
			statement.setString(7, obj.getCepFornec());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean atualizarFornecedor(Fornecedor objFornec) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FORNECEDOR);
			statement.setString(1, objFornec.getNomeFornec());
			statement.setString(2, objFornec.getTelFornec());
			statement.setString(3, objFornec.getRuaFornec());
			statement.setString(4, objFornec.getNumEndFornec());
			statement.setString(5, objFornec.getBairroFornec());
			statement.setString(6, objFornec.getCidadeFornec());
			statement.setString(7, objFornec.getCepFornec());
			statement.setInt(8, objFornec.getNumFornec());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirFornecedores(int idFornecedor) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_FORNECEDOR);
			statement.setInt(1, idFornecedor);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
}
