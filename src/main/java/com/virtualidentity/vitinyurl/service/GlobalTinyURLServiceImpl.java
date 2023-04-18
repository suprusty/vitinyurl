package com.virtualidentity.vitinyurl.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.exception.TinyURLNotFoundException;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;
import com.virtualidentity.vitinyurl.repository.GlobalTinyURLRepository;
import com.virtualidentity.vitinyurl.utils.ShortenedURLStatisticsHandler;
import com.virtualidentity.vitinyurl.utils.UrlShortener;

@Service
public class GlobalTinyURLServiceImpl implements GlobalTinyURLService {
	
	private final GlobalTinyURLRepository globalTinyURLRepository;

	@Autowired
	ShortenedURLStatisticsHandler ShortenedURLStatisticsHandler;

	@Autowired
	UrlShortener urlShortener;

	GlobalTinyURLServiceImpl(GlobalTinyURLRepository globalTinyURLRepository) {
		this.globalTinyURLRepository = globalTinyURLRepository;
	}

	@Override
	public GlobalTinyURL getOriginalUrl(String shortenedUrl) {
		GlobalTinyURL globalTinyURL = globalTinyURLRepository.findByShortUrl(shortenedUrl);
		if (globalTinyURL == null) {
			throw new TinyURLNotFoundException("Invalid shortenedUrl, Please proive the valid shortenedUrl.");
		}
		ShortenedURLStatisticsHandler.incrementAccessCount(globalTinyURL);
		return globalTinyURL;
	}

	@Override
	public GlobalTinyURL getGlobalTinyURL(String originalUrl,boolean isCreateRequest) {
		GlobalTinyURL globalTinyURL = globalTinyURLRepository.findByOriginalUrl(originalUrl);
		if (globalTinyURL != null && isCreateRequest) {
			globalTinyURL.setLastModifiedTime(new Date());
			ShortenedURLStatisticsHandler.incrementShortenedCount(globalTinyURL);
		}
		return globalTinyURL;
	}

	@Override
	public GlobalTinyURL createGlobalTinyURL(TinyURLDTO tinyURLDTO) {
		String shortenedUrl = urlShortener.shortenUrl(tinyURLDTO.getOriginalUrl());
		GlobalTinyURL newTinyurl = new GlobalTinyURL();
		newTinyurl.setOriginalUrl(tinyURLDTO.getOriginalUrl());
		Date date = new Date();
		newTinyurl.setCreatedTime(date);
		newTinyurl.setLastModifiedTime(date);
		newTinyurl.setShortUrl(shortenedUrl);
		newTinyurl.incrementShortenedCount();
		newTinyurl = globalTinyURLRepository.save(newTinyurl);
		return newTinyurl;
	}
}
