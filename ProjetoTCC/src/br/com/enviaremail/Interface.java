package br.com.enviaremail;

import java.awt.EventQueue;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.apache.commons.mail.EmailException;

import br.com.bean.ClienteDent;
import br.com.dao.ClienteDao;
import br.com.exception.DaoException;
import br.com.util.BarraDeProgresso;
import br.com.util.ValidaEmail;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Interface extends JFrame {

	private JProgressBar progressBar = new JProgressBar();

	BarraDeProgresso barraDeProgresso = new BarraDeProgresso();
    Thread threadDaBarra = new Thread(barraDeProgresso);
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private static final long serialVersionUID = 1L;

	private String caminho = "";
	JFileChooser fc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		setResizable(false);
		setTitle("Enviar Email");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interface.class.getResource("/br/com/images/logo_transp.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar.setBounds(91, 300, 349, 19);

		contentPane.add(progressBar);

		progressBar.setBorderPainted(false);
		progressBar.setString("");
		progressBar.setMinimum(0);
		progressBar.setMaximum(500000);
		progressBar.setIndeterminate(false);
		progressBar.setBackground(Color.white);
		progressBar.setForeground(new Color(144, 238, 144));
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPara.setBounds(10, 80, 46, 14);
		contentPane.add(lblPara);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 111, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMensagem.setBounds(10, 205, 89, 14);
		contentPane.add(lblMensagem);
		
		textField = new JTextField();
		textField.setBounds(115, 78, 310, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115, 109, 310, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAssunto.setBounds(10, 142, 73, 14);
		contentPane.add(lblAssunto);
		
		textField_2 = new JTextField();
		textField_2.setBounds(115, 140, 310, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		

		JButton btnEnviar = new JButton("");
		btnEnviar.setToolTipText("Enviar E-mail");
		btnEnviar.setIcon(new ImageIcon(Interface.class.getResource("/br/com/images/send.png")));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
				Mensagem mensagem = new Mensagem();
				
				mensagem.setDestinatario(textField_1.getText());
				mensagem.setAssunto(textField_2.getText());
				mensagem.setMensagem(textField_3.getText());
			
				String email = textField_1.getText(); 
				if(ValidaEmail.validaEmail(email) == true){
				
					if(caminho.isEmpty() == true){
						Carteiro_so_mensagem carteiro = new Carteiro_so_mensagem();
						try {									
						 for (int i = 0; i < 500000; i++){  
					           System.out.println(i);  
					           setProgresso(i);
					        }
//					    threadDaBarra.start();					    
					    
						carteiro.enviarMensagem(mensagem);						
						JOptionPane.showMessageDialog(null, "Enviado com Sucesso!");
					} catch (EmailException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ops! N�o foi poss�vel enviar a mensagem.");
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}else{					
				
						mensagem.setCaminho(caminho);
								 
						Carteiro carteiro = new Carteiro();
						try {									
							for (int i = 0; i < 500000; i++){  
								System.out.println(i);  
								setProgresso(i);
							}
							
						carteiro.enviarMensagem(mensagem);						
						JOptionPane.showMessageDialog(null, "Enviado com Sucesso!");
						} catch (EmailException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Ops! N�o foi poss�vel enviar a mensagem.");
						} 
						catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btnEnviar.setBounds(358, 266, 67, 30);
		contentPane.add(btnEnviar);
		
		textField_3 = new JTextField();
		textField_3.setBounds(115, 171, 310, 90);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEnviarEmail = new JLabel("ENVIAR EMAIL");
		lblEnviarEmail.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblEnviarEmail.setBounds(171, 11, 157, 14);
		contentPane.add(lblEnviarEmail);
		
		JButton btnProcurar = new JButton("");
		btnProcurar.setIcon(new ImageIcon(Interface.class.getResource("/br/com/images/pesquisar.png")));
		btnProcurar.setToolTipText("Procurar o cliente para enviar email");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_4.getText() == ""){
					JOptionPane.showMessageDialog(null, "Digite um n�mero!");
				}
				
				else{
					Integer numero = Integer.parseInt(textField_4.getText());
					atualizaFormulario(numero);		
				}
						
			}

			private void atualizaFormulario(Integer idCliente) {
				ClienteDent objCliente = new ClienteDent();
			//	Integer idCliente = Integer.parseInt(textField_4.getText()); //n�mero do cliente digitado pelo funcion�rio
			//	objCliente.setNumCliente(idCliente);
				ClienteDao objDao = new ClienteDao();				
				
				try {
					objCliente = objDao.procurarClienteID(idCliente);
					
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				textField.setText(objCliente.getNomeCliente());
			    textField_1.setText(objCliente.getEmailCliente());
				
				idCliente = objCliente.getNumCliente();
				textField_4.setText(idCliente.toString());
				
			}
		});
		btnProcurar.setBounds(240, 44, 46, 23);
		contentPane.add(btnProcurar);
		
		textField_4 = new JTextField();
		textField_4.setBounds(115, 45, 100, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmero.setBounds(10, 48, 73, 14);
		contentPane.add(lblNmero);
		
		fc = new JFileChooser();
		
		JButton btnAnexo = new JButton("");
		btnAnexo.setToolTipText("Anexar um arquivo");
		btnAnexo.setIcon(new ImageIcon(Interface.class.getResource("/br/com/images/clip.png")));
		btnAnexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(Interface.this);				

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	caminho = fc.getSelectedFile().getAbsolutePath();
	            }	
			}
		});
		btnAnexo.setBounds(115, 266, 56, 30);
		contentPane.add(btnAnexo);
	}
	public void setProgresso(int i) {
		progressBar.setValue(i);
		progressBar.setString("Enviando...  "+i/5000+"%");		
	}
}
