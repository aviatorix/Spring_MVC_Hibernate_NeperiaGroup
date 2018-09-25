package com.neperiagroup.spring.services;

import java.util.List;

import com.neperiagroup.spring.model.User;

public interface UserService {

	public List <User> listAllUser();

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(Integer userId);

	public String findUserById(Integer userId);
	
	public String findIdByEmail(User user);


}
