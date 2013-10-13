package br.com.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.menu.Login;
import br.com.menu.Principal;
import br.com.menu.PrincipalAdm;
import br.com.util.CriptografiaUtil;

import javax.swing.JOptionPane;

import br.com.exception.DaoException;
import br.com.util.DbUtil;
import br.com.bean.Funcionario;

public class FuncionarioDao {	
	private static final String EXCLUIR_FUNCIONARIO = 
			"delete from tbfuncionario where numFunc = ?";
	
	private static final String INSERIR_FUNCIONARIO =
			"insert into tbfuncionario(nomeFunc, telFunc, profissaoFunc, "+
			"login_funcionario, senha_funcionario, salarioFunc, comissaoFunc) " +
			"values (?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_FUNCIONARIO =
			"update tbfuncionario set " +
			"nomeFunc = ?, " +
			"telFunc = ?, " +
			"profissaoFunc = ?, " +
			"login_funcionario = ?, " +
			"senha_funcionario = ?, " +
			"salarioFunc = ?, " +
			"comissaoFunc = ? " +
			"where numFunc = ? ";
	
	private static final String ATUALIZAR_FUNCIONARIO_FOLHA =
			"update tbfuncionario set " +
			"nomeFunc = ?, " +
			//"telFunc = ?, " +
			"profissaoFunc = ?, " +
			"salarioFunc = ?, " +
			//"login_funcionario = ?, " +
			//"senha_funcionario = ? " +
			"where numFunc = ? ";
	
	private static final String VALIDAR_LOGIN_SENHA = 
		"select "+
			"count(numFunc) as total " +
		"from "+
			"tbfuncionario f "+
		"where "+
			"f.login_funcionario = ? and " +
			"f.senha_funcionario = ?";
	
	private static final  String CONSULTA_FUNCIONARIOS =
			"select * from tbfuncionario order by numFunc";
	
	private static final  String CONSULTA_FUNCIONARIO_ID = 
			"select * from tbfuncionario where numFunc = ?";
	
	private static final  String CONSULTA_FUNCIONARIOS_NOME =
			"select * from tbfuncionario where nomeFunc like ? order by nomeFunc";	
	
	private static final String PROCURAR_FUNCIONARIO_ID =
			"select nomeFunc, profissaoFunc, salarioFunc, comissaoFunc from tbfuncionario where numFunc = ?";
	
	private static final String LOGIN_IGUAL = 
			"select login_funcionario from tbfuncionario";
	
	private static final  String PROCURAR_FUNCIONARIO_NOME = 
			"select numFunc from tbfuncionario where nomeFunc = ?";
	
	private static final  String FUNCIONARIO_HABILITAR =
			"select profissaoFunc from tbFuncionario where login_funcionario = ?";
	
	/**
	 * Através do número digitado pelo usuário, o sistema faz uma busca e retorna o nome do funcionario
	 * @param string
	 * @return
	 * @throws DaoException
	 */
	Login login;
	
