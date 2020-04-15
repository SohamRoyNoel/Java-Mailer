package Mailer.JavaSMTPMailer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMailSender {
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	public void generateAndSendEmail() throws AddressException, MessagingException {
		 
		// Step1
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
		// Step2
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("soham.roy.developer@gmail.com"));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("happiergrimreaper@gmail.com"));
		generateMailMessage.setSubject("Greetings from ASS Corporation..");
		String emailBody = "Test email by Soham Roy by JavaMail API example. " + "<br><br> Regards, <br>Soham Roy";
		generateMailMessage.setContent(emailBody, "text/html");
 
		// Step3
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", SecretCresentials.emailId, SecretCresentials.password);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	
}
