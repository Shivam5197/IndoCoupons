/**
 * 
 */
package com.indoCoupon.test.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.indoCoupon.test.modals.CouponsModal;
import com.indoCoupon.test.modals.Users;
import com.indoCoupon.test.services.CouponService;
import com.indoCoupon.test.utils.APIResponseModal;
import com.indoCoupon.test.utils.Constants;
import com.indoCoupon.test.utils.Utils;
import com.indoCoupon.test.utils.mail.MailService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 * Added on 29-Apr-2022
 * Package  com.indoCoupon.test.controllers
 */

@Controller
@RequestMapping(value = "/coupon")
@Slf4j
public class CouponController {

	@Autowired
	CouponService couponService;

	@Autowired
	MailService mailService;

	@ResponseBody
	@RequestMapping(value = "/saveCoupon", method = {RequestMethod.POST,RequestMethod.GET})
	public APIResponseModal saveCoupon(@ModelAttribute CouponsModal couponModal , HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		log.info("Coupon Model :::" + couponModal);
		try {
			Users user = (Users) session.getAttribute("loggedInUser");			
			if(new Utils().isNotNull(user) && user.getRole() == Constants.userRole.ADMIN) {
				couponService.saveCoupon(couponModal, errorList);		
				if(errorList.isEmpty()) {
					apiResponseModal.setStatus(HttpStatus.OK);
					apiResponseModal.setMessage("Saved Successfully !");
					apiResponseModal.setData(null);
				}else {
					apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
					apiResponseModal.setMessage(errorList.toString());
					apiResponseModal.setData(null);
				}			
			}else {
				apiResponseModal.setStatus(HttpStatus.UNAUTHORIZED);
				apiResponseModal.setMessage("Session Timed out !");
				apiResponseModal.setData(null);
			}
		} catch (Exception e) {
			apiResponseModal.setMessage("Something went wrong !!");
			apiResponseModal.setStatus(HttpStatus.EXPECTATION_FAILED);
			e.printStackTrace();
		}
		return apiResponseModal;
	}

	@ResponseBody
	@RequestMapping(value = "/getAllCoupons")
	public APIResponseModal getCouponsList(HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();			
		List<CouponsModal> coupons = new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		try {
			coupons= couponService.getAllCoupons(errorList);		
			if(errorList.isEmpty()) {
				apiResponseModal.setData(coupons.toString());
				apiResponseModal.setMessage("List Found");
				apiResponseModal.setStatus(HttpStatus.OK);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiResponseModal;
	}


	@ResponseBody
	@RequestMapping(value = "/getCouponDetails/{couponId}", method = {RequestMethod.GET,RequestMethod.POST})
	public APIResponseModal getCouponByIdCon(@PathVariable Integer couponId, HttpSession session){
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();
		List<String> errorList = new ArrayList<>();
		CouponsModal coupon = new CouponsModal();
		try {
			Users admin = (Users) session.getAttribute("loggedInUser");

			if(new Utils().isNotNull(couponId)) {
					coupon = couponService.getCouponByID(couponId, errorList);	

					if(errorList.isEmpty()) {
						apiResponseModal.setData(coupon.toString());
						apiResponseModal.setMessage("Found Coupon ");
						apiResponseModal.setStatus(HttpStatus.OK);
						return apiResponseModal;
					}else {
						apiResponseModal.setData(null);
						apiResponseModal.setMessage(errorList.toString());
						apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
					}
				}else {
					errorList.add("Coupon not found !!");
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiResponseModal;	
	}	

	
	@ResponseBody
	@RequestMapping(value = "/getActiveCoupons",method = {RequestMethod.GET,RequestMethod.POST})
	public APIResponseModal getActiveCoupons() {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();			
		List<CouponsModal> coupons = new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		try {
			coupons= couponService.getActiveCoupons(errorList);		
			if(errorList.isEmpty()) {
				apiResponseModal.setData(coupons.toString());
				apiResponseModal.setMessage("List Found");
				apiResponseModal.setStatus(HttpStatus.OK);
			}		
		} catch (Exception e) {
			// TODO: handle exception
			apiResponseModal.setMessage("Something Went wrong Please try after sometime");	
			e.printStackTrace();
		}
//		log.info("API REsponse Model : Active Coupons :" + apiResponseModal );
		return apiResponseModal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/listbyBrand/{value}",method = {RequestMethod.GET,RequestMethod.POST})
	public APIResponseModal getListByBrand(@PathVariable Integer value, HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();			
		List<CouponsModal> coupons = new ArrayList<>();
		List<String> errorList = new ArrayList<>();

		try {
			if(new Utils().isNotNull(value)) {
					coupons = couponService.getCouponByBrand(value, errorList);
					if(errorList.isEmpty()) {
						apiResponseModal.setData(coupons.toString());
						apiResponseModal.setMessage("Success !!");
						apiResponseModal.setStatus(HttpStatus.OK);
					}else {
						apiResponseModal.setMessage(errorList.toString());
						apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
					}
		}else {
				apiResponseModal.setMessage("Brand not found in Library");
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setMessage("Something went Wrong !");
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseModal;
	}
	

	@ResponseBody
	@RequestMapping(value = "/sendMailToAdmin/{value}",method = {RequestMethod.GET,RequestMethod.POST})
	public APIResponseModal sendMailToAdmin(@PathVariable Integer value, HttpSession session) {
		APIResponseModal apiResponseModal = new Utils().getDefaultApiResponse();			
		List<String> errorList = new ArrayList<>();
		Users user = (Users) session.getAttribute("loggedInUser");
		try {
			CouponsModal coupon = couponService.getCouponByID(value, errorList);
			if(new Utils().isNotNull(value)) {
				mailService.updateAdminForUPIPayment(user, coupon, errorList); 
				if(errorList.isEmpty()) {
						apiResponseModal.setData("We Have Informend Admin about your Request !!"
								+ "/n "
								+ "Once the Payment Approved by Admin Your Coupon Details will be mailed you on Your Email Id");
						apiResponseModal.setMessage("Success !!");
						apiResponseModal.setStatus(HttpStatus.OK);
					}else {
						apiResponseModal.setMessage(errorList.toString());
						apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
					}
		}else {
				apiResponseModal.setMessage("Brand not found in Library");
				apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			apiResponseModal.setMessage("Something went Wrong !");
			apiResponseModal.setStatus(HttpStatus.BAD_REQUEST);
		}
		return apiResponseModal;
	}

	
}
