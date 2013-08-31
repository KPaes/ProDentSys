package br.com.enviaremail;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
 
public class Carteiro_so_mensagem {
 
	private String hostName = "smtp.gmail.com";
	private String usuario = "kbyte.tcc"; // Seu login do Gmail
	private String senha = "empresaTCC"; // Sua senha do Gmail
	private String email = "kbyte.tcc@gmail.com"; // Seu e-mail do Gmail
 
	private SimpleEmail simpleEmail;
 
	public Carteiro_so_mensagem() {
		this.simpleEmail = new SimpleEmail();
		//MimeBodyPart attachFilePart = new MimeBodyPart();
	}
 
	public void enviarMensagem(Mensagem mensagem) throws EmailException, MessagingException {
 
        SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SSLContext.setDefault(ctx);
		
		simpleEmail.setHostName(hostName);
		simpleEmail.setAuthentication(usuario, senha);
		simpleEmail.setSSLCheckServerIdentity(false);

		simpleEmail.setSmtpPort(587);
		simpleEmail.setStartTLSEnabled(true);
		simpleEmail.setFrom(email);
		simpleEmail.addTo(mensagem.getDestinatario());
		simpleEmail.setSubject(mensagem.getAssunto());
		simpleEmail.setMsg(mensagem.getMensagem());
		
           MimeBodyPart attachFilePart = new MimeBodyPart();
      FileDataSource fds = 
          new FileDataSource("/br/com/anexos/Tabela.jpg"); //anexo ao email
      attachFilePart.setDataHandler(new DataHandler(fds));
      attachFilePart.setFileName(fds.getName());

      Multipart mp = new MimeMultipart();
      //mp.addBodyPart(textPart);
      mp.addBodyPart(attachFilePart);

		simpleEmail.send();
 
	}
 
	  private static class DefaultTrustManager implements X509TrustManager {
		  
	        @Override
	        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
	        }
	 
	        @Override
	        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
	        }
	 
	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }
	 
	    }
}