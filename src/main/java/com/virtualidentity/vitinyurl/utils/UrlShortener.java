package com.virtualidentity.vitinyurl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UrlShortener {

	private final MessageDigest md;
	private final Map<String, String> urlMap;

	public UrlShortener() throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance(VITinyURLConsants.ALGORITHM);
		this.urlMap = new HashMap<>();
	}

	public String shortenUrl(String originalUrl) {
		String shortUrl = urlMap.get(originalUrl);

		if (shortUrl == null) {
			byte[] hash = md.digest(originalUrl.getBytes());
			byte[] bytes = Base64.getUrlEncoder().withoutPadding().encode(hash);

			shortUrl = new String(bytes).substring(0, VITinyURLConsants.SHORT_URL_LENGTH);
			urlMap.put(originalUrl, shortUrl);
		}

		return shortUrl;
	}
}
