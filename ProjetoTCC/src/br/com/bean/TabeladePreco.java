package br.com.bean;

public class TabeladePreco{
	//Atributos Tabela de Pre�o
	private Integer numProtese;
	private String nomeProtese;
	private String tipoProtese;
	private Double precoProtese;
	private boolean selecione;  
	
	public static void main(String args[]){
			TabeladePreco Prot = new TabeladePreco();
			cadastroProteses(Prot);
	}	
	//M�todos
	
	//Getter and Setter Tabela de Pre�o
			public String getNomeProtese() {
				return nomeProtese;
			}
			public void setNomeProtese(String nomeProtese) {
				this.nomeProtese = nomeProtese;
				
			}
			
			public String getTipoProtese() {
				return tipoProtese;
			}
			public void setTipoProtese(String tipoProtese) {
				
					this.tipoProtese = tipoProtese;
				
			}
			
			public Double getPrecoProtese() {
				return precoProtese;
			}
			public void setPrecoProtese(Double precoProtese) {
				this.precoProtese = precoProtese;				
			}
			
			public Integer getNumProtese() {
				return numProtese;
			}
			
			
			public static void cadastroProteses(TabeladePreco Prot){
				
			}

			public void setNumProtese(Integer numProtese) {
				this.numProtese = numProtese;
			}
				
			 public boolean isSelecione() {  
			        return selecione;  
			    }  
			    public void setSelecione(boolean selecione) {  
			        this.selecione = selecione;  
			    }  	
}
