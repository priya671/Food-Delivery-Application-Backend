package com.edu.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.Orders;
import com.edu.repository.OrderREpository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderREpository orderRepo;

	@Override
	public void saveOrder(Integer custid, Integer restid, Integer itemid, Integer cartid,Integer intitalQuantity) {
		Orders existingorder =orderRepo.isOrderExist(custid, itemid, cartid, restid);			
		if(existingorder==null) {			
			orderRepo.saveOrder("unpaid",custid, restid, itemid, cartid,intitalQuantity);
		}
		else {			
			existingorder.setQuantity(intitalQuantity);
			orderRepo.save(existingorder);
		}
		

	}

	@Override
	public List<Orders> getORderbyRestId(Integer id) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByrestId(id);
	}

	@Override
	public List<Orders> getOrderByCustomerID(Integer id) {
		// TODO Auto-generated method stub
		return orderRepo.getOrderByCustomerID(id);
	}

	@Override
	public List<Orders> getOrderByCustomerIdAndStatusUnpaid(Integer custId) {
		// TODO Auto-generated method stub
		System.out.println(orderRepo.getOrderByCustomerIdAndStatuUnpaid(custId));
		return orderRepo.getOrderByCustomerIdAndStatuUnpaid(custId);
	}

	@Override
	public List<Orders> getOrderByCustomerIdAndStatusPaid(Integer custId) {
		// TODO Auto-generated method stub
		System.out.println(orderRepo.getOrderByCustomerIdAndStatuPaid(custId));
		
		return orderRepo.getOrderByCustomerIdAndStatuPaid(custId);
	}
	
	@Override
	public Object updateOrderStatus(Integer id) {
		// TODO Auto-generated method stub
		Orders order = orderRepo.findById(id).get();
		order.setStatus("paid");
		 java.util.Date date = new java.util.Date();
		  String strDateFormat = "MM/dd/yyyy 	hh:mm:ss a";
		 DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		 String formattedDate= dateFormat.format(date);
		order.setOrderDate(formattedDate);
		return orderRepo.save(order);
		
	}

	@Override
	public String deleteOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		 orderRepo.deleteById(orderId);
		 return "orderDeleted";
	}

	@Override
	public String deleteOrderhistory(Integer custId) {
		// TODO Auto-generated method stub
		 orderRepo.deleteOrderHistory(custId);
		 return "deleted";
		 }

	
}
