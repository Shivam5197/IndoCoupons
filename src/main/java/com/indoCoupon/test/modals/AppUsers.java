/**
 * 
 */
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
public class AppUsers {

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

//public AppUsers() {
//	
//}


public AppUsers(String fullName, String email, String phoneNumber, String userName, String password, Timestamp addedAt,
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

//public Integer getUserId() {
//	return userId;
//}
//
//public void setUserId(Integer userId) {
//	this.userId = userId;
//}
//
//public String getFullName() {
//	return fullName;
//}
//
//public void setFullName(String fullName) {
//	this.fullName = fullName;
//}
//
//public String getEmail() {
//	return email;
//}
//
//public void setEmail(String email) {
//	this.email = email;
//}
//
//public String getPhoneNumber() {
//	return phoneNumber;
//}
//
//public void setPhoneNumber(String phoneNumber) {
//	this.phoneNumber = phoneNumber;
//}
//
//public String getUserName() {
//	return userName;
//}
//
//public void setUserName(String userName) {
//	this.userName = userName;
//}
//
//public String getPassword() {
//	return password;
//}
//
//public void setPassword(String password) {
//	this.password = password;
//}
//
//public Timestamp getAddedAt() {
//	return addedAt;
//}
//
//public void setAddedAt(Timestamp addedAt) {
//	this.addedAt = addedAt;
//}
//
//public Integer getRole() {
//	return role;
//}
//
//public void setRole(Integer role) {
//	this.role = role;
//}
//
//public Collection<CouponsModal> getCouponsModals() {
//	return couponsModals;
//}
//
//public void setCouponsModals(Collection<CouponsModal> couponsModals) {
//	this.couponsModals = couponsModals;
//}
//
//@Override
//public String toString() {
//	return "{\"userId\":\"" + userId + "\", \"fullName\":\"" + fullName + "\", \"email\":\"" + email
//			+ "\", \"phoneNumber\":\"" + phoneNumber + "\", \"userName\":\"" + userName + "\", \"password\":\""
//			+ password + "\", \"addedAt\":\"" + addedAt + "\", \"role\":\"" + role + "\", \"couponsModals\":\""
//			+ couponsModals + "\"}";
//}

}
