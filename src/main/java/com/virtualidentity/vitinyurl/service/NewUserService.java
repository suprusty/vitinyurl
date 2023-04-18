package com.virtualidentity.vitinyurl.service;

import com.virtualidentity.vitinyurl.entity.NewUser;

public interface NewUserService {
	NewUser findByEmail(String email);
	String createNewUser(NewUser user);
}
