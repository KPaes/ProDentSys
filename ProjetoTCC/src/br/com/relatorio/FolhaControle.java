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

public class FolhaControle {
	private RelatorioDao dao;
	 
    public void gerarRelatorio() throws DaoException {
//        String arquivo = String.valueOf(getClass().getResourceAsStream("/br/com/relatorio/folhapag.jasper"));
    	
    	String arquivo = "src/br/com/relatorio/folhapag.jasper";    	 
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
    
    public void gerarRelatorioFolha(int func, Date date, Date date2) throws DaoException {
//        String arquivo = String.valueOf(getClass().getResourceAsStream("/br/com/relatorio/folhapag.jasper"));
 
//        String arquivo = "C:\\Program Files (x86)\\ProDentSys\\folhapag.jasper";
        String arquivo = "src/br/com/relatorio/folhapag.jasper";  
        dao = new RelatorioDao();
        JRDataSource jrds = new JRResultSetDataSource(dao.folhaResultSet(func, date, date2));
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

    public void gerarRelatorioFolhaSimples(int func, Date date, Date date2) throws DaoException {
//      String arquivo = String.valueOf(getClass().getResourceAsStream("/br/com/relatorio/folhapag.jasper"));

//      String arquivo = "C:\\Program Files (x86)\\ProDentSys\\folhaSimples.jasper";
      String arquivo = "src/br/com/relatorio/folhaSimples.jasper";  
      dao = new RelatorioDao();
      JRDataSource jrds = new JRResultSetDataSource(dao.folhaResultSimples(func, date, date2));
      gerarRelatorioDesktopMes(jrds, null, arquivo);
  }
}
