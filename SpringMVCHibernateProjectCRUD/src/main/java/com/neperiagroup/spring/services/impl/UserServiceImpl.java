package com.neperiagroup.spring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neperiagroup.spring.dao.UserDao;
import com.neperiagroup.spring.model.User;
import com.neperiagroup.spring.services.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public List<User> listAllUser() {
		return userDao.listAllUser();
	}

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	

	@Override
	@Transactional
	public User findUserById(User user) {
		return userDao.findUserById(user);
	}

	@Override
	@Transactional
	public void deleteUser(Integer userId) {
		userDao.deleteUser(userId);
	}
}
