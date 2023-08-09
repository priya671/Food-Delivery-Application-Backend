package com.edu.repository;

import java.util.List;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.dao.Orders;

@Repository
public interface OrderREpository extends JpaRepository<Orders, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "insert into orders(status,customerid,restid,itemid,food_cart_id,quantity) values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	void saveOrder(String status,Integer custid, Integer restid, Integer itemid, Integer cartid,Integer quantity);
	
	@Query(value = " select * from orders where restid=?1 and status='paid'",nativeQuery = true)
	List<Orders> getOrderByrestId(Integer restID);
	
	@Query(value = " select * from orders where customerid=?1",nativeQuery = true)
	List<Orders> getOrderByCustomerID(Integer id);

	@Query(value = " select * from orders where customerid=? and status='paid'",nativeQuery = true)
	List<Orders> getOrderByCustomerIdAndStatuPaid(int custId);
	
	@Query(value = " select * from orders where customerid=? and status='unpaid'",nativeQuery = true)
	List<Orders> getOrderByCustomerIdAndStatuUnpaid(int custId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from orders where restid=?1",nativeQuery = true)
	void deleteOrderByRestId(Integer restId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from orders where customerid=?1",nativeQuery = true)
	void deleteOrderByCustId(Integer custId);
	
	
	@Query(value = "select * from orders where customerid=?1 and itemid=?2 and food_cart_id=?3 and status='unpaid' and restid=?4",nativeQuery = true)
	public Orders isOrderExist(Integer custId,Integer itemId,Integer cartid,Integer restId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from orders where customerid=?1 and status='paid'",nativeQuery = true)
	public void deleteOrderHistory(Integer custId);
	

}
