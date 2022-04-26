/**
 * 
 */
package com.indoCoupon.test.utils.mail;

import java.util.List;

import com.indoCoupon.test.modals.AppUsers;

/**
 * @author shiva
 * Added on 22-Apr-2022
 * Package  com.indoCoupon.test.utils.mail
 */
public interface MailService {

//	public void sendMail(String emailTo,String userName,List<String> errorList);

	public  void sendMail(MailDTO mailDto,List<String> errorList);
	
	public void WelcomeMail(AppUsers user , List<String> errorList );
}
