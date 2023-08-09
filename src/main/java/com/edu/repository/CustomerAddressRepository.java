package com.edu.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.dao.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>{

	
	@Transactional
	@Modifying
	@Query(value = "delete from customer_address where customerid=?1",nativeQuery = true)
	public void  deleteCustomerAddbyCustId(Integer custId);
}