	public List<String> buscarLogin() throws DaoException{				
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<String> retorno = new ArrayList<String>();
		try {
			statement = conn.prepareStatement(LOGIN_IGUAL);
			result = statement.executeQuery();
			while (result.next()) {
				String login = result.getString("login_funcionario");
				retorno.add(login);		
//				objFunc.setLogin(result.getString(1));								
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return retorno;		
	}
	
	
	public Funcionario procurarFuncionarioID(Integer idFunc) throws DaoException{
		Funcionario objFunc = new Funcionario();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(PROCURAR_FUNCIONARIO_ID);
			statement.setInt(1, idFunc);
			result = statement.executeQuery();
			if(result.next()) {
			
				objFunc.setNomeFunc(result.getString(1)); // o número 1 indica a posição do dado no sql--> select (1)nomeCliente, (2)emailCliente from tbcliente where numCliente = ?
				objFunc.setProfissaoFunc(result.getString(2));
				objFunc.setSalarioFunc(result.getDouble(3));
				objFunc.setComissaoFunc(result.getDouble(4));
				
			}else{
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
			}
		}
			catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return objFunc;	
	}
	
	public Funcionario procurarFuncionarioNome(String nomeFunc) throws DaoException{
		Funcionario objFunc = new Funcionario();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(PROCURAR_FUNCIONARIO_NOME);
			statement.setString(1, nomeFunc);
			result = statement.executeQuery();
			if(result.next()) {
			
				objFunc.setNumFunc(result.getInt(1)); 
				
			}else{
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
			}
		}
			catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DbUtil.close(conn, statement, result);
			}
			return objFunc;	
	}
	
	public boolean getAutenticacao(String nome, String senha) throws DaoException, SQLException {
		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		int numReg = 0;
		boolean autenticado = false;
		
		String profissao;
		
//		if(nome == "admin" && senha == "admin"){
//			try{
////			if (result.next()) {
//			numReg = 1 ;
//			} finally {
//				DbUtil.close(conn, statement, result);
//			}
//			if(numReg != 0){
//				return autenticado = true;
//			}else{
//				return autenticado;			
//			}
//		}else{
//		
			try {			
			statement = conn.prepareStatement(VALIDAR_LOGIN_SENHA);
			statement.setString(1, nome);
//			statement.setString(2, senha); //habilitar essa linha quando for acessar o sistema pela 1ª vez  e desabilitar o try e catch abaixo
			try {
				statement.setString(2, CriptografiaUtil.encripta(senha)); //Encripta a senha para poder comparar com a senha salva no BD
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = statement.executeQuery();
			if (result.next()) {
				numReg = result.getInt("total");
				
				statement = conn.prepareStatement("select profissaoFunc from tbFuncionario where login_funcionario = ?");
				statement.setString(1, nome);
				
				result = statement.executeQuery();
				
				result.next();
				profissao = result.getString(1);
				
				if(profissao != "Administrador" || profissao != "Adm" || profissao != "Diretor" ){

//					JOptionPane.showMessageDialog(null, profissao);
					
					PrincipalAdm principal = new PrincipalAdm();
					principal.setVisible(true);				
					
					
				}else{
//					JOptionPane.showMessageDialog(null, profissao);
					
					Principal principal = new Principal();
					principal.setVisible(true);
					
										
				}
				
				
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		if(numReg != 0){
			return autenticado = true;
		}
		else{
			return autenticado;			
		}
//		}
	}		

	
	public List<Funcionario> consultarFuncionarios() throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Funcionario> listaFunc = new ArrayList<Funcionario>();
		try {
			statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS);
			result = statement.executeQuery();
			while (result.next()) {
				Funcionario objFunc = new Funcionario();
				objFunc.setNumFunc(result.getInt(1));
				objFunc.setNomeFunc(result.getString(2));
				objFunc.setTelFunc(result.getString(3));
				objFunc.setProfissaoFunc(result.getString(4));
				objFunc.setLogin(result.getString(5));
				objFunc.setSenha(result.getString(6));
				objFunc.setSalarioFunc(result.getDouble(7));
				objFunc.setComissaoFunc(result.getDouble(8));	
				listaFunc.add(objFunc);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFunc;		
	}

	public Funcionario consultarFuncionarioID(int idFunc) throws DaoException{		
		Funcionario objFunc = new Funcionario();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_FUNCIONARIO_ID);
			statement.setInt(1, idFunc);
			result = statement.executeQuery();
			while (result.next()) {
				//JOptionPane.showMessageDialog(null, String.valueOf(result.getInt(1)));
				objFunc.setNumFunc(result.getInt(1));
				objFunc.setNomeFunc(result.getString(2));
				objFunc.setTelFunc(result.getString(3));
				objFunc.setProfissaoFunc(result.getString(4));
				objFunc.setLogin(result.getString(5));
				objFunc.setSenha(result.getString(6));
				objFunc.setSalarioFunc(result.getDouble(7));
				objFunc.setComissaoFunc(result.getDouble(8));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objFunc;		
	}
	
	public List<Funcionario> consultarFuncionarios(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Funcionario> listaFunc = new ArrayList<Funcionario>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_FUNCIONARIOS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Funcionario objFunc = new Funcionario();
				objFunc.setNumFunc(result.getInt(1));
				objFunc.setNomeFunc(result.getString(2));
				objFunc.setTelFunc(result.getString(3));
				objFunc.setProfissaoFunc(result.getString(4));
				objFunc.setSalarioFunc(result.getDouble(7));
				objFunc.setComissaoFunc(result.getDouble(8));
				objFunc.setLogin(result.getString(5));
				objFunc.setSenha(result.getString(6));
				listaFunc.add(objFunc);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaFunc;		
	}	

	public int inserirFuncionarios(Funcionario obj, int matri) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = conn.prepareStatement(INSERIR_FUNCIONARIO);
			statement.setString(1, obj.getNomeFunc());
			statement.setString(2, obj.getTelFunc());
			statement.setString(3, obj.getProfissaoFunc());
			statement.setString(4, obj.getLogin());
			try {
				statement.setString(5, CriptografiaUtil.encripta(obj.getSenha())); //criptografando a senha
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement.setDouble(6, obj.getSalarioFunc());			

			statement.setDouble(7, obj.getComissaoFunc());
			
			statement.executeUpdate();
			
			
			statement = conn.prepareStatement("select max(numFunc) from tbfuncionario");
			result = statement.executeQuery();
			
			result.next();
			matri = result.getInt(1);
			
			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!\n Número do Funcionário: " + matri);
			

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return matri;		
	}
	
	public boolean atualizarFuncionario(Funcionario objFunc) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FUNCIONARIO);
			statement.setString(1, objFunc.getNomeFunc());
			statement.setString(2, objFunc.getTelFunc());
			statement.setString(3, objFunc.getProfissaoFunc());
			statement.setString(4, objFunc.getLogin());
			try {
				statement.setString(5, CriptografiaUtil.encripta(objFunc.getSenha())); //criptografando a senha	
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			statement.setInt(8, objFunc.getNumFunc()); //6
			statement.setDouble(6, objFunc.getSalarioFunc());
			statement.setDouble(7, objFunc.getComissaoFunc());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean atualizarFuncionarioFunc(Funcionario objFunc) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_FUNCIONARIO_FOLHA);
			statement.setString(1, objFunc.getNomeFunc());
			//statement.setString(2, objFunc.getTelFunc());
			statement.setString(3, objFunc.getProfissaoFunc());
			statement.setDouble(6, objFunc.getSalarioFunc());
			//statement.setString(5, objFunc.getLogin());
			//statement.setString(6, objFunc.getSenha());		
			statement.setInt(7, objFunc.getNumFunc());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirFuncionarios(int idFuncioanrio) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_FUNCIONARIO);
			statement.setInt(1, idFuncioanrio);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	

	public Funcionario habilitarMenu(String nomeFunc) throws DaoException{		
		Funcionario objFunc = new Funcionario();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(FUNCIONARIO_HABILITAR);
			statement.setString(1, nomeFunc);
			result = statement.executeQuery();
			while (result.next()) {
				
				objFunc.setProfissaoFunc(result.getString(1));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objFunc;	
}
}