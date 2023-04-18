package com.virtualidentity.vitinyurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualidentity.vitinyurl.entity.TinyURL;

@Repository
public interface TinyURLRepository extends JpaRepository<TinyURL, Long> {
	TinyURL findByOriginalUrl(String originalUrl);
}
