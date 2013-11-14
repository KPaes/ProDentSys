package br.com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.exception.DaoException;
import br.com.util.DbUtil;
import br.com.bean.ClienteDent;

public class ClienteDao {

	private static final String EXCLUIR_CLIENTE = 
//			"delete from tbcliente where numCliente = ?";
			"update tbcliente set " +
			"situacaoCliente = ? " +
			"where numCliente = ? ";

	private static final String ATIVAR_CLIENTE = 
			"update tbcliente set " +
			"situacaoCliente = ? " +
			"where numCliente = ? ";
	
	private static final String INSERIR_CLIENTE =
			"insert into tbcliente(nomeCliente, cpfCliente, telCliente, "+
			"ruaCliente, numEndCliente, bairroCliente, cidadeCliente, cepCliente, emailCliente, complCliente, situacaoCliente) " +
			"values (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_CLIENTE =
			"update tbCliente set " +
			"nomeCliente = ?, " +
			"cpfCliente = ?, " +
			"telCliente = ?, " +
			"ruaCliente = ?, " +
			"numEndCliente = ?, " +
			"bairroCliente = ?, " +
			"cidadeCliente = ?, " +
			"cepCliente = ?, " +
			"emailCliente = ?, " +
			"complCliente = ? " +
			"where numCliente = ? ";
	
	
	private static final  String CONSULTA_CLIENTES =
			"select * from tbcliente where situacaoCliente = 'A' order by nomeCliente";
	
	private static final  String CONSULTA_CLIENTES_ID = 
			"select * from tbcliente where numCliente = ?";	
	
	private static final  String CONSULTA_CLIENTE_NOME =
			"select * from tbcliente where nomeCliente like ? and situacaoCliente='A' order by nomeCliente";		
	
	private static final String PROCURAR_CLIENTE_ID =
			"select nomeCliente, emailCliente, cpfCliente from tbcliente where numCliente = ? and situacaoCliente='A'";
	
	private static final String VERIFICAR_CPF_EXISTENTE =
			"SELECT * FROM tbcliente WHERE cpfCliente = ?";
	
	private static final  String CONSULTA_CLIENTES_INATIVOS =
			"select * from tbcliente where situacaoCliente = 'I' order by nomeCliente";
	
