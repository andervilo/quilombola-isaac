package br.org.quilombola.arquitetura.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtils {

	public static Boolean enviarEmailSimpleUser(String email, String nome, String senha) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Boolean retorno = true;
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sipq.ufpa@gmail.com", "sipqquilombo");
			}
		});
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("sipq.ufpa@gmail.com", "SIPQ"));
		} catch (UnsupportedEncodingException | MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		} catch (AddressException e) {
			retorno = false;
			e.printStackTrace();
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setSubject("Confirmação de Cadastro");
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setSentDate(new Date());
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		MimeBodyPart messageBP = new MimeBodyPart();
		try {
			messageBP.setContent("<p>Olá <b>"+nome+"</b></p>"		
					+ "<p>Seu cadastro foi realizado com sucesso no Sistema de Informações Públicas sobre Quilombos(SIPQ).</p>"
					
					+ "<p>Você pode acessar o Sistema com as credenciais abaixo.</p>"
					
					+ "<p><b>Usuário:</b> "+email+"</p>"	
					+ "<p><b>Senha:</b> "+senha+"</p>", "text/html");
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		Multipart multipart = new MimeMultipart();
		
		try {
			multipart.addBodyPart(messageBP);
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setContent(multipart);
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public static Boolean enviarEmailSimpleUserResetPass(String email, String nome, String senha) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Boolean retorno = true;
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sipq.ufpa@gmail.com", "sipqquilombo");
			}
		});
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("sipq.ufpa@gmail.com", "SIPQ"));
		} catch (UnsupportedEncodingException | MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		} catch (AddressException e) {
			retorno = false;
			e.printStackTrace();
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setSubject("Reset de Senha");
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setSentDate(new Date());
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		MimeBodyPart messageBP = new MimeBodyPart();
		try {
			messageBP.setContent("<p>Olá <b>"+nome+"</b></p>"		
					+ "<p>Sua senha foi resetada. Você pode acessar o Sistema com as credenciais abaixo.</p>"
					
					+ "<p><b>Usuário:</b> "+email+"</p>"	
					+ "<p><b>Senha:</b> "+senha+"</p>", "text/html");
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		Multipart multipart = new MimeMultipart();
		
		try {
			multipart.addBodyPart(messageBP);
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			message.setContent(multipart);
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			retorno = false;
			e.printStackTrace();
		}
		
		return retorno;
	}
}
