package br.com.bean;

public class Pessoa extends TabeladePreco { //esse extends pode?
	//Cliente
		private Integer numCliente;
		private String nomeCliente;
		private String ruaCliente;
		private String numEndCliente;
		private String bairroCliente;
		private String cidadeCliente;
		private String cepCliente;
		private String cpfCliente;
		private String telCliente;
		private String emailCliente;
	//Fornecedor	
		private Integer numFornec;
		private String nomeFornec;
		private String ruaFornec;
		private String numEndFornec;
		private String bairroFornec;
		private String cidadeFornec;
		private String cepFornec;
		private String telFornec;

	//Funcionário
		private Integer numFunc;
		private String nomeFunc;
		private String telFunc;
		private String profissaoFunc;
		private Double salarioFunc;
		private Double comissaoFunc;
			
	//Getter and Setter Cliente		
		
		public String getNomeCliente() {
			return nomeCliente;
		}
		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente.trim();
		}
		public String getRuaCliente() {
			return ruaCliente;
		}
		public void setRuaCliente(String ruaCliente) {	
			this.ruaCliente = ruaCliente.trim();
		}
		
		public String getNumEndCliente() {
			return numEndCliente;
		}
		public void setNumEndCliente(String numEndCliente) {	
			this.numEndCliente = numEndCliente.trim();
		}
		
		public String getBairroCliente() {
			return bairroCliente;
		}
		public void setBairroCliente(String bairroCliente) {	
			this.bairroCliente = bairroCliente.trim();
		}
		
		public String getCidadeCliente() {
			return cidadeCliente;
		}
		public void setCidadeCliente(String cidadeCliente) {	
			this.cidadeCliente = cidadeCliente.trim();
		}
		
		public String getCepCliente() {
			return cepCliente;
		}
		public void setCepCliente(String cepCliente) {	
			this.cepCliente = cepCliente.trim();			
		}
		
		public String getTelCliente() {
			return telCliente;
		}
		public void setTelCliente(String telCliente) {
			this.telCliente = telCliente;
		}
		
		public Integer getNumCliente() {
			return numCliente;
		}
		public String getCpfCliente() {
			return cpfCliente;
		}
		public void setCpfCliente(String cpfCliente) {
			this.cpfCliente = cpfCliente;
		}
		public String getEmailCliente() {
			return emailCliente;
		}
		public void setEmailCliente(String emailCliente) {
			this.emailCliente = emailCliente;
		}
	//Getter and Setter Fornecedor
		public String getNomeFornec() {
			return nomeFornec;
		}

		public void setNomeFornec(String nomeFornec) {
			this.nomeFornec = nomeFornec.trim();
		}

		public String getRuaFornec() {
			return ruaFornec;
		}
		public void setRuaFornec(String ruaFornec) {	
			this.ruaFornec = ruaFornec.trim();			
		}
		
		public String getNumEndFornec() {
			return numEndFornec;
		}
		public void setNumEndFornec(String numEndFornec) {	
			this.numEndFornec = numEndFornec.trim();
		}
		
		public String getBairroFornec() {
			return bairroFornec;
		}
		public void setBairroFornec(String bairroFornec) {	
			this.bairroFornec = bairroFornec.trim();
		}
		
		public String getCidadeFornec() {
			return cidadeFornec;
		}
		public void setCidadeFornec(String cidadeFornec) {	
			this.cidadeFornec = cidadeFornec.trim();
		}
		
		public String getCepFornec() {
			return cepFornec;
		}
		public void setCepFornec(String cepFornec) {	
			this.cepFornec = cepFornec.trim();
		}

		public String getTelFornec() {
			return telFornec;
		}

		public void setTelFornec(String telFornec) {
			this.telFornec = telFornec.trim();			
		}
		
		public Integer getNumFornec() {
			return numFornec;
		}
		
	//Getter and Setter Funcionário
		public String getNomeFunc() {
			return nomeFunc;
		}
		public void setNomeFunc(String nomeFunc) {
			this.nomeFunc = nomeFunc.trim();
		}
		public String getTelFunc() {
			return telFunc;
		}
		public void setTelFunc(String telFunc) {
			this.telFunc = telFunc.trim();
		}
		public String getProfissaoFunc() {
			return profissaoFunc;
		}
		public void setProfissaoFunc(String profissaoFunc) {
			this.profissaoFunc = profissaoFunc.trim();			
		}
		public void setNumFunc(Integer numFunc){
			this.numFunc = numFunc;
		}
		public Integer getNumFunc() {
			return numFunc;
		}
		public void setNumCliente(Integer numCliente) {
			this.numCliente = numCliente;
		}
		public void setNumFornec(Integer numFornec) {
			this.numFornec = numFornec;
		}
		public Double getSalarioFunc() {
			return salarioFunc;
		}
		public void setSalarioFunc(Double salarioFunc) {
			this.salarioFunc = salarioFunc;
		}
		public Double getComissaoFunc() {
			return comissaoFunc;
		}
		public void setComissaoFunc(Double comissaoFunc) {
			this.comissaoFunc = comissaoFunc;
		}
		
		
	
}
