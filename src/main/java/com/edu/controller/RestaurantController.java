package com.edu.controller;

import java.util.List;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.dao.Item;
import com.edu.dao.Restaurant;
import com.edu.error.GlobalException;
import com.edu.repository.RestaurantRepository;
import com.edu.service.RestaurantService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	

	
	@PostMapping("/saveRestaurant")
	public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant){
		Restaurant cust = restaurantService.saveRestaurant(restaurant);
		return new ResponseEntity<Restaurant>(cust, HttpStatus.CREATED);
	}
	
	//http://localhost:9999/getAllRestaurantAddress
	@GetMapping("/getAllRestaurant")
	List<Restaurant> getAllRestaurant(){
		return restaurantService.getAllRestaurant();
	}
	
	//http://localhost:8990/deleteRestaurantById/{addid}
	@DeleteMapping("/deleteRestaurantById/{restid}")
	List<Restaurant> deleteRestaurantById(@PathVariable("restid") Integer restid) throws GlobalException {
		return restaurantService.deleteRestaurantById(restid);
		//return "Record Deleted";
	}
	
	//http://localhost:8990/updateRestaurantAddressById/{addid}
	@PutMapping("/updateRestaurantById/{restid}")
	Object updateRestaurantById(@PathVariable("restid") Integer restid, @RequestBody Restaurant restaurant) throws GlobalException {
		return restaurantService.updateRestaurantById(restid, restaurant);
	}
	

	@GetMapping("/getRestaurantById/{restid}")
	public Restaurant getRestaurantById(@PathVariable("restid") Integer restid) {
		return restaurantService.getRestaurantById(restid);
	}

	
	@PostMapping("/saveItemByRestIdi/{restid}")
	public  Restaurant saveItemByRestIdi(@Valid @RequestBody Item item,	 @PathVariable("restid") Integer restid) throws GlobalException {
		 restaurantService.saveItemByRestIdi(item,restid);
		 return restaurantRepository.findById(restid).get();
	}
	

	
	@GetMapping("/getRestaurantByEmail/{email}/{password}")
	public Restaurant getRestaurantByEmail(@PathVariable("email") String email,@PathVariable("password") String password) throws GlobalException {
		return restaurantService.getRestaurantByEmail(email,password);
	}
	
	//search
	@GetMapping("/getAllRestaurantsearch")
	public List<Restaurant> getAllRestaurantsearch(@RequestParam(defaultValue = "") String searchkey){
		List<Restaurant> result = restaurantService.getAllRestaurantsearch(searchkey);
		//System.out.println("Result size is: " + result.size());
		return result;
	}
	
	@GetMapping("/getRestaurantByEmail/{email}")
	public Integer getRestaurantByEmail(@PathVariable("email") String Email) {
		return restaurantService.getRestaurantByEmail( Email);
	}
	
	@GetMapping("/viewOrdersByRestaurant/{id}")
	public List<Item> viewOrdersByRestauranat(@PathVariable("id") Integer id) {
		return restaurantService.viewOrdersByRestauranat(id);
	}
	
}
