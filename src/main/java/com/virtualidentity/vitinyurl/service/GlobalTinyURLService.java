package com.virtualidentity.vitinyurl.service;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;

public interface GlobalTinyURLService {
	GlobalTinyURL getOriginalUrl(String shortenedUrl);
	GlobalTinyURL getGlobalTinyURL(String originalUrl,boolean isCreateRequest);
	GlobalTinyURL createGlobalTinyURL(TinyURLDTO tinyURLDTO);
}
