package br.com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.exception.DaoException;
import br.com.util.DbUtil;
import br.com.bean.Pedido;

public class PedidoDao {

	private static final String EXCLUIR_PEDIDO = 
			"delete from tbpedido where numPed = ?";
	
	private static final String INSERIR_PEDIDO =
			"insert into tbpedido(nomeCliente, numCliente, nomePaciente, dataPedido, "+
			"dataEntrega, tipoProtese, nomeProtese,  nomeFunc, " + //precoProtese, numProtese,
			"numFunc, totalPedido, observacoesPed, cpfCliente) " +
			"values (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String ATUALIZAR_PEDIDO =
			"update tbpedido set " +
			"nomeCliente = ?, " +
			"numCliente = ?, " +
			"nomePaciente = ?, " +
			"dataPedido = ?, " +
			"dataEntrega = ?, " +
			"tipoProtese = ?, " +
			"nomeProtese = ?, " +					
			"nomeFunc = ?, " +
			"numFunc = ?, " +
			"totalPedido = ?, " +
			"observacoesPed = ?, " +
			"cpfCliente = ? " +
			"where numPed = ? ";
	
	
	private static final  String CONSULTA_PEDIDOS =
			"select * from tbpedido order by numPed desc";
	
	private static final  String CONSULTA_PEDIDOS_ID = 
			"select * from tbpedido where numPed = ?";
	
	private static final  String CONSULTA_PEDIDOS_NOME =
			"select * from tbpedido where nomeCliente like ? order by nomeCliente";	
	
	private static final String TOTAL_BUSCAR = 
			"select SUM(totalPedido) from tbpedido where numFunc = ? and dataEntrega between ? and ?";
	
	
	public Pedido comissaoTotalPedido(int idFunc, Date data1, Date data2) throws DaoException{		
		Pedido objPed = new Pedido();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(TOTAL_BUSCAR);
			statement.setInt(1, idFunc);
			statement.setDate(2, DbUtil.getSqlDate( data1));
        	statement.setDate(3, DbUtil.getSqlDate( data2));
			result = statement.executeQuery();
			
			while (result.next()){				
			objPed.setTotalPedido(result.getDouble(1));
				
			}
			
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objPed;		
	}
	
