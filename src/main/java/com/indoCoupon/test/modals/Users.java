package com.indoCoupon.test.modals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shiva
 * Added on 17-Mar-2022
 * Package  com.indoCoupon.test.modals
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String  fullName;
	private String email;
	private String phoneNumber;
	private String userName;
	private String password;
	private Timestamp addedAt;
	private Integer role;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<CouponsModal> couponsModals = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<UserCards> cards = new ArrayList<>();

	//public AppUsers() {
	//	
	//}


	public Users(String fullName, String email, String phoneNumber, String userName, String password, Timestamp addedAt,
			Integer role) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.addedAt = addedAt;
		this.role = role;
	}

	
	@Override
	public String toString() {

		return "{\"" +(userId != null ? "userId\":\"" + userId + "\" " : "")
				+(fullName != null ? ",\"fullName\":\"" + fullName + "\" " : "")
				+(email != null ? ",\"email\":\"" + email + "\" " : "")
				+(role != null ? ",\"role\":\"" + role + "\" " : "")
				+(phoneNumber != null ? ",\"phoneNumber\":\"" + phoneNumber + "\" " : "")
				+(userName != null ? ",\"userName\":\"" + userName + "\" " : "")
				+(password != null ? ",\"password\":\"" + password + "\" " : "")
				+ (Hibernate.isInitialized(couponsModals) && couponsModals != null ? ",\"couponsModals\":" + couponsModals   : "")
				+ (Hibernate.isInitialized(cards) && cards != null ? ",\"cards\":" + cards   : "")
				+(addedAt != null ? ",\"addedAt\":\"" + addedAt + "\" " : "")
				+ "}\t";
	}
	
}
