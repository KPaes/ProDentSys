package br.com.bean;

import java.util.Date;

public class Pedido extends Pessoa{
	//Atributos
	private Integer numPed;
	private String nomePaciente;
//	private String dataPedido;
//	private String dataEntrega;
	private Date dataPedido;
	private Date dataEntrega;
	private Double totalPedido;
	private String observacoesPed;

		
	//Getter and Setter	
	public String getNomePaciente() {
		return nomePaciente;
	}
	

	public String getObservacoesPed() {
		return observacoesPed;
	}


	public void setObservacoesPed(String observacoesPed) {
		this.observacoesPed = observacoesPed;
	}


	public Double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;		
	}
	
	public Date getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataEntrega(Date dataEntrega) {
//		int tamanho = 10;
//		if(tamanho == dataEntrega.length()){ //dd/mm/aaaa
			this.dataEntrega = dataEntrega;
//		}
	}
	
	public Integer getNumPed() {
		return numPed;
	}
	
	public void setDataPedido(Date date) {
//		int tamanho = 10;
//		if(tamanho == dataPedido.length()){ //dd/mm/aaaa
			this.dataPedido = date;
//		}
	}
	
	
	public void setNumPed(Integer numPed) {
		this.numPed = numPed;
	}

	public Date getDataPedido() {
		return dataPedido;
	}
	
	
	
	//Métodos
	public void efetuaPedido(){
		
		
	}

}
