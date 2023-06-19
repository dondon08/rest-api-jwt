package com.test.demotest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.demotest.entity.User;

public interface UserService {
	
	public boolean validateUser(String username, String password);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	User findByUsername(String username);

}
