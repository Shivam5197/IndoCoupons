/**
 * 
 */
package com.indoCoupon.test.modals;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.modals
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponsModal {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer couponId;
    private String couponCode;
    private String pin;
    private Timestamp couponExpiryDate;	
    private Integer couponStatus;
    

	public CouponsModal(String couponCode, String pin, Timestamp couponExpiryDate, Integer couponStatus) {
		super();
		this.couponCode = couponCode;
		this.pin = pin;
		this.couponExpiryDate = couponExpiryDate;
		this.couponStatus = couponStatus;
	}
//	public String getCouponCode() {
//		return couponCode;
//	}
//	public void setCouponCode(String couponCode) {
//		this.couponCode = couponCode;
//	}
//	public String getPin() {
//		return pin;
//	}
//	public void setPin(String pin) {
//		this.pin = pin;
//	}
//	public Timestamp getCouponExpiryDate() {
//		return couponExpiryDate;
//	}
//	public void setCouponExpiryDate(Timestamp couponExpiryDate) {
//		this.couponExpiryDate = couponExpiryDate;
//	}
//	public Integer getCouponStatus() {
//		return couponStatus;
//	}
//	public void setCouponStatus(Integer couponStatus) {
//		this.couponStatus = couponStatus;
//	}
    
    
    
    
    
}
