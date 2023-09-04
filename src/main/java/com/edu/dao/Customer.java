package com.edu.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerid;

	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{2,19}$")
	@Column(nullable = false, length = 20)
	private String customername;

	@Pattern(regexp = "^[5-9]\\d{0,9}$")
	@Column(length = 10, nullable = false)
	private String customermobilenumber;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "invalid Email")
	@Column(unique = true, length = 50)
	private String customeremail;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "enter proper password")
	@Column(length = 20)
	private String customerpassword;

	
	@OneToMany(mappedBy = "cust")
	Set<CustomerAddress> cob = new HashSet<CustomerAddress>();

	public Set<CustomerAddress> getCob() {
		return cob;
	}

	public void setCob(Set<CustomerAddress> cob) {
		this.cob = cob;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String customername, String customermobilenumber, String customeremail, String customerpassword) {

		super();
		this.customername = customername;
		this.customermobilenumber = customermobilenumber;
		this.customeremail = customeremail;
		this.customerpassword = customerpassword;

	}

	public String getCustomername() {
		return customername;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomermobilenumber() {
		return customermobilenumber;
	}

	public void setCustomermobilenumber(String customermobilenumber) {
		this.customermobilenumber = customermobilenumber;
	}

	public String getCustomeremail() {
		return customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public String getPassword() {
		return customerpassword;
	}

	public void setPassword(String customerpassword) {
		this.customerpassword = customerpassword;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername=" + customername + ", customermobilenumber="
				+ customermobilenumber + ", customeremail=" + customeremail + ", password=" + customerpassword + ", cob=" + cob
				+ "]";
	}

//	public void assignCartToCustomer(FoodCart cart2) {
//		// TODO Auto-generated method stub
//		this.cart = cart2;
//	}

}
