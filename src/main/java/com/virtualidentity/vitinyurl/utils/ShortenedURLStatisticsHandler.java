package com.virtualidentity.vitinyurl.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.repository.GlobalTinyURLRepository;


public class ShortenedURLStatisticsHandler {
	@Autowired
	private GlobalTinyURLRepository globalTinyURLRepository;

	private ExecutorService executor;

	public ShortenedURLStatisticsHandler() {
		this.executor = Executors.newFixedThreadPool(3);
	}

	public void incrementShortenedCount(GlobalTinyURL globalTinyURL) {
		executor.execute(() -> {
			globalTinyURL.incrementShortenedCount();
			globalTinyURLRepository.save(globalTinyURL);
		});
	}

	public void incrementAccessCount(GlobalTinyURL globalTinyURL) {
		executor.execute(() -> {
			globalTinyURL.incrementAccessCount();
			globalTinyURLRepository.save(globalTinyURL);
		});
	}
}
