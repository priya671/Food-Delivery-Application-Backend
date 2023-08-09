package com.edu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.Customer;
import com.edu.dao.CustomerAddress;
import com.edu.dao.Item;
import com.edu.dao.Restaurant;
import com.edu.error.GlobalException;
import com.edu.repository.CustomerAddressRepository;
import com.edu.repository.CustomerRepository;
import com.edu.repository.FoodCartRepository;
import com.edu.repository.OrderREpository;
import com.edu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAddressRepository cusAddRepo;
	
	@Autowired
	private OrderREpository orderRepo;
	
	@Autowired
	private FoodCartRepository cartRepo;

	@Override
	public Customer saveCustomer(Customer customer) {

		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {

		return customerRepository.findAll();
	}

	@Override
	public List<Customer> deleteCustomerById(Integer customerid) {

		
		orderRepo.deleteOrderByCustId(customerid);
		cartRepo.deletecartByCustId(customerid);
		cusAddRepo.deleteCustomerAddbyCustId(customerid);
		customerRepository.deleteById(customerid);
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomerById(Integer customerid, @Valid Customer customer) {

		Customer cus = customerRepository.findById(customerid).get();
		if (cus != null) {
			cus.setCustomername(customer.getCustomername());
			cus.setCustomermobilenumber(customer.getCustomermobilenumber());
			cus.setCustomeremail(customer.getCustomeremail());
			cus.setPassword(customer.getPassword());
			customerRepository.save(cus);
		}
		return cus;
	}

	@Override
	public Customer getCustomerByEmail(String email, String password) {

		Customer cust = customerRepository.getCustomerByEmail(email, password);
		System.out.println(cust);
		return cust;
	}

	@Override
	public Customer getCustomerById(Integer customerid) {

		return customerRepository.findById(customerid).get();
	}

	@Override
	public Customer updateCustomerAddByid(Integer customerid, CustomerAddress cob) {

		Optional<Customer> cus = customerRepository.findById(customerid);

		if (cus.isPresent()) {
			Customer customer = customerRepository.findById(customerid).get();
			Set<CustomerAddress> add = customer.getCob();
			if (add.isEmpty()) {
				Set<CustomerAddress> newaddress = new HashSet<>();
				newaddress.add(cusAddRepo.save(cob));
				int id = cob.getAddressid();
				CustomerAddress newadd = cusAddRepo.findById(id).get();
				Customer dob = customerRepository.findById(id).get();
				newadd.addressAssignedCustomer(dob);
				customer.setCob(newaddress);
				return customerRepository.save(customer);
			} else {
				add.add(cusAddRepo.save(cob));
				int id = cob.getAddressid();
				CustomerAddress newadd = cusAddRepo.findById(id).get();
				Customer dob = customerRepository.findById(id).get();
				newadd.addressAssignedCustomer(dob);

				return customerRepository.save(dob);
			}
		} else {
			return null;
		}
	}

	@Override
	public Customer getCustomerByEmail(String email) {

		return customerRepository.getCustomerByEmail1(email);

	}

	@Override
	public Set<CustomerAddress> getCustomerAddByEmail(String email) {
		Customer customer = customerRepository.getCustomerByEmail1(email);

		Set<CustomerAddress> cutomerAddList = customer.getCob();
		return cutomerAddList;
	}

}
