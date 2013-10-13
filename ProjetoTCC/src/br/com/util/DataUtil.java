package br.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.jdesktop.swingx.JXDatePicker;


import com.toedter.calendar.JDateChooser;


public class DataUtil {

	
	
	public static Date getDateFromDatePicker(JXDatePicker dtData, boolean obrigatorio) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = dtData.getEditor().getText();
		
			return sdf.parse(data);
		
	}
	
	public static Date getDateFromDateChooser(JDateChooser dtData, boolean obrigatorio) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(dtData.getDate());
		
			return sdf.parse(data);
		
	}
	
	public static String dateToString(JXDatePicker dtData){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return dtData.getDate() == null ? "" : sdf.format(dtData.getDate());		
	}


	
}
