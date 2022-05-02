/**
 * 
 */
package com.indoCoupon.test.repo;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.indoCoupon.test.modals.CouponsModal;
import com.indoCoupon.test.utils.Constants;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.repo
 */
public interface CouponRepo extends JpaRepository<CouponsModal, Integer> {

	CouponsModal getCouponByCouponId(Integer id);
	
	@Query("SELECT c FROM CouponsModal c WHERE c.couponStatus = "+Constants.couponStatus.ACTIVE+"")
	Collection<CouponsModal> findAllActiveCoupons(Sort sort); 
	
}
