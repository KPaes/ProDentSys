package br.com.util;

import javax.swing.JProgressBar;

public class BarraDeProgresso implements Runnable{
	JProgressBar progressBar;
	
	  public void rodar () {
	    // mostra barra de progresso e vai atualizando ela
		  
	  }
	  public void setProgresso(int i) {
			progressBar.setValue(i);
			progressBar.setString("Enviando...  "+i/5000+"%");		
		}
	    public void barra(){ //implements Runnable{
	    	for (int i = 0; i < 500000; i++){  
		           System.out.println(i);  
		           setProgresso(i);
		        }
	    }
		@Override
		public void run() {
			int i;
			// TODO Auto-generated method stub
			for ( i = 0; i < 500000; i++){  
		           System.out.println(i);  
		           setProgresso(i);
		        }
			setProgresso(i);
			
		}
}
