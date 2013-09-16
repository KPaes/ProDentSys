package br.com.relatorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.exception.DaoException;

public class RelatorioMain extends JFrame{
	/**
	 * 
	 */
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("Relatório Pedidos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        JButton botao = new JButton("Gerar relatório!");
        botao.addActionListener(new ActionListener() {	 
        	public void actionPerformed(ActionEvent arg0) {
                PedidoControle controle = new PedidoControle();
                try {
					controle.gerarRelatorio();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        
        });
 
        frame.getContentPane().add(botao);
        frame.pack();
        frame.setVisible(true);
	}
	
	private static final long serialVersionUID = 1L;

	public RelatorioMain() throws DaoException {
		main(null);
	/* public static void main(String[] args) {
	        JFrame frame = new JFrame("Meu relatorio!");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 
	        JButton botao = new JButton("Gerar relatório!");
	        botao.addActionListener(new ActionListener() {	 
	        	public void actionPerformed(ActionEvent arg0) {
	                PedidoControle controle = new PedidoControle();
	                controle.gerarRelatorio();
	        	}
	        
	        });
	 
	        frame.getContentPane().add(botao);
	        frame.pack();
	        frame.setVisible(true);	 
	//    }*/
	}
}
