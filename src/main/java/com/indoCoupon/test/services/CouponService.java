/**
 * 
 */
package com.indoCoupon.test.services;

import java.util.List;

import com.indoCoupon.test.modals.CouponsModal;

/**
 * @author shiva
 * Added on 28-Apr-2022
 * Package  com.indoCoupon.test.services
 */


public interface CouponService {

	public void saveCoupon(CouponsModal coupon , List<String> errorList);
	public List<CouponsModal> getActiveCoupons(List<String> errorList);
	public void deleteCoupon(Integer couponId);
	public List<CouponsModal> getAllCoupons(List<String> errorList);
	public CouponsModal getCouponByID(Integer couponId,List<String> errorList);
	public List<CouponsModal> getCouponByBrand(Integer brand,List<String> errorList);
	
}
