package br.com.bean;

public class Agenda extends Pedido{
	public static void main(String args[]){
		ClienteDent cliente = new ClienteDent();
		Pedido objPedido = new Pedido();
		servicosAgendados(cliente, objPedido);
		
		todosClientes(cliente);
		
		Fornecedor objFornecedor = new Fornecedor();
		todosFornecedores(objFornecedor);
	}
	
	public static void servicosAgendados(ClienteDent cliente, Pedido objPedido){
		System.out.println(cliente.getNumCliente());
		System.out.println(cliente.getNomeCliente());
		System.out.println(cliente.getTelCliente());
		System.out.println(cliente.getCpfCliente());
		System.out.println(cliente.getRuaCliente());
		System.out.println(cliente.getNumEndCliente());
		System.out.println(cliente.getBairroCliente());
		System.out.println(cliente.getCidadeCliente());
		System.out.println(cliente.getCepCliente());
		System.out.println(objPedido.getNumPed());
		System.out.println(objPedido.getNomePaciente());
		System.out.println(objPedido.getDataEntrega());
		System.out.println(objPedido.getDataPedido());
		
	}
	
	public static void todosClientes(ClienteDent cliente){
		System.out.println(cliente.getNumCliente());
		System.out.println(cliente.getNomeCliente());
		System.out.println(cliente.getTelCliente());
		System.out.println(cliente.getCpfCliente());
		System.out.println(cliente.getRuaCliente());
		System.out.println(cliente.getNumEndCliente());
		System.out.println(cliente.getBairroCliente());
		System.out.println(cliente.getCidadeCliente());
		System.out.println(cliente.getCepCliente());
		System.out.println(cliente.getEmailCliente());
	}
	public static void todosFornecedores(Fornecedor objFornecedor){
		System.out.println(objFornecedor.getNomeFornec());
		System.out.println(objFornecedor.getTelFornec());
		System.out.println(objFornecedor.getRuaFornec());
		System.out.println(objFornecedor.getNumEndFornec());
		System.out.println(objFornecedor.getBairroFornec());
		System.out.println(objFornecedor.getCidadeFornec());
		System.out.println(objFornecedor.getCepFornec());
	}

}
