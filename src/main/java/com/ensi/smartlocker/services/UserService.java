package com.ensi.smartlocker.services;

import com.ensi.smartlocker.domain.User;

public interface UserService {
	
	Iterable<User> getAllUsers();

	User getUserById(String email);

	User saveUser(User user);
	
	void deleteUserById(String email);
	
	User getRealUserById(String email);


}
