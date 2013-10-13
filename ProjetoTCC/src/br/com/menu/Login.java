package br.com.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import br.com.bean.Funcionario;
import br.com.dao.FuncionarioDao;
import br.com.exception.DaoException;
import br.com.telas.SplashScreen;
import br.com.util.SwingUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class Login extends JDialog {
	private int tentativas = 0;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	int aux;
	
	FuncionarioDao loginTeste = new FuncionarioDao();
	
	static Login dialog;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SplashScreen teste = new SplashScreen();
			teste.initSplash();
			
	        for (int i = 0; i < 500000; i++){  
	           System.out.println(i);  
	           teste.setProgresso(i);
	        }	
	        
	        teste.fechaSplash();
	        
			dialog = new Login();			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setModal(true);
		    int width = 300;
	        int height =135;
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width-width)/2;
	        int y = (screen.height-height)/3;
	        setBounds(x,y,343,178);
	        SwingUtil.lookWindows(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
			lblUsurio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsurio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			lblUsurio.setBounds(10, 36, 67, 14);
			contentPanel.add(lblUsurio);
		}
		{
			JLabel lblSenha = new JLabel("Senha:");
			lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSenha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			lblSenha.setBounds(10, 67, 67, 14);
			contentPanel.add(lblSenha);
		}
		
		textField = new JTextField();
		textField.setBounds(87, 34, 174, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 65, 174, 20);
		contentPanel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/br/com/images/java.png")));
		lblNewLabel.setBounds(74, -33, 233, 221);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
//						if(textField.getText().toString()=="admin" && passwordField.getPassword().toString()=="admin"){
////							Principal.habilita();
//							Principal principal = new Principal();
//							principal.setVisible(true);
//							dispose();
//						}else{
							
						
						if(tentativas >=2){
							System.exit(0);
						}
//						else{
							String senha = new String(passwordField.getPassword()); 
							String  nome = textField.getText();
							
							try {
								try {
									if(loginTeste.getAutenticacao(nome, senha)){										
//										Funcionario profissao = loginTeste.habilitarMenu(nome);																				
										
//										JOptionPane.showMessageDialog(null, profissao.getProfissaoFunc());
										
//										if(aux==2){
//																					
//											
//											Principal principal = new Principal();
//											principal.setVisible(true);
//											
//											dispose();
//									}else{	
//											PrincipalAdm principal = new PrincipalAdm();
//											principal.setVisible(true);
										
										dialog.setVisible(false);
										
										
									}
									
									else{
										JOptionPane.showMessageDialog(null,"Falha ao logar");
										tentativas++;
									}
								} catch (HeadlessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}						
						catch (DaoException e) {
							e.printStackTrace();
						}
						}
//						}
//					}
				});
				{
					JButton btnCancelar = new JButton("Cancelar");
					btnCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.exit(0);
						}
					});
					buttonPane.add(btnCancelar);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	public void profissaoAdm(){
//		PrincipalAdm principal = new PrincipalAdm();											
		aux = 2;
//		Principal principal = new Principal();
//		principal.setVisible(true);
//		
//		dispose();
	}
	public void profissao(){
//		PrincipalAdm principal = new PrincipalAdm();											
		
		aux = 1;
		
//		Principal principal = new Principal();
//		principal.setVisible(true);
//		
//		dispose();
	}
	
}
