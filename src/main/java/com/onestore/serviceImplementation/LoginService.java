package com.onestore.serviceImplementation;

import com.onestore.exception.LoginException;
import com.onestore.model.User;

public interface LoginService {
	
	public String LoginYourAccount(User user) throws LoginException;
	public String LogoutYourAccount(String key) throws LoginException;

}
