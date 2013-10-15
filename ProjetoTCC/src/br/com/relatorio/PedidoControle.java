package br.com.relatorio;

import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.dao.RelatorioDao;
import br.com.exception.DaoException;


public class PedidoControle {
	private RelatorioDao dao;
	 
    public void gerarRelatorio() throws DaoException {
//        String arquivo = String.valueOf(getClass().getResourceAsStream("br/com/relatorio/report.jasper"));
 
    	String arquivo = "src/br/com/relatorio/report.jasper";
        dao = new RelatorioDao();
        JRDataSource jrds = new JRResultSetDataSource(dao.pedidosResultSet());
        gerarRelatorioDesktop(jrds, null, arquivo);
    }
 
    private void gerarRelatorioDesktop(JRDataSource jrds, Map<String, Object> parametros, String arquivo) {
        try {
            JasperPrint print = JasperFillManager.fillReport(arquivo, parametros, jrds);           
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    
    public void gerarRelatorioMes(Date mes, Date data2) throws DaoException {
//        String arquivo = String.valueOf(getClass().getResourceAsStream("br/com/relatorio/report.jasper"));
 
    	String arquivo = "src/br/com/relatorio/report.jasper";
        dao = new RelatorioDao();
        JRDataSource jrds = new JRResultSetDataSource(dao.pedidosResultSet(mes, data2));
        gerarRelatorioDesktopMes(jrds, null, arquivo);
    }
 
    private void gerarRelatorioDesktopMes(JRDataSource jrds, Map<String, Object> parametros, String arquivo) {
        try {
            JasperPrint print = JasperFillManager.fillReport(arquivo, parametros, jrds);           
            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
