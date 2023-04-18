package com.virtualidentity.vitinyurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;

@Repository
public interface GlobalTinyURLRepository extends JpaRepository<GlobalTinyURL, Long> {
	GlobalTinyURL findByShortUrl(String shortUrl);
	GlobalTinyURL findByOriginalUrl(String originalUrl);
}
