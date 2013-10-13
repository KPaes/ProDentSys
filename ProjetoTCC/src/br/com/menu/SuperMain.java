package br.com.menu;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import br.com.telas.SplashScreen;

public class SuperMain {
	public static void main(String[] args)  throws PropertyVetoException, ClassNotFoundException, SQLException{
		
		SplashScreen teste = new SplashScreen();
		teste.initSplash();
		
        for (int i = 0; i < 500000; i++){  
           System.out.println(i);  
           teste.setProgresso(i);
        }				
		
//        Principal frame = new Principal();
//		frame.setVisible(true);
		
		teste.fechaSplash();	
		
		Login login = new Login();
		login.setVisible(true);
			
}
}

