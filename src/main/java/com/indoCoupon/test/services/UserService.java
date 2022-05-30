/**
 * 
 */
package com.indoCoupon.test.services;

import java.util.List;

import com.indoCoupon.test.modals.Users;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.services
 */
public interface UserService {

	public Users saveUser(Users user,List<String> errorList);
	public Users getUserNameById(Integer userId,List<String> errorList);
	public void deleteUser(Integer user,List<String> errorList);
	public Users validateUser(Users user,List<String> errorList);
	public Users updateUser(Users loggedInUser,Integer userId,List<String> errorList);
	public Boolean userNameExits(String userName, List<String> errorList);
	public void assignCouponToUser(Integer userId,Integer couponId,List<String> errorList);
	public List<Users> findAllUsers(List<String> errorList);
	public Boolean SendForgotPasswordMail(String username, List<String> errorList);
	public Users updateUserPassword(Integer userId, String newPassword,List<String> errorList);

}
