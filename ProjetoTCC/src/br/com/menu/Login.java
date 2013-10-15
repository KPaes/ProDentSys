package br.com.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwfSenha;
	
	int tentativas = 0;

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
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(87, 34, 174, 20);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		pwfSenha = new JPasswordField();
		pwfSenha.setBounds(87, 65, 174, 20);
		contentPanel.add(pwfSenha);
		
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
							autenticarUsuario();
						
						
					}
				});
				getRootPane().setDefaultButton(okButton);
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

	
	private void autenticarUsuario(){
		
		String usuario = txtUsuario.getText();
		String senha = new String(pwfSenha.getPassword());
		if(usuario.equals("admin") && senha.equals("admin")){
			Principal tela = new Principal();
			tela.setVisible(true);
			dispose();
		}else{
			try{			
			
			if(tentativas < 3){
				Funcionario auteticacao = new FuncionarioDao().getAutenticacao(usuario, senha);
				
				
				if(auteticacao != null){
					String prof = auteticacao.getProfissaoFunc();
					
					if(prof.equals("Administrador") || prof.equals("Adm") || prof.equals("Diretor")){
						new PrincipalAdm();
					}else{
						new Principal();
					}
					
					dispose();
				}else{
					tentativas++;
					JOptionPane.showMessageDialog(this, "Acesso negado, tente novamente!",  "Acesso negado", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}else{
				JOptionPane.showMessageDialog(this, "Você excedeu o limite de tentativas", "Limite de tentativas", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			
			
			
		}catch (DaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}
