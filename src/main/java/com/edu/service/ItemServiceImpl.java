package com.edu.service;

import java.util.ArrayList;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.Item;
import com.edu.dao.Restaurant;
import com.edu.error.GlobalException;
import com.edu.repository.ItemRepository;
import com.edu.repository.RestaurantRepository;


@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	
	@Override
	public Item saveItem(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}



	@Override
	public Item updateItemById(Integer itemid, Item item)
			throws GlobalException {
		// TODO Auto-generated method stub
		Item ite = null;
		Optional<Item> cob = itemRepository.findById(itemid);
		if (!cob.isPresent()) {
			throw new GlobalException("Item id does not exist");
		} else {
			ite = itemRepository.findById(itemid).get();
			ite.setItemname(item.getItemname());
			ite.setItemstatus(item.getItemstatus());
			ite.setItemcost(item.getItemcost());
			ite.setItemimage(item.getItemimage());
		}
		return itemRepository.save(ite);
	}

	
	@Override
	public Item itemAssignedRestaurant(Integer itemid, Integer restid) {
		// TODO Auto-generated method stub
		Item eob=itemRepository.findById(itemid).get();
		Restaurant dob= restaurantRepository.findById(restid).get();
		 eob.itemAssignedRestaurantAddress(dob);
		 return itemRepository.save(eob);
	}

	@Override
	public List<Item> getItemByRestId(Integer addressid) {
		// TODO Auto-generated method stub
		return itemRepository.getItemByRestId(addressid);
	}


	
	@Override
	public Item saveItemByRestId(Item item, Integer restid) throws GlobalException {
		// TODO Auto-generated method stub
		Optional<Restaurant> res = restaurantRepository.findById(restid);
		
		if(!res.isPresent()) {
			throw new GlobalException(restid+ " is not present");
		}
		else {

			Restaurant restaurant = restaurantRepository.findById(restid).get();
			List<Item> list = restaurant.getItem();
			if(list.isEmpty()){
				List<Item> newList = new ArrayList<>();
				newList.add(item);
				restaurant.setItem(newList);
				restaurantRepository.save(restaurant);
				return item;
				}
			else {
				list.add(item);
				restaurantRepository.save(restaurant);
				return item;
			}
		}
	}

	@Override
	public List<Item> deleteItemById(Integer itemid,Integer restid) {
		// TODO Auto-generated method stub
		System.out.println(itemid);
		
		itemRepository.deleteById(itemid);
		
				
		return itemRepository.getItemByRestId(restid);
	}

	@Override
	public Item getItemById(Integer itemid) {
		// TODO Auto-generated method stub
		return itemRepository.findById(itemid).get();
	}
	

}
