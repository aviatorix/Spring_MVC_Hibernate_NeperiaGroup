package com.neperiagroup.spring.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neperiagroup.spring.dao.UserDao;
import com.neperiagroup.spring.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> listAllUser() {
		
		return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
	}

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}

	@Override
	public void deleteUser(Integer userId) {
		 User user = (User) sessionFactory.getCurrentSession()
				.load(User.class, userId);
		 if (user != null) {
			 this.sessionFactory.getCurrentSession().delete(user);
		 }
	}

	@Override
	public User findUserById(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}

}
