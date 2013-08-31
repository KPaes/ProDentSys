package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.bean.Pedido;
import br.com.exception.DaoException;
import br.com.util.DbUtil;

public class RelatorioDao {
	private static final  String CONSULTA_PEDIDOS =
			"select * from tbpedido order by numPed";
	
	private static final  String CONSULTA_RELATORIO_DATA =
			"select * from tbpedido where dataEntrega like ? order by dataEntrega";	

	private static final  String CONSULTA_RELATORIO_ID = 
			"select * from tbpedido where numPed = ?";
	
	public List<Pedido> consultarRelatorio(String data) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		try {
			if(data.equals("")){
				statement = conn.prepareStatement(CONSULTA_PEDIDOS);
			}else{
				statement = conn.prepareStatement(CONSULTA_RELATORIO_DATA);
				statement.setString(1, data);
			}
			result = statement.executeQuery();
			if (result.next()) {
				Pedido objPedido = new Pedido();
				objPedido.setNumPed(result.getInt(1)); 
				objPedido.setNumCliente(result.getInt(3)); 
				objPedido.setNomeCliente(result.getString(2));
				objPedido.setNomePaciente(result.getString(4));
				objPedido.setDataPedido(result.getString(5));
				objPedido.setDataEntrega(result.getString(6));
				objPedido.setTipoProtese(result.getString(7)); //arrumar
				objPedido.setNomeProtese(result.getString(8)); //arrumar
				objPedido.setNomeFunc(result.getString(9));
				objPedido.setNumFunc(result.getInt(10));
				objPedido.setTotalPedido(result.getDouble(11));
				listaPedido.add(objPedido);
			}else{
				JOptionPane.showMessageDialog(null, "Não há pedidos para ser entregues nesta data");
			}				
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return listaPedido;		
	}	
	
	public List<Pedido> consultarRelatorio() throws DaoException{		
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
				objPedido.setDataPedido(result.getString(5));
				objPedido.setDataEntrega(result.getString(6));
				objPedido.setTipoProtese(result.getString(7)); 
				objPedido.setNomeProtese(result.getString(8)); 
				objPedido.setNomeFunc(result.getString(9));
				objPedido.setNumFunc(result.getInt(10));
				objPedido.setTotalPedido(result.getDouble(11));
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
			statement = conn.prepareStatement(CONSULTA_RELATORIO_ID);
			statement.setInt(1, idPedido);
			result = statement.executeQuery();
			while (result.next()) {
				objPedido.setNumPed(result.getInt(1)); 
				objPedido.setNumCliente(result.getInt(3)); 
				objPedido.setNomeCliente(result.getString(2));
				objPedido.setNomePaciente(result.getString(4));
				objPedido.setDataPedido(result.getString(5));
				objPedido.setDataEntrega(result.getString(6));
				objPedido.setTipoProtese(result.getString(7));
				objPedido.setNomeProtese(result.getString(8));
				objPedido.setNomeFunc(result.getString(9));
				objPedido.setNumFunc(result.getInt(10));
				objPedido.setTotalPedido(result.getDouble(11));				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement, result);
		}
		return objPedido;		
	}

}
