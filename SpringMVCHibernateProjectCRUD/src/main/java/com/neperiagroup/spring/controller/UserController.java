package com.neperiagroup.spring.controller;


import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.neperiagroup.spring.constants.UserURIConstants;
import com.neperiagroup.spring.model.User;
import com.neperiagroup.spring.services.UserService;

@RestController
@RequestMapping(value= UserURIConstants.CTRLUSER)
public class UserController {

	private static final Logger logger = LoggerFactory.logger(UserController.class);

	@Autowired 
	public UserService userService;

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
		userService.addUser(user);
		logger.info("Mapping: ADDPOST");
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity <User> (user, responseHeaders, HttpStatus.CREATED);
	
	}
	
	@DeleteMapping(value = UserURIConstants.DELUS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Object> deleteUser(@PathVariable int userId) {
		System.out.println("DELUS: /del/user/{userId}");
		System.out.println("userId: "+userId);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
		userService.deleteUser(userId);
		logger.info("Mapping: DELUS");
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity <Object> (response.put("id", userId), responseHeaders, HttpStatus.OK); 
	}
	
	
	@GetMapping(value = UserURIConstants.FINDUSID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Object> findUserById(@PathVariable int userId) {
		System.out.println("FINDUSID: /find/user/{userId}");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode response = mapper.createObjectNode();
		logger.info("Mapping: FINDUSID");
		HttpHeaders responseHeaders = new HttpHeaders();
		String name = userService.findUserById(userId);
		return new ResponseEntity <Object> (response.put("name_user", name), responseHeaders, HttpStatus.OK);
	}
	
	@PostMapping(value = UserURIConstants.FINDUSIDEMAIL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Object> findIdByEmail(@RequestBody User user) {
		System.out.println("FINDUSEMAIL: /find/email");
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode response = mapper.createObjectNode();
		logger.info("Mapping: FINDUSEMAIL");
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity <Object> (response.put("Id", userService.findIdByEmail(user)), responseHeaders, HttpStatus.OK);
	}

}
