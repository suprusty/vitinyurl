package com.virtualidentity.vitinyurl.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.virtualidentity.vitinyurl.entity.TinyURL;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;

@Service
public class TinyURLServiceImpl implements TinyURLService {
	@Override
	public TinyURL createNewTinyURL(TinyURLDTO tinyURLDTO, String shortenedUrl) {
		TinyURL newTinyurl = new TinyURL();
		newTinyurl.setOriginalUrl(tinyURLDTO.getOriginalUrl());
		newTinyurl.setShortUrl(shortenedUrl);
		newTinyurl.setCreatedTime(new Date());
		newTinyurl.setLastModifiedTime(new Date());
		return newTinyurl;
	}
}
