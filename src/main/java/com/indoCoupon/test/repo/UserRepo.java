/**
 * 
 */
package com.indoCoupon.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indoCoupon.test.modals.Users;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.repo
 */
public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByUserName(String userName);
	Users findByUserId(Integer id);
}
