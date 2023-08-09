package com.edu.controller;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.dao.Orders;
import com.edu.service.OrderService;
import com.edu.service.OrderServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/saveOrder/customerid/{custid}/restid/{restid}/itemid/{itemid}/cartid/{cartid}/quantity/{intitalQuantity}")
	public String saveOrder(@PathVariable Integer custid, @PathVariable Integer restid, @PathVariable Integer itemid,
			@PathVariable Integer cartid,@PathVariable("intitalQuantity")Integer intitalQuantity) {
		 orderService.saveOrder(custid, restid, itemid, cartid,intitalQuantity);
		 return "hey";		
	}
	
	@GetMapping("getOrderByRestId/{id}")
	public List<Orders> getOrderByRestId(@PathVariable("id") Integer id) {
		return orderService.getORderbyRestId(id);
	}
	
	@GetMapping("getOrderByCustomerId/{id}")
	public List<Orders> getOrderByCustomerID(@PathVariable("id") Integer id){
		return orderService.getOrderByCustomerID(id);
		
	}
	
	@GetMapping("/getOrderByCustomerIdAndStatusUnpaid/{custId}")
	public List<Orders> getOrderByCustomerIdAndStatusUnpaid(@PathVariable("custId") Integer custId){
		return orderService.getOrderByCustomerIdAndStatusUnpaid(custId);
	}
	
	@GetMapping("/getOrderByCustomerIdAndStatusPaid/{custId}")
	public List<Orders> getOrderByCustomerIdAndStatusPaid(@PathVariable("custId") Integer custId){
		return orderService.getOrderByCustomerIdAndStatusPaid(custId);
	}
	
	@GetMapping("/updateOrderStatus/{id}")
	public Object  updateOrderStatus(@PathVariable("id") Integer id) {
		return orderService.updateOrderStatus(id);
	}
	
	@DeleteMapping("/deleteOrderById/{orderId}")
	public String deleteOrderById(@PathVariable("orderId") Integer orderId) {	
		return orderService.deleteOrderById(orderId);
	}
	
	@DeleteMapping("deleteOrderhistory/{custID}")
	public String deleteOrderhistory(@PathVariable("custID") Integer custId) {
		return orderService.deleteOrderhistory(custId);
	}

}
