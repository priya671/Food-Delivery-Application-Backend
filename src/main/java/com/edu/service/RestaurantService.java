package com.edu.service;

import java.util.List;


import javax.validation.Valid;

import com.edu.dao.Item;
import com.edu.dao.Restaurant;
import com.edu.error.GlobalException;

public interface RestaurantService{

	public Restaurant saveRestaurant(Restaurant restaurant);

	public List<Restaurant> getAllRestaurant();

	public List<Restaurant> deleteRestaurantById(Integer restid) throws GlobalException;

	public Restaurant updateRestaurantById(Integer restid, Restaurant restaurant) throws GlobalException;

	public Restaurant saveItemByRestIdi(@Valid Item item, Integer restid) throws GlobalException;


	public List<Restaurant> getAllRestaurantsearch(String searchkey);


	public Restaurant getRestaurantById(Integer restid);
	public Restaurant getRestaurantByEmail(String email, String password);

	public Integer getRestaurantByEmail(String email);

	public List<Item> viewOrdersByRestauranat(Integer id);


}
