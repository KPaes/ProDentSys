package br.com.backup;

import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
  
/** 
* 
* @author antoniel 
*/  
public class ManageBackup {  
  
    public static void createScriptBackup() {  
          
        File file = null;  
        StringBuffer buffer = null;  
  
        if(File.separator.compareTo("/") == 0 ) {  
            file = new File( "backup.sh" );  
            buffer = new StringBuffer("#!/bin/bash\n\n");  
        } else {  
            file = new File( "backup.bat" );  
            buffer = new StringBuffer();              
        }  
          
        buffer.append("");//CODIGO DO BACKUP  
  
        try {  
  
            BufferedWriter saida = new BufferedWriter(new PrintWriter(file));  
            saida.write(buffer.toString());  
            saida.close();  
              
        } catch (FileNotFoundException ex) {  
            Logger.getLogger(ManageBackup.class.getName()).log(Level.SEVERE, null, ex);  
        }  
        catch (IOException ex) {  
            Logger.getLogger(ManageBackup.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }  
  
    public static void createScriptRestore() {  
        
        File file = null;  
        StringBuffer buffer = null;  
  
        if(File.separator.compareTo("/") == 0 ) {  
            file = new File( "restore.sh" );  
            buffer = new StringBuffer("#!/bin/bash\n\n");  
        } else {  
            file = new File( "restore.bat" );  
            buffer = new StringBuffer();  
        }  
  
        buffer.append("");//CODIGO DO RESTORE DO BANCO DE DADOS  
  
        try {  
  
            BufferedWriter saida = new BufferedWriter(new PrintWriter(file));  
            saida.write(buffer.toString());  
            saida.close();  
  
        } catch (FileNotFoundException ex) {  
            Logger.getLogger(ManageBackup.class.getName()).log(Level.SEVERE, null, ex);  
        }  
        catch (IOException ex) {  
            Logger.getLogger(ManageBackup.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }  
  
  
    /**Retorna o tempo em milisegundos*/  
    public static int getTime(String time) {  
  
        int ponto = time.indexOf(":");//captura o indice do caractere delimitador  
        if (ponto == -1) {  
            throw new IllegalArgumentException("Tempo invalido forneça o tempo no formato \"hh:mm\"");  
        }  
        int hora = Integer.parseInt(time.substring(0, ponto));//recupera o numero de horas  
        int min = Integer.parseInt(time.substring(ponto + 1,time.length()));//  
  
        return ( ( ( (hora * 60 ) + min ) *  60 ) * 1000 );//retorna o tempo em milisegundos  
    }  
  
    /*Retorna o path de onde o script de restore estar salvo*/  
    public static String getScriptBackup() {  
          
        if ( File.separator.compareTo("/") == 0 ) {  
            return "backup.sh";  
        }  
  
        return "backup.bat";  
    }  
  
    /*Retorna o path de onde o script de restore estar salvo*/  
    public static String getScriptRestore() {  
  
        if ( File.separator.compareTo("/") == 0 ) {  
            return "restore.sh";  
        }  
  
        return "restore.bat";  
    }  
      
}  