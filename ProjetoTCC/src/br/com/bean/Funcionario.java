package br.com.bean;

public class Funcionario extends Pessoa {
	private String login;
	private String senha;
	public static void main(String args[]){
		Pessoa funcionario = new Pessoa();
		cadastroFuncionario(funcionario);
		
		Pessoa cliente = new Pessoa();
		cadastroCliente(cliente);
		
		//TabeladePreco Prot = new TabeladePreco();
		//cadastroProteses(Prot);
	
	}
	//Métodos
	public static void cadastroFuncionario(Pessoa funcionario){
				
	}
	public static void cadastroCliente(Pessoa cliente){
				
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
