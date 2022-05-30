/**
 * 
 */
package com.indoCoupon.test.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.indoCoupon.test.modals.Users;
import com.indoCoupon.test.repo.CouponRepo;
import com.indoCoupon.test.repo.UserRepo;
import com.indoCoupon.test.modals.CouponsModal;
import com.indoCoupon.test.services.CouponService;
import com.indoCoupon.test.utils.Constants;
import com.indoCoupon.test.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 * Added on 28-Apr-2022
 * Package  com.indoCoupon.test.servicesImpl
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponRepo couponRepo;
	
	@Autowired
	UserRepo userRepo;	
	
	@Override
	public void saveCoupon(CouponsModal coupon, List<String> errorList) {
		log.info("SAVE COupon : " + coupon);
		try {
			if(new Utils().isNotNull(coupon)) {
				coupon.setCouponStatus(Constants.couponStatus.ACTIVE);
				if(new Utils().isNotNull(coupon.getCouponKey())) {
					coupon.setCouponKey(coupon.getCouponKey());
				}else {
					coupon.setCouponKey("");
				}
				if(new Utils().isNotNull(coupon.getCouponKeyValue())) {
					coupon.setCouponKeyValue(coupon.getCouponKeyValue());
				}else {
					coupon.setCouponKeyValue("");
				}
				couponRepo.save(coupon);
			}else {
				errorList.add("Please Provide all Required Details !!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add("Something went Wrong!! Please try again later");
		}		
	}

	@Override
	public List<CouponsModal> getActiveCoupons(List<String> errorList) {
			
		List<CouponsModal> coupons = new ArrayList<>();
		try {
			coupons = (List<CouponsModal>) couponRepo.findAllActiveCoupons(Sort.by(Sort.Direction.DESC, "couponId"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coupons;
	}
	
	@Override
	public void deleteCoupon(Integer couponId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CouponsModal> getAllCoupons(List<String> errorList) {
		List<CouponsModal> coupons = new ArrayList<>();
		try {
			coupons = (List<CouponsModal>) couponRepo.findAll(Sort.by(Sort.Direction.DESC, "couponId"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coupons;
	}

	@Override
	public CouponsModal getCouponByID(Integer couponId,List<String> errorList) {
		CouponsModal couponObj = new CouponsModal();
		try {
			if(new Utils().isNotNull(couponId)) {
			couponObj = couponRepo.getCouponByCouponId(couponId);
			}else {
				errorList.add("No Coupon Found !");
			}
			} catch (Exception e) {
		}
		return couponObj;
	}
	
	@Override
	public List<CouponsModal> getCouponByBrand(Integer brand, List<String> errorList) {
		List<CouponsModal> couponsByBrand = new ArrayList<>();
		try {
			if(new Utils().isNotNull(brand)) {
				couponsByBrand= couponRepo.findByBrandEquals(brand);
			}else {
				errorList.add("Brand not found in the library");
			}		
		} catch (Exception e) {
			e.printStackTrace();
			errorList.add("Something went Wrong");
		}	
		return couponsByBrand;
	}

	@Override
	public List<CouponsModal> getCouponsByUser(Users user, Integer userId,List<String> errorList) {
		List<CouponsModal> coupons = new ArrayList<CouponsModal>();
		try {
			if(new Utils().isNotNull(userId) && user == null ){
				user = userRepo.findByUserId(userId);
			}
			
			if(new Utils().isNotNull(user))	
				coupons = couponRepo.findByUser(user);

		} catch (Exception e) {
			e.printStackTrace();
			errorList.add("Something Went Wrong !");
		}
		return coupons;
	}

	@Override
	public List<CouponsModal> getSoldCoupons(List<String> errorList) {

		List<CouponsModal> coupons = new ArrayList<>();
		try {
			coupons = (List<CouponsModal>) couponRepo.findAllSoldCoupons(Sort.by(Sort.Direction.DESC, "couponId"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coupons;
	}

@Override
public CouponsModal updateCoupon(CouponsModal newCoupon, Integer couponId, List<String> errorList) {
		CouponsModal updateCoupon = new CouponsModal();
//		log.info("Servicee Impl New Coupon: " + newCoupon);
//		log.info("Servicee Impl NEw CouponID: " + couponId);
		
		try {
			if(new Utils().isNotNull(couponId)) {
				updateCoupon = couponRepo.getById(couponId);
				
				if(new Utils().isNotNull(updateCoupon) && new Utils().isNotNull(newCoupon)) {

					updateCoupon.setBrand(newCoupon.getBrand());
					updateCoupon.setCouponCode(newCoupon.getCouponCode());
					updateCoupon.setCouponKey(newCoupon.getCouponKey());
					updateCoupon.setCouponKeyValue(newCoupon.getCouponKeyValue());
					updateCoupon.setCouponPrice(newCoupon.getCouponPrice());
					updateCoupon.setCouponValue(newCoupon.getCouponValue());
					updateCoupon.setCouponExpiryDate(newCoupon.getCouponExpiryDate());
					updateCoupon.setCouponStatus(newCoupon.getCouponStatus());
					couponRepo.save(updateCoupon);
					
				}else {
					errorList.add("Coupon Not Found");
				}			
			}else {
				errorList.add("Coupon Not Found in Database");
			}
		} catch (Exception e) {
			errorList.add("Something Went wrong");
			e.printStackTrace();
		}	
	return updateCoupon;
}
	
}
