package com.virtualidentity.vitinyurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.entity.NewUser;
import com.virtualidentity.vitinyurl.entity.TinyURL;
import com.virtualidentity.vitinyurl.entity.User;
import com.virtualidentity.vitinyurl.exception.JWTTokenNotFoundException;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;
import com.virtualidentity.vitinyurl.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Autowired
	NewUserService newUserService;
	@Autowired
	GlobalTinyURLService globalTinyURLService;
	@Autowired
	TinyURLService tinyURLService;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public GlobalTinyURL createShortenUrl(TinyURLDTO tinyURLDTO) {
		GlobalTinyURL globalTinyURL = globalTinyURLService.getGlobalTinyURL(tinyURLDTO.getOriginalUrl(),true);
		if (globalTinyURL != null && isEmptyString(tinyURLDTO.getJwtToken())) {
			//If the original URL is present in the "GlobalTinyURL" database table and the JWT token is empty, the API returns the appropriate short URL.
			return globalTinyURL;
		} else if (globalTinyURL == null && isEmptyString(tinyURLDTO.getJwtToken())) {
			//The API creates new data in "GlobalTinyURL" and returns the matching short URL if the original URL doesn't exist in the database table "GlobalTinyURL" and the JWT token is empty.
			globalTinyURL = createNewGlobalTinyURL(tinyURLDTO);
			return globalTinyURL;
		} else if (!isEmptyString(tinyURLDTO.getJwtToken())) {

			String jwtToken = tinyURLDTO.getJwtToken();
			String[] parts = jwtToken.split("\\+");
			if (parts == null || parts.length!=2) {
				throw new JWTTokenNotFoundException("Invalid Token, Please proive the valid token ID.");
			}
			String username = parts[0];
			String email = parts[1];
			
			NewUser newUser = newUserService.findByEmail(email);

			if (newUser == null) {
				throw new JWTTokenNotFoundException("Invalid Token, Please proive the valid token ID.");
			}
			
			if (globalTinyURL == null) {
				globalTinyURL = createNewGlobalTinyURL(tinyURLDTO);
			}
			
			User user = userRepository.findByEmail(email);
			if (user == null) {
				user = new User();
				user.setEmail(email);
				user.setUserName(username);
			}
			
			TinyURL tinyURL = tinyURLService.createNewTinyURL(tinyURLDTO, globalTinyURL.getShortUrl());
			user.addTinyURL(tinyURL);
			userRepository.save(user);
			return globalTinyURL;

		}

		return globalTinyURL;
	}

	private boolean isEmptyString(String string) {
		return string == null || string.isBlank();
	}

	private GlobalTinyURL createNewGlobalTinyURL(TinyURLDTO tinyURLDTO) {
		return globalTinyURLService.createGlobalTinyURL(tinyURLDTO);
	}

	

}
