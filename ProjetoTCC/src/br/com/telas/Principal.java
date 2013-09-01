package br.com.telas;

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


import br.com.enviaremail.Interface;
import br.com.exception.DaoException;
import br.com.telas.CadFuncionario;
import br.com.telas.CadCliente;
import br.com.telas.CadFornecedor;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Principal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)  throws PropertyVetoException, ClassNotFoundException, SQLException{
								
					SplashScreen teste = new SplashScreen();
					teste.initSplash();
					
			        for (int i = 0; i < 500000; i++){  
			           System.out.println(i);  
			           teste.setProgresso(i);
			        }				
					
			        Principal frame = new Principal();
					frame.setVisible(true);
					teste.fechaSplash();					
					Login login = new Login();
					login.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public Principal() {
		super("ProDentSys");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("ProDentSys");
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
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Consultas");
		menuBar.add(mnCadastros);
		mnCadastros.setToolTipText("Consultar"); //Hint das Consultas
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
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
		mnTabela.setToolTipText("Consultar as tabelas de preços");
		menuBar.add(mnTabela);
		
		JMenuItem mntmTabelaDePreo_1 = new JMenuItem("Tabela de Pre\u00E7o Neila");
		mntmTabelaDePreo_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mntmTabelaDePreo_1.setToolTipText("Tabela de preço da Neila");
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
		menuBar.add(mnEnviarEmail);
		
		JMenuItem mntmEmailParaCliente = new JMenuItem("E-mail Para Cliente");
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
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmRelatrioDoDia = new JMenuItem("Relat\u00F3rio do Dia");
		mntmRelatrioDoDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeladeRelatorio relatorio;
				try {
					relatorio = new TeladeRelatorio();
					relatorio.setVisible(true);					
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnRelatrio.add(mntmRelatrioDoDia);
		
		JMenu menu_1 = new JMenu("Restrito");
		menu_1.setToolTipText("Área restrita!");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("Folha de Pagamento");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
		menuItem_1.setToolTipText("Folha de Pagamento Funcionário");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TelaFolhadePagamento folhaPag;
				
				LoginRestrito restrito;
				
				//try{
									
					//folhaPag = new TelaFolhadePagamento(); //Para tela não ser restrita
					//folhaPag.setVisible(true);
					
					restrito = new LoginRestrito(); //excluir essas linhas para tirar o login do restrito
					restrito.setVisible(true);
				
					//} catch (DaoException e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
			//	}
			}
		});
		menu_1.add(menuItem_1);
		
		JMenu mnSobre = new JMenu("Ajuda");
		menuBar.add(mnSobre);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.addActionListener(new ActionListener() {
			/**Acesso ao manual do usuário em .pdf
			  */
			public void actionPerformed(ActionEvent arg0) {
				try {
					String file = this.getClass().getResource("/br/com/anexos/ManualCliente.pdf").getFile().replaceAll("%20", " ");  
	  
				    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();     
				    desktop.open(new File(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		mntmAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnSobre.add(mntmAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre ProDentsys");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SobreSys sobre = new SobreSys();
				sobre.setVisible(true);
			}
		});
		mnSobre.add(mntmSobre);
		
		JMenuItem menuItem_3 = new JMenuItem("Sair");
		menuItem_3.setToolTipText("Sair do sistema!");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(menuItem_3);
		
	}
}