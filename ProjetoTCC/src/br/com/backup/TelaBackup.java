package br.com.backup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import testes.FileChooser;

import br.com.exception.DaoException;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Toolkit;

public class TelaBackup extends JFrame implements ActionListener {

	/**
	 * 
	 */
	
	private JProgressBar progressBar = new JProgressBar();
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static private final String newline = "\n";
    JButton bkpRestore, bkpCompleto;
    JTextArea log;
    JFileChooser fc;
    private JButton bkpDiferencial;
    private JButton btnRestoreDif;
    private JLabel lblAntesDeRestaurar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBackup frame = new TelaBackup();					
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
	public TelaBackup() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaBackup.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Backup");
		setBounds(100, 100, 432, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar.setBounds(28, 261, 366, 19);

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
	
		
		JLabel lblBackup = new JLabel("BACKUP");
		lblBackup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBackup.setBounds(181, 11, 74, 14);
		contentPane.add(lblBackup);
        JScrollPane logScrollPane = new JScrollPane();

        //Create a file chooser
        fc = new JFileChooser();
                        
      //Create the open button.  We use the image from the JLF
                        //Graphics Repository (but we extracted it from the jar).
                        bkpRestore = new JButton("Restaurar Completo");
                        bkpRestore.setToolTipText("Escolha qual backup salvo restaurar");
                        bkpRestore.setBounds(28, 39, 143, 23);
                        contentPane.add(bkpRestore);
                        bkpRestore.addActionListener(this);
                        
                                //Create the save button.  We use the image from the JLF
                                //Graphics Repository (but we extracted it from the jar).
                                bkpCompleto = new JButton("Fazer Backup Completo");
                                bkpCompleto.setToolTipText("Salvar backup completo");
                                bkpCompleto.setBounds(243, 36, 151, 23);
                                contentPane.add(bkpCompleto);
                                bkpCompleto.addActionListener(this);
        
        
        	
        	 //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setBounds(29, 150, 365, 100);
        contentPane.add(log);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        getContentPane().add(logScrollPane, BorderLayout.CENTER);
        
        bkpDiferencial = new JButton("Backup Diferencial");
        bkpDiferencial.addActionListener(this);
        bkpDiferencial.setToolTipText("Backup diferencial s\u00F3 do que alterou");
        bkpDiferencial.setBounds(240, 72, 154, 23);
        contentPane.add(bkpDiferencial);
        
        btnRestoreDif = new JButton("Restaurar Diferencial");
        btnRestoreDif.setToolTipText("Antes restaure o backup completo e depois o diferencial!!");
        btnRestoreDif.addActionListener(this);
        btnRestoreDif.setBounds(28, 73, 143, 23);
        contentPane.add(btnRestoreDif);
        
        lblAntesDeRestaurar = new JLabel("Antes de restaurar o diferencial, restaure o completo.");
        lblAntesDeRestaurar.setFont(new Font("Tahoma", Font.ITALIC, 11));
        lblAntesDeRestaurar.setBounds(29, 125, 304, 14);
        contentPane.add(lblAntesDeRestaurar);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
    	String caminho;
    	BackupDao bkpDao = new BackupDao();
    	
        //Handle open button action.
        if (e.getSource() == bkpRestore) {
            int returnVal = fc.showOpenDialog(TelaBackup.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	caminho = fc.getSelectedFile().getAbsolutePath().replace("\\" , "\\\\" );  
            	if(caminho.contains(".bak")){            		
            		try {            			
            			bkpDao.restoreFull(caminho);
            			//thread
            			
            			
            		} catch (DaoException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            	}else{
            		JOptionPane.showMessageDialog(null, "Por gentileza, selecionar arquivo com extensão .bak!");
            		}
            	
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        
        }//Handle save button action.
        
        else if (e.getSource() == btnRestoreDif) {
            int returnVal = fc.showOpenDialog(TelaBackup.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	caminho = fc.getSelectedFile().getAbsolutePath().replace("\\" , "\\\\" );  
            	if(caminho.contains(".bak")){
            		try {
            			bkpDao.restoreDif(caminho);
            			
            		} catch (DaoException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            	}else{
            		JOptionPane.showMessageDialog(null, "Por gentileza, selecionar arquivo com extensão .bak!");
            		}
            	
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Restaurando: " + file.getName() + "." + newline);
            } else {
                log.append("Restauração cancelada pelo usuário." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        
        else if (e.getSource() == bkpCompleto) {
            int returnVal = fc.showSaveDialog(TelaBackup.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	
            	caminho = fc.getSelectedFile().getAbsolutePath().replace("\\" , "\\\\" );                                
//                JOptionPane.showMessageDialog(null, caminho); //se tá modificando
                
            	if(caminho.contains("C:")){
            		JOptionPane.showMessageDialog(null, "Por gentileza, salvar em outro disco!");
            	}
            	
            	else{            	
            	
            		if(caminho.contains(".bak")){
            		try {
						bkpDao.gerarBackupNovo(caminho);
					} catch (DaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	
            		}else{
            			String file;
            			file = caminho + ".bak";
            			try {
            				bkpDao.gerarBackupNovo(file);
            			} catch (DaoException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            		}
            		
                
            	
                File file = fc.getSelectedFile();
                file = new File(file+".bak");                 
                
                //This is where a real application would save the file.
                log.append("Salvando: " + file.getName() + "." + newline);
            	}
            	} else {
                log.append("Opção de salvar cancelada." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        
        else if (e.getSource() == bkpDiferencial) {
            int returnVal = fc.showSaveDialog(TelaBackup.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	
            	caminho = fc.getSelectedFile().getAbsolutePath().replace("\\" , "\\\\" );    
                
            	if(caminho.contains("C:")){
            		JOptionPane.showMessageDialog(null, "Por gentileza, salvar em outro disco!");
            	}
            	
            	else{            	            	
            		if(caminho.contains(".bak")){
            		try {
						bkpDao.gerarBackupDiferencial(caminho);
					} catch (DaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	
            		}else{
            			String file;
            			file = caminho + ".bak";
            			try {
            				bkpDao.gerarBackupDiferencial(file);
            			} catch (DaoException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            		}            		                
            	
                File file = fc.getSelectedFile();
                file = new File(file+".bak");                 
                
                //This is where a real application would save the file.
                log.append("Salvando: " + file.getName() + "." + newline);
            	}
            	} else {
                log.append("Opção de salvar cancelada." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    
		
	}
    public void setProgresso(int i) {
		progressBar.setValue(i);
		progressBar.setString("Enviando...  "+i/5000+"%");		
	}
    public void barra(){ //implements Runnable{
    	for (int i = 0; i < 500000; i++){  
	           System.out.println(i);  
	           setProgresso(i);
	        }
    }
}

