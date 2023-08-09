package com.edu.service;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.stereotype.Service;

import com.edu.dao.Orders;

@Service
public interface OrderService {
	public void saveOrder(Integer custid, Integer restid, Integer itemid, Integer cartid,Integer intitalQuantity);
	
	public List<Orders> getORderbyRestId(Integer id);

	public  List<Orders> getOrderByCustomerID(Integer id) ;

	public List<Orders> getOrderByCustomerIdAndStatusUnpaid(Integer custId);

	public Object updateOrderStatus(Integer id);

	public List<Orders> getOrderByCustomerIdAndStatusPaid(Integer custId);

	public String deleteOrderById(Integer orderId);

	public String deleteOrderhistory(Integer custId);
}
