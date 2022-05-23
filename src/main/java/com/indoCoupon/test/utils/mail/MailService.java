/**
 * 
 */
package com.indoCoupon.test.utils.mail;

import java.util.List;

import com.indoCoupon.test.modals.CouponsModal;
import com.indoCoupon.test.modals.Users;

/**
 * @author shiva
 * Added on 22-Apr-2022
 * Package  com.indoCoupon.test.utils.mail
 */
public interface MailService {

//	public void sendMail(String emailTo,String userName,List<String> errorList);

	public  void sendMail(MailDTO mailDto,List<String> errorList);
	
	public void WelcomeMail(Users user , List<String> errorList );
	public void updateMail(String previousName, String previousMail, String previousPhone , Users updatedDetails , List<String> errorList );
	public void updateAdminForUPIPayment(Users user, CouponsModal coupons, List<String> errorList);
	public void sendCouponToUser(Users user , CouponsModal coupons, List<String> errorList);
}
