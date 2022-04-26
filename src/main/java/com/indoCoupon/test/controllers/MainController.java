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
	UserService userService;

	@RequestMapping(value = "/home")
	public String homePage() {
		return homePage;
	}

	@RequestMapping(value = "/login")
	public String loginPage() {
		return loginPage;
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
	
	@RequestMapping(value = "/account_settings")
	public String getAccountDetails(HttpSession session) {
		AppUsers user = (AppUsers) session.getAttribute("loggedInUser");
		
		if(new Utils().isNotNull(user)) {
			return "settings";
		}else {
			return loginPage;
		}
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
				log.info("ErrorList Afte Save USER CALL:  "+errorList);
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
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something went Wrong please try again");
		}

//		log.info("API Response Modal : " + apiResponseModal);
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
