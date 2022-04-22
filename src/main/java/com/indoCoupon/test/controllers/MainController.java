/**
 * 
 */
package com.indoCoupon.test.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indoCoupon.test.modals.AppUsers;
import com.indoCoupon.test.services.UserService;
import com.indoCoupon.test.utils.APIResponseModal;
import com.indoCoupon.test.utils.Constants;
import com.indoCoupon.test.utils.Utils;
import com.indoCoupon.test.utils.mail.MailDTO;
import com.indoCoupon.test.utils.mail.MailService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.controllers
 */

@Controller
@RequestMapping(value = "/indoCoupon/v1")
@Slf4j
public class MainController {
	private static final Logger logger = LogManager.getLogger(MainController.class);

	public static final String homePage = "index";
	public static final String loginPage = "login";
	public static final String adminPage = "admin";
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/home")
	public String homePage() {
		return homePage;
	}

	@RequestMapping(value = "/login")
	public String loginPage() {
		return loginPage;
	}
	
	
	@RequestMapping("/sendMail")
	public void sendMail() {
	
		try {
			List<String> errorList = new ArrayList<>();
			MailDTO maildto= new MailDTO();
			maildto.setFROM("indocoupon.noreply@gmail.com");
			maildto.setPASSWORD("indoCoupon@2597");
			maildto.setSUBJECT("Welcome");
			maildto.setTO("savitajaijaniya@gmail.com");
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
			
			mailService.sendMail(maildto, errorList);
			
			log.info("Mail Sent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/checkUser",method = {RequestMethod.POST,RequestMethod.GET})
	public APIResponseModal checkUser(HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		try {
			AppUsers loginUser = (AppUsers) session.getAttribute("loggedInUser");		

			if(loginUser !=null) {
				apiResponseModal.setData(loginUser.toString());
				apiResponseModal.setStatus(HttpStatus.OK);
				apiResponseModal.setMessage("User already Logged in");
			}else {
				apiResponseModal.setData(null);
				apiResponseModal.setStatus(HttpStatus.OK);
				apiResponseModal.setMessage("No user logged in!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Api Response CheckUSer: " + apiResponseModal);
		return apiResponseModal;
	}
	
	
	@RequestMapping(value = "/adminDashboard")
	public String admin(HttpSession session) {
		AppUsers admin = (AppUsers) session.getAttribute("loggedInUser");
		if(new Utils().isNotNull(admin) &&admin.getRole() == Constants.userRole.ADMIN) {
			return adminPage;
		}
		return "redirect:/indoCoupon/v1/login";
	}
	
	@RequestMapping(value = "/sign-up")
	public String registerPage(HttpSession session) {
		if(session.getAttribute("loggedInUser") != null) {
			return homePage;
		}
		return "register";
	}


	@ResponseBody
	@RequestMapping(value = "/validateUser",method = RequestMethod.POST)
	public APIResponseModal validateUser(@ModelAttribute AppUsers user,HttpSession session) {
		logger.info("Inside Main Controller: "+ user);
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		AppUsers loggedInUser = null;
		try {
			if(user.getUserName()!= null & user.getPassword() !=null) {
				loggedInUser = userService.validateUser(user, errorList);

				if(new Utils().isNotNull(loggedInUser) && errorList.isEmpty()) {
					apiResponseModal.setData(loggedInUser.toString());
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setMessage("Welcome!!");
					session.setAttribute("loggedInUser", loggedInUser);
				}else {
					apiResponseModal.setData(null);
					apiResponseModal.setStatus(HttpStatus.NOT_FOUND);
					apiResponseModal.setMessage(errorList.toString());
				}
			}else {
				apiResponseModal.setData(null);
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setMessage("Username and Password are Mandatory fields!!");
			}
			logger.info("Logged in USer  : " + apiResponseModal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	public APIResponseModal saveUser(@ModelAttribute AppUsers user,HttpSession session) throws Exception {
		logger.info("Inside Main Controller: "+ user);
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();

		try {
			if(new Utils().isNotNull(user) && new Utils().isNotNull(user.getUserName())&& new Utils().isNotNull(user.getFullName())
					&& new Utils().isNotNull(user.getEmail())&& new Utils().isNotNull(user.getPhoneNumber())
					&& new Utils().isNotNull(user.getPassword())) {
				userService.saveUser(user, errorList);
				if(errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData(user.toString());
					apiResponseModal.setMessage("Registered Successfully !! Will be redirected to Login page to continue!!");
				}
			}else {
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setData(null);
				apiResponseModal.setMessage("Please Provide all the Required Fields");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something went Wrong please try again");
		}

		return apiResponseModal;
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout",method = {RequestMethod.POST,RequestMethod.GET})
	public String logoutUser(HttpSession session) {
		try {
			if(new Utils().isNotNull(session)) {
				session.invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return homePage;
	}


}
