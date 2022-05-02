/**
 * 
 */
package com.indoCoupon.test.modals;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer brand;
    private String couponCode;
    private String couponKey;
    private String couponKeyValue;
    private Integer couponValue;
    private Integer couponPrice;
    private String couponExpiryDate;	
    private Integer couponStatus;
        
    


	public CouponsModal(Integer brand, String couponCode, String couponKey, String couponKeyValue, Integer couponValue,
			Integer couponPrice, String couponExpiryDate, Integer couponStatus) {
		super();
		this.brand = brand;
		this.couponCode = couponCode;
		this.couponKey = couponKey;
		this.couponKeyValue = couponKeyValue;
		this.couponValue = couponValue;
		this.couponPrice = couponPrice;
		this.couponExpiryDate = couponExpiryDate;
		this.couponStatus = couponStatus;
	}

    
	@Override
	public String toString() {

		return "{\"" +(couponId != null ? "couponId\":\"" + couponId + "\" " : "")
				+(brand != null ? ",\"brand\":\"" + brand + "\" " : "")
				+(couponCode != null ? ",\"couponCode\":\"" + couponCode + "\" " : "")
				+(couponKey != null ? ",\"couponKey\":\"" + couponKey + "\" " : "")
				+(couponKeyValue != null ? ",\"couponKeyValue\":\"" + couponKeyValue + "\" " : "")
				+( couponValue != null ? ",\"couponValue\":\"" + couponValue + "\" " : "")
				+(couponPrice != null ? ",\"couponPrice\":\"" + couponPrice + "\" " : "")
				+(couponExpiryDate != null ? ",\"couponExpiryDate\":\"" + couponExpiryDate + "\" " : "")
				+(couponStatus != null ? ",\"couponStatus\":\"" + couponStatus + "\" " : "")
//				+ (Hibernate.isInitialized(couponsModals) && couponsModals != null ? ",\"couponsModals\":" + couponsModals   : "")
//				+ (Hibernate.isInitialized(cards) && cards != null ? ",\"cards\":" + cards   : "")
//				+(addedAt != null ? ",\"addedAt\":\"" + addedAt + "\" " : "")
				+ "}\t";
	}




    
    
    
}
