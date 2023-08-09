package com.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.Customer;
import com.edu.dao.FoodCart;
import com.edu.dao.Item;
import com.edu.error.GlobalException;
import com.edu.repository.CustomerRepository;
import com.edu.repository.FoodCartRepository;
import com.edu.repository.ItemRepository;

@Service
public class FoodCartServiceImpl implements FoodCartService {

	@Autowired
	FoodCartRepository cartRepo;

	@Autowired
	ItemRepository itemRepo;

	@Autowired
	CustomerRepository custrepo;

	@Override
	public FoodCart savecart(FoodCart foodCart) {
		foodCart.setPaymentStatus("unpaid");

		return cartRepo.save(foodCart);
	}

	@Override
	public FoodCart getCartById(Integer id) {

		FoodCart cart = cartRepo.getCartByvalidItem(id);
		System.out.println(cart.getItemList());
		return cart;
	}

	@Override
	public List<FoodCart> getAllCart() {

		return cartRepo.getAllFoodCart();
	}

	@Override
	public List<Item> getItemByCartId(Integer id) throws GlobalException {

		FoodCart cart = cartRepo.findById(id).get();
		if (cart != null) {
			List<Item> itemList = cart.getItemList();
			return itemList;
		} else
			throw new GlobalException("cart with id not exist");

	}

	@Override
	public FoodCart updateCartByItem(Integer id, Item item) throws GlobalException {

		Optional<FoodCart> existingcart = cartRepo.findById(id);
		if (existingcart.isPresent()) {
			FoodCart cart = existingcart.get();
			List<Item> itemList = cart.getItemList();

			itemList.add(itemRepo.save(item));
			int itemId = item.getItemid();
			Item assigingitem = itemRepo.findById(itemId).get();
			assigingitem.assignCart(cart);
			return cartRepo.save(cart);

		} else {
			throw new GlobalException("cart not exist");
		}

	}

	@Override
	public FoodCart updateCartbyCustomer(Integer id, Customer cust) {

		FoodCart cart = cartRepo.findById(id).get();
		Integer custId = cust.getCustomerid();
		cart.setCust(cust);

		return cartRepo.save(cart);

	}

	@Override
	public FoodCart getCartBYEmail(String email) throws GlobalException {

		Customer customer = custrepo.getCustomerByEmail1(email);
		if (customer == null) {
			throw new GlobalException("customer with emailID: " + email + " not exist");

		} else {
			int custId = customer.getCustomerid();
			FoodCart cart = cartRepo.getCartByCustomerId(custId);
			return cart;
		}
	}

	@Override
	public FoodCart updatePaymentStatus(Integer id) {

		Optional<FoodCart> existingcart = cartRepo.findById(id);
		if (existingcart.isPresent()) {
			FoodCart cart = cartRepo.findById(id).get();
			cart.setPaymentStatus("paid");
			return cartRepo.save(cart);
		} else {
			return null;
		}
	}

}
