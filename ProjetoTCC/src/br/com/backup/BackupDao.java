package br.com.backup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.exception.DaoException;
import br.com.util.DbUtil;

public class BackupDao {

	private final String BAKCUP_GERAR = 
			" BACKUP DATABASE PROJETO_TCC_PRODENTSYS " +
			" TO  DISK = 'D:\\TCC\\BACKUP\\backupTCC.bpk' " +
			" WITH NOFORMAT, INIT";
	
	private final String BAKCUP_GERAR_NOVO = 
			" BACKUP DATABASE PROJETO_TCC_PRODENTSYS " +
			" TO  DISK = ? " +
			" WITH NOFORMAT, INIT";
	
	
	private final String BAKCUP_RESTAURAR = 
			" RESTORE DATABASE PROJETO_TCC_PRODENTSYS " +
				" from DISK = 'D:\\TCC\\BACKUPbackupTCC.bpk' " +
				" WITH FILE=1, NORECOVERY ";
	
	
	public boolean gerarBackup(boolean sucesso) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		boolean result = false;
		try {
			statement = conn.prepareStatement(BAKCUP_GERAR);			
			result = statement.execute();						
			JOptionPane.showMessageDialog(null,  "Backup gerado com sucesso!");	
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement);
		}	
		return true;
	}
}
