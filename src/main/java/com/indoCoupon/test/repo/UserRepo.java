/**
 * 
 */
package com.indoCoupon.test.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.indoCoupon.test.modals.Users;
import com.indoCoupon.test.utils.Constants;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.repo
 */
public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByUserName(String userName);
	Users findByUserId(Integer id);

	@Query("SELECT u FROM Users u WHERE u.role = "+Constants.userRole.CUSTOMER+"")
	List<Users> findAllCustomerRoleUsers(Sort sort);
}
