package br.com.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.exception.DaoException;

public class LoginRestrito extends JDialog {
	
	private int tentativas = 0;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginRestrito() {
		setTitle("Login Administrador");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		    int width = 300;
	        int height =135;
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width-width)/2;
	        int y = (screen.height-height)/3;
	        setBounds(x,y,width,height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
			lblUsurio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsurio.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblUsurio.setBounds(10, 13, 67, 14);
			contentPanel.add(lblUsurio);
		}
		{
			JLabel lblSenha = new JLabel("Senha:");
			lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSenha.setFont(new Font("Arial Black", Font.PLAIN, 12));
			lblSenha.setBounds(10, 39, 67, 14);
			contentPanel.add(lblSenha);
		}
		
		textField = new JTextField();
		textField.setBounds(87, 11, 174, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 37, 174, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(tentativas >=2){
							System.exit(0);
						}
						String senha = new String(passwordField.getPassword()); 
						String  nome = textField.getText();
						//FuncionarioDao loginTeste = new FuncionarioDao();
						try {
							 TelaFolhadePagamento folhaPagamento;
							 if(senha.equals("adm") && nome.equals("adm")){
							 dispose();
								folhaPagamento = new TelaFolhadePagamento();
								folhaPagamento.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(null,"Falha ao logar! Somente o Administrador pode acessar.");
								tentativas++;
							}							
						} catch (DaoException e) {
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
