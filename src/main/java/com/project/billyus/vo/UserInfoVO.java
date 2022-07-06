package com.project.billyus.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//---------------------------------------------------------
// public class UserInfoVO 유저 정보
//---------------------------------------------------------
@Component(value="userInfoVO")
public class UserInfoVO {

	//---------------------------------------------------------
	// 선언부
	//---------------------------------------------------------
	private String 	id;
	private String 	name;
	private String 	pwd;
	private String 	email;
	private String 	gender;
	private Date 	join_date;
	private Date 	last_join;
	private String Address;
	private String sample4_postcode;
	private String sample4_roadAddress;
	private String sample4_jibunAddress;
	private String sample4_detailAddress;
	private String sample4_extraAddress;
	private String emailAccess;
	
	//----------------------------------------------------------------
	// getter setter
	//----------------------------------------------------------------
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public Date getLast_join() {
		return last_join;
	}
	public void setLast_join(Date last_join) {
		this.last_join = last_join;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getSample4_postcode() {
		return sample4_postcode;
	}
	public void setSample4_postcode(String sample4_postcode) {
		this.sample4_postcode = sample4_postcode;
	}
	public String getSample4_roadAddress() {
		return sample4_roadAddress;
	}
	public void setSample4_roadAddress(String sample4_roadAddress) {
		this.sample4_roadAddress = sample4_roadAddress;
	}
	public String getSample4_jibunAddress() {
		return sample4_jibunAddress;
	}
	public void setSample4_jibunAddress(String sample4_jibunAddress) {
		this.sample4_jibunAddress = sample4_jibunAddress;
	}
	public String getSample4_detailAddress() {
		return sample4_detailAddress;
	}
	public void setSample4_detailAddress(String sample4_detailAddress) {
		this.sample4_detailAddress = sample4_detailAddress;
	}
	public String getSample4_extraAddress() {
		return sample4_extraAddress;
	}
	public void setSample4_extraAddress(String sample4_extraAddress) {
		this.sample4_extraAddress = sample4_extraAddress;
	}
	public String getEmailAccess() {
		return emailAccess;
	}
	public void setEmailAccess(String emailAccess) {
		this.emailAccess = emailAccess;
	}
	
	//----------------------------------------------------------------
	// toString
	//----------------------------------------------------------------
	@Override
	public String toString() {
		return "UserInfoVO [id=" + id + ", name=" + name + ", pwd=" + pwd + ", email=" + email + ", gender=" + gender
				+ ", join_date=" + join_date + ", last_join=" + last_join + ", Address=" + Address
				+ ", sample4_postcode=" + sample4_postcode + ", sample4_roadAddress=" + sample4_roadAddress
				+ ", sample4_jibunAddress=" + sample4_jibunAddress + ", sample4_detailAddress=" + sample4_detailAddress
				+ ", sample4_extraAddress=" + sample4_extraAddress + ", emailAccess=" + emailAccess + "]";
	}

	
	
	
	
	
	
}
