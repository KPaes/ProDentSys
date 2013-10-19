package br.com.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SobreSys extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SobreSys dialog = new SobreSys();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SobreSys() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SobreSys.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Sobre ProDentSys");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSobreProdentsys = new JLabel("PRODENTSYS");
			lblSobreProdentsys.setFont(new Font("Kalinga", Font.BOLD | Font.ITALIC, 14));
			lblSobreProdentsys.setBounds(162, 11, 167, 14);
			contentPanel.add(lblSobreProdentsys);
		}
		{
			JLabel lblUmSistema = new JLabel("\u00C9 um sistema para auxiliar os prot\u00E9ticos em");
			lblUmSistema.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblUmSistema.setBounds(20, 47, 303, 14);
			contentPanel.add(lblUmSistema);
		}
		{
			JLabel lblKbyte = new JLabel("(c) Copyright ProDentSys, 2012 and 2013.");
			lblKbyte.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblKbyte.setBounds(20, 109, 259, 14);
			contentPanel.add(lblKbyte);
		}
		{
			JLabel lblVerso = new JLabel("Vers\u00E3o: 1.5");
			lblVerso.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblVerso.setBounds(20, 84, 78, 14);
			contentPanel.add(lblVerso);
		}
		{
			JLabel lblDesenvolvidoPorKbyte = new JLabel("Desenvolvido por KByte");
			lblDesenvolvidoPorKbyte.setFont(new Font("Tahoma", Font.ITALIC, 12));
			lblDesenvolvidoPorKbyte.setBounds(20, 185, 151, 14);
			contentPanel.add(lblDesenvolvidoPorKbyte);
		}
		{
			JLabel lblImagem = new JLabel("imagem");
			lblImagem.setIcon(new ImageIcon(SobreSys.class.getResource("/br/com/images/java.png")));
			lblImagem.setBounds(201, 36, 233, 193);
			contentPanel.add(lblImagem);
		}
		{
			JLabel lblAllRightsReserved = new JLabel("All rights reserved.");
			lblAllRightsReserved.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAllRightsReserved.setBounds(20, 124, 118, 14);
			contentPanel.add(lblAllRightsReserved);
		}
		{
			JLabel lblSeuTrabalhoCom = new JLabel("seu trabalho com pr\u00F3teses.");
			lblSeuTrabalhoCom.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSeuTrabalhoCom.setBounds(10, 61, 167, 14);
			contentPanel.add(lblSeuTrabalhoCom);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
