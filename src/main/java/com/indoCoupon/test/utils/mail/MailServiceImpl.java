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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.indoCoupon.test.modals.CouponsModal;
import com.indoCoupon.test.modals.Users;
import com.indoCoupon.test.utils.Utils;

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
//			log.info("Mail DTO --------- "+mailDto);
//			log.info("errorList --------- "+errorList);
			mailDto.setFROM("indocoupon.noreply@gmail.com");
			mailDto.setPASSWORD("rsnxskykavvpclan");
			
			if (mailDto.getTO() != null) {
				
				Properties properties = System.getProperties();
				properties.put("mail.smtp.host", HOST);
				properties.put("mail.smtp.port", PORT);
				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail.smtp.auth", "true");
				Session session = Session.getInstance(properties, new Authenticator() {
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
					mime.setContent(mailDto.getMESSAGE(), "text/html");
					Transport.send(mime);
					log.info(
							"-----------------------------Mail Sent Successfully--------------------------------------");
				} catch (MessagingException me) {
					me.printStackTrace();
				} 
			}else {
				errorList.add("Mail Send Configuration Not Found");				
			}
		}catch(Exception e) {
			e.printStackTrace();
			errorList.add("Something Went wrong while sending the mail!!");
		}

	}

	@Override
	public void WelcomeMail(Users user , List<String> errorList ) {

		try {
			if(new Utils().isNotNull(user)){

				MailDTO maildto= new MailDTO();
				maildto.setSUBJECT("Welcome To IndoCoupon Family.");
				maildto.setTO(user.getEmail());
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
						+ "		<img src=\"resources/static/images/logo.jpg\" alt=\"IndoCoupon logo\" style=\"height: 40%; width: 15%;  \r\n"
						+ "		display: block;\r\n"
						+ "		margin-left: auto;\r\n"
						+ "		margin-right: auto;\r\n"
						+ "		padding: 4% \">\r\n"
						+ "		<h1></h1>\r\n"
						+ "	</div>\r\n"
						+ "\r\n"
						+ "	<h3 style=\"margin-left: 2%;\">Hello, " +user.getFullName()+"</h3>\r\n"
						+ "	<h3 style=\"margin-left: 2%;\">Your username is, " +user.getUserName()+". Please save it for login into the application.</h3>\r\n"
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
				
				sendMail(maildto, errorList);
				log.info("Send MAil IS called Successfully");
			}else {
				errorList.add("User not Found !!");
				throw new UsernameNotFoundException("User not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

@Override
	public void updateMail(String previousName, String previousMail, String previousPhone, Users updatedDetails, List<String> errorList) {

	final String baseUrl = 
			ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();	
	try {
		if( new Utils().isNotNull(updatedDetails) ) {
			
			MailDTO maildto= new MailDTO();
			maildto.setSUBJECT("Important information about your IndoCoupon account");
			maildto.setTO(previousMail);
			maildto.setMESSAGE("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "	<meta charset=\"UTF-8\">\r\n"
					+ "	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "	<title>IndoCoupon</title>\r\n"
					+ "</head>\r\n"
					+ "<style>\r\n"
					+ ".button {\r\n"
					+ "margin-left: 2%;\r\n"
					+ "width: 5vw;\r\n"
					+ "  background-color: white;  \r\n"
					+ "  border-radius: 5px;\r\n"
					+ "  color: black;\r\n"
					+ "  padding: .5em;\r\n"
					+ "  text-decoration: none;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ ".button:focus,\r\n"
					+ ".button:hover {\r\n"
					+ "  background-color: #514a9b;\r\n"
					+ "  color: White;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "</style>\r\n"
					+ "<body>\r\n"
					+ "	<div class=\"Main-card\" style=\"\r\n"
					+ "		box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\r\n"
					+ "		transition: 0.3s;\r\n"
					+ "		width: 80%;\r\n"
					+ "		height: 100%\"\r\n"
					+ "	  >\r\n"
					+ "	<div class=\"insideHead\" style=\"height: 26%; width: 100%; background-color: #514a9b\">\r\n"
					+ "		<img src=\"\" alt=\"IndoCoupon logo\" style=\"height: 40%; width: 15%;  \"\r\n"
					+ "		display: block\r\n"
					+ "		margin-left: auto\r\n"
					+ "		margin-right: auto\r\n"
					+ "		padding: 4% >\r\n"
					+ "		<h1></h1>\r\n"
					+ "	</div>\r\n"
					+ "	<h3 style=\"margin-left: 2%;\">Hello, "+previousName+"</h3>\r\n"
					+ "	<h4 style=\"margin-left: 2%\">Hope you and your loved ones are doing good!!</h4>\r\n"
					+ "\r\n"
					+ "  <P style=\"margin-left: 2%\">We would like to inform you that there are a couple of Changes made in your account that are listed below.</P>\r\n"
					+ "\r\n"
					+ "	<ul>\r\n"
					+ "		<li><a style=\"color: #514a9b; font-weight: bold\">"+previousName+"</a> is replaced with <a style=\"color: #514a9b; font-weight: bold\">"+updatedDetails.getFullName()+"</a></li>\r\n"
					+ "		<li><a style=\"color: #514a9b; font-weight: bold\">"+previousMail+"</a> is replaced with <a style=\"color: #514a9b; font-weight: bold\">"+updatedDetails.getEmail()+"</a></li>\r\n"
					+ "		<li><a style=\"color: #514a9b; font-weight: bold\">"+previousPhone+"</a> is replaced with <a style=\"color: #514a9b; font-weight: bold\">"+updatedDetails.getPhoneNumber()+"</a></li>\r\n"
					+ "	</ul>\r\n"
					+ "	  \r\n"
					+ "	<h4 style=\"margin-left: 2%\">All the communication from now will be make with updated values.</h4>\r\n"
					+ "	  \r\n"
					+ "	<h4 style=\"margin-left: 2%\">If you have not requested these Changes. Please report it to us to secure your account</h4>\r\n"
					+ "	<a href=\""+baseUrl+"/indoCoupon/v1/report/{1}\" type=\"button\" class=\"button\">Report</a>\r\n"
					+ "	<hr>\r\n"
					+ "	<h4 style=\"margin-left: 2%\">Best Regards,</h4>\r\n"
					+ "	<h4 style=\"margin-left: 2%\">Savita Jaijaniya</h4>\r\n"
					+ "	</div>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>");
			sendMail(maildto, errorList);			
		}else {
			
		}		
	} catch (Exception e) {
		e.printStackTrace();
		errorList.add("Something Went Wrong While sending the mail !");
	}
	
}

@Override
	public void updateAdminForUPIPayment(Users user, CouponsModal coupons, List<String> errorList) {
	final String baseUrl = 
			ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();	

	try {
		if(new Utils().isNotNull(user) && new Utils().isNotNull(coupons)){

			MailDTO maildto= new MailDTO();
			maildto.setSUBJECT("Coupon Requested !");
			maildto.setTO("indocoupon.noreply@gmail.com");
			maildto.setMESSAGE("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<style>\r\n"
					+ "* {\r\n"
					+ "  box-sizing: border-box;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "/* Create two equal columns that floats next to each other */\r\n"
					+ ".column {\r\n"
					+ "  float: left;\r\n"
					+ "  width: 50%;\r\n"
					+ "  padding: 10px;\r\n"
					+ "}\r\n"
					+ "\r\n"
					+ "/* Clear floats after the columns */\r\n"
					+ ".row:after {\r\n"
					+ "  content: \"\";\r\n"
					+ "  display: table;\r\n"
					+ "  clear: both;\r\n"
					+ "}\r\n"
					+ ".button {\r\n"
					+ "					margin-left: 42%;\r\n"
					+ "					background-color: white;\r\n"
					+ "					border-radius: 5px;\r\n"
					+ "					color: black;\r\n"
					+ "					padding: .5em;\r\n"
					+ "					 text-decoration: none;\r\n"
					+ "					}\r\n"
					+ ".button:focus,\r\n"
					+ "					.button:hover {\r\n"
					+ "					background-color: #514a9b;\r\n"
					+ "					color: White;\r\n"
					+ "					}          \r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "  <div class=Main-card style=\"\r\n"
					+ "  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\r\n"
					+ "  transition: 0.3s;\r\n"
					+ "  width: 80%;\r\n"
					+ "  height: 100%;\r\n"
					+ "  \">\r\n"
					+ "<div class=insideHead style=\"height: 26%; width: 100%; background-color: #514a9b;\">\r\n"
					+ "  <img src=resources/static/images/logo.jpg alt=IndoCoupon logo style=\"height: 40%; width: 15%;\r\n"
					+ "  display: block;\r\n"
					+ "  margin-left: auto;\r\n"
					+ "  margin-right: auto;\r\n"
					+ "  padding: 4% \">\r\n"
					+ "  <h1></h1>\r\n"
					+ "</div>\r\n"
					+ "\r\n"
					+ "<h3 style=\"margin-left: 2%;\">Hello, Admin</h3>\r\n"
					+ "<h3 style=\"margin-left: 2%;\">We got a new Coupon Request From "+user.getFullName()+"</h3>\r\n"
					+ "\r\n"
					+ "<div class=\"row\">\r\n"
					+ "  <div class=\"column\">\r\n"
					+ "    <h2 style=\"text-align: center; color: 514a9b;\">User Details: </h2>\r\n"
					+ "    <ul>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">User Name: "+user.getUserName()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">User Email: "+user.getEmail()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">User Phone: "+user.getPhoneNumber()+"</p></li>\r\n"
					+ "    </ul>\r\n"
					+ "  \r\n"
					+ "  </div>\r\n"
					+ "  <div class=\"column\">\r\n"
					+ "    <h2 style=\"text-align: center; color: 514a9b;\">Coupon Details </h2>\r\n"
					+ "    <ul>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">Coupon Code: "+coupons.getCouponCode()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">Coupon Key: "+coupons.getCouponKey()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">Coupon Value: "+coupons.getCouponKeyValue()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">Coupon Expiry Date: "+coupons.getCouponExpiryDate()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">Coupon Value: "+coupons.getCouponValue()+"</p></li>\r\n"
					+ "      <li><p style=\"font-weight: bold;\">Coupon Price: "+coupons.getCouponPrice()+" (Amount you have recived !)</p></li>\r\n"
					+ "    </ul>\r\n"
					+ "  </div>\r\n"
					+ "</div>\r\n"
					+ "<a href=\""+baseUrl+"/indoCoupon/v1/provideCoupon/"+user.getUserId()+"/"+coupons.getCouponId()+"\" type=\"button\" class=\"button\">Payment Recived</a>\r\n"
					+ "\r\n"
					+ "<h3 style=\"margin-left: 2%;\">Best Regards,</h3>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
			
			sendMail(maildto, errorList);
		}else {
			errorList.add("User not Found !!");
			throw new UsernameNotFoundException("User not found");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	@Override
	public void sendCouponToUser(Users user , CouponsModal coupons, List<String> errorList) {
		try {

			if(new Utils().isNotNull(user) &&new Utils().isNotNull(coupons) ) {
				String mailTemplet = "<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "<style>\r\n"
						+ "* {\r\n"
						+ "  box-sizing: border-box;\r\n"
						+ "}\r\n"
						+ "</style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "  <div class=Main-card style=\"\r\n"
						+ "  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\r\n"
						+ "  transition: 0.3s;\r\n"
						+ "  width: 80%;\r\n"
						+ "  height: 100%;\r\n"
						+ "  \">\r\n"
						+ "<div class=insideHead style=\"height: 26%; width: 100%; background-color: #514a9b;\">\r\n"
						+ "  <img src=resources/static/images/logo.jpg alt=IndoCoupon logo style=\"height: 40%; width: 15%;\r\n"
						+ "  display: block;\r\n"
						+ "  margin-left: auto;\r\n"
						+ "  margin-right: auto;\r\n"
						+ "  padding: 4% \">\r\n"
						+ "  <h1></h1>\r\n"
						+ "</div>\r\n"
						+ "<p style=\"margin-left: 2%;\">Hello, User</p>\r\n"
						+ "<p style=\"margin-left: 2%;\">Here are the Coupon Details you Requested For</p>\r\n"
						+ "<hr>\r\n"
						+ "<ul>\r\n"
						+ "  <li>Coupon Code: "+coupons.getCouponCode()+"</li>\r\n"
						+ "  <li>Expiry Date: "+coupons.getCouponExpiryDate()+"</li>\r\n";
						if(coupons.getCouponKey() != null || coupons.getCouponKey() !="") {
							mailTemplet +="  <li>"+coupons.getCouponKey()+": "+coupons.getCouponKeyValue()+"</li>\r\n";
						}
						mailTemplet +="  <li>Coupon Value: "+coupons.getCouponValue()+"</li>\r\n"
						+ "</ul>\r\n"
						+ "<h4 style=\"margin-left: 2%;\">Thank You So much For buying Coupon, Happy Shopping</h4>\r\n"
						+ "<h4 style=\"margin-left: 2%;\">Best Regards,</h4>\r\n"
						+ "<h4 style=\"margin-left: 2%;\">IndoCoupon Team</h4>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "";
				
				
				MailDTO maildto= new MailDTO();
				maildto.setSUBJECT("Coupon Notification");
				maildto.setTO(user.getEmail());
				maildto.setMESSAGE(mailTemplet);
				sendMail(maildto, errorList);
			}else {
				errorList.add("User not Found !!");
				throw new UsernameNotFoundException("User not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendForgotPassWordMail(Users user, List<String> errorList) {
		final String baseUrl = 
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();	

		try {
			if(new Utils().isNotNull(user)){

				MailDTO maildto= new MailDTO();
				maildto.setSUBJECT("Reset Password");
				maildto.setTO(user.getEmail());
				maildto.setMESSAGE("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "<style>\r\n"
						+ "* {\r\n"
						+ "  box-sizing: border-box;\r\n"
						+ "}\r\n"
						+ ".button {\r\n"
						+ "									margin-left: 42%;\r\n"
						+ "									background-color: #514a9b;\r\n"
						+ "									border-radius: 5px;\r\n"
						+ "									color: black;\r\n"
						+ "									padding: .5em;\r\n"
						+ "									 text-decoration: none;\r\n"
						+ "									}\r\n"
						+ ".button:focus,\r\n"
						+ "					.button:hover {\r\n"
						+ "					background-color: #514a9b;\r\n"
						+ "					color: White;\r\n"
						+ "					}\r\n"
						+ "</style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "  <div class=Main-card style=\"\r\n"
						+ "  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\r\n"
						+ "  transition: 0.3s;\r\n"
						+ "  width: 80%;\r\n"
						+ "  height: 100%;\r\n"
						+ "  \">\r\n"
						+ "<div class=insideHead style=\"height: 26%; width: 100%; background-color: #514a9b;\">\r\n"
						+ "  <img src=resources/static/images/logo.jpg alt=IndoCoupon logo style=\"height: 40%; width: 15%;\r\n"
						+ "  display: block;\r\n"
						+ "  margin-left: auto;\r\n"
						+ "  margin-right: auto;\r\n"
						+ "  padding: 4% \">\r\n"
						+ "  <h1></h1>\r\n"
						+ "</div>\r\n"
						+ "<p style=\"margin-left: 2%;\">Hello, "+user.getFullName()+" </p>\r\n"
						+ "<p style=\"margin-left: 2%;\">Forgot Password? No worries You can reset your password Just by Click on the ResetButton </p>\r\n"
						+ "<hr>\r\n"
						+ "<a href=\""+baseUrl+"/indoCoupon/v1/resetPassword/"+user.getUserId()+"\" class=\"button\">Reset Password</a>\r\n"
						+ "<h4 style=\"margin-left: 2%;\">If You have not Requested <a href=\"\">Click here to Report</a></h4>\r\n"
						+ "<h4 style=\"margin-left: 2%;\">Best Regards,</h4>\r\n"
						+ "<h4 style=\"margin-left: 2%;\">IndoCoupon Team</h4>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
				
				sendMail(maildto, errorList);
			}else {
				errorList.add("User not Found !!");
				throw new UsernameNotFoundException("User not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
	}


}
