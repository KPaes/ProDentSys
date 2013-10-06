package br.com.telas;

import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.Font;  
import java.awt.Toolkit;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import java.awt.event.ActionListener;  
import java.awt.event.ActionEvent;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.swing.JButton;  
import javax.swing.JDialog;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.SwingConstants;  
import javax.swing.SwingUtilities;  
import javax.swing.UIManager;  
import javax.swing.JTextField;  

import testes.CadPedido;



import br.com.TableModel.ProtesesTableModel;
import br.com.TableModel.TableCellRenderer;
import br.com.bean.TabeladePreco;
import br.com.dao.TabeladePrecoDao;
import br.com.exception.DaoException;
  
  
public class TabelaCarrinho extends JDialog {  
  
    private static final long serialVersionUID = 1L;  
    
    private TabeladePrecoDao tabDao = new TabeladePrecoDao();
    
    private Double valorServ;  
    private Double valorProt;  
    private Double pedidoTotal;  
      
    final JPanel lista = new JPanel();    
    private JTable table;  
    private JTextField textValorProt;  
    private JTextField textValorServ;  
    private static JTextField textTotal;  
      
    
    private ProtesesTableModel tableModel;  
   // private List<Proteses> proteses;        
    
    List<TabeladePreco> listaTab  = new ArrayList<TabeladePreco>();
    
      
    public TabelaCarrinho() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(TabelaCarrinho.class.getResource("/br/com/images/logo_transp.png")));  
        inicialize();  
    }  
    
      
    /** 
     * metodo inicializa os componentes da classe 
     */  
    private void inicialize() {  
        this.setTitle("Seleção de Próteses");  
        int width = 800;  
        int height =600;  
        // setModal(true);  
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  
        int x = (screen.width-width)/2;  
        int y = (screen.height-height)/3;  
        setBounds(x,y,821,600);  
        getContentPane().setLayout(null);  
  
        final JPanel buttonPanel = new JPanel();  
        buttonPanel.setBackground(UIManager.getColor("Button.background"));  
        buttonPanel.setBounds(0, 0, 152, 562);  
        getContentPane().add(buttonPanel);  
        buttonPanel.setLayout(null);  
  
        lista.setBounds(152, 0, 656, 562);  
        getContentPane().add(lista);  
        lista.setLayout(null);  
  
        JLabel lblFuncionriosCadastrados = new JLabel("Selecione as Próteses");  
        lblFuncionriosCadastrados.setFont(new Font("Kalinga", Font.BOLD, 16));  
        lblFuncionriosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);  
        lblFuncionriosCadastrados.setBackground(Color.WHITE);  
        lblFuncionriosCadastrados.setBounds(10, 11, 612, 29);  
        lista.add(lblFuncionriosCadastrados);  
        lista.setVisible(true);    
  
 
        table = new JTable(tableModel);  
      
        listaTab = new ArrayList<TabeladePreco>();            
       tableModel = new ProtesesTableModel(listaTab);    
       table.setModel(tableModel);
        try {
			refreshTable();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
       
        table.addMouseListener(tableMouseListener);  
        table.setPreferredScrollableViewportSize(table.getPreferredSize());  
        JScrollPane scrollPane = new JScrollPane(table);  
        scrollPane.setBounds(0, 50, 636, 423);  
        lista.add(scrollPane);  
        table.getColumnModel().getColumn(0).setPreferredWidth(55);  
        table.getColumnModel().getColumn(0).setMinWidth(55);  
        table.getColumnModel().getColumn(1).setPreferredWidth(200);  
        table.getColumnModel().getColumn(1).setMinWidth(200);  
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  
        table.getColumnModel().getColumn(2).setMinWidth(80);  
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  
        table.getColumnModel().getColumn(3).setMinWidth(100);  
        table.getColumnModel().getColumn(4).setPreferredWidth(100);  
        table.getColumnModel().getColumn(4).setMinWidth(100);  
        table.setBounds(39, 175, 530, 232); 
        
  
        scrollPane.setViewportView(table);  
  
        JButton btnCalcular = new JButton("Calcular");  
        btnCalcular.addActionListener(calcularActionListener);  
        btnCalcular.setBounds(10, 484, 89, 23);  
        lista.add(btnCalcular);  
  
        textValorProt = new JTextField();  
        textValorProt.setBounds(216, 484, 86, 20);  
        lista.add(textValorProt);  
        textValorProt.setColumns(10);  
  
        JLabel lblSoma = new JLabel("Valor Pr\u00F3tese(s):");  
        lblSoma.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSoma.setBounds(118, 488, 106, 14);  
        lista.add(lblSoma);  
  
        textValorServ = new JTextField();  
        textValorServ.setBounds(378, 484, 86, 20);  
        lista.add(textValorServ);  
        textValorServ.setColumns(10);  
  
        JLabel lblService = new JLabel("Servi\u00E7o:");  
        lblService.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblService.setBounds(327, 487, 72, 14);  
        lista.add(lblService);  
  
        JButton btnSomar = new JButton("Somar");  
        btnSomar.addActionListener(somarActionListener);  
        btnSomar.setBounds(481, 484, 89, 23);  
        lista.add(btnSomar);  
  
        textTotal = new JTextField();  
        textTotal.setBounds(167, 531, 86, 20);  
        lista.add(textTotal);  
        textTotal.setColumns(10);  
        
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblTotal.setBounds(118, 533, 55, 14);
        lista.add(lblTotal);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		pegaTotalPedido("", textTotal);
        		        		
        	}
        });
        btnSalvar.setBounds(273, 530, 89, 23);
        lista.add(btnSalvar);
    }  
  
    /** 
     * Evento para mouse da tabela 
     */  
    MouseListener tableMouseListener=new MouseListener() {  
        @Override  
        public void mouseReleased(MouseEvent arg0) {}  
        @Override  
        public void mousePressed(MouseEvent arg0) {}  
        @Override  
        public void mouseExited(MouseEvent arg0) {}  
        @Override  
        public void mouseEntered(MouseEvent arg0) {}  
        @Override  
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        	Double precoProtese = 0.0 ;    
            List<TabeladePreco> protesesTable = tableModel.getProteses();  
              
            //for each  
            for(TabeladePreco protesesIn : protesesTable){  
                  
                //está selecionado  
                if(protesesIn.isSelecione() == true)  
                precoProtese+=protesesIn.getPrecoProtese();  
            }  

        textValorProt.setText(String.valueOf(precoProtese));  
        }  
    };  
  
    /** 
     * Evento p/ o botão calcular 
     */  
    ActionListener calcularActionListener = new ActionListener() {  
        /** 
         * Soma os preços das próteses que foram selecionadas 
         */  
        public void actionPerformed(ActionEvent e) {                                  
               
                Double precoProtese = 0.0 ;    
                List<TabeladePreco> protesesTable = tableModel.getProteses();  
                  
                //for each  
                for(TabeladePreco protesesIn : protesesTable){  
                      
                    //está selecionado  
                    if(protesesIn.isSelecione() == true)  
                    precoProtese+=protesesIn.getPrecoProtese();  
                }  
  
            textValorProt.setText(String.valueOf(precoProtese));  
        }  
    };   
  
   
    /** 
     * Evento p/o botão somar 
     */  
    ActionListener somarActionListener = new ActionListener() {  
        /** 
         * Soma o valor total do pedido com o serviço do protético 
         */  
        public void actionPerformed(ActionEvent arg0) {  
            try{  
                if(textValorServ.getText().trim().length() > 0)  
                    valorServ = Double.parseDouble(textValorServ.getText());  
                else  
                    valorServ=0D;  
  
                if(textValorProt.getText().trim().length() > 0)  
                    valorProt = Double.parseDouble(textValorProt.getText());  
                else  
                    valorProt=0D;  
  
                pedidoTotal = valorServ + valorProt;  
            }catch (Exception e) {  
                e.printStackTrace();  
            }  
            textTotal.setText(String.valueOf(pedidoTotal));  
  
        }  
    };  
  
	public void refreshTable() throws DaoException{      
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		listaTab = tabDao.consultarProteses();    
		tableModel.deleteAll();  
		tableModel.insertAll(listaTab);  
   } 

	//salvar
	public static String pegaTotalPedido(String pegaTotal, JTextField textTotalOutro){
		try {
			CadPedido volta = new CadPedido();
			//String pegaTotal;
    		pegaTotal = textTotal.getText();
    		
    		//mudar o foco
    	//	volta.formulario.setVisible(true);
    		volta.setVisible(true);
    		
    		TabelaCarrinho frame;  
    		frame = new TabelaCarrinho();  
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    		
            return pegaTotal;    		    		
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
                TabelaCarrinho frame;  
                try {  
                    frame = new TabelaCarrinho();  
                    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);          
                    frame.setVisible(true);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }                     
  
            }  
        });  
    }  
} 