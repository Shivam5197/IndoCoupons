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
 * Added on 28-Apr-2022
 * Package  com.indoCoupon.test.modals
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCards {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cardId;
	private String cardNumber;
	private Integer endMonth;
	private Integer endYear;
	private String nameOnCard;
		
}
