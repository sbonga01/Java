package email;

import java.util.*;
import java.util.logging.Level;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class JavaMailUtil{
	public static void sendMail(String recepient) throws MessagingException{
		
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		
		//properties.put("mail.smtp.auth", "false");
		//properties.put("mail.smtp.starttls.enable", "false");
		//properties.put("mail.smtp.host", "smtp.office365.com");
		//properties.put("mail.smtp.port", "587");
		
		properties.put("mail.imaps.ssl.checkserveridentity", "false");
		properties.put("mail.imaps.ssl.trust", "*");
		properties.put("mail.smtp.host", "cas.za.sbicdirectory.com");
		properties.put("mail.smtp.auth", "true");
		
		String myAcc_email = "xxxxxxxx@xxxxxx.co.za";
		String password = "xxxxxxxxxx";
		
		//properties.put("mail.smtp.auth", "True");
		//properties.put("mail.smtp.starttls.enable", "True");
		//properties.put("mail.smtp.host", "smtp.gmail.com");
		//properties.put("mail.smtp.port", "587");
		
		//String myAcc_email = "xxxxxxx@gmail.com";
		//String password = "xxxxxxxx";
				
		//Session session = Session.getInstance(properties,new Authenticator(){
	
		//protected PasswordAuthentication getPasswordAuthenticator() {
		//	return new PasswordAuthentication(myAcc_email,password);
		//}	
			
		//});
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(myAcc_email, password);
		    }
		});
        //session.setDebug(true);
		
		Message message = prepareMessage (session,myAcc_email,recepient);
		Transport.send(message);
		System.out.println("Messege sent succefully");
	}	
	    
		private static Message prepareMessage (Session session, String myAcc_email, String recepient){
			try{
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(myAcc_email));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
				message.setSubject("Java sent email");
				message.setText("Hey, \n from Java");
				return message;
				
			}catch (Exception ex) {
				// TODO: handle exception
				Logger.getLogger(JavaMailUtil.class.getName()).log(null, Level.SEVERE, ex);
			}
			return null;
		}
}

