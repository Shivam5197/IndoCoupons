/**
 * 
 */
package com.indoCoupon.test.services;

import java.util.List;

import com.indoCoupon.test.modals.AppUsers;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.services
 */
public interface UserService {

	public AppUsers saveUser(AppUsers user,List<String> errorList);
	public AppUsers getUserNameById(Integer userId,List<String> errorList);
	public void deleteUser(AppUsers user,List<String> errorList);
	public AppUsers validateUser(AppUsers user,List<String> errorList);
	
	
}
