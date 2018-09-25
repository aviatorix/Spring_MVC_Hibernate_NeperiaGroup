package com.neperiagroup.spring.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="user")

@NamedQueries({
	//@NamedQuery(name="GetDetailsByName" , query="SELECT * FROM user WHERE name=:name"),
	@NamedQuery(name="FindUserByEmail", query="select em from User em where em.email = :email")

})

// http://www.tutorialspoint.com/jpa/
public class User {

	@Id
	private int id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String regDate;

	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="surname")
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="regDate")
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.regDate = format.format(new Date());
	}
}
