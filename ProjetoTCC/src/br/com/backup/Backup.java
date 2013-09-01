package br.com.backup;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
  
/** 
* 
* @author antoniel 
*/  
public class Backup {  
  
    public Backup(long time) {  
        this.time = time;  
    }  
  
    /**Executa o backup em concorrencia com a aplicação.*/  
    public void createBackup(String shell) {          
        thread = new Thread(new RunBackup(shell));          
    }  
  
    /**Executa restore do banco de dados apartir de um backup.*/  
    public void createRestore(String shell) {  
        try {  
            processo = Runtime.getRuntime().exec(shell);  
            printErrorExecucao();  
        } catch (IOException ex) {  
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }  
  
    public void stop() {  
        thread.stop();  
    }  
  
    public void start() {  
        thread.start();  
    }  
  
    public long getTime() {  
        return time;  
    }  
  
    public void setTime(int time) {  
        this.time = time;  
    }  
      
    public class RunBackup implements Runnable {  
  
        private RunBackup(String shell) {  
            this.shell = shell;  
        }  
  
        @Override  
        public void run() {  
              
            try {  
                while(true) {  
                    Thread.sleep(time);  
                    processo = Runtime.getRuntime().exec(shell);  
                    printErrorExecucao();  
                }  
  
            } catch (InterruptedException ex) {  
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);  
            } catch (IOException ex) {  
                Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);  
            }  
        }  
  
        private String shell;  
    }  
  
    private void printErrorExecucao() {  
  
        BufferedReader entrada = new BufferedReader(new InputStreamReader(processo.getErrorStream()));  
        String linha = null;  
        try {  
  
            while ((linha = entrada.readLine()) != null) {  
                System.out.println(linha);  
            }  
  
            entrada.close();  
              
        } catch (IOException ex) {  
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }  
      
    private long time;  
    private Thread thread;  
    private Process processo;  
      
}  