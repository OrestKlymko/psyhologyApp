package com.psychology.web.customer.checker;

import com.psychology.web.customer.entity.CustomerEntity;

import java.util.regex.Pattern;


public class CustomerIsValid {
	public static boolean check(CustomerEntity customer){
		String email = customer.getEmail();
		String password = customer.getPassword();
		String  regexPatternEmail = "^(.+)@(\\S+)$";
		String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{4,12}$";
		boolean validEmail = patternMatches(email, regexPatternEmail);
		boolean validPassword = patternMatches(password,regexPassword);
		return validPassword&&validEmail;
	}

	private static boolean patternMatches(String check, String regexPattern) {
		return Pattern.compile(regexPattern)
				.matcher(check)
				.matches();
	}
}
