package com.virtualidentity.vitinyurl.service;

import com.virtualidentity.vitinyurl.entity.TinyURL;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;

public interface TinyURLService {
	TinyURL createNewTinyURL(TinyURLDTO tinyURLDTO, String shortenedUrl);
}
