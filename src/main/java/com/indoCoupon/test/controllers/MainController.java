/**
 * 
 */
package com.indoCoupon.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.indoCoupon.test.modals.CouponsModal;
import com.indoCoupon.test.modals.Users;
import com.indoCoupon.test.services.CouponService;
import com.indoCoupon.test.services.UserService;
import com.indoCoupon.test.utils.APIResponseModal;
import com.indoCoupon.test.utils.Constants;
import com.indoCoupon.test.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva Added on 17-Mar-2022 Package com.indoCoupon.test.controllers
 */

@Controller
@RequestMapping(value = "/indoCoupon/v1")
@Slf4j
public class MainController {
//	private static final Logger logger = LogManager.getLogger(MainController.class);

	public static final String homePage = "index";
	public static final String loginPage = "login";
	public static final String adminPage = "admin";
	public static final String paymentPage = "payment";

	@Autowired
	UserService userService;

	@Autowired
	CouponService couponService;

	@RequestMapping(value = "/home")
	public String homePage() {
		return homePage;
	}

	@RequestMapping(value = "/login")
	public String loginPage() {
		return loginPage;
	}

	@RequestMapping(value = "/payments")
	public String payment(HttpSession session) {
		Users user = (Users) session.getAttribute("loggedInUser");
		if (new Utils().isNotNull(user)) {
			return paymentPage;
		} else {
			return loginPage;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/paymentPage", method = { RequestMethod.POST })
	public APIResponseModal paymentPagecheckUser(HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		try {
			Users loginUser = (Users) session.getAttribute("loggedInUser");

			if (loginUser != null) {
				apiResponseModal.setData(loginUser.toString());
				apiResponseModal.setStatus(HttpStatus.OK);
				apiResponseModal.setMessage("User Logged In");
			} else {
				apiResponseModal.setData(null);
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setMessage("No user logged in!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setMessage("No user logged in!");
		}
//		log.info("Api Response CheckUSer: " + apiResponseModal);
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/checkUser", method = { RequestMethod.POST, RequestMethod.GET })
	public APIResponseModal checkUser(HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		try {
			Users loginUser = (Users) session.getAttribute("loggedInUser");

			if (loginUser != null) {
				apiResponseModal.setData(loginUser.toString());
				apiResponseModal.setStatus(HttpStatus.OK);
				apiResponseModal.setMessage("User already Logged in");
			} else {
				apiResponseModal.setData(null);
				apiResponseModal.setStatus(HttpStatus.OK);
				apiResponseModal.setMessage("No user logged in!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		log.info("Api Response CheckUSer: " + apiResponseModal);
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/checkLoginUser", method = { RequestMethod.POST, RequestMethod.GET })
	public APIResponseModal checkLoginUser(HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		try {
			Users loginUser = (Users) session.getAttribute("loggedInUser");

			if (loginUser != null) {
				apiResponseModal.setData(loginUser.toString());
				apiResponseModal.setStatus(HttpStatus.OK);
				apiResponseModal.setMessage("User already Logged in");
			} else {
				apiResponseModal.setData(null);
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setMessage("No user logged in!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		log.info("Api Response CheckUSer: " + apiResponseModal);
		return apiResponseModal;
	}

	@RequestMapping(value = "/customer_account_settings")
	public String getAccountDetails(HttpSession session) {
		Users user = (Users) session.getAttribute("loggedInUser");
		if (new Utils().isNotNull(user)) {
			return "settings";
		} else {
			return loginPage;
		}
	}

	@RequestMapping(value = "/adminDashboard")
	public String admin(HttpSession session) {
		Users admin = (Users) session.getAttribute("loggedInUser");
		if (new Utils().isNotNull(admin) && admin.getRole() == Constants.userRole.ADMIN) {
			return adminPage;
		}
		return "redirect:/indoCoupon/v1/login";
	}

	@RequestMapping(value = "/sign-up")
	public String registerPage(HttpSession session) {
		if (session.getAttribute("loggedInUser") != null) {
			return homePage;
		}
		return "register";
	}

	@ResponseBody
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public APIResponseModal validateUser(@ModelAttribute Users user, HttpSession session) {
//		logger.info("Inside Main Controller: " + user);
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		Users loggedInUser = null;
		try {
			if (user.getUserName() != null & user.getPassword() != null) {
				loggedInUser = userService.validateUser(user, errorList);

				if (new Utils().isNotNull(loggedInUser) && errorList.isEmpty()) {
					apiResponseModal.setData(loggedInUser.toString());
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setMessage("Welcome!!");
					session.setAttribute("loggedInUser", loggedInUser);
				} else {
					apiResponseModal.setData(null);
					apiResponseModal.setStatus(HttpStatus.NOT_FOUND);
					apiResponseModal.setMessage(errorList.toString());
				}
			} else {
				apiResponseModal.setData(null);
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setMessage("Username and Password are Mandatory fields!!");
			}
//			logger.info("Logged in USer  : " + apiResponseModal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public APIResponseModal saveUser(@ModelAttribute Users user, HttpSession session) throws Exception {
//		logger.info("Inside Main Controller: " + user);
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();

		try {
			if (new Utils().isNotNull(user) && new Utils().isNotNull(user.getUserName())
					&& new Utils().isNotNull(user.getFullName()) && new Utils().isNotNull(user.getEmail())
					&& new Utils().isNotNull(user.getPhoneNumber()) && new Utils().isNotNull(user.getPassword())) {
				userService.saveUser(user, errorList);
				log.info("ErrorList Afte Save USER CALL:  " + errorList);
				if (errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData(user.toString());
					apiResponseModal
							.setMessage("Registered Successfully !! Will be redirected to Login page to continue!!");
				}
			} else {
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
	@RequestMapping(value = "/updateDetails/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public APIResponseModal updateUserProfile(@ModelAttribute Users user, @PathVariable("id") Integer customerId,
			HttpSession session) {
		List<String> errorList = new ArrayList<>();
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
//		log.info("User in Controller: "+ user);
//		log.info("User ID Controller: "+ customerId);
		try {
			if (new Utils().isNotNull(customerId) || new Utils().isNotNull(session)) {
				if (new Utils().isNotNull(user.getEmail()) && new Utils().isNotNull(user.getFullName())
						&& new Utils().isNotNull(user.getPhoneNumber())) {
					Users updatedUser = userService.updateUser(user, customerId, errorList);

					if (errorList.isEmpty()) {
						apiResponseModal.setData(updatedUser.toString());
						apiResponseModal.setMessage("Updated Successfully!");
						apiResponseModal.setStatus(HttpStatus.OK);
					} else {
						apiResponseModal.setData(null);
						apiResponseModal.setMessage(errorList.toString());
						apiResponseModal.setStatus(HttpStatus.EXPECTATION_FAILED);
					}
				} else {
					apiResponseModal.setData(null);
					apiResponseModal.setMessage("Please fill all the fields !!");
				}
			} else {
				apiResponseModal.setData(null);
				apiResponseModal.setMessage("Session timed out please login again!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something Went Wrong !! Please try again aftersome time!!");
		}
		return apiResponseModal;
	}

	@RequestMapping(value = "/provideCoupon/{userId}/{couponId}", method = { RequestMethod.POST, RequestMethod.GET })
	public String assignCouponToUser(@PathVariable("userId") Integer userId,
			@PathVariable("couponId") Integer couponId) {
		String succesPage = "success";
		String failPage = "failed";
		List<String> errorList = new ArrayList<String>();
		if (new Utils().isNotNull(userId)) {
			if (new Utils().isNotNull(couponId)) {
				userService.assignCouponToUser(userId, couponId, errorList);
			} else {
				return failPage;
			}
		} else {
			return failPage;
		}
		if (errorList.isEmpty())
			return succesPage;

		return failPage;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.POST)
	public APIResponseModal deleteUser(@PathVariable("id") Integer userId) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();

		try {
			if (new Utils().isNotNull(userId)) {
				userService.deleteUser(userId, errorList);
				if (errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData("");
					apiResponseModal.setMessage("Deleted Successfully !!");
				}
			} else {
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setData("");
				apiResponseModal.setMessage(errorList.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something went Wrong please try again");
		}
		return apiResponseModal;
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public String logoutUser(HttpSession session) {

		try {
			if (new Utils().isNotNull(session)) {
				session.invalidate();
				return loginPage;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return homePage;
	}

//	@RequestMapping(value = "report/{1}")
//	public String reportMethod(@PathVariable Integer reportType) {
//		
//		return "";
//		}

	@ResponseBody
	@RequestMapping(value = "/validateUserName", method = RequestMethod.POST)
	public APIResponseModal checkuserNamePresent(@RequestBody String userName) {
		List<String> errorList = new ArrayList<String>();
		APIResponseModal apiResponse = new Utils().getDefaultApiResponse();
		try {
			if (new Utils().isNotNull(userName)) {

				Boolean isExists = userService.userNameExits(userName, errorList);
				if (isExists) {
					apiResponse.setMessage("User name already exist please Choose another Username !");
					apiResponse.setStatus(HttpStatus.BAD_REQUEST);
					apiResponse.setData(getSaltString());
				} else {
					apiResponse.setMessage("Username not found");
					apiResponse.setStatus(HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setMessage("Username not found");
			apiResponse.setStatus(HttpStatus.OK);
		}
		return apiResponse;
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 9) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	@ResponseBody
	@RequestMapping(value = "/userCoupons", method = RequestMethod.POST)
	public APIResponseModal getListUserCoupons(HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		List<CouponsModal> userCoupons = new ArrayList<CouponsModal>();
		Users user = (Users) session.getAttribute("loggedInUser");

		try {
			if (new Utils().isNotNull(user)) {
//				log.info("User Id in Controller :"+user);
				userCoupons = couponService.getCouponsByUser(user, null, errorList);
				if (errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData(userCoupons.toString());
					apiResponseModal.setMessage("Found Successfully !!");
				}
			} else {
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setData("");
				apiResponseModal.setMessage(errorList.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something went Wrong please try again");
		}
//		log.info("Api :" + apiResponseModal);
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/allActiveUsers", method = RequestMethod.POST)
	public APIResponseModal getUserList() {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<Users> userList = new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		try {
			userList = userService.findAllUsers(errorList);
			if (errorList.isEmpty()) {
				apiResponseModal.setData(userList.toString());
				apiResponseModal.setMessage("List Found");
				apiResponseModal.setStatus(HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setData("");
			apiResponseModal.setMessage("Something Went Wrong !");
			apiResponseModal.setStatus(HttpStatus.BAD_GATEWAY);
		}
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/customerCoupons/{userId}", method = RequestMethod.POST)
	public APIResponseModal getListCustomerCoupons(@PathVariable("userId") Integer userId, HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		List<CouponsModal> userCoupons = new ArrayList<CouponsModal>();

		try {
			if (new Utils().isNotNull(userId)) {
//				log.info("User Id in Controller :"+user);
				userCoupons = couponService.getCouponsByUser(null, userId, errorList);
				if (errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData(userCoupons.toString());
					apiResponseModal.setMessage("Found Successfully !!");
				}
			} else {
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setData("");
				apiResponseModal.setMessage(errorList.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something went Wrong please try again");
		}
//		log.info("Api :" + apiResponseModal);
		return apiResponseModal;
	}

	
	  //sendForgotPasswordto/"+name+"" //resetPassword/"+user.getUserId()+" public

	@ResponseBody
	@RequestMapping(value = "/sendForgotPasswordto/{username}", method = {RequestMethod.GET,RequestMethod.POST})
	public APIResponseModal sendForgotPassMail(@PathVariable("username") String username, HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		Boolean isSent = false;
		try {
			if (new Utils().isNotNull(username)) {
//				log.info("User Id in Controller :"+user);
				isSent = userService.SendForgotPasswordMail(username, errorList);
				if (errorList.isEmpty() && isSent) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setData("");
					apiResponseModal.setMessage("We have Sent the Password Reset Mail to your email Id");
				}
			} else {
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
				apiResponseModal.setData("");
				apiResponseModal.setMessage(errorList.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			apiResponseModal.setData(null);
			apiResponseModal.setMessage("Something went Wrong please try again");
		}
//		log.info("Api :" + apiResponseModal);
		return apiResponseModal;		
	}
	
	
	
	@RequestMapping(value = "/resetPassword/{userId}", method = {RequestMethod.GET})
	public ModelAndView resetPasswordPage(@PathVariable("userId") Integer userId) {
		ModelAndView model = new ModelAndView("passwordReset");
		model.addObject("userId",userId);
		return model;
	  }
	  

	@ResponseBody
	@RequestMapping(value = "/reset/{id}" , method = {RequestMethod.GET,RequestMethod.POST})
	public APIResponseModal reset(@PathVariable("id") Integer id,@RequestBody String passWord) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		Users user = new Users();
		
		try {
			if(new Utils().isNotNull(id) && new Utils().isNotNull(passWord)) {
				user = userService.updateUserPassword(id, passWord, errorList);
				
				if(errorList.isEmpty()) {
					apiResponseModal.setData(user.toString());
					apiResponseModal.setMessage("Password Updated Successfully !!");
					apiResponseModal.setStatus(HttpStatus.OK);
				}else {
					apiResponseModal.setData("");
					apiResponseModal.setMessage(errorList.toString());
					apiResponseModal.setStatus(HttpStatus.BAD_GATEWAY);
				}
				
			}else {
				apiResponseModal.setData("");
				apiResponseModal.setMessage("Please Enter Required values !!");
				apiResponseModal.setStatus(HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception e) {
			apiResponseModal.setData("");
			apiResponseModal.setMessage("Something Went Wrong");
			apiResponseModal.setStatus(HttpStatus.BAD_GATEWAY);
			e.printStackTrace();
		}		
		return apiResponseModal;
	}
	 
}