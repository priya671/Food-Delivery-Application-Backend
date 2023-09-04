package com.edu.service;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.dao.Item;
import com.edu.dao.Restaurant;
import com.edu.error.GlobalException;
import com.edu.repository.ItemRepository;
import com.edu.repository.OrderREpository;
import com.edu.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	
	@Autowired
	private OrderREpository orderRepo;
	
	public static List<Item> newList = new ArrayList<>();
	@Override
	public Restaurant saveRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return restaurantRepository.save(restaurant);
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> deleteRestaurantById(Integer restid) throws GlobalException {
		// TODO Auto-generated method stub
		Optional<Restaurant> rob = restaurantRepository.findById(restid);
		if(!rob.isPresent()) {
			throw new GlobalException("Restaurant Address id=" + restid + " does not exist");
		}
	
		orderRepo.deleteOrderByRestId(restid);
		 restaurantRepository.deleteById(restid);
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant updateRestaurantById(Integer restid, Restaurant restaurantaddress) throws GlobalException {
		// TODO Auto-generated method stub
		Restaurant restaurant = null;
		Optional<Restaurant> rob = restaurantRepository.findById(restid);
		if(!rob.isPresent()) {
			throw new GlobalException("Restaurant Address id=" + restid + " does not exist");
		}
		else {
			restaurant = restaurantRepository.findById(restid).get();
			restaurant.setArea(restaurantaddress.getArea());
			restaurant.setCity(restaurantaddress.getCity());
			restaurant.setCountry(restaurantaddress.getCountry());
			restaurant.setPincode(restaurantaddress.getPincode());
			restaurant.setState(restaurantaddress.getState());
			restaurant.setPic(restaurantaddress.getPic());
			restaurant.setRestname(restaurantaddress.getRestname());
			restaurant.setManagerName(restaurantaddress.getManagerName());
			restaurant.setContactNumber(restaurantaddress.getContactNumber());
			restaurant.setEmail(restaurantaddress.getEmail());
			restaurant.setPassword(restaurantaddress.getPassword());	
			restaurant.setStatus(restaurantaddress.isStatus());
		}
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant saveItemByRestIdi(Item item, Integer restid) throws GlobalException{
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
				newList.add(itemRepository.save(item));
				int id=item.getItemid();
				
				Item eob=itemRepository.findById(id).get();
				Restaurant dob= restaurantRepository.findById(restid).get();
				eob.itemAssignedRestaurantAddress(dob);
				
				itemRepository.save(eob);
				
				return restaurant;
				}
			else {
				list.add(itemRepository.save(item));
				System.out.println(list);

				int id=item.getItemid();
				
				Item eob=itemRepository.findById(id).get();
				Restaurant dob= restaurantRepository.findById(restid).get();
				eob.itemAssignedRestaurantAddress(dob);
				
				itemRepository.save(eob);
				return restaurant;
			}
		}
	}
	
	
	@Override
	public List<Restaurant> getAllRestaurantsearch(String searchkey){
		
		if(searchkey.equals("")) {
			List<Restaurant> res = restaurantRepository.findAll();
			
			  if(res!=null) 
			  { res= restaurantRepository.searchByStatus(res); } else 
			  {
			  res=null; }
			 
			return res;
		}
		else {
		List<Restaurant>ress= restaurantRepository.findByRestnameContainingIgnoreCase(searchkey);
		System.out.println("ress="+ress);
		return ress;
		}
		
		
	}

	@Override
	public Restaurant getRestaurantById(Integer restid) {
		// TODO Auto-generated method stub
		return restaurantRepository.findById(restid).get();
	}
	
	@Override
	public Restaurant getRestaurantByEmail(String email, String password) {
		// TODO Auto-generated method stub
		 return restaurantRepository.getRestaurantByEmail(email,password);
	}

	@Override
	public Integer getRestaurantByEmail(String email) {
		// TODO Auto-generated method stub
		
		return restaurantRepository.getRestaurantByEmail(email);
	}

	@Override
	public List<Item> viewOrdersByRestauranat(Integer id) {
		// TODO Auto-generated method stub
//		List<FoodCart> AllCart =cartRepo.findCartByStatusPaid();
//		for(int i=0;i<AllCart.size();i++) {
//			FoodCart cart = AllCart.get(i);
//			List<Item> itemListInCart = cart.getItemList();
////			for(int j=0;j<itemListInCart.size();j++) {
//				Item item1 = itemListInCart.get(i);
//				Restaurant res = item1.getRest();
//				if(id == res.getRestid()) {
//				Item item = itemListInCart.get(i);
//				newList.add(item1);
//				return newList;
//				}
////			}
//		}
		return null;
			

	}
}
