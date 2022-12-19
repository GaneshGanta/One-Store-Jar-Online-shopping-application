package com.onestore.serviceImplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onestore.exception.LoginException;
import com.onestore.model.CurrentUserSession;
import com.onestore.model.Customer;
import com.onestore.model.User;
import com.onestore.repository.CustomerDao;
import com.onestore.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private CustomerDao custD;
	
	@Autowired
	private UserSessionDao sessionD;

	@Override
	public String LoginYourAccount(User user) throws LoginException{
		
		
		if(user.getRole().equals("admin")) {
			
			String key = RandomString.make(6);
	        Random rand = new Random();

	        int rand_int1 = rand.nextInt(100);

			
			CurrentUserSession userSession = new CurrentUserSession(rand_int1,key,LocalDateTime.now(),user.getEmail(),user.getPassword(),user.getRole());
			sessionD.save(userSession);
			return userSession.toString();
			
			
			
		}
		
		
	
		Customer existingCust = custD.findByEmail(user.getEmail());
		
		if( existingCust == null ) {
			
			throw new LoginException("Please Enter Valid Email");
		}
		
		
		Optional<CurrentUserSession> checkLogin = sessionD.findById(existingCust.getCustomerId());
		
		if( checkLogin.isPresent()) {
			throw new LoginException("Very Bad You already Login Man");
		}
		
		if( existingCust.getPassword().equals(user.getPassword())) {
			
			String key = RandomString.make(6);
			
			CurrentUserSession userSession = new CurrentUserSession(existingCust.getCustomerId(),key,LocalDateTime.now(),user.getEmail(),user.getPassword(),user.getRole());
			sessionD.save(userSession);
			return userSession.toString();
			
		}
		else 
			throw new LoginException("Please Enter Valid Password");
	}

	
	@Override
	public String LogoutYourAccount(String key) throws LoginException{
		
		
		CurrentUserSession validUserSession =  sessionD.findByUuid(key);
		
		if( validUserSession == null ) {
			
			throw new LoginException("User Not Logged in with this Email Id");
		}
		
		sessionD.delete(validUserSession);
		
		return "Logged Out!";
	}

}
