/**
 * 
 */
package com.indoCoupon.test.servicesImpl;

import java.util.List;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.indoCoupon.test.modals.Users;
import com.indoCoupon.test.repo.CouponRepo;
import com.indoCoupon.test.repo.UserRepo;
import com.indoCoupon.test.services.UserService;
import com.indoCoupon.test.utils.Constants;
import com.indoCoupon.test.utils.Utils;
import com.indoCoupon.test.utils.mail.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.servicesImpl
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	CouponRepo couponRepo;

	@Autowired
	MailService mailService;

	@Override
	public Users saveUser(Users user, List<String> errorList) {
		Users userToSave = null;	
		try {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setRole(Constants.userRole.CUSTOMER);
			user.setAddedAt(new java.sql.Timestamp(System.currentTimeMillis()));
			userToSave =userRepo.save(user);
			mailService.WelcomeMail(user, errorList);
		}catch(Exception e) {
			e.printStackTrace();
			errorList.add("Something went Wrong! While Create new User please try again ! ");
		}
		return userToSave;
	}

	@Override
	public Users getUserNameById(Integer userId, List<String> errorList) {

		return null;
	}

	@Override
	public void deleteUser(Users user, List<String> errorList) {
		// TODO Auto-generated method stub

	}

	@Override
	public Users validateUser(Users user, List<String> errorList) {
		Users loginUser = null;
		try {
			if(new Utils().isNotNull(user.getUserName()) && new Utils().isNotNull(user.getPassword()) ) {

				loginUser = userRepo.findByUserName(user.getUserName());

				if(new Utils().isNotNull(loginUser)) {
					if(new BCryptPasswordEncoder().matches(user.getPassword(), loginUser.getPassword())) {
						//						log.info("Logged in Service Imple: " + loginUser);
						return loginUser;
					}else {
						errorList.add("Incorrect Password !! ");
					}
				}else {
					errorList.add("User not found!! Please Check the username you entered !");
					throw new UsernameNotFoundException("UserName is not found in the DataBase");
				}
			}else {
				errorList.add("Please Enter Valid UserName & Password");
				throw new UsernameNotFoundException("UserName is not found in the DataBase");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//			errorList.add("Something Went Wrong|| Please make sure to use Correct Username and Password !");
		}
		//		log.info("Logged in Service Imple: " + loginUser);
		return loginUser;
	}

	@Override
	public Users updateUser(Users loggedInUser, Integer userId, List<String> errorList) {
		Users updateUser = new Users();
		//		log.info("Logged in user in ServiceImpl: " + loggedInUser);
		try {
			if(new Utils().isNotNull(userId)) {
				updateUser = userRepo.findByUserId(userId);
				if(new Utils().isNotNull(loggedInUser.getEmail()) && new Utils().isNotNull(loggedInUser.getFullName()) 
						&& new Utils().isNotNull(loggedInUser.getPhoneNumber())) {
					String previousName = updateUser.getFullName();
					String previousEmail = updateUser.getEmail();
					String previousPhone = updateUser.getPhoneNumber();

					updateUser.setFullName(loggedInUser.getFullName());
					updateUser.setEmail(loggedInUser.getEmail());
					updateUser.setPhoneNumber(loggedInUser.getPhoneNumber());
					userRepo.save(updateUser);
					mailService.updateMail(previousName,previousEmail,previousPhone,updateUser, errorList);
				}else {
					errorList.add("Please fill all required Fields");
				}
			}else {
				errorList.add("Session Logged out please Login again");
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add("Something went Wrong !!");
		}
		return updateUser;
	}

	@Override
	public Boolean userNameExits(String userName, List<String> errorList) {
		Users user = new Users();
		try {
			if(new Utils().isNotNull(userName)) {
				user = userRepo.findByUserName(userName);				
			}
			if(new Utils().isNotNull(user)) {
				if(user.getUserName().equals(userName)) {
					return true;	
				}else {
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
