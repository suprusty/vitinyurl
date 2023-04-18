package com.virtualidentity.vitinyurl.service;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;

public interface UserService {
	GlobalTinyURL createShortenUrl(TinyURLDTO tinyURLDTO);
}
