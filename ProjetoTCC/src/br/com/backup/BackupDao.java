package br.com.backup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.exception.DaoException;
import br.com.util.DbUtil;

public class BackupDao {

	private final String BAKCUP_GERAR = 
			" BACKUP DATABASE PROJETO_TCC_PRODENTSYS " +
			" TO  DISK = 'D:\\TCC\\BACKUP\\backupTCC.bpk' " +
			" WITH NOFORMAT, INIT";
	
	private final String BAKCUP_FULL = 
			" BACKUP DATABASE PROJETO_TCC_PRODENTSYS " +
			" TO  DISK = ? " +
			" WITH FORMAT, INIT";
	
	private final String BAKCUP_DIF = 
			" BACKUP DATABASE PROJETO_TCC_PRODENTSYS " +
			" TO  DISK = ? " +
			" WITH FORMAT, DIFFERENTIAL";
	
	
	private final String RESTAURAR_FULL = 
			" RESTORE DATABASE PROJETO_TCC_PRODENTSYS " +
				" from DISK = ? " +
				" WITH FILE=1, NORECOVERY ";
	
	private final String RESTAURAR_DIF = 
			" RESTORE DATABASE PROJETO_TCC_PRODENTSYS " +
				" from DISK = ? " +
				" WITH FILE=2, NORECOVERY ";
	
	/*
	 * ALTER DATABASE PROJETO_TCC_PRODENTSYS SET RECOVERY SIMPLE; //pro banco fazer restauração simples
	 * 
	*/
	
	
	public boolean gerarBackup(boolean sucesso) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		@SuppressWarnings("unused")
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
	
	public boolean gerarBackupNovo(String file) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		@SuppressWarnings("unused")
		boolean result = false;
		try {
			statement = conn.prepareStatement(BAKCUP_FULL);	
			statement.setString(1, file);			
			result = statement.execute();							
			JOptionPane.showMessageDialog(null,  "Backup gerado com sucesso!");	
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement);
		}	
		return true;
	}
	
	public boolean gerarBackupDiferencial(String file) throws DaoException{		
		Connection conn = DbUtil.getConnection();
		PreparedStatement statement = null;
		@SuppressWarnings("unused")
		boolean result = false;
		try {
			statement = conn.prepareStatement(BAKCUP_DIF);	
			statement.setString(1, file);			
			result = statement.execute();							
			JOptionPane.showMessageDialog(null,  "Backup gerado com sucesso!");	
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement);
		}	
		return true;
	}
	
	
	
	public boolean restoreFull(String file) throws DaoException{		
		Connection conn = DbUtil.getConnection2();
		PreparedStatement statement = null;
		@SuppressWarnings("unused")
		boolean result = false;
		try {
			//statement = conn.prepareStatement("ALTER DATABASE PROJETO_TCC_PRODENTSYS SET RECOVERY SIMPLE");
			statement = conn.prepareStatement(RESTAURAR_FULL);	
			statement.setString(1, file);			
			result = statement.execute();							
			JOptionPane.showMessageDialog(null,  "Backup restaurado com sucesso! \nNão faça nada até o banco se estabilizar novamente!");	
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement);
		}	
		return true;
	}
	
	public boolean restoreDif(String file) throws DaoException{		
		Connection conn = DbUtil.getConnection2();
		//BackupBean obj = new BackupBean();
		PreparedStatement statement = null;
		@SuppressWarnings("unused")
		boolean result = false;
		try {
			statement = conn.prepareStatement(RESTAURAR_DIF);	
			statement.setString(1, file);			
			result = statement.execute();							
			JOptionPane.showMessageDialog(null,  "Backup restaurado com sucesso! \nNão faça nada até o banco se estabilizar novamente!");	
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DbUtil.close(conn, statement);
		}	
		return true;
	}
}
