/**
 * 
 */
package com.indoCoupon.test.utils.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 * Added on 22-Apr-2022
 * Package  com.indoCoupon.test.utils.mail
 */
@Repository
@Slf4j
public class MailServiceImpl implements MailService {

	//	@Autowired
	//	private MailDTO mail;

	@Value("smtp.gmail.com")	
	private String HOST;

	@Value("465")
	private String PORT;


	@Override
	public void sendMail(MailDTO mailDto, List<String> errorList) {
		log.info("-----------------------------Mail Sent Initiated--------------------------------------");

		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", HOST);
			properties.put("mail.smtp.port",PORT);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");

			Session session =  Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailDto.getFROM(), mailDto.getPASSWORD());
				} 
			});

			session.setDebug(true);
			//Step 2: Compose the Message
			MimeMessage mime = new MimeMessage(session);

			try {
				mime.setFrom(mailDto.getFROM());
				mime.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDto.getTO()));
				mime.setSubject(mailDto.getSUBJECT());
				mime.setContent(mailDto.getMESSAGE(),"text/html");
				Transport.send(mime);
				log.info("-----------------------------Mail Sent Successfully--------------------------------------");
			} catch (MessagingException me) {
				// TODO Auto-generated catch block
				me.printStackTrace();
			}

		}catch(Exception e) {
			e.printStackTrace();
			errorList.add("Something Went wrong while sending the mail!!");
		}

	}

	public void WelcomeMail() {
		
		MailDTO maildto= new MailDTO();
		maildto.setFROM("indocoupon.noreply@gmail.com");
		maildto.setPASSWORD("indoCoupon@2597");
		maildto.setSUBJECT("Welcome");
		maildto.setTO("sharmaascent6@gmail.com");
		maildto.setMESSAGE(""
				+ "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"UTF-8\">\r\n"
				+ "	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "	<title>IndoCoupon</title>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "	\r\n"
				+ "	<div class=\"Main-card\" style=\"\r\n"
				+ "		box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\r\n"
				+ "		transition: 0.3s;\r\n"
				+ "		width: 80%;\r\n"
				+ "		height: 100%;\r\n"
				+ "	  \">\r\n"
				+ "	<div class=\"insideHead\" style=\"height: 26%; width: 100%; background-color: #514a9b;\">\r\n"
				+ "		<img src=\"images/logo.PNG\" alt=\"IndoCoupon logo\" style=\"height: 40%; width: 15%;  \r\n"
				+ "		display: block;\r\n"
				+ "		margin-left: auto;\r\n"
				+ "		margin-right: auto;\r\n"
				+ "		padding: 4% \">\r\n"
				+ "		<h1></h1>\r\n"
				+ "	</div>\r\n"
				+ "\r\n"
				+ "	<h3 style=\"margin-left: 2%;\">Hello Shweta</h3>\r\n"
				+ "	<h4 style=\"margin-left: 2%;\">My name is Savita and I am the Managing Director of IndoCoupon! I am so glad to have you on board! </h4>\r\n"
				+ "\r\n"
				+ "  <P style=\"margin-left: 2%;\">Do not know where to begin? Here are some easy steps you can follow to get started with IndoCoupon.</P>\r\n"
				+ "	\r\n"
				+ "	<ul>\r\n"
				+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 1</h5>As you have already Registered so Step one is done!! Yaay</li>\r\n"
				+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 2</h5>Login in application with valid username and password.</li>\r\n"
				+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 3</h5>Search for the Coupon you want !!</li>\r\n"
				+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 4</h5>Click on Get pin Button to make it yours</li>\r\n"
				+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 5</h5>You have two options to pay \r\n"
				+ "		<ul>\r\n"
				+ "			<li>Credit/Debit card</li>\r\n"
				+ "			<li>Wallet or UPI</li>\r\n"
				+ "		</ul>\r\n"
				+ "		</li>\r\n"
				+ "	</ul>\r\n"
				+ "	  \r\n"
				+ "	<h4 style=\"margin-left: 2%;\">If you need anything, do not hesitate to contact me directly! I will be happy to help you out!</h4>\r\n"
				+ "	\r\n"
				+ "	<h3 style=\"margin-left: 2%;\">Best Regards,</h3>\r\n"
				+ "	<h3 style=\"margin-left: 2%;\">Savita Jaijaniya</h3>\r\n"
				+ "	</div>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
//		mailService.sendMail(maildto, errorList);

	}
	
	
	
}