	public List<Pedido> consultarPedidos(String nome) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		try {
			if(nome.equals("")){
				statement = conn.prepareStatement(CONSULTA_PEDIDOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_PEDIDOS_NOME);
				statement.setString(1, "%"+nome+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				Pedido objPedido = new Pedido();
				objPedido.setNumPed(result.getInt(1)); 
				objPedido.setNumCliente(result.getInt(3)); 
				objPedido.setNomeCliente(result.getString(2));
				objPedido.setNomePaciente(result.getString(4));
				
				objPedido.setDataPedido(result.getDate(5));
				objPedido.setDataEntrega(result.getDate(6));
				
				objPedido.setTipoProtese(result.getString(7));
				objPedido.setNomeProtese(result.getString(8)); 
				objPedido.setNomeFunc(result.getString(9));
				objPedido.setNumFunc(result.getInt(10));
				objPedido.setTotalPedido(result.getDouble(11));
				objPedido.setObservacoesPed(result.getString(12));
				objPedido.setCpfCliente(result.getString(13));
				listaPedido.add(objPedido);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaPedido;		
	}	
	
	public List<Pedido> consultarPedidos() throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		try {
			statement = conn.prepareStatement(CONSULTA_PEDIDOS);
			result = statement.executeQuery();
			while (result.next()) {
				Pedido objPedido = new Pedido();
				objPedido.setNumPed(result.getInt(1)); 
				objPedido.setNumCliente(result.getInt(3)); 
				objPedido.setNomeCliente(result.getString(2));
				objPedido.setNomePaciente(result.getString(4));
				
//				objPedido.setDataPedido(result.getString(5));
//				objPedido.setDataEntrega(result.getString(6));
				
				objPedido.setDataPedido(result.getDate(5)); 
				objPedido.setDataEntrega(result.getDate(6));
				
				
				objPedido.setTipoProtese(result.getString(7)); //arrumar
				objPedido.setNomeProtese(result.getString(8)); //arrumar
			//	objPedido.setPrecoProtese(result.getDouble(9)); //arrumar
			//	objPedido.setNumProtese(result.getInt(10)); //arrumar
				objPedido.setNomeFunc(result.getString(9));
				objPedido.setNumFunc(result.getInt(10));
				objPedido.setTotalPedido(result.getDouble(11));
				objPedido.setObservacoesPed(result.getString(12));
				objPedido.setCpfCliente(result.getString(13));
				listaPedido.add(objPedido);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaPedido;		
	}

	public Pedido consultarPedidoID(int idPedido) throws DaoException{		
		Pedido objPedido = new Pedido();
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(CONSULTA_PEDIDOS_ID);
			statement.setInt(1, idPedido);
			result = statement.executeQuery();
			while (result.next()) {
				objPedido.setNumPed(result.getInt(1)); 
				objPedido.setNumCliente(result.getInt(3)); 
				objPedido.setNomeCliente(result.getString(2));
				objPedido.setNomePaciente(result.getString(4));
				
//				objPedido.setDataPedido(result.getString(5));
//				objPedido.setDataEntrega(result.getString(6));
				
				objPedido.setDataPedido(result.getDate(5));
				objPedido.setDataEntrega(result.getDate(6));
				
				objPedido.setTipoProtese(result.getString(7)); //arrumar
				objPedido.setNomeProtese(result.getString(8)); //arrumar
			//	objPedido.setPrecoProtese(result.getDouble(9)); //arrumar
			//	objPedido.setNumProtese(result.getInt(10)); //arrumar
				objPedido.setNomeFunc(result.getString(9));
				objPedido.setNumFunc(result.getInt(10));
				objPedido.setTotalPedido(result.getDouble(11));
				objPedido.setObservacoesPed(result.getString(12));
				objPedido.setCpfCliente(result.getString(13)); //criar
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objPedido;		
	}

	public boolean inserirPedidos(Pedido obj) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(INSERIR_PEDIDO);
			statement.setInt(2, obj.getNumCliente());
			statement.setString(1, obj.getNomeCliente());
			statement.setString(3, obj.getNomePaciente());
			
//			statement.setString(4, obj.getDataPedido());
//			statement.setString(5, obj.getDataEntrega());
			

			statement.setDate(4, DbUtil.getSqlDate(obj.getDataPedido()));
			statement.setDate(5, DbUtil.getSqlDate( obj.getDataEntrega()));
			
			statement.setString(6, obj.getTipoProtese()); //busca da tabela
			statement.setString(7, obj.getNomeProtese()); //""
		//	statement.setDouble(8, obj.getPrecoProtese()); //"
		//	statement.setInt(9, obj.getNumProtese());			
			statement.setString(8, obj.getNomeFunc());
			statement.setInt(9, obj.getNumFunc());
			statement.setDouble(10, obj.getTotalPedido());
			statement.setString(11, obj.getObservacoesPed());
			statement.setString(12, obj.getCpfCliente()); 
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	
	public boolean atualizarPedido(Pedido objPedido) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(ATUALIZAR_PEDIDO);
			statement.setInt(2, objPedido.getNumCliente());
			statement.setString(1, objPedido.getNomeCliente());
			statement.setString(3, objPedido.getNomePaciente());
			
//			statement.setString(4, objPedido.getDataPedido());
//			statement.setString(5, objPedido.getDataEntrega());
			

			statement.setDate(4, DbUtil.getSqlDate(objPedido.getDataPedido()));
			statement.setDate(5, DbUtil.getSqlDate(objPedido.getDataEntrega()));
			
			statement.setString(6, objPedido.getTipoProtese()); //tabela de preço
			statement.setString(7, objPedido.getNomeProtese()); //" "
	//		statement.setDouble(8, objPedido.getPrecoProtese()); //" "
	//		statement.setInt(9, objPedido.getNumProtese());
			statement.setString(8, objPedido.getNomeFunc());
			statement.setInt(9, objPedido.getNumFunc());
			statement.setDouble(10, objPedido.getTotalPedido());
			statement.setString(11, objPedido.getObservacoesPed());
			statement.setString(12, objPedido.getCpfCliente()); 
			statement.setInt(13, objPedido.getNumPed());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}

	public boolean excluirPedidos(int idPedido) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = conn.prepareStatement(EXCLUIR_PEDIDO);
			statement.setInt(1, idPedido);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return true;		
	}
	

}
