package Mailer.JavaSMTPMailer;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleMailSenderWithAttachment {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	public void SendMailWithAttachment() throws AddressException, MessagingException  {

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
		// Subject
		generateMailMessage.setSubject("Greetings from ASS Corporation..");		
		// Body
		BodyPart messageBodyPart = new MimeBodyPart();
		String emailBody = "Test email by Soham Roy by JavaMail API example. " + "<br><br> Regards, <br>Soham Roy";
		messageBodyPart.setText(emailBody);

		// Create a multipar message
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String filename = "C:\\Users\\soham\\Pictures\\9b9.jpg";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		generateMailMessage.setContent(multipart);

		// Step3
		Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", SecretCresentials.emailId, SecretCresentials.password);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();

	}

}
