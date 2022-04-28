/**
 * 
 */
package com.indoCoupon.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indoCoupon.test.modals.AppUsers;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.repo
 */
public interface UserRepo extends JpaRepository<AppUsers, Integer> {

	AppUsers findByUserName(String userName);
	AppUsers findByUserId(Integer id);
}
