package br.com.bean;

import java.util.Date;

import br.com.bean.Funcionario;

public class FolhaPagamento extends Funcionario{
	private Double comissaoFuncTotal;
	private Double bonusFunc;
	private Double totalFunc;
	private Integer codDep;
	private Date dataInicio;
	private Date dataFim;
	
	public static void main(String args[]){
			
	}

	//Getter and Setter	
	
	public Double getComissaoFuncTotal() {
		return comissaoFuncTotal;
	}

	public Integer getCodDep() {
		return codDep;
	}

	public void setCodDep(Integer codDep) {
		this.codDep = codDep;
	}

	public Double getTotalFunc() {
		return totalFunc;
	}

	public void setComissaoFuncTotal(Double comissaoFuncTotal) {
		this.comissaoFuncTotal = comissaoFuncTotal;
	}

	public Double getBonusFunc() {
		return bonusFunc;
	}

	public void setBonusFunc(Double bonusFunc) {
		this.bonusFunc = bonusFunc;
	}
	
	public Double getTotalFunc(Double comissao, Double bonus, Double salario) {
		return totalFunc = comissao + bonus + salario;
	}

	public void setTotalFunc(Double totalFunc) {
		this.totalFunc = totalFunc;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	//Métodos
	public Double folhadePagamento(Double salario){
		
		return salario;
	}

}
