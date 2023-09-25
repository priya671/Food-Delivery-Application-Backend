package com.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.dao.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	@Query(value = "select * from restaurant where restname like %?% and status=true" ,nativeQuery = true)
	public List<Restaurant> findByRestnameContainingIgnoreCase(String key1);

	public List<Restaurant> findAll();
	
	@Query(value="select * from restaurant where restname=? and password=?",nativeQuery = true)
	public List<Restaurant> findByRestaurantnameAndRestaurantpassword(String uname, String pass);
	

	@Query(value = "select * from restaurant where email=? and password=?",nativeQuery = true)
	public Restaurant getRestaurantByEmail(String email,String password);

	
	@Query(value = "select * from restaurant where status=true ",nativeQuery = true)
	public List<Restaurant> searchByStatus(List<Restaurant> listrest);
	
	@Query(value = "select restid from restaurant where email=?",nativeQuery = true)
	public Integer getRestaurantByEmail(String email);
	
	@Query(value = "select * from restaurant where email=?",nativeQuery = true)
	public Restaurant getRestaurantByEmail2(String email);
	


}





