package br.com.backup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.exception.DaoException;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaBackup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setTitle("Backup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BackupDao bkpDao = new BackupDao();
				boolean sucesso = false;
				try {
					bkpDao.gerarBackup(sucesso);					
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGerar.setBounds(297, 204, 89, 23);
		contentPane.add(btnGerar);
		
		JLabel lblBackup = new JLabel("BACKUP");
		lblBackup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBackup.setBounds(200, 23, 74, 14);
		contentPane.add(lblBackup);
		
		JLabel lblLocalParaArmazenamento = new JLabel("Local para Armazenamento:");
		lblLocalParaArmazenamento.setBounds(10, 61, 145, 14);
		contentPane.add(lblLocalParaArmazenamento);
	}
}
