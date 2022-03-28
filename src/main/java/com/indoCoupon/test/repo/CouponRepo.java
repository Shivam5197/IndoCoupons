/**
 * 
 */
package com.indoCoupon.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indoCoupon.test.modals.CouponsModal;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.repo
 */
public interface CouponRepo extends JpaRepository<CouponsModal, Integer> {

	CouponsModal getCouponByCouponId(Integer id);
	
}
