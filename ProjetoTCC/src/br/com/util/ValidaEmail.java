package br.com.util;

import java.util.regex.*; 
import javax.swing.JOptionPane;

public class ValidaEmail {
	/**
	 * Metodo de validacao de email*/
	public static boolean validaEmail(String email) {
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
	         Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);  
	         Matcher matcher = pattern.matcher(email); 
	    if (matcher.matches()){
	      //System.out.println("O email "+email+" e valido");
	      return true;
	    }
	    else{
	      JOptionPane.showMessageDialog(null, "O E-mail "+ email +" é inválido");
	      return false;
	    }  
	}
		return false;
	}
}
