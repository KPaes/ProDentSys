package br.com.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.jdesktop.swingx.JXDatePicker;


import br.com.exception.DaoException;
import br.com.util.CalcularData;
import br.com.util.ValidacaoUtil;
import br.com.relatorio.PedidoControle;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

public class TelaRelatorio extends JFrame {

	/**
	 *Author: Karina Paes
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JXDatePicker datePicker;
	private JXDatePicker datePicker2;
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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRelatorio.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Relat\u00F3rio de Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGerar = new JButton("");
		btnGerar.setToolTipText("Gerar o relat\u00F3rio");
		btnGerar.setIcon(new ImageIcon(TelaRelatorio.class.getResource("/br/com/images/gerar.png")));
		btnGerar.addActionListener(gerarMes);
		btnGerar.setBounds(178, 164, 52, 33);
		contentPane.add(btnGerar);
		
		JLabel lblMs = new JLabel("Data de In\u00EDcio:");
		lblMs.setToolTipText("Coloque o n\u00FAmero do m\u00EAs dos pedidos");
		lblMs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMs.setBounds(59, 77, 97, 14);
		contentPane.add(lblMs);
		
		JLabel lblRelatrio = new JLabel("Relat\u00F3rio de Pedidos");
		lblRelatrio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRelatrio.setBounds(142, 21, 197, 14);
		contentPane.add(lblRelatrio);
		
	
		datePicker = new JXDatePicker();
		datePicker.getEditor().setToolTipText("Data prevista de entrega para gerar relatório!");
		datePicker.getEditor();
		datePicker.setFormats(new String[] {"dd/MM/yyyy"});
		datePicker.setBounds(159, 75, 97, 20);
		contentPane.add(datePicker);
		
		datePicker2 = new JXDatePicker();
		datePicker2.getEditor().setToolTipText("Data prevista de entrega para gerar relatório!");
		datePicker2.getEditor();
		datePicker2.setFormats(new String[] {"dd/MM/yyyy"});
		datePicker2.setBounds(159, 120, 97, 20); //
		contentPane.add(datePicker2);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataFinal.setBounds(87, 122, 80, 14);
		contentPane.add(lblDataFinal);
		
		
	
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
			if(validarFormulario()){
				PedidoControle controle = new PedidoControle();
			
				try {           
            	
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            	
					try {
						Date data1 = datePicker.getDate();
						Date data2 = datePicker2.getDate();
						if(CalcularData.TirarDiferenca(data1, data2) < 0){
							JOptionPane.showMessageDialog(null,"As datas devem ser iguais ou a data final ser superior a data inicial!");
						}else
							controle.gerarRelatorioMes(df.parse(datePicker.getEditor().getText()), df.parse(datePicker2.getEditor().getText()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	
	public boolean validarFormulario(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(datePicker)){
			JOptionPane.showMessageDialog(null, "Campo Data Inicial Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(datePicker2)){
			JOptionPane.showMessageDialog(null, "Campo Data Final Vazio!");
			result = false;
		}

	
		return result;
	}	
}
