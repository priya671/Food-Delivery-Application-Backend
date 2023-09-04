package com.edu.dao;


import javax.persistence.*;


@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderid;
	
	@Column(length = 5)
	private String Flag;

	@Column(length = 6)
	private String status;
	
	@Column(length = 30)
	private String orderDate;
	
	@Column
	private Integer quantity;

//	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "restid", referencedColumnName = "restid")
	Restaurant res;

//	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "itemid", referencedColumnName = "itemid")
	Item item;

//	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "customerid", referencedColumnName = "customerid")
	Customer cus;

//	@JsonIgnore
	

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Orders(Integer orderid, String flag, String status, String orderDate, Integer quantity, Restaurant res,
		Item item, Customer cus) {
	super();
	this.orderid = orderid;
	Flag = flag;
	this.status = status;
	this.orderDate = orderDate;
	this.quantity = quantity;
	this.res = res;
	this.item = item;
	this.cus = cus;
}
	
	public String isFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Restaurant getRes() {
		return res;
	}

	public void setRes(Restaurant res) {
		this.res = res;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Customer getCus() {
		return cus;
	}

	public void setCus(Customer cus) {
		this.cus = cus;
	}



	@Override
	public String toString() {
		return "Orders [orderid=" + orderid + ", Flag=" + Flag + ", status=" + status + ", orderDate=" + orderDate
				+ ", quantity=" + quantity + ", res=" + res + ", item=" + item + ", cus=" + cus + "]";
	}
	
	
}
