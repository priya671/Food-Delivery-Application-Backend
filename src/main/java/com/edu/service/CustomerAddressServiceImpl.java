package com.edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.Customer;
import com.edu.dao.CustomerAddress;
import com.edu.error.GlobalException;
import com.edu.repository.CustomerAddressRepository;
import com.edu.repository.CustomerRepository;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerAddress saveCustomerAddress(CustomerAddress customeraddress) {

		return customerAddressRepository.save(customeraddress);
	}

	@Override
	public List<CustomerAddress> getAllCustomerAddress() {

		return customerAddressRepository.findAll();
	}

	@Override
	public List<CustomerAddress> deleteCustomerAddressById(Integer addressid) throws GlobalException {

		Optional<CustomerAddress> cob = customerAddressRepository.findById(addressid);
		if (!cob.isPresent()) {
			throw new GlobalException("Customer Address id= " + addressid + " does not exist");
		}
		customerAddressRepository.deleteById(addressid);
		return customerAddressRepository.findAll();

	}

	@Override
	public CustomerAddress updateCustomerAddressById(Integer addressid, CustomerAddress customeraddress)
			throws GlobalException {

		CustomerAddress customer = null;
		Optional<CustomerAddress> cob = customerAddressRepository.findById(addressid);
		if (!cob.isPresent()) {
			throw new GlobalException("Customer Address id=" + addressid + " does not exist");
		} else {
			customer = customerAddressRepository.findById(addressid).get();
			customer.setArea(customeraddress.getArea());
			customer.setCity(customeraddress.getCity());
			customer.setCountry(customeraddress.getCountry());
			customer.setPincode(customeraddress.getPincode());
			customer.setState(customeraddress.getState());
		}
		return customerAddressRepository.save(customer);
	}

	@Override
	public CustomerAddress customerAssigncustomeraddress(Integer custid, Integer addid) {

		CustomerAddress cuadd = customerAddressRepository.findById(addid).get();
		Customer cus = customerRepository.findById(custid).get();
		cuadd.customerAssigncustomeraddress(cus);
		return customerAddressRepository.save(cuadd);
	}

	@Override
	public CustomerAddress getCustomerAddById(Integer id) {

		return customerAddressRepository.findById(id).get();
	}

}
