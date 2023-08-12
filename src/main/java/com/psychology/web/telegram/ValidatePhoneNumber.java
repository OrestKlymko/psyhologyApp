package com.psychology.web.telegram;

public class ValidatePhoneNumber {

	public static boolean isValid(String phoneNumber){
		String regex = "\\+380\\d{9}";
		return phoneNumber.matches(regex);
	}
}
