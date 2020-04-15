package Mailer.JavaSMTPMailer;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class App 
{
    public static void main( String[] args )
    {
    	SimpleMailSender sms = new SimpleMailSender();
    	SimpleMailSenderWithAttachment smsA = new SimpleMailSenderWithAttachment();
    	try {
    		// General Email
			 sms.generateAndSendEmail();
			// Email With attachment
			smsA.SendMailWithAttachment();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
}