	private static final  String CONSULTA_CLIENTE_NOME_INATIVO =
			"select * from tbcliente where nomeCliente like ? and situacaoCliente = 'I' order by nomeCliente";
	
	
	/**
	 * Através do número digitado pelo usuário, o sistema faz uma busca e retorna o nome e o email
	 * @param idCliente
	 * @return
	 * @throws DaoException
	 */
	public ClienteDent procurarClienteID(Integer idCliente) throws DaoException{
		ClienteDent objCliente = new ClienteDent();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(PROCURAR_CLIENTE_ID);
			statement.setInt(1, idCliente);
			result = statement.executeQuery();
			if (result.next()) {
			
				objCliente.setNomeCliente(result.getString(1)); // o número 1 indica a posição do dado no sql--> select (1)nomeCliente, (2)emailCliente from tbcliente where numCliente = ?
				objCliente.setEmailCliente(result.getString(2));
				objCliente.setCpfCliente(result.getString(3));
			}else{
				JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
			}
			
		}
			catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return objCliente;	
	}
	
	
	
	public List<ClienteDent> consultarClientes() throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteDent> listaCliente = new ArrayList<ClienteDent>();
		try {
			statement = conn.prepareStatement(CONSULTA_CLIENTES);
			result = statement.executeQuery();
			while (result.next()) {
				ClienteDent objCliente = new ClienteDent();
				objCliente.setNumCliente(result.getInt(1)); 
				objCliente.setNomeCliente(result.getString(2));
				objCliente.setCpfCliente(result.getString(3));
				objCliente.setTelCliente(result.getString(4));
				objCliente.setRuaCliente(result.getString(5));
				objCliente.setNumEndCliente(result.getString(6));
				objCliente.setBairroCliente(result.getString(7));
				objCliente.setCidadeCliente(result.getString(8));
				objCliente.setCepCliente(result.getString(9));
				objCliente.setEmailCliente(result.getString(10));
				objCliente.setComplCliente(result.getString(11));
				listaCliente.add(objCliente);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaCliente;		
	}

	public ClienteDent consultarClienteID(int idCliente) throws DaoException{		
		ClienteDent objCliente = new ClienteDent();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_CLIENTES_ID);
			statement.setInt(1, idCliente);
			result = statement.executeQuery();
			while (result.next()) {
				objCliente.setNumCliente(result.getInt(1));
				objCliente.setNomeCliente(result.getString(2));
				objCliente.setCpfCliente(result.getString(3));
				objCliente.setTelCliente(result.getString(4));
				objCliente.setRuaCliente(result.getString(5));
				objCliente.setNumEndCliente(result.getString(6));
				objCliente.setBairroCliente(result.getString(7));
				objCliente.setCidadeCliente(result.getString(8));
				objCliente.setCepCliente(result.getString(9));
				objCliente.setEmailCliente(result.getString(10));
				objCliente.setComplCliente(result.getString(11));

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objCliente;		
	}
	
	public List<ClienteDent> consultarClientes(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteDent> listaCliente = new ArrayList<ClienteDent>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_CLIENTES);
			}else{
				statement = conn.prepareStatement(CONSULTA_CLIENTE_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ClienteDent objCliente = new ClienteDent();
				objCliente.setNumCliente(result.getInt(1));
				objCliente.setNomeCliente(result.getString(2));
				objCliente.setCpfCliente(result.getString(3));
				objCliente.setTelCliente(result.getString(4));
				objCliente.setRuaCliente(result.getString(5));
				objCliente.setNumEndCliente(result.getString(6));
				objCliente.setBairroCliente(result.getString(7));
				objCliente.setCidadeCliente(result.getString(8));
				objCliente.setCepCliente(result.getString(9));
				objCliente.setEmailCliente(result.getString(10));
				objCliente.setComplCliente(result.getString(11));
				listaCliente.add(objCliente);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaCliente;		
	}	
	
	public List<ClienteDent> consultarClientesInativos(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<ClienteDent> listaCliente = new ArrayList<ClienteDent>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_CLIENTES_INATIVOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_CLIENTE_NOME_INATIVO);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				ClienteDent objCliente = new ClienteDent();
				objCliente.setNumCliente(result.getInt(1));
				objCliente.setNomeCliente(result.getString(2));
				objCliente.setCpfCliente(result.getString(3));
				objCliente.setTelCliente(result.getString(4));
				objCliente.setRuaCliente(result.getString(5));
				objCliente.setNumEndCliente(result.getString(6));
				objCliente.setBairroCliente(result.getString(7));
				objCliente.setCidadeCliente(result.getString(8));
				objCliente.setCepCliente(result.getString(9));
				objCliente.setEmailCliente(result.getString(10));
				objCliente.setComplCliente(result.getString(11));
				listaCliente.add(objCliente);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaCliente;		
	}	
	
	public int inserirClientes(ClienteDent obj, int matri) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_CLIENTE);
			statement.setString(1, obj.getNomeCliente());
			statement.setString(3, obj.getTelCliente());
			statement.setString(2, obj.getCpfCliente());
			statement.setString(4, obj.getRuaCliente());
			statement.setString(5, obj.getNumEndCliente());
			statement.setString(6, obj.getBairroCliente());
			statement.setString(7, obj.getCidadeCliente());
			statement.setString(8, obj.getCepCliente());
			statement.setString(9, obj.getEmailCliente());
			statement.setString(10, obj.getComplCliente());
			statement.setString(11, "A");
			statement.executeUpdate();
			
			statement = conn.prepareStatement("select max(numCliente) from tbcliente");
			result = statement.executeQuery();
			
			
			result.next();
			matri = result.getInt(1);
			
			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!\n Número do Cliente: " + matri);

			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}	
		return matri;		
	}
	
	public boolean atualizarCliente(ClienteDent objCliente) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_CLIENTE);
			statement.setString(1, objCliente.getNomeCliente());
			statement.setString(3, objCliente.getTelCliente());
			statement.setString(2, objCliente.getCpfCliente());
			statement.setString(4, objCliente.getRuaCliente());
			statement.setString(5, objCliente.getNumEndCliente());
			statement.setString(6, objCliente.getBairroCliente());
			statement.setString(7, objCliente.getCidadeCliente());
			statement.setString(8, objCliente.getCepCliente());
			statement.setString(9, objCliente.getEmailCliente());
			statement.setString(10, objCliente.getComplCliente());
			statement.setInt(11, objCliente.getNumCliente());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirClientes(int idCliente) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_CLIENTE);
			statement.setString(1, "I");
			statement.setInt(2, idCliente);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean ativarClientes(int idCliente) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATIVAR_CLIENTE);
			statement.setString(1, "A");
			statement.setInt(2, idCliente);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean buscarCpfExistente(String cpfPaciente) throws DaoException{
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;		
		boolean existe = false;
		try{
			statement = conn.prepareStatement(VERIFICAR_CPF_EXISTENTE);	
			statement.setString(1, cpfPaciente);
			result = statement.executeQuery();
			if(result.next()){
				existe = true;				
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DbUtil.close(conn, statement, result);
		
		}
		
		return existe;
	}
}
