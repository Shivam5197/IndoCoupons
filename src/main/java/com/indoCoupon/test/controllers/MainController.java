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
import com.indoCoupon.test.utils.Utils;

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
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/home")
	public String homePage() {
		return homePage;
	}

	@RequestMapping(value = "/login")
	public String loginPage() {
		return "login";
	}


	@ResponseBody
	@RequestMapping(value = "/validateUser",method = RequestMethod.POST)
	public APIResponseModal validateUser(@ModelAttribute AppUsers user,HttpSession session) {
		logger.info("Inside Main Controller: "+ user);
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		AppUsers loggedInUser = null;
		if(user.getUserName()!= null & user.getPassword() !=null) {
			loggedInUser = userService.validateUser(user, errorList);
			apiResponseModal.setData(loggedInUser.toString());
			apiResponseModal.setStatus(HttpStatus.OK);
			apiResponseModal.setMessage("Verified!!");
			if(loggedInUser !=null) {
			session.setAttribute("loggedInUser", loggedInUser);
			}
		}else {
		apiResponseModal.setData(null);
		apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
		apiResponseModal.setMessage("Username and Password are Mandatory fields!!");
		}
		return apiResponseModal;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	public APIResponseModal saveUser(@ModelAttribute AppUsers user,HttpSession session) throws Exception {
		logger.info("Inside Main Controller: "+ user);
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		AppUsers loggedInUser = null;
		
		try {
			if(new Utils().isNotNull(user)) {
				userService.saveUser(user, errorList);
				if(errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData(user.toString());
					apiResponseModal.setMessage("Registered Successfully !!");
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

}
