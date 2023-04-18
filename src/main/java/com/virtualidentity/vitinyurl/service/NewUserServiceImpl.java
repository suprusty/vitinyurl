package com.virtualidentity.vitinyurl.service;

import org.springframework.stereotype.Service;

import com.virtualidentity.vitinyurl.entity.NewUser;
import com.virtualidentity.vitinyurl.exception.UserFoundException;
import com.virtualidentity.vitinyurl.repository.NewUserRepository;

@Service
public class NewUserServiceImpl implements NewUserService {

	private NewUserRepository userRepository;

	NewUserServiceImpl(NewUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String createNewUser(NewUser user) {
		NewUser existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			throw new UserFoundException("The User address has already been taken.");
		}
		user.setPassword(user.getPassword());
		NewUser dbUser = userRepository.save(user);
		String dummayJWTToken = dbUser.getUsername() +"+"+ dbUser.getEmail();
		return dummayJWTToken;
	}

	@Override
	public NewUser findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
