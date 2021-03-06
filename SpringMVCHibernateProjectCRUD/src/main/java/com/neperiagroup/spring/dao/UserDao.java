package com.neperiagroup.spring.dao;

import java.util.List;

import com.neperiagroup.spring.model.User;

public interface UserDao {
	
	public List <User> listAllUser(); 

	public void addUser(User user);

	public User updateUser(User user);

	public String findUserById(Integer userId);

	public void deleteUser(Integer userId);

	public String findIdByEmail(User user);
	
}
