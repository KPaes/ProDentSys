package testes;

public class RunBackup {  
	  
    /** 
     * @param args the command line arguments 
     */  
    public static void main(String[] args) {  
  
        ManageBackup.createScriptBackup();  
          
        Backup backup = new Backup(ManageBackup.getTime("05:00"));//executa o backup de hora em hora  
  
        backup.createBackup(ManageBackup.getScriptBackup());  
  
        backup.start();//inicia a thread  
  
          
          
    }  
  
}  