package com.neperiagroup.spring.dao.impl;


import java.util.List;

import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import com.neperiagroup.spring.authentication.CryptPassword;
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
		user.setRegDate();
		user.setPassword(CryptPassword.hashPassword(user.getPassword()));
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User updateUser(User user) {
		user.setRegDate();
		user.setPassword(CryptPassword.hashPassword(user.getPassword()));
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
	public String findUserById(Integer userId) {
		User user = (User) sessionFactory.getCurrentSession()
				.load(User.class, userId);
		if (user != null) {
			return user.getName();
		}
		return "user not found";
	}
	
	public String findIdByEmail(User user) {

		Query query = sessionFactory.getCurrentSession().createQuery("select id, name from User where email = :email").setEntity("email", user);
		System.out.println("userId: "+query.getReturnTypes());
		return query.getNamedParameters().toString();
	}
}
