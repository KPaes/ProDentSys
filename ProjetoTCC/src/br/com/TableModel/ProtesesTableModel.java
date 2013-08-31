package br.com.TableModel;


import java.util.List;  
import javax.swing.table.AbstractTableModel;  
import br.com.bean.TabeladePreco;
  
public class ProtesesTableModel extends AbstractTableModel {  
      
	private static final long serialVersionUID = 1L;
	/** 
     * constantes do indice colunas 
     */  
    private final int NUMERO=0;  
    private final int NOME=1;  
    private final int TIPO=2;  
    private final int PRECO=3;  
    private final int SELECIONE=4;  
      
    private String[] columnNames = {"Número", "Nome", "Tipo", "Preço", "Selecione"};  
    private List<TabeladePreco> proteses;  
      
    public List<TabeladePreco> getProteses() {  
        return proteses;  
    }  
  
    public ProtesesTableModel(List<TabeladePreco> proteses) {  
        this.proteses = proteses;  
    }  
      
    @Override  
    public int getColumnCount() {  
        return columnNames.length;  
    }  
  
    public void setRowCount(int i) {
		// TODO Auto-generated method stub
		
	} 
    
    @Override  
    public int getRowCount() {  
        return proteses.size();  
    }  
  
    @Override  
    public String getColumnName(int column) {  
        return columnNames[column];  
    }  
      
    @Override  
    public Object getValueAt(int rowIndex, int columnIndex) {  
        TabeladePreco protese = proteses.get(rowIndex);  
        try{  
            switch (columnIndex) {  
            case NUMERO:  
                return protese.getNumProtese();  
            case NOME:  
                return protese.getNomeProtese();  
            case TIPO:  
                return protese.getTipoProtese();  
            case PRECO:  
                return protese.getPrecoProtese();  
            case SELECIONE:  
                return protese.isSelecione();  
            default:  
                throw new IndexOutOfBoundsException();  
            }  
        }finally{  
            fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula  
        }  
    }  
      
     @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override  
     public Class getColumnClass(int column) {  
         switch (column) {  
             case NUMERO:  
                 return String.class;  
             case NOME:  
                 return String.class;  
             case TIPO:  
                 return String.class;  
             case PRECO:  
                 return String.class;  
             case SELECIONE:  
                 return Boolean.class;  
         }  
         return Object.class;  
     }  
      
     @Override  
        public boolean isCellEditable(int rowIndex, int columnIndex) {  
            // apenas o campo "ATIVO" será editável  
            return columnIndex == SELECIONE;  
        }  
       
        @Override  
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
            // Pega o sócio referente a linha especificada.  
            TabeladePreco protese = proteses.get(rowIndex);  
       
            switch (columnIndex) {  
            case NUMERO:  
                 protese.setNumProtese((Integer)aValue);  
                 break;  
            case NOME:  
                protese.setNomeProtese((String)aValue);  
                 break;  
            case TIPO:  
                protese.setTipoProtese((String)aValue);  
                break;  
            case PRECO:  
                protese.setPrecoProtese((Double)aValue);  
                break;  
            case SELECIONE:  
                protese.setSelecione((Boolean)aValue);  
            default:  
              //  throw new IndexOutOfBoundsException();  
            }  
               
            fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula  
        }  
       
     /*metodos de manipulação de dados*/  
          
        public void insert(TabeladePreco protese) {          	
            proteses.add(protese);  
            fireTableRowsInserted(proteses.indexOf(protese), proteses.indexOf(protese));  
        }  
       
         
        public void insertAll(List<TabeladePreco> lista) {         	
            proteses.addAll(lista);  
            fireTableRowsInserted(0, getRowCount()); 
            fireTableDataChanged();
        }  
          
    /*    public void delete(int position) {  
            proteses.remove(position);  
            fireTableRowsDeleted(position, position);  
        } */ 
        
        public void delete(TabeladePreco protese) {  
            proteses.remove(protese);  
            fireTableRowsDeleted(proteses.indexOf(protese), proteses.indexOf(protese));  
        }  
          
        public void deleteAll() {  
            proteses.clear();  
            fireTableRowsDeleted(0, getRowCount()); 
            fireTableDataChanged();
        }

				 
       
} 