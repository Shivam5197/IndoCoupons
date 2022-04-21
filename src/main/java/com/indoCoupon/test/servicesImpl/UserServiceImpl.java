/**
 * 
 */
package com.indoCoupon.test.servicesImpl;

import java.sql.Timestamp;
import java.util.List;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.indoCoupon.test.modals.AppUsers;
import com.indoCoupon.test.repo.CouponRepo;
import com.indoCoupon.test.repo.UserRepo;
import com.indoCoupon.test.services.UserService;
import com.indoCoupon.test.utils.Constants;
import com.indoCoupon.test.utils.Utils;

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

	@Override
	public AppUsers saveUser(AppUsers user, List<String> errorList) {
		AppUsers userToSave = null;	
		try {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setRole(Constants.userRole.CUSTOMER);
			user.setAddedAt(new java.sql.Timestamp(System.currentTimeMillis()));
			userToSave =userRepo.save(user);
		}catch(Exception e) {
			e.printStackTrace();
			errorList.add("Something went Wrong! While Create new User please try again ! ");
		}
		return userToSave;
	}

	@Override
	public AppUsers getUserNameById(Integer userId, List<String> errorList) {

		return null;
	}

	@Override
	public void deleteUser(AppUsers user, List<String> errorList) {
		// TODO Auto-generated method stub

	}

	@Override
	public AppUsers validateUser(AppUsers user, List<String> errorList) {

		AppUsers loginUser = null;
		try {
			if(new Utils().isNotNull(user.getUserName()) && new Utils().isNotNull(user.getPassword()) ) {

				loginUser = userRepo.findByUserName(user.getUserName());

				if(new Utils().isNotNull(loginUser)) {
					if(new BCryptPasswordEncoder().matches(user.getPassword(), loginUser.getPassword())) {
						log.info("Logged in Service Imple: " + loginUser);
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
		log.info("Logged in Service Imple: " + loginUser);
		return loginUser;
	}

}
