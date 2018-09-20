package com.neperiagroup.spring.controller;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neperiagroup.spring.constants.UserURIConstants;
import com.neperiagroup.spring.model.User;
import com.neperiagroup.spring.services.UserService;


@RestController
@RequestMapping(value= UserURIConstants.CTRLUSER)
public class UserController {

	private static final Logger logger = LoggerFactory.logger(UserController.class);

	@Autowired 
	public UserService userService;

	@GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getJSON() {
		System.out.println("/get/json");
		List<String> list = new ArrayList<>();
		list.add("One");
		list.add("Two");
		list.add("Three");
		System.out.println("list: "+list);
		logger.info("Get_Mapping");
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity <List<String>> (list, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = UserURIConstants.GET_ALL_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<User>> getListAllUser() {
		System.out.println("GET_ALL_USERS: /users");
		List<User> listUsers = userService.listAllUser();
		logger.info("Mapping: GET_ALL_USER");
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity <List<User>> (listUsers, responseHeaders, HttpStatus.OK);
	}
	
	@PostMapping(value = UserURIConstants.ADDPOST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <User> saveUser(@RequestBody User user) {
		System.out.println("ADDPOST: /add/users");
		System.out.println("user: "+user);
		System.out.println("ID: "+user.getId());
		if (user.getId() == 0) {
			userService.addUser(user);
		} else {
			userService.updateUser(user);
		}
		logger.info("Mapping: ADDPOST");
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity <User> (user, responseHeaders, HttpStatus.CREATED);
	
	}
}
