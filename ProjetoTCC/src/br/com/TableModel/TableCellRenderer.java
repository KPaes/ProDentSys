package br.com.TableModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
	if(row % 2 == 0 ){
		setBackground(Color.CYAN);
		//setForeground(Color.WHITE);
	}else{
		setBackground(null);
		setForeground(new Color(51, 51, 51));
	}
	
	if(isSelected){
		setBackground(Color.LIGHT_GRAY);
		setForeground(getForeground()); 
	}
	
	int col = table.getColumnModel().getColumnCount();
	if(col==5){
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(140);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
				
	}else{
		if(col==6){
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMaxWidth(380);
			table.getColumnModel().getColumn(2).setMaxWidth(100);
			table.getColumnModel().getColumn(3).setMaxWidth(200);
			table.getColumnModel().getColumn(4).setMaxWidth(50);
			table.getColumnModel().getColumn(5).setMaxWidth(50);
		}
		if(col==4){
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMaxWidth(90);
			table.getColumnModel().getColumn(2).setMaxWidth(90);
			table.getColumnModel().getColumn(3).setMaxWidth(50);
		}
		if(col==7){
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMaxWidth(200);
			table.getColumnModel().getColumn(2).setMaxWidth(120);
			table.getColumnModel().getColumn(3).setMaxWidth(90);
			table.getColumnModel().getColumn(4).setMaxWidth(56);
			table.getColumnModel().getColumn(5).setMaxWidth(60);
			table.getColumnModel().getColumn(6).setMaxWidth(60);
		}
//		if(col==8){
//			table.getColumnModel().getColumn(0).setMaxWidth(35);
//			table.getColumnModel().getColumn(1).setMaxWidth(170);
//			table.getColumnModel().getColumn(2).setMaxWidth(80);
//			table.getColumnModel().getColumn(3).setMaxWidth(50);
//			table.getColumnModel().getColumn(4).setMaxWidth(100);
//			table.getColumnModel().getColumn(5).setMaxWidth(40);
//			table.getColumnModel().getColumn(6).setMaxWidth(50);
//			table.getColumnModel().getColumn(7).setMaxWidth(40);
//						
//		}
		
	}

	
	return this;

	}	
}