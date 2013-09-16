package br.com.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import br.com.exception.DaoException;
import br.com.relatorio.PedidoControle;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRelatorio extends JFrame {

	/**
	 *Author: Karina Paes
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorio frame = new TelaRelatorio();
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
	public TelaRelatorio() {
		setTitle("Relat\u00F3rio de Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		
		JButton btnGerarDeTodos = new JButton("Gerar de Todos os Meses");
		btnGerarDeTodos.addActionListener(gerarTodos); 		
		btnGerarDeTodos.setBounds(136, 48, 197, 23);
		contentPane.add(btnGerarDeTodos);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(gerarMes);
		btnGerar.setBounds(244, 95, 89, 23);
		contentPane.add(btnGerar);
		
		txtMes = new JTextField();
		txtMes.setToolTipText("Ex: 09");
		txtMes.setBounds(148, 96, 86, 20);
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		
		JLabel lblMs = new JLabel("M\u00EAs:");
		lblMs.setToolTipText("Coloque o n\u00FAmero do m\u00EAs dos pedidos");
		lblMs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMs.setBounds(65, 99, 58, 14);
		contentPane.add(lblMs);
	
	
	}
	
	ActionListener gerarTodos = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PedidoControle controle = new PedidoControle();
            try {
				controle.gerarRelatorio();
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
	ActionListener gerarMes = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			PedidoControle controle = new PedidoControle();
            try {
            	String mes = txtMes.getText();
				controle.gerarRelatorioMes(mes);
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
}
