package com.neperiagroup.spring.authentication;

import org.mindrot.jbcrypt.BCrypt;

public class CryptPassword {
	
	public static String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}


	protected static String checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
			System.out.println("The password matches.");
			System.out.println(hashedPassword);
			return hashedPassword;
		} else {
			System.out.println("The password does not match.");
			return "The password does not match.";
		}
	}
	
}
