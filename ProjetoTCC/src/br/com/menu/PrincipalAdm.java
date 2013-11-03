package br.com.menu;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import br.com.backup.TelaBackup;
import br.com.enviaremail.Interface;
import br.com.exception.DaoException;
import br.com.util.SwingUtil;
import br.com.telas.CadFuncionario;
import br.com.telas.CadCliente;
import br.com.telas.CadFornecedor;
import br.com.telas.ConTabeladePreco;
import br.com.telas.Ped;
import br.com.telas.SobreSys;
import br.com.telas.TelaFolhadePagamento;
import br.com.telas.TelaRelatorio;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.awt.Font;

public class PrincipalAdm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static JMenuBar menuBar;
	static JMenu menu_1;

	/**
	 * Author: Karina Paes
	 * 
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)  throws PropertyVetoException, ClassNotFoundException, SQLException{
		new PrincipalAdm();
	}
	
	/**
	 * Create the frame.
	 */
	public PrincipalAdm() {
		super("ProDentSys");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("ProDentSys v1.0 - Administrador");
        for (int i = 0; i < 500; i++){  
            System.out.println(i);      
        }        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 1000;
        int height =600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,1382,729);
        this.setExtendedState(Principal.MAXIMIZED_BOTH); 
        SwingUtil.lookWindows(this);
        
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, 1356, 657);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/ProDentSys.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Consultas e Cadastros");
		menuBar.add(mnCadastros);
		mnCadastros.setMnemonic('C');
		mnCadastros.setToolTipText("Consultar ou Cadastrar"); //Hint das Consultas
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmCliente.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/cliente.png")));
		mntmCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
		mntmCliente.setToolTipText("Consultar os clientes"); //Hint Cliente
		mntmCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadCliente cadCliente;
				try {
					cadCliente = new CadCliente();
					cadCliente.setVisible(true);
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}										
			}
		});
		mnCadastros.add(mntmCliente);

		JMenuItem mntmFuncionrios = new JMenuItem("Funcion\u00E1rio");
		mntmFuncionrios.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmFuncionrios.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/people-icon.png")));
		mntmFuncionrios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.SHIFT_MASK));
		mntmFuncionrios.setToolTipText("Consultar os funcionários");
		mntmFuncionrios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadFuncionario cadFunc;
				try {
					cadFunc = new CadFuncionario();
					cadFunc.setVisible(true);
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}												
			}
		});	
		mnCadastros.add(mntmFuncionrios);
		
		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/Office-Customer-Male-Light-icon.png")));
		mntmFornecedor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmFornecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_MASK));
		mntmFornecedor.setToolTipText("Consultar os fornecedores");
		mntmFornecedor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CadFornecedor cadFornec;
				try {
					cadFornec = new CadFornecedor();
					cadFornec.setVisible(true);
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCadastros.add(mntmFornecedor);
		
		JMenuItem mntmPreos = new JMenuItem("Pedido");
		mntmPreos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmPreos.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/Sport-Wrestler-Male-Light-icon.png")));
		mntmPreos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_MASK));
		mntmPreos.setToolTipText("Consultar os pedidos");
		mntmPreos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	CadPedido cadPedido;
				Ped cadPedido;
				try {
			//		cadPedido = new CadPedido();
					cadPedido = new Ped();
					cadPedido.setVisible(true);
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCadastros.add(mntmPreos);
		
		JMenu mnTabela = new JMenu("Tabela de Pre\u00E7o");
		mnTabela.setMnemonic('T');
		mnTabela.setToolTipText("Consultar as tabelas de preços");
		menuBar.add(mnTabela);
		
		JMenuItem mntmTabelaDePreo_1 = new JMenuItem("Tabela de Pre\u00E7o");
		mntmTabelaDePreo_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmTabelaDePreo_1.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/tooth-icon.png")));
		mntmTabelaDePreo_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mntmTabelaDePreo_1.setToolTipText("Tabela de pre\u00E7o");
		mnTabela.add(mntmTabelaDePreo_1);
		mntmTabelaDePreo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConTabeladePreco consultaProt;
				try{
					consultaProt = new ConTabeladePreco();
					consultaProt.setVisible(true);
			    } catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			});
		
		JMenu mnEnviarEmail = new JMenu("Enviar E-mail");
		mnEnviarEmail.setMnemonic('E');
		menuBar.add(mnEnviarEmail);
		
		JMenuItem mntmEmailParaCliente = new JMenuItem("E-mail Para Cliente");
		mntmEmailParaCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmEmailParaCliente.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/email.png")));
		mntmEmailParaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface email;
				email = new Interface();
				email.setVisible(true);
			}
		});
		mnEnviarEmail.add(mntmEmailParaCliente);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		mnRelatrio.setMnemonic('i');
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmRelatrioDoDia = new JMenuItem("Relat\u00F3rio de Pedidos");
		mntmRelatrioDoDia.setToolTipText("Gerar Relatórios de Pedidos");
		mntmRelatrioDoDia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));
		mntmRelatrioDoDia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmRelatrioDoDia.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/rela.png")));
		mntmRelatrioDoDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorio relatorio;
				relatorio = new TelaRelatorio();
				relatorio.setVisible(true);
			}
		});
		mnRelatrio.add(mntmRelatrioDoDia);
		
		menu_1 = new JMenu("Restrito");
		menu_1.setMnemonic('R');
		menu_1.setToolTipText("Área restrita!");
		menuBar.add(menu_1);	

		
		JMenuItem menuItem_1 = new JMenuItem("Folha de Pagamento");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaFolhadePagamento tela;
				try {
					tela = new TelaFolhadePagamento();
					tela.setVisible(true);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		menuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuItem_1.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/secrecy-icon.png")));
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
		menuItem_1.setToolTipText("Folha de Pagamento Funcionário");		
		menu_1.add(menuItem_1);
		
		JMenuItem mntmGerarBackup = new JMenuItem("Backup");
		mntmGerarBackup.setIcon(new ImageIcon(PrincipalAdm.class.getResource("/br/com/images/bkp.png")));
		mntmGerarBackup.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menu_1.add(mntmGerarBackup);
		mntmGerarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBackup bkp = new TelaBackup();
				bkp.setVisible(true);
			}
		});
		
		JMenu mnSobre = new JMenu("Ajuda");
		mnSobre.setMnemonic('A');
		menuBar.add(mnSobre);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/help.png")));
		mntmAjuda.addActionListener(new ActionListener() {
			/**Acesso ao manual do usuário em .pdf
			  */
			public void actionPerformed(ActionEvent arg0) {
				try {
//					InputStream file = this.getClass().getResourceAsStream("/br/com/anexos/ManualCliente.pdf");
//					String file = this.getClass().getResource("C:\\Users\\Karina\\Documents\\ManualCliente.pdf").getFile().replaceAll("%20", " ");  
//					Desktop.getDesktop().open(new File("/br/com/anexos/ManualCliente.pdf"));
//				    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();     
//				    desktop.open(new File(file));				    
				    
				    openPdf();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		mntmAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnSobre.add(mntmAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre ProDentsys");
		mntmSobre.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/inf.png")));
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SobreSys sobre = new SobreSys();
				sobre.setVisible(true);
			}
		});
		mnSobre.add(mntmSobre);
		
		JMenuItem menuItem_3 = new JMenuItem("");
		menuItem_3.setIcon(new ImageIcon(Principal.class.getResource("/br/com/images/off.png")));
		menuItem_3.setToolTipText("Sair do sistema!");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(menuItem_3);
		
		
		setVisible(true);
	}
//	}
	
	public static boolean habilita(){
		menu_1 = new JMenu("Restrito");
		menu_1.setMnemonic('R');
		menu_1.setToolTipText("Área restrita!");
		menuBar.add(menu_1);
		menu_1.setEnabled(true);
//		menu_1.setVisible(true);
		return true;
	}
	
	private void openPdf() throws IOException {  
//        getThePdfAndPutOnC();  
		Desktop.getDesktop().open(new File("C:\\Program Files (x86)\\ProDentSys\\ManualCliente.pdf")); // Hardcode mesmo  

        
    }
	
	public void getThePdfAndPutOnC() throws IOException{  
        InputStream from = getClass().getResourceAsStream("/br/com/anexos/ManualCliente.pdf"); // Criar pacote e colocar nele o .pdf  
        File to = new File("C:\\Users\\Karina\\Desktop\\Setup\\ManualCliente.pdff"); // Endereço de saída do arquivo .pdf  
        OutputStream transferByteByByte = new FileOutputStream(to);  
        byte[] bufferTotal = new byte[1024];  
        int size = 0;  
        while ((size = from.read(bufferTotal)) > 0){  
            transferByteByByte.write(bufferTotal, 0, size);  
        }  
        transferByteByByte.close();  
        from.close();  
    } 
	
}