package br.com.util;

/*
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MascaraUtil {

	public static MaskFormatter setMascara(String mascara){  
	    MaskFormatter mask = null;  
	    try{  
	        mask = new MaskFormatter(mascara);                        
	        }catch(java.text.ParseException ex){}  
	    return mask;  
	} 
	
	public static  JFormattedTextField hideMascara(JFormattedTextField txt){  
	    try{  
	       txt.setFormatterFactory(null);                        
	        }catch(Exception ex){
	        	JOptionPane.showMessageDialog(null, "Erro ao formatar dados!");
	        }  
	    return txt;  
	}
	
	public static  String hideMascaraMoeda(JFormattedTextField txt){
		String aux = null;
		aux =	txt.getText();
		aux =   aux.replace("R$", "");
		aux =	aux.replace(".", "");
		aux =	aux.replace(",", ".");
		return aux;  
	}	
	
}*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class MascaraUtil {
	
	public static String setMaskDateInTable(Date data) throws ParseException{
		String ret = new SimpleDateFormat("dd/MM/yyyy").format(data);
		return ret;
	}
	
	public static String setMaskHoraInTable(Date data) throws ParseException{
		String ret = new SimpleDateFormat("HH:mm:ss").format(data);
		return ret;
	}
	
	public static String setMaskHoraMinutoInTf(Date data) throws ParseException{
		String ret = new SimpleDateFormat("HH:mm").format(data);
		return ret;
	}
	
	public static AbstractFormatter setMaskDateInTf(JFormattedTextField ftData) throws java.text.ParseException{
		MaskFormatter mk = new MaskFormatter("##/##/####");
		mk.setValidCharacters("0123456789");
		mk.setPlaceholderCharacter('_');
		return mk;
	}
	
	public static AbstractFormatter setMaskCpfInTf(JFormattedTextField ftCpf) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("###.###.###-##");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskHoraMinutoInTf(JFormattedTextField ftHora) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("##:##");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskCepInTf(JFormattedTextField ftCep) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("#####-###");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskTelefoneInTf(JFormattedTextField ftTelefone) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##)####-####");
		mk.setValidCharacters("0123456789");
		return mk;
	}
	
	public static AbstractFormatter setMaskCelularInTf(JFormattedTextField ftCelular) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##) #####-####");
		mk.setValidCharacters("0123456789");
		return mk;
	}

	public static Object setMaskCpfInTable(String cpf) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("###.###.###-##");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(cpf);
	}
	
	public static Object setMaskCepInTable(JFormattedTextField textField_3) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("#####-###");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(textField_3);
	}
	
	public static Object setMaskCelularInTable(String celular) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##) #####-####");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(celular);
	}
	
	public static Object setMaskTelefoneInTable(String telefone) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("(##) ####-####");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(telefone);
	}
	
	public static Object setMaskSalario(JFormattedTextField salario) throws java.text.ParseException {
		MaskFormatter mk = new MaskFormatter("?####,##");
		mk.setValueContainsLiteralCharacters(false);
		return mk.valueToString(salario);
	}
	
	public static MaskFormatter setMascara(String mascara){  
	    MaskFormatter mask = null;  
	    try{  
	        mask = new MaskFormatter(mascara);                        
	        }catch(java.text.ParseException ex){}  
	    return mask;  
	}
	
	public static  String hideMascaraMoeda(JTextField txt){
		String aux = null;
		aux =	txt.getText();
		//aux =   aux.replace("R$", "");
		aux =	aux.replace(".", "");
		aux =	aux.replace(",", ".");
		return aux;  
	}	
}

 

